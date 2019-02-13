package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alibaba.druid.sql.visitor.functions.If;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.car.mapper.CarPassRecordsMapper;
import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.db.car.CarPassRecordsDM;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPA;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPagePA;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.car.CarAttentionCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassHoursCountUM;
import com.hotcomm.community.common.bean.ui.car.CarMonParkCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarMonParkCountUM;
import com.hotcomm.community.common.bean.ui.car.CarMonTimeCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarMonTimeCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassHourCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarPosturePakingYearUM;
import com.hotcomm.community.common.bean.ui.car.CarPostureParkingCountUM;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.bean.ui.car.CarmMonEnterCountUM;
import com.hotcomm.community.common.bean.ui.device.DeviceMacDataUM;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarAlarmRuleService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.framework.comm.FileUploadResponse;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.utils.FileUploadHelper;
import com.hotcomm.framework.utils.UUIDUtils;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.utils.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 车辆通行记录
 * @author: lhx
 * @date: 2019-01-24 11:46
 **/
@Service
public class CarPassRecordsImpl extends BaseService implements CarPassRecordsService {

	@Autowired
	private CarPassRecordsMapper carPassRecordsMapper;

	@Autowired
	private CarLabelRelationService carLabelRelationService;

	@Autowired
	private CarRegService carRegService;

	@Autowired
	private CarStrService carStrService;

	@Autowired
	private CarAlarmRuleService carAlarmRuleService;

	@Autowired
	private CarAlarmRecordsService carAlarmRecordsService;

	@Autowired
	private ProscessService alarmService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private MsgServiceImpl msgServiceImpl;

	/**分页查询车辆通行记录*/
	@Override
	public PageInfo<CarPassRecordsUM> page(CarPassRecordsPagePA carPassRecordsPagePA) throws HKException {
		Map<String, Object> map = super.getTableParams(carPassRecordsPagePA.getCommunityId());
		PageHelper.startPage(carPassRecordsPagePA.getPageIndex(), carPassRecordsPagePA.getPageSize());
		map.put("carType", carPassRecordsPagePA.getCarType());
		map.put("labelId", carPassRecordsPagePA.getLabelId());
		map.put("adress", carPassRecordsPagePA.getAdress());
		map.put("num", carPassRecordsPagePA.getNum());
		map.put("endTime", carPassRecordsPagePA.getEndTime());
		map.put("startTime", carPassRecordsPagePA.getStartTime());
		map.put("passType", carPassRecordsPagePA.getPassType());
		map.put("color", carPassRecordsPagePA.getColor());
		map.put("brand", carPassRecordsPagePA.getBrand());
		map.put("modelType", carPassRecordsPagePA.getModelType());
		Page<CarPassRecordsUM> page = carPassRecordsMapper.pagelist(map);
		List<CarPassRecordsUM> carPassRecords = page;
		Map<String, Object> labelMap = new HashMap<String, Object>();
		carPassRecords.forEach(Item -> {
			labelMap.clear();
			labelMap.put("carNum", Item.getNum());
			labelMap.put("dynamicDbname", map.get("dynamic_dbname").toString());
			// 陌生车辆与标签一对多
			ArrayList<String > arrayList = carLabelRelationService.selectLabelByCarNum(labelMap);
			Item.setLabelNameList(arrayList);
		});
		return new PageInfo<>(page.getTotal(), carPassRecords, carPassRecordsPagePA.getPageSize(),carPassRecordsPagePA.getPageIndex());
	}

	/**新增车辆通行记录*/
	@Override
	@Transactional
	public Integer insert(CarPassRecordsPA carPassRecordsPA) throws HKException {
		try {
			//根据mac查询社区编号
			DeviceMacDataUM deviceMacDataUM =deviceService.deviceCommunityId(carPassRecordsPA.getVideoCode());
			Integer communityId = deviceMacDataUM.getCommunityId();
			carPassRecordsPA.setDynamicDbname(super.getTableParams(communityId).get("dynamic_dbname").toString());
			//根据mac查询设备信息
			SelectVideoDeviceOnMacUM deviceDetail =deviceService.selectVideoDeviceOnMac(carPassRecordsPA.getVideoCode(),communityId);
			Integer devLocation = deviceDetail.getDevLocation();
			//passType:0:驶入，1:驶出，2:经过,devLocation:0入口，1出口，2路边，3其他
			if(devLocation.equals(3)){
				carPassRecordsPA.setPassType(2);
			}else {
				carPassRecordsPA.setPassType(devLocation);
			}
			carPassRecordsPA.setAdress(deviceDetail.getCode());
			carPassRecordsPA.setCreateTime(carPassRecordsPA.getCreateTime());
			String carNum = carPassRecordsPA.getCarNum();
			FileUploadResponse FileUploadResponse = new FileUploadResponse();
			JSONObject jsonObject = new JSONObject();
			//封装消息推送的data
			JSONObject msgObject = new JSONObject();
			msgObject.put("adress", deviceDetail.getCode());
			msgObject.put("num",carNum);
			msgObject.put("createTime", carPassRecordsPA.getCreateTime());
			msgObject.put("lon", deviceDetail.getLon());
			msgObject.put("lat", deviceDetail.getLat());
			msgObject.put("deviceId", deviceDetail.getMac());

			if (carPassRecordsPA.getCarImgBase64()!=null) {
				//上传车辆抓拍图片
				FileUploadResponse = FileUploadHelper.upload(FileUploadHelper.base64ToMultipart(carPassRecordsPA.getCarImgBase64()), "car");
				jsonObject.put("carImg", FileUploadResponse.getBackPath());
				msgObject.put("carImgPath", FileUploadResponse.getBackPath());
			}else {
				msgObject.put("carImgPath", carPassRecordsPA.getImgPath());
				jsonObject.put("carImg", carPassRecordsPA.getImgPath());
			}
			carPassRecordsPA.setImgPath(jsonObject.toString());
			//查登记车辆
			CarRegDetailUM carRegDetailUM = carRegService.detail(communityId,null,carNum);

			ArrayList<Integer> arrayList = new  ArrayList<Integer>();
			if (carRegDetailUM != null) {
				carPassRecordsPA.setCarType(carRegDetailUM.getCarTypeCode());
				carPassRecordsPA.setFirstImgPath(carRegDetailUM.getFrontPhoto());
				msgObject.put("carType", carRegDetailUM.getCarType());
				if (carRegDetailUM.getLabelId()!=null) {
					arrayList.add(carRegDetailUM.getLabelId());
				}
			} else {
				//查陌生车辆列表
				CarStrPA carStrPA = new CarStrPA();
				carStrPA.setNum(carNum);
				carStrPA.setCommunityId(communityId);
				CarStrUM carStrUM = carStrService.detail(carStrPA);

				CarStrDM carStrDM = new CarStrDM();
				carStrDM.setNum(carNum);
				carStrDM.setCommunityId(communityId);
				carStrDM.setState(carPassRecordsPA.getPassType());

				msgObject.put("carType", "陌生车");

				if (carStrUM != null) {
					arrayList.addAll(carStrUM.getLabelIdList());
					carPassRecordsPA.setFirstImgPath(carStrUM.getCarImg());
					// 车辆入时出入次数加1
					if (carPassRecordsPA.getPassType() == 0) {
						carStrDM.setEnterCount(carStrUM.getEnterCount() + 1);
					}
					// 只有在车辆出、入时才改变车辆状态并更新车辆状态
					if (carPassRecordsPA.getPassType() != 2) {
						carStrDM.setId(carStrUM.getId());
						carStrDM.setUpdateTime(carPassRecordsPA.getCreateTime());
						carStrService.update(carStrDM);
					}
					carPassRecordsPA.setCarType(3);
				} else {
					carStrDM.setEnterCount(1);
					carPassRecordsPA.setCarType(3);
					carStrDM.setPhoto(jsonObject.toString());
					carStrDM.setCreateUser("车辆通行");
					carStrDM.setColor(carPassRecordsPA.getColor());
					carStrDM.setBrand(carPassRecordsPA.getBrand());
					carStrDM.setModelType(carPassRecordsPA.getModelType());
					carStrDM.setModel(carPassRecordsPA.getModel());
					//车辆经过与进入都算在小区内
					if (carPassRecordsPA.getPassType() != 1) {
						carStrDM.setState(0);
					}else {
						carStrDM.setState(1);
					}
					//非登記車輛第一次出現視為陌生車
					carStrService.insert(carStrDM);
				}
			}

			//新增通行记录
			CarPassRecordsDM carPassRecordsDM = new  CarPassRecordsDM();
			BeanUtils.copyProperties(carPassRecordsPA, carPassRecordsDM);
		    carPassRecordsMapper.insert(carPassRecordsDM);
		    Integer id = carPassRecordsDM.getId();
		    //添加报警（黑名单车辆发现后立即报警）
			if (carRegDetailUM != null) {
				if (!StringUtils.isEmpty(carRegDetailUM.getLabelTypeName())) {
					if (carRegDetailUM.getLabelTypeName().equals("黑名单车辆")) {
						//查询报警规则
						CarAlarmRulePA alarmRule = new CarAlarmRulePA();
						alarmRule.setCommunityId(communityId);
						alarmRule.setRuleId(5);
						alarmRule.setState(0);
						CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(alarmRule);
						if (carAlarmRuleUM != null) {
							CarAlarmRecordsDM carAlarmRecordsDM = new CarAlarmRecordsDM();
							carAlarmRecordsDM.setCommunityId(communityId);
							carAlarmRecordsDM.setType(carRegDetailUM.getCarTypeId());
							carAlarmRecordsDM.setNum(carNum);
							//触犯车辆报警黑名单规则，数据库此处不能乱动哦
							carAlarmRecordsDM.setRuleId(5);
							carAlarmRecordsDM.setAddress(carPassRecordsPA.getAdress());
							carAlarmRecordsDM.setCreateTime(carPassRecordsPA.getCreateTime());
							carAlarmRecordsDM.setPhoto(jsonObject.getString("carImg"));
							carAlarmRecordsDM.setMac(carPassRecordsPA.getVideoCode());
							carAlarmRecordsService.insert(carAlarmRecordsDM);

							msgObject.put("alarmLevel", carAlarmRuleUM.getAlarmLeve());
							JSONArray jsonArray =	JSONArray.fromObject(JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person"));
							StringBuilder strBuilder= new StringBuilder();
							for (int k = 0; k < jsonArray.size(); k++) {
								strBuilder.append(jsonArray.get(k)+",");
							}
							AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
							alarmInsertPA.setCommunityId(communityId);
							alarmInsertPA.setAlarmType(2);
							alarmInsertPA.setAlarmSourceType(id);
							alarmInsertPA.setAlarmSource(carNum);
							alarmInsertPA.setAlarmNatureOfPerson(carAlarmRecordsDM.getId()+"");
							alarmInsertPA.setAlarmSourceId(carRegDetailUM.getId());
							alarmInsertPA.setAlarmVideo(deviceDetail.getMac());
							alarmInsertPA.setAlarmLevel(carAlarmRuleUM.getAlarmLeve());
							alarmInsertPA.setAlarmMessage("黑名单车辆 "+carNum+"入侵");
							alarmInsertPA.setAlarmNatureOfVehicle(carRegDetailUM.getCarType());
							alarmInsertPA.setAlarmAddress(carPassRecordsPA.getAdress());
							alarmInsertPA.setAlarmImg(FileUploadResponse.getBackPath());
							alarmInsertPA.setHandelUser(strBuilder.substring(0, strBuilder.length()-1));
							alarmService.insertAlarmToMain(alarmInsertPA);
						}
					}
				}
			}
			//消息推送
			msgObject.put("id", id);
			msgObject.put("labelIdList", arrayList);
			Message msg = new Message();
			msg.setCommunityId(communityId+"");
			msg.setSource("0");
			msg.setCategory("C02");
			msg.setCode("car");
			msg.setData(msgObject);
			msgServiceImpl.sendMessage(msg);
			return id;
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("CAR0004", e);
		}
	}


	/**查询指定天数车辆的通行数*/
	@Override
	public Integer selectDayCount(Map<String, Object> map) throws HKException {
		return carPassRecordsMapper.selectDayCount(map);
	}

	/**查询当月敏感时段车辆总数*/
	@Override
	public Integer selectSenTimeCount(Map<String, Object> map) throws HKException {

		// 获取过夜车时间范围
		CarAlarmRulePA alarmRule = new CarAlarmRulePA();
		alarmRule.setCommunityId(Integer.parseInt(map.get("communityId").toString()));
		alarmRule.setRuleId(1);
		alarmRule.setState(0);
		CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(alarmRule);

		JSONObject jsonobject = JSONObject.fromObject(carAlarmRuleUM.getRule().toString());
		String startTime = jsonobject.optString("startTime");
		String endTime = jsonobject.optString("endTime");
		String subStartTime = startTime.substring(0, startTime.indexOf(":"));
		String subEndTime = endTime.substring(0, endTime.indexOf(":"));
		subStartTime = subStartTime.length() < 2 ? "0" + subStartTime : subStartTime;
		subEndTime = subEndTime.length() < 2 ? "0" + subEndTime : subEndTime;

		map.put("startTime", subStartTime);
		map.put("endTime", subEndTime);
		// 获取当前月份
		map.put("month", DateUtils.getYearMon(new Date()));
		// 敏感时段跨天情况
		if (Integer.parseInt(subStartTime) <= Integer.parseInt(subEndTime)) {
			return carPassRecordsMapper.selectSenTimeCount(map);
		} else {
			return carPassRecordsMapper.selectSenTimeCount1(map);
		}
	}

	/**查询指定月份各时段进出车辆总数*/
	@Override
	public CarMonTimeCountListUM selectMonTimeCount(Integer communityId) throws HKException {
		CarMonTimeCountListUM carMonTimeCountListUM = new  CarMonTimeCountListUM();
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
        // 获取上月月份（2018-12）
		map.put("month", DateUtils.getLastYearMon());
		map.put("beginOfDate", DateUtils.initDateByDay().get("beginOfDate").toString());
		//passType 0:驶入、1:驶入
		map.put("passType",0);
        // 因为数据不够，暂时先把天数写少点
		int day = 1;
		List<CarMonTimeCountUM> carEnterCountList = carPassRecordsMapper.selectMonTimeCount(map);
		carEnterCountList.stream().forEach(Item -> Item.setPreHoursCount(Item.getHoursCount() / day));
		carMonTimeCountListUM.setCarEnterCount(carEnterCountList);
		map.put("passType",1);
		List<CarMonTimeCountUM> carOutCountList = carPassRecordsMapper.selectMonTimeCount(map);
		carOutCountList.stream().forEach(Item -> Item.setPreHoursCount(Item.getHoursCount() / day));
		carMonTimeCountListUM.setCarOutCount(carOutCountList);
		return carMonTimeCountListUM;
	}

	/**查询本月停车记录（进入次数）*/
	@Override
	public CarMonParkCountListUM selectMonParkingCount(Integer communityId) throws HKException {
		CarMonParkCountListUM  carMonParkCountListUM  = new CarMonParkCountListUM();
		Map<String, Object> map = new HashMap<>();
		int curMonDayCount = DateUtils.getLastMonDay().get("curMonDay");
		int limitDay = 0;
		for (int i = 0; i < curMonDayCount; i++) {
			if (i % 2 == 1) {
				limitDay++;
			}
		}
		//carType 0:登记车 1：陌生车
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		map.put("carType",0 );
		map.put("monFirstDay", DateUtils.initDateByDay().get("beginOfDate"));
		map.put("curMonDayCount", curMonDayCount);
		map.put("limitDay", limitDay);
		map.put("month",DateUtils.getYearMon(new Date()));
		List<CarMonParkCountUM> regCarCountList = carPassRecordsMapper.selectMonParkingCount(map);
		carMonParkCountListUM.setRegCarCountList(regCarCountList);
		map.put("carType",1 );
		List<CarMonParkCountUM> strCarCountList = carPassRecordsMapper.selectMonParkingCount(map);
		carMonParkCountListUM.setStrCarCountList(strCarCountList);
		return carMonParkCountListUM;
	}

	/**本月关注车辆统计*/
	@Override
	public List<CarAttentionCountUM> selectAttentionCount(Integer communityId) throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
        //获取本月1号零点
		map.put("time", DateUtils.getMonthFristDate());
		List<CarAttentionCountUM> list = carPassRecordsMapper.selectAttentionCount(map);
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		Optional<Integer> total = list.stream().map(CarAttentionCountUM :: getCount).reduce(Integer :: sum);
		// 求取百分比
		list.stream().forEach(carAttentionCountUM -> {
			if (total.get() != 0) {
				carAttentionCountUM.setCountPer(Double.parseDouble(df.format((carAttentionCountUM.getCount() * 1.00) / total.get())));
			}else {
				carAttentionCountUM.setCountPer(0);
			}
		});
		return list;
	}

	/**查询陌生车辆当月进入次数*/
	@Override
	public List<CarmMonEnterCountUM> selectMonEnterCount(Integer communityId,CarAlarmRuleUM carAlarmRuleUM) throws HKException {
        //该社区的该任务暂停时carAlarmRuleUM为Null,直接跳过
		if (carAlarmRuleUM!=null) {
			Map< String, Object> map  = new HashMap<>();
			map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
			map.put("carType", 3);
			map.put("passType", 0);
			map.put("maxCount", Integer.parseInt( JSONObject.fromObject(carAlarmRuleUM.getRule()).optString("maxCount")));
			map.put("month", DateUtils.getYearMon(new Date()));
			return carPassRecordsMapper.selectMonEnterCount(map);
		}else {
			return null;
		}
	}

	/**按小时查询今日通行车辆数*/
	@Override
	public List<CarFeelPassHoursCountUM> selectDistinctCarPassCount(String dynamicDbname) throws HKException {
		String today = DateUtils.getTime();
		String beginOfDate = DateUtils.initDateByDay().get("beginOfDate").toString();
		return carPassRecordsMapper.selectDistinctCarPassCount(dynamicDbname, beginOfDate, today);
	}

	/**按小时查询今日通行车辆次数*/
	@Override
	public List<CarFeelPassHoursCountUM> selectCarPassCount(String dynamicDbname) throws HKException {
		String today = DateUtils.getTime();
		String beginOfDate = DateUtils.initDateByDay().get("beginOfDate").toString();
		return carPassRecordsMapper.selectCarPassCount(dynamicDbname, beginOfDate, today);
	}

	/**查询指定天数通行的车辆数*/
	@Override
	public Integer selectDayCarCount(Map<String, Object> map) throws HKException {
		return carPassRecordsMapper.selectDayCarCount(map);
	}

	/**按小时查询最进一个月、一年车辆进、出次数(态势分析)*/
	@Override
	public CarPassHourCountUM selectMonYearCarPassCount(String dynamicDbname, Integer timeType) throws HKException {
		CarPassHourCountUM carPassHourCountUM = new CarPassHourCountUM();
		String beginOfDate = DateUtils.initDateByDay().get("beginOfDate").toString();
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", dynamicDbname);
		map.put("beginOfDate", beginOfDate);
		//timeType 0:最近30天、else:最近一年
		if (timeType==0) {
			map.put("time", DateUtils.getBeforeNdayTime(30));
			map.put("passType", 0);
            //天数，暂时写1天，以后改为30
			map.put("day", 1);
		}else {
			map.put("time", DateUtils.getBeforeNdayTime(365));
			map.put("passType", 1);
			//天数，暂时写1天，以后改为365
			map.put("day", 1);
		}
		//驶入车辆数
		List<CarFeelPassHoursCountUM> carEnterList = carPassRecordsMapper.selectMonYearCarPassCount(map);
		carPassHourCountUM.setCarEnterCount(carEnterList);
		map.put("passType", 1);
		//驶出车辆数
		List<CarFeelPassHoursCountUM> carOutList = carPassRecordsMapper.selectMonYearCarPassCount(map);
		carPassHourCountUM.setCarOutCount(carOutList);
		return carPassHourCountUM;
	}

	/**按天数查询最一个月、一年停车车辆数(态势分析)*/
	@Override
	public CarPostureParkingCountUM selectPostureParkingCount(String dynamicDbname, Integer timeType)throws HKException {
		CarPostureParkingCountUM carPostureParkingCountUM = new CarPostureParkingCountUM();
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", dynamicDbname);
		String time ="";
		//timeType 0:最近30天、else:最近一年
		if (timeType==0) {
			map.put("time", DateUtils.getBeforeNdayTime(30));
			map.put("carType", 3);
            //天数，暂时写1天，以后改为30
			map.put("day", 1);
			map.put("beginOfDate", DateUtils.getBeforeNdayTime(30));
            //非登记车
			 List<CarMonParkCountUM> strCarList = carPassRecordsMapper.selectPostureParkingCount(map);
			 carPostureParkingCountUM.setStrCarList( this.getSortDayList(strCarList));
            //登记车
			 map.put("carType", -1);
			 List<CarMonParkCountUM> regCarList = carPassRecordsMapper.selectPostureParkingCount(map);
			 carPostureParkingCountUM.setRegCarList(this.getSortDayList(regCarList));
		}else {
			map.put("time", time= DateUtils.getBeforeNdayTime(365));
            //登记车
			map.put("carType",-1);
			 List<CarPosturePakingYearUM> strCarList = carPassRecordsMapper.selectPostureParkingYearCount(map);
			 carPostureParkingCountUM.setRegYearCarList( this.getSortYearList(strCarList));
            //非登记车
			 map.put("carType", 3);
			 List<CarPosturePakingYearUM> regCarList = carPassRecordsMapper.selectPostureParkingYearCount(map);
			 carPostureParkingCountUM.setStrYearCarList(this.getSortYearList(regCarList));
		}
		return carPostureParkingCountUM;
	}

	/**按月排序，去年的月份放在前面，今年的月份放在后面*/
	private ArrayList<CarPosturePakingYearUM> getSortYearList(List<CarPosturePakingYearUM> carList){
		 List<Integer> monList = new ArrayList<>();
		 CarPosturePakingYearUM curMonCount  = new CarPosturePakingYearUM();
		 carList.forEach(item -> monList.add(item.getMon()) );
		//填充没有的月份
		 for (int i = 1; i < 13; i++) {
			boolean bl = monList.contains(i);
			if (!bl) {
				CarPosturePakingYearUM carPosturePakingYearUM = new CarPosturePakingYearUM();
				carPosturePakingYearUM.setMon(i);
				carPosturePakingYearUM.setMonCount(0);
				carList.add(carPosturePakingYearUM);
			}
		}
		int curMonth = DateUtils.getYmd(new Date()).get("month");
		List<CarPosturePakingYearUM>  beforeMonList = new ArrayList<>();
		carList.stream().forEach(item ->{
			if (item.getMon()>curMonth) {
				beforeMonList.add(item);
			} else {
				curMonCount.setMon(item.getMon());
				curMonCount.setMonCount(item.getMonCount());
			}
		});
		Collections.sort( beforeMonList,(o1,o2) -> o1.getMon().compareTo(o2.getMon()));
		beforeMonList.add(curMonCount);
		return (ArrayList<CarPosturePakingYearUM>) beforeMonList;
	}

	/**按天数排序，上个月的日期放在前面，本月的排在后面*/
	private ArrayList<CarMonParkCountUM> getSortDayList(List<CarMonParkCountUM> carList){
		 int today = DateUtils.getYmd(new Date()).get("day");
		 List<CarMonParkCountUM>  monDayList = new ArrayList<>();
		 List<CarMonParkCountUM>  beforeMonDayList = new ArrayList<>();
		 carList.stream().forEach(item -> {
			if (item.getDay()>today) {
				beforeMonDayList.add(item);
			} else {
				monDayList.add(item);
			}
		 });
		 beforeMonDayList.addAll(monDayList);
		return (ArrayList<CarMonParkCountUM>) beforeMonDayList;
	}

	/**关注车辆出入统计(态势分析)*/
	@Override
	public List<CarAttentionCountUM> selectPosAttentionCount(String dynamicDbname, Integer timeType) throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", dynamicDbname);
		String time ="";
		//timeType 0:最近30天、1:最近一年、2:最近一周
		if (timeType==0) {
			time = DateUtils.getBeforeNdayTime1(30);
		}else if(timeType==1){
			time = DateUtils.getBeforeNdayTime1(365);
		}else if (timeType==2) {
			time = DateUtils.getBeforeNdayTime1(7);
		}
		map.put("time", time);
		return carPassRecordsMapper.selectPosAttentionCount(map);
	}

	/**查询通行记录详情*/
	@Override
	public CarPassRecordsUM detail(Integer communityId ,Integer id) throws HKException {
		String dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		CarPassRecordsUM carPassRecordsUM = carPassRecordsMapper.detail(dynamicDbname, id);
		return carPassRecordsUM;
	}

	/**查询车辆最新一条通行记录*/
	@Override
	public CarPassRecordsUM selectNewCarAddress(Integer communityId, String num) throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		map.put("num", num);
		return carPassRecordsMapper.selectNewCarAddress(map);
	}

	/**查询车辆通行记录列表*/
	@Override
	public List<CarPassRecordsUM> selectPassRecordList(Map<String, Object> map) throws HKException {
		List<CarPassRecordsUM> list = carPassRecordsMapper.selectPassRecordList(map);
		Map<String ,Object> labelMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			CarPassRecordsUM carPassRecordsUM = list.get(i);
			labelMap.clear();
			labelMap.put("carNum", carPassRecordsUM.getNum());
			labelMap.put("dynamicDbname", map.get("dynamicDbname").toString());
			// 陌生车辆与标签一对多
			ArrayList<Integer> arrayList = carLabelRelationService.selectLabelIdByCarNum(labelMap);
			carPassRecordsUM.setLabelIdList(arrayList);
		}
	 return list;
	}
}
