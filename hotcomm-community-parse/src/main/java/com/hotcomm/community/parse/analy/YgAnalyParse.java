package com.hotcomm.community.parse.analy;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class YgAnalyParse {

	private Map<String, Object> alarmMap = new HashMap<String, Object>();

	/**
	 * 烟感 品牌：拓宝
	 * 
	 * @param moduleid
	 * @param model
	 */
	public ReceiveModel yg_AnalysisFun_TuoBao(ReceiveModel data) {
		// 字符串转16进制字节数组
		byte[] yg_date = ConvertHelp.strToToHexByte(data.getData());
		// 取出包头判断数据信息 0xB1 代表报警信息，为 0xB2 代表工作状态信息
		String bthead = String.format("%02x", new Integer(yg_date[0] & 0xff));
		// 设备是否故障
		boolean FaultAlarm = false;
		boolean temp_alarm = false;
		String fire_alarm = "false";
		String lb_alarm = "false";
		if (bthead.equalsIgnoreCase("b1")) {// 该条数据为报警信息
			// 分析yg_date[1]
			int FrameCountStateint = Integer.parseInt(String.format("%02x", new Integer(yg_date[1] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(FrameCountStateint, n);
				switch (n) {
				case 0:
					temp_alarm = i > 0 ? true : false;
					break;
				case 2:// SFA故障报警
					FaultAlarm = i > 0 ? true : false;
					break;
				case 3:// FRA
					fire_alarm = i > 0 ? "true" : "false";
					break;
				}
			}
			// 分析yg_date[2]
			int ParkFlagBatteryLevelint = Integer.parseInt(String.format("%02x", new Integer(yg_date[2] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(ParkFlagBatteryLevelint, n);
				if (n == 1 && i > 0) {
					lb_alarm = "true";
				}
			}
			// 报警类型：火灾报警！//报警类型：低电报警！//报警类型：故障报警
			if (fire_alarm == "true" || lb_alarm == "true") {
				// 设备报警
				if (fire_alarm == "true") {
					// 烟雾报警
					alarmMap.put("alarmid", DeviceAlarmState.getByName("1烟雾报警").getId());
				} else if (lb_alarm == "true") {
					// 低电压报警
					alarmMap.put("alarmid", DeviceAlarmState.getByName("1低电压报警").getId());
				}
			} else if (FaultAlarm) {
				// 故障报警
				alarmMap.put("alarmid", DeviceAlarmState.getByName("1故障报警").getId());
			}
		} else if (bthead.equalsIgnoreCase("b2")) {// 该条数据为心跳信息
			alarmMap.put("alarmid", 0);
		}
		data.setAnalyMsg(alarmMap);
		return data;
	}

	/**
	 * 烟感 品牌：泛海三江
	 * 
	 * @param moduleid
	 * @param model
	 */
	public ReceiveModel yg_Analysis_FunFanHaiSJ(ReceiveModel data) {
		// data转ASCII
		String ASCII = ConvertHelp.asciiToString(ConvertHelp.s16To10str(data.getData()));
		switch (ASCII) {
		case "020100":
			System.out.println("火警");
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1烟雾报警").getId());
			break;
		case "020200":
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1故障报警").getId());
			break;
		case "020300":
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1低电压报警").getId());
			break;
		case "020400":// 火警消除
		case "020500":// 传感器故障消除
		case "020600":// 低电压恢复
		case "020000":// 正常状态
		case "020E00":// 探测器上电
		case "020D00":// 按键自检
			alarmMap.put("alarmid", 0);
			System.out.println("心跳数据");
			break;
		default:
			break;
		}
		data.setAnalyMsg(alarmMap);
		return data;
	}

	/**
	 * 烟感 品牌：hotcomm
	 * 
	 * @param moduleid
	 * @param model
	 */
	public ReceiveModel yg_AnalysisFun_Hotcomm(ReceiveModel data) {

		// 字符串转16进制字节数组
		byte[] yg_date = ConvertHelp.strToToHexByte(data.getData());

		// 电量
		DecimalFormat df = new DecimalFormat("#0.00");
		double batteryd = Double
				.parseDouble(Integer.parseInt(String.format("%02x", new Integer(yg_date[6] & 0xff)), 16) + "") / 100;
		String battery = df
				.format(Integer.parseInt(String.format("%02x", new Integer(yg_date[5] & 0xff)), 16) + batteryd);
		// 温度
		double temperatured = Double
				.parseDouble(Integer.parseInt(String.format("%02x", new Integer(yg_date[4] & 0xff)), 16) + "") / 100;
		String temperature = df
				.format(Integer.parseInt(String.format("%02x", new Integer(yg_date[3] & 0xff)), 16) + temperatured);

		// 温升报警0正常 1报警
		Integer wensheng = Integer.parseInt(String.format("%02x", new Integer(yg_date[2] & 0xff)), 16);
		if (wensheng == 1)
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1温度波动异常").getId());

		// bit7~bit4:默认为0000
		// bit3:1表示温度超出阀值报警，0表示温度正常bit2：1表示低电压报警，0表示电压正常
		// bit1~bit0：传感器状态，00表示正常，01表示火警，10表示自检，11表示故障
		Integer devstate = Integer.parseInt(String.format("%02x", new Integer(yg_date[2] & 0xff)), 16);
		String devstateBit = "";
		for (int n = 0; n < 8; n++) {
			int i = ConvertHelp.getIntegerSomeBit(devstate, n);
			switch (n) {
			case 0:
			case 1:
			case 2:
			case 3:
				break;
			case 4:
				if (i == 1)
					alarmMap.put("alarmid", DeviceAlarmState.getByName("1温度报警").getId());
				break;
			case 5:
				if (i == 1)
					alarmMap.put("alarmid", DeviceAlarmState.getByName("1低电压报警").getId());
				break;
			case 6:
			case 7:
				devstateBit += i;
				break;
			}
		}
		switch (devstateBit) {
		case "00":
			// "设备状态：正常；";
			break;
		case "01":
			// "设备状态：火警；";
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1烟雾报警").getId());
			break;
		case "10":
			// "设备状态：按键自检；";
			break;
		case "11":
			// "设备状态：故障；";
			alarmMap.put("alarmid", DeviceAlarmState.getByName("1故障报警").getId());
			break;
		default:
			break;
		}

		alarmMap.put("battery", battery);
		alarmMap.put("temp", temperature);
		data.setAnalyMsg(alarmMap);
		return data;

		// // 字符串转16进制字节数组
		// byte[] yg_date = ConvertHelp.strToToHexByte(data.getData());
		// // 设备状态0x00 正常状态 0x01 火警 0x02 自检 0x03 故障 0x04 低电压
		// String devstate = String.format("%02x", new Integer(yg_date[2] &
		// 0xff));
		// // 传感器数据Byte3：温度值整数 Byte2：温度值小数 Byte1：电压值整数 Byte0：电压值小数
		// // 电压
		// String battery =
		// String.valueOf(Integer.parseInt(String.format("%02x", new
		// Integer(yg_date[4] & 0xff)), 16));
		// // 温度
		// String temperature =
		// String.valueOf(Integer.parseInt(String.format("%02x", new
		// Integer(yg_date[6] & 0xff)), 16))
		// + "." + String.valueOf(Integer.parseInt(String.format("%02x", new
		// Integer(yg_date[5] & 0xff)), 16));
		// switch (devstate) {
		// case "00":// 正常
		// case "02":// 按键自检
		// alarmMap.put("alarmid", 0);
		// System.out.println("心跳数据");
		// break;
		// case "01":// 火警
		// alarmMap.put("alarmid", DeviceAlarmState.getByName("1烟雾报警").getId());
		// break;
		// case "03":// 故障
		// alarmMap.put("alarmid", DeviceAlarmState.getByName("1故障报警").getId());
		// break;
		// case "04":// 低电压
		// alarmMap.put("alarmid",
		// DeviceAlarmState.getByName("1低电压报警").getId());
		// break;
		// default:
		// break;
		// }
		// alarmMap.put("battery", battery);
		// alarmMap.put("temp", temperature);
		// data.setAnalyMsg(alarmMap);
		// return data;
	}

}
