package com.hotcomm.community.parse.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotcomm.community.common.bean.db.parse.AlarmGz;
import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmGz;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.common.bean.db.parse.SelectCommunityDM;
import com.hotcomm.community.parse.analy.KrqAnalyParse;
import com.hotcomm.community.parse.analy.SyAnalyParse;
import com.hotcomm.community.parse.analy.SydlAnalyParse;
import com.hotcomm.community.parse.analy.YgAnalyParse;
import com.hotcomm.community.parse.utils.Injection;

/**
 * 线程池任务类
 * 
 * @author Lktao
 *
 */
public class MyTask implements Runnable {

	private YgAnalyParse ygParse;

	private KrqAnalyParse krqParse;

	private SydlAnalyParse sydlAnalyParse;

	private SyAnalyParse syParse;

	private Logger logger;

	private int moduleid;

	private ReceiveModel model;

	public MyTask(int moduleid, ReceiveModel model) {
		this.moduleid = moduleid;
		this.model = model;
		this.ygParse = new YgAnalyParse();
		this.krqParse = new KrqAnalyParse();
		this.sydlAnalyParse = new SydlAnalyParse();
		this.syParse = new SyAnalyParse();
		this.logger = LoggerFactory.getLogger(MyTask.class);
	}

	@Override
	public void run() {
		try {
			// 发送到业务层的数据,先查出设备信息
			DeviceSendMsg deviceSendMsg = Injection.injection.parseService.selectDeviceId(model.getMacAddr());
			// 查不到设备就不往下执行
			if (deviceSendMsg != null) {
				String macToTow = model.getData().substring(0, 2);
				ReceiveModel alarmMsg = new ReceiveModel();
				switch (moduleid) {
				case 1:// 烟感
					if (macToTow.equalsIgnoreCase("b1") || macToTow.equalsIgnoreCase("b2")
							|| macToTow.equalsIgnoreCase("b3")) {
						// 拓宝
						alarmMsg = ygParse.yg_AnalysisFun_TuoBao(model);
					} else if (macToTow.equals("30")) {
						// 泛海三江
						alarmMsg = ygParse.yg_Analysis_FunFanHaiSJ(model);
					} else if (model.getData().substring(2, 4).equals("22")) {
						// Hotcomm
						alarmMsg = ygParse.yg_AnalysisFun_Hotcomm(model);
					}
					break;
				case 2:// 可燃气
					if (model.getData().substring(2, 4).equals("29")) {
						// Hotcomm
						alarmMsg = krqParse.krq_AnalysisFunHotcomm(model);
					} else if (macToTow.equalsIgnoreCase("b1") || macToTow.equalsIgnoreCase("b2")
							|| macToTow.equalsIgnoreCase("b3")) {
						alarmMsg = krqParse.krq_AnalysisFun_TuoBao(model);
					}
					break;
				case 3:// 水压
					if (model.getData().substring(2, 4).equals("01")) {
						// 拓普索尔
						alarmMsg = syParse.sy_AnalysisFun_TuoPuSuoEr(model);
					} else if (model.getData().substring(2, 4).equals("03")) {
						// 消安
						alarmMsg = syParse.sy_AnalysisFun_XiaoAn(model);
					}
					break;
				case 4:// 剩余电流
					alarmMsg = sydlAnalyParse.sydl_AnalysisFun_XiaoAn(model);
					break;
				default:
					break;
				}

				Map<String, Object> alarmMap = alarmMsg.getAnalyMsg();
				// 是否报警
				Integer is_alarm = 0;
				// 数据名称，心跳或者什么报警
				String alarmName = "";
				SelectCommunityDM selectCommunityDM = Injection.injection.parseService
						.selectCommunity(model.getMacAddr());
				if (selectCommunityDM == null)// 该条数据的设备不在数据库，不做处理
					return;

				Integer community = selectCommunityDM.getCId();
				Integer alarmid = 0;
				if (moduleid != 0) {// 过滤非法模块
					Double batteryDouble = null;

					if (alarmMap.containsKey("battery"))
						batteryDouble = Double.parseDouble((String) alarmMap.get("battery"));

					// 更新数据信息
					Map<String, Object> code = Injection.injection.parseService.updateDeviceTime(model.getMacAddr(),
							batteryDouble, community);

					// 如果修改影响行数为0则说明该设备不存在
					if (code == null || (int) code.get("code") <= 0)
						return;

					if (alarmMap.get("alarmid").equals(0)) {
						// 心跳数据
						alarmName = "心跳";
						logger.info("info:{}", "设备mac:" + alarmMsg.getMacAddr() + "，心跳数据");
					} else {// 设备报警数据

						alarmName = DeviceAlarmGz.getById((int) alarmMap.get("alarmid")).getName();
						if (community == null)// 改设备不在设备表中
							return;

						// 写入报警类数据
						AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
						alarmInsertPA.setCommunityId(community);
						alarmInsertPA.setSource(1);
						alarmInsertPA.setAlarmType(1);
						alarmInsertPA.setAlarmSourceType((int) alarmMap.get("alarmid"));
						alarmInsertPA.setAlarmSource(model.getMacAddr());
						alarmInsertPA.setLib(code);
						alarmInsertPA.setModuleId(moduleid);
						alarmInsertPA.setAlarmSourceId(deviceSendMsg.getId());

						if (!alarmName.equals("") || !alarmName.equals(null)) {// 该报警类型存在规则
							// 查询出设备报警对应规则
							AlarmGz alarmGz = Injection.injection.parseService.selectAlarmGz(model.getMacAddr(),
									alarmName);
							if (alarmGz != null && alarmGz.getIsOpen() == 0)// 如果规则未开启，直接返回
								return;

							switch (moduleid) {
							case 1:// 烟感
							case 2:// 可燃气
									// 该类设备没有阈值，直接报警
								is_alarm = 1;
								alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								break;
							case 3:// 水压
									// 该类设备有阈值，需要比较阈值大小
								if (!alarmMap.containsKey("sy"))
									return;
								if ((int) alarmMap.get("alarmid") == 9 || (int) alarmMap.get("alarmid") == 10) {
									// 防偷，被盗报警, 直接报警
									is_alarm = 1;
									alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								}
								Double syValue = (Double) alarmMap.get("sy");
								alarmInsertPA.setAlarmValue(syValue + "");
								Map<String, Integer> sy = alarmGz.getAlarmVauleMap();
								if (syValue != null && syValue > sy.get("symax")) {// 水压超过预设最大阈值
									is_alarm = 1;
									alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								} else if (syValue < sy.get("symin")) {// 水压低于预设最小阈值
									is_alarm = 1;
									alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								}
								break;
							case 4:// 剩余电流
									// 该类设备有阈值，需要比较阈值大小 阈值判断是否报警
								if (!alarmMap.containsKey("sydl"))// 电流为空就return
									return;
								Double sydl = (Double) alarmMap.get("sydl");
								alarmInsertPA.setAlarmValue(sydl + "");
								Map<String, Integer> sxdl = alarmGz.getAlarmVauleMap();
								if (sydl > sxdl.get("dlmax")) {// 电流超过预设最大阈值，就报警
									is_alarm = 1;
									alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								} else if (sydl < sxdl.get("dlmin")) {// 电流低于预设最小阈值，就报警
									is_alarm = 1;
									alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
								}
								break;
							default:
								break;
							}
						} else {
							// 该报警类型不存在规则,直接报警
							is_alarm = 1;
							alarmName = "异常";
							alarmid = Injection.injection.alarmService.insertAlarmToMainDevice(alarmInsertPA);
						}
					}
					// 发送到业务层的数据
					deviceSendMsg.setMac(alarmMsg.getMacAddr());
					deviceSendMsg.setIs_alarm(is_alarm);
					deviceSendMsg.setAlarmid(alarmid);
					deviceSendMsg.setCommunityId(community);
					deviceSendMsg.setTableParams(code);
					deviceSendMsg.setModuleId(moduleid);
					deviceSendMsg.setAlarmName(alarmName);
					Injection.injection.deviceService.DeviceSendMsg(deviceSendMsg);
				}
			}
		} catch (Exception e) {
			logger.error("error:{}", e);
			return;
		}
	}
}