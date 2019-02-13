package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class SxdyAnalyParse {
	String deviceType = "三相电压";

	/**
	 * 三相电压 品牌：消安
	 * 
	 * @param data
	 * @return
	 */
	public void sxdy_AnalysisFun_XiaoAn(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";

		// 分割协议数据
		byte[] sxdy_byte = ConvertHelp.strToToHexByte(data.getData());
		// 电压
		// 电量
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(sxdy_byte[1] & 0xff)), 16), false);
		Integer battery = (int) (((battery7bit / 10) / 3.6) * 100);
		// 电压A
		String DY_AInt = String.format("%02x", new Integer(sxdy_byte[2] & 0xff));
		// 电压A
		String DY_APart = String.format("%02x", new Integer(sxdy_byte[3] & 0xff));
		// 电压A数据
		String DY_A = String.valueOf(Integer.parseInt(DY_APart + DY_AInt, 16) * 0.1);
		// 电压B
		String DY_BInt = String.format("%02x", new Integer(sxdy_byte[4] & 0xff));
		// 电压B
		String DY_BPart = String.format("%02x", new Integer(sxdy_byte[5] & 0xff));
		// 电压B数据
		String DY_B = String.valueOf(Integer.parseInt(DY_BPart + DY_BInt, 16) * 0.1);
		// 电压C
		String DY_CInt = String.format("%02x", new Integer(sxdy_byte[6] & 0xff));
		// 电压C
		String DY_CPart = String.format("%02x", new Integer(sxdy_byte[7] & 0xff));
		// 电压C数据
		String DY_C = String.valueOf(Integer.parseInt(DY_CPart + DY_CInt, 16) * 0.1);
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(
				parseAfterMessage + "电量：" + battery + "；电压A：" + DY_A + "V；电压B：" + DY_B + "V；电压C：" + DY_C + "V");
		return data;*/
	}
}
