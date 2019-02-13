package com.hotcomm.community.parse.analy;

import java.util.HashMap;
import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class SydlAnalyParse {
	private Map<String, Object> alarmMap = new HashMap<String, Object>();

	/**
	 * 剩余电流 品牌：消安
	 * 
	 * @param data
	 * @return
	 */
	public ReceiveModel sydl_AnalysisFun_XiaoAn(ReceiveModel data) {
		// 分割协议数据
		byte[] sydl_byte = ConvertHelp.strToToHexByte(data.getData());
		// 剩余电流
		// 转发特斯联的数据
		// 电量
		int battery7bit = ConvertHelp.setIntegerSomeBit(7,
				Integer.parseInt(String.format("%02x", new Integer(sydl_byte[1] & 0xff)), 16), false);
		Integer battery = (int) (((battery7bit / 10) / 3.6) * 100);
		// 剩余电流前部分
		String electriccuintegerPart = String.format("%02x", new Integer(sydl_byte[2] & 0xff));
		// 剩余电流后部分
		String electriccudecimalPart = String.format("%02x", new Integer(sydl_byte[3] & 0xff));
		// 剩余电流数据
		String ElectriccurrentStr = String
				.valueOf(Integer.parseInt((electriccudecimalPart + electriccuintegerPart), 16) * 0.1);
		alarmMap.put("alarmid", DeviceAlarmState.getByName("4电流异常报警").getId());
		alarmMap.put("battery", battery);
		alarmMap.put("sydl", ElectriccurrentStr);
		data.setAnalyMsg(alarmMap);
		return data;
	}
}
