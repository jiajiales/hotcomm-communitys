package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class SjAnalyParse {
	String deviceType = "烟感";

	/**
	 * 水浸 品牌：大洋
	 * 
	 * @param data
	 * @return
	 */
	public void sj_AnalysisFun_DaYang(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";

		// 分割协议数据
		byte[] sj_byte = ConvertHelp.strToToHexByte(data.getData());
		// 检查是否低电压报警
		boolean lowbattery = ConvertHelp
				.getIntegerSomeBit(Integer.parseInt(String.format("%02x", new Integer(sj_byte[3] & 0xff)), 16), 7) == 1
						? true : false;
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(sj_byte[3] & 0xff)), 16), false);
		// 电量
		Integer battery = (int) (((battery7bit / 10) / 3.6) * 100);
		// Water1Leak
		byte[] a4 = new byte[1];
		System.arraycopy(sj_byte, 4, a4, 0, 1);
		String Water1LeakStr = ConvertHelp.StringParseByte(a4);
		double Water1LeakInt = Integer.parseInt(Water1LeakStr, 16);// 16进制转10进制
		// Water2Leak
		byte[] a5 = new byte[1];
		System.arraycopy(sj_byte, 5, a5, 0, 1);
		String Water2LeakStr = ConvertHelp.StringParseByte(a5);
		double Water2LeakInt = Integer.parseInt(Water2LeakStr, 16);// 16进制转10进制
		if (Water1LeakInt == 1 || Water2LeakInt == 1) {
			// 漏水报警
			parseAfterMessage += "设备状态：漏水报警；";
			type = "报警数据";
		} else if (lowbattery) {
			// 低电压报警
			parseAfterMessage += "设备状态：低电压报警；";
			type = "报警数据";
		} else {
			parseAfterMessage += "设备状态：正常；";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + battery);
		return data;*/
	}
}
