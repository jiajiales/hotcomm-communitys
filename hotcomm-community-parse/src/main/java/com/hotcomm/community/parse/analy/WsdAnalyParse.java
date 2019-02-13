package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class WsdAnalyParse {
	String deviceType = "温湿度";

	/**
	 * 温湿度 品牌：Hotcomm
	 * 
	 * @param data
	 * @return
	 */
	public void wsd_AnalysisFun_Hotcomm(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";
		// 字符串转16进制字节数组
		byte[] wsd_date = ConvertHelp.strToToHexByte(data.getData());
		Integer tempZ = Integer.parseInt(String.format("%02x", new Integer(wsd_date[2] & 0xff)), 16);
		Integer tempX = Integer.parseInt(String.format("%02x", new Integer(wsd_date[3] & 0xff)), 16);
		String temp = String.valueOf(tempZ) + "." + String.valueOf(tempX);
		Integer shidu = Integer.parseInt(String.format("%02x", new Integer(wsd_date[4] & 0xff)), 16);
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setIsPass(0);
		data.setParseAfterMessage(parseAfterMessage + "温度：" + temp + "；湿度：" + shidu);
		if ((tempZ < 0 && tempZ > 40) || (shidu < 0 && shidu > 90) || (Double.parseDouble(data.getRssi()) <= -60.0)
				|| (Double.parseDouble(data.getSnr()) <= 10.0)) {
			data.setIsPass(1);
		}
		return data;*/
	}
}
