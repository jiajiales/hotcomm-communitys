package com.hotcomm.community.parse.analy;

import java.util.HashMap;
import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class SyAnalyParse {
	private Map<String, Object> alarmMap = new HashMap<String, Object>();

	/**
	 * 水压 品牌：消安
	 * 
	 * @param data
	 * @return
	 */
	public ReceiveModel sy_AnalysisFun_XiaoAn(ReceiveModel data) {
		// 分割协议数据
		byte[] sy_byte = ConvertHelp.strToToHexByte(data.getData());
		// 电量
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(sy_byte[0] & 0xff)), 16), false);
		double battery = (((battery7bit / 10) / 3.6) * 100);
		// 水压数据
		String pressureStr = String
				.valueOf(Integer.parseInt(String.format("%02x", new Integer(sy_byte[2] & 0xff)), 16));
		alarmMap.put("alarmid", DeviceAlarmState.getByName("3压力异常报警").getId());
		alarmMap.put("battery", battery);
		alarmMap.put("sy", pressureStr);
		data.setAnalyMsg(alarmMap);
		return data;
	}

	/**
	 * 水压 品牌：拓普索尔
	 * 
	 * @param data
	 * @return
	 */
	public ReceiveModel sy_AnalysisFun_TuoPuSuoEr(ReceiveModel data) {
		String vaule = "";
		// 分割协议数据 10 01 1E 01 01 00 00 01 41 22 50
		byte[] sy_byte = ConvertHelp.strToToHexByte(data.getData());
		// devtype 设备类型 01消火栓 02 压力表 03 液位
		// String devtype =
		// String.valueOf(Integer.parseInt(String.format("%02x", new
		// Integer(sy_byte[1] & 0xff)), 16));
		// if (devtype.equals("1")) { 设备类型 01消火栓 02 压力表 03 液位
		// devstate 01为消火栓状态正常；02为消火栓被撞，设备发生倾斜；03为消火栓被盗，有加速度产生；
		String devstate = String.valueOf(Integer.parseInt(String.format("%02x", new Integer(sy_byte[4] & 0xff)), 16));
		alarmMap.put("alarmid", DeviceAlarmState.getByName("3压力异常报警").getId());
		switch (devstate) {
		case "1":
			// 01为状态正常
			alarmMap.put("alarmid", 0);
			break;
		case "2":
			// 02为消火栓被撞，设备发生倾斜
			alarmMap.put("alarmid", DeviceAlarmState.getByName("3设备倾斜报警").getId());
			break;
		case "3":
			// 03为消火栓被盗，有加速度产生
			alarmMap.put("alarmid", DeviceAlarmState.getByName("3设备被盗报警").getId());
			break;
		default:
			break;
		}
		// 解析出数据值 01 41 22 50
		// State设备数据状态
		String State = data.getData().substring(14, 15);
		// Unit数据单位
		String Unit = data.getData().substring(15, 16);
		// 小数点位置
		Integer position = 5 - Integer.valueOf(data.getData().substring(16, 17));
		// vaule数据数值
		vaule = data.getData().substring(17);
		// 构造一个StringBuilder对象
		StringBuilder sb = new StringBuilder(vaule);
		sb.insert(position, ".");
		vaule = sb.toString();
		// 数据状态
		switch (State) {
		// 0表示数据正常，1表示数据低压超限，2表示数据高压超限，3表示压力波动变化超限，4表示设备故障
		case "0":// 正常
			break;
		case "1":// 数据低压超限
			break;
		case "2":// 数据高压超限
			break;
		case "3":// 压力波动变化超限
			break;
		case "4":// 设备故障
			alarmMap.put("alarmid", DeviceAlarmState.getByName("3压力异常报警").getId());
			break;
		default:
			break;
		}
		// 数据单位
		switch (Unit) {
		// 1表示压力MPa， 2表示压力Bar，3表示压力KPa，4表示温度℃；5表示液位M，6表示流量m3/h
		case "1":
			Unit = "MPa";
			break;
		case "2":
			Unit = "Bar";
			break;
		case "3":
			Unit = "KPa";
			break;
		case "4":
			Unit = "℃";
			break;
		case "5":
			Unit = "M";
			break;
		case "6":
			Unit = "m3/h";
			break;
		default:
			break;
		}
		alarmMap.put("sy", vaule);
		data.setAnalyMsg(alarmMap);
		return data;
	}
}
