package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class DcAnalyParse {
	String deviceType = "地磁";

	/**
	 * 地磁 品牌：拓宝
	 * 
	 * @param model
	 */
	public void dc_AnalysisFun_TuoBao(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";
		// 地磁检测状态
		String recvStr = "";
		// 是否占用
		boolean carflag = false;
		// 电量
		String voltageStr = "";
		// 字符串转16进制字节数组
		byte[] dc_date = ConvertHelp.strToToHexByte(data.getData());
		// 取出包头判断数据信息 0xB1 代表报警信息，为 0xB2 代表工作状态信息
		String bthead = String.format("%02x", new Integer(dc_date[0] & 0xff));
		// 取出包头判断数据信息
		if (bthead.equalsIgnoreCase("ab")) {
			/*
			 * 地磁车辆检测器状态/信息： 0：车位变空 1：车位被占 2：心跳上报 3：强磁干扰 4：低电压报警 5: 磁传感器检测失效（可读
			 * IC 信息） F: 磁传感器硬件损坏(不可读 IC 信息)
			 */
			int FrameCountStateint = Integer.parseInt(String.format("%02x", new Integer(dc_date[1] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(FrameCountStateint, n);
				switch (n) {
				case 0:
				case 1:
				case 2:
				case 3:
					recvStr = i + recvStr;
					break;
				}
			}
			recvStr = String.valueOf(Integer.parseInt(recvStr, 2));// 字符串转换二进制
			switch (recvStr) {
			case "0":
				parseAfterMessage += "设备状态：正常；";
				break;
			case "1":
				parseAfterMessage += "设备状态：正常；";
				break;
			case "2":
				parseAfterMessage += "设备状态：正常；";
				break;
			case "3":
				parseAfterMessage += "设备状态：强磁干扰；";
				type = "报警数据";
				break;
			case "4":
				parseAfterMessage += "设备状态：低电压报警；";
				type = "报警数据";
				break;
			case "5":
				parseAfterMessage += "设备状态：磁传感器检测失效；";
				type = "报警数据";
				break;
			case "f":
				parseAfterMessage += "设备状态：磁传感器硬件损坏；";
				type = "报警数据";
				break;
			default:
				break;
			}
			// 分析yg_date[2]
			int ParkFlagBatteryLevelint = Integer.parseInt(String.format("%02x", new Integer(dc_date[2] & 0xff)), 16);
			for (int n = 0; n < 8; n++) {
				int i = ConvertHelp.getIntegerSomeBit(ParkFlagBatteryLevelint, n);
				switch (n) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					voltageStr = i + voltageStr;
					break;
				case 7:
					carflag = i > 0 ? true : false;
					break;
				}
			}
			if (carflag)
				parseAfterMessage += "车位状态：车位被占；";
			else
				parseAfterMessage += "车位状态：车位空；";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + String.valueOf(Integer.parseInt(voltageStr, 2)));
		return data;*/
	}

}
