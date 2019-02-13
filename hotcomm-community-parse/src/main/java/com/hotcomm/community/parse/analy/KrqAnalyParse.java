package com.hotcomm.community.parse.analy;

import java.util.HashMap;
import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class KrqAnalyParse {
	private Map<String, Object> alarmMap = new HashMap<String, Object>();

	/**
	 * 可燃气 品牌：拓宝
	 * 
	 * @param data
	 * @return
	 */
	public ReceiveModel krq_AnalysisFun_TuoBao(ReceiveModel data) {
		// 分割协议数据
		byte[] krq_byte = ConvertHelp.strToToHexByte(data.getData());
		// 判断包头; B1报警信息，B2正常信息
		byte[] a0 = new byte[1];
		System.arraycopy(krq_byte, 0, a0, 0, 1);
		String FrameTypeStr = ConvertHelp.StringParseByte(a0);
		if (FrameTypeStr.equalsIgnoreCase("b1")) {// 报警数据
			// 火灾报警状态
			int state1 = Integer.parseInt(String.format("%02x", new Integer(krq_byte[1] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(state1, n);
				switch (n) {
				case 3:// 火灾报警(Fire Alarm)指示， 为 1 有效
					alarmMap.put("alarmid", DeviceAlarmState.getByName("2气体泄漏").getId());
					break;
				}
			}
			// 无线底座状态
			int state2 = Integer.parseInt(String.format("%02x", new Integer(krq_byte[2] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(state2, n);
				switch (n) {
				case 0:// 无线底座其他故障报警
					if (String.valueOf(i) == "1")
						alarmMap.put("alarmid", DeviceAlarmState.getByName("2故障报警").getId());
					break;
				case 1:// 无线底座低电压报警
					if (String.valueOf(i) == "1")
						alarmMap.put("alarmid", DeviceAlarmState.getByName("2低电压报警").getId());
					break;
				case 4:// 无线底座防拆报警
					alarmMap.put("alarmid", DeviceAlarmState.getByName("2防拆报警").getId());
					break;
				}
			}
		} else if (FrameTypeStr.equalsIgnoreCase("b2")) {// 心跳
			alarmMap.put("alarmid", 0);
		} else if (FrameTypeStr.equalsIgnoreCase("b3")) {// 测试报警数据
		}
		data.setAnalyMsg(alarmMap);
		return data;
	}

	/**
	 * 可燃气 品牌：Hotocmm
	 * 
	 * @param moduleid
	 * @param model
	 */
	public ReceiveModel krq_AnalysisFunHotcomm(ReceiveModel data) {
		// 字符串转16进制字节数组
		byte[] krq_date = ConvertHelp.strToToHexByte(data.getData());
		// 29代表可燃气
		String bthead = String.format("%02x", new Integer(krq_date[1] & 0xff));
		// 设备状态0x00 正常状态 0x01 防拆报警 0x02 气体泄漏和防拆报警 0x03 气体泄漏 0x04 低电压
		String devstate = String.format("%02x", new Integer(krq_date[2] & 0xff));
		// 传感器数据 Byte3：温度值整数 Byte2：温度值小数 Byte1：电压值整数 Byte0：电压值小数
		// 电压00 29 00 22550311 0000350000
		String battery = String.valueOf(Integer.parseInt(String.format("%02x", new Integer(krq_date[4] & 0xff)), 16))
				+ "." + String.valueOf(Integer.parseInt(String.format("%02x", new Integer(krq_date[3] & 0xff)), 16));
		// 温度
		String temperature = String
				.valueOf(Integer.parseInt(String.format("%02x", new Integer(krq_date[6] & 0xff)), 16)) + "."
				+ String.valueOf(Integer.parseInt(String.format("%02x", new Integer(krq_date[5] & 0xff), 16)));
		// 判断是否为可燃气数据
		// 判断是否报警0x00 正常状态 0x01 防拆报警 0x02 气体泄漏和防拆报警 0x03 气体泄漏 0x04 低电压
		switch (devstate) {
		case "00":// 正常
			alarmMap.put("alarmid", 0);
			break;
		case "01":// 防拆报警
			alarmMap.put("alarmid", DeviceAlarmState.getByName("2防拆报警").getId());
			break;
		case "02":// 气体泄漏和防拆同时报警
			alarmMap.put("alarmid", DeviceAlarmState.getByName("2气体泄漏和防拆同时报警").getId());
			break;
		case "03":// 气体泄漏
			alarmMap.put("alarmid", DeviceAlarmState.getByName("2气体泄漏").getId());
			break;
		case "04":// 低电压
			alarmMap.put("alarmid", DeviceAlarmState.getByName("2低电压报警").getId());
			break;
		default:
			break;
		}
		alarmMap.put("battery", battery);
		alarmMap.put("temp", temperature);
		data.setAnalyMsg(alarmMap);
		return data;
	}
}
