package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class LjtAnalyParse {
	String deviceType = "垃圾桶";

	/**
	 * 垃圾桶 品牌：南鹏物联
	 * 
	 * @param data
	 * @return
	 */
	public void ljt_AnalysisFun_NanPengWuLian(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";

		// 字符串转16进制字节数组
		byte[] ljt_date = ConvertHelp.strToToHexByte(data.getData());
		// 判断包头0A
		byte[] trashHighbyte = new byte[2];
		// 将ljt_date第三第四位赋值给trashHighbyte解析，传感器采集值垃圾高度
		System.arraycopy(ljt_date, 2, trashHighbyte, 0, 2);
		String trashHighStr = "";
		for (int n = 0; n < 2; n++) {
			// 去除0X
			trashHighStr += String.format("%02x", new Integer(trashHighbyte[n] & 0xff));
		}
		// 垃圾高度
		trashHighStr = String.valueOf(Integer.parseInt(trashHighStr, 16)) + "cm";
		// 取出第五位数据电压值，转十进制
		String btvoltageStr = String.format("%02x", new Integer(ljt_date[4] & 0xff));
		Integer battery = (int) (((Integer.parseInt(btvoltageStr, 16) / 10) / 3.6) * 100);
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + battery + "；垃圾高度：" + trashHighStr);
		return data;*/
	}
}
