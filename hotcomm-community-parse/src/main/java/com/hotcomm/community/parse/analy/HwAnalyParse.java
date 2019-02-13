package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class HwAnalyParse {
	String deviceType = "红外";

	/**
	 * 红外 品牌：大洋
	 * 
	 * @param data
	 * @return
	 */
	public void hw_AnalysisFun_DaYang(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";
		// 分割data数据
		byte[] hw_byte = ConvertHelp.strToToHexByte(data.getData());
		// 检查是否低电压报警
		boolean lowbattery = ConvertHelp
				.getIntegerSomeBit(Integer.parseInt(String.format("%02x", new Integer(hw_byte[3] & 0xff)), 16), 7) == 1
						? true : false;
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(hw_byte[3] & 0xff)), 16), false);
		// 电量
		Integer battery = (int) (((battery7bit / 10) / 3.6) * 100);
		// 温度
		byte[] a4 = new byte[2];
		System.arraycopy(hw_byte, 4, a4, 0, 2);
		double TemperatureInt = Integer.parseInt(ConvertHelp.StringParseByte(a4), 16) * 0.01;// 16进制转10进制
		// 光照
		byte[] a6 = new byte[2];
		System.arraycopy(hw_byte, 6, a6, 0, 2);
		double illuminanceInt = Integer.parseInt(ConvertHelp.StringParseByte(a6), 16);// 16进制转10进制
		// 是否有侵入
		boolean OccupyBool = Integer.parseInt(String.format("%02x", new Integer(hw_byte[8] & 0xff)), 16) == 1 ? true
				: false;
		if (OccupyBool) {
			// 侵入报警
			parseAfterMessage += "设备状态：检测报警";
		}
		if (lowbattery) {
			// 低电压报警
			parseAfterMessage += "设备状态：低电量报警";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + battery + "；温度：" + String.valueOf(TemperatureInt) + "；光照："
				+ String.valueOf(illuminanceInt));
		return data;*/
	}
}
