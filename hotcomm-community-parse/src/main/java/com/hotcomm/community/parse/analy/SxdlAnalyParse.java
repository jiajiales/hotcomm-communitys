package com.hotcomm.community.parse.analy;

import java.util.HashMap;
import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class SxdlAnalyParse {
	private Map<String, Object> alarmMap = new HashMap<String, Object>();

	public ReceiveModel sxdl_Analysis_XiaoAn(ReceiveModel data) {
		// 分割协议数据
		byte[] sxdl_byte = ConvertHelp.strToToHexByte(data.getData());
		// 判断是否为三箱电流设备
		// 电量
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(sxdl_byte[1] & 0xff)), 16), false);
		double battery = (((battery7bit / 10) / 3.6) * 100);
		// 电流A数据前部分
		String DL_AInt = String.format("%02x", new Integer(sxdl_byte[6] & 0xff));
		// 电流A数据后部分
		String DL_APart = String.format("%02x", new Integer(sxdl_byte[7] & 0xff));
		// 电流A数据
		String DL_A = String.valueOf(Integer.parseInt(DL_APart + DL_AInt, 16) * 0.01);
		// 电流数据前部分
		String DL_BInt = String.format("%02x", new Integer(sxdl_byte[4] & 0xff));
		// 电流B数据后部分
		String DL_BPart = String.format("%02x", new Integer(sxdl_byte[5] & 0xff));
		// 电流B数据
		String DL_B = String.valueOf(Integer.parseInt(DL_BPart + DL_BInt, 16) * 0.01);
		// 电流C数据前部分
		String DL_CInt = String.format("%02x", new Integer(sxdl_byte[2] & 0xff));
		// 电流C数据后部分
		String DL_CPart = String.format("%02x", new Integer(sxdl_byte[3] & 0xff));
		// 电流C数据
		String DL_C = String.valueOf(Integer.parseInt(DL_CPart + DL_CInt, 16) * 0.01);
		alarmMap.put("alarmid", DeviceAlarmState.getByName("4电流异常报警").getId());
		alarmMap.put("battery", battery);
		alarmMap.put("sxdla", DL_A);
		alarmMap.put("sxdlb", DL_B);
		alarmMap.put("sxdlc", DL_C);
		data.setAnalyMsg(alarmMap);
		return data;
	}
}
