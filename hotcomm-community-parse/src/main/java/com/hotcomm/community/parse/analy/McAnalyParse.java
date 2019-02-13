package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class McAnalyParse {
	String deviceType = "门磁";

	/**
	 * 门磁 品牌：大洋
	 * 
	 * @param data
	 * @return
	 */
	public void mc_AnalysisFun_DaYang(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";

		// 分割协议数据
		byte[] mc_byte = ConvertHelp.strToToHexByte(data.getData());
		// 检查是否低电压报警
		boolean lowbattery = ConvertHelp
				.getIntegerSomeBit(Integer.parseInt(String.format("%02x", new Integer(mc_byte[3] & 0xff)), 16), 7) == 1
						? true : false;
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(mc_byte[3] & 0xff)), 16), false);
		// 电量
		Integer battery = (int) (((battery7bit / 10) / 3.6) * 100);
		// 开关
		boolean ContactSwitchOnOffBool = Integer.parseInt(String.format("%02x", new Integer(mc_byte[4] & 0xff)),
				16) == 1 ? true : false;
		if (ContactSwitchOnOffBool) {
			// 开门报警
			parseAfterMessage += "设备状态：开门警报；";
			type = "报警数据";
		} else if (lowbattery) {
			// 低电压报警
			parseAfterMessage += "设备状态：低电压报警；";
			type = "报警数据";
		} else {
			// 正常
			parseAfterMessage += "设备状态：正常；";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + battery);
		return data;*/
	}
}
