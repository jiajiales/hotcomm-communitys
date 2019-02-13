package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class JgAnalyParse {
	String deviceType = "井盖";

	/**
	 * 井盖 品牌：博频
	 * 
	 * @param data
	 * @return
	 */
	public void jg_AnalysisFun_BoPin(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";
		boolean voltagestate = false;
		boolean devstate = false;
		byte[] jg_date = ConvertHelp.strToToHexByte(data.getData());
		// 取第一段进行分析
		byte[] status = new byte[1];
		System.arraycopy(jg_date, 0, status, 0, 1);
		String stateStr = "";
		for (int n = 0; n < 1; n++) {
			stateStr += String.format("%02x", new Integer(status[n] & 0xff));
		}
		int state = Integer.parseInt(stateStr, 16);
		for (int n = 0; n < 8; n++) {
			// 二進制裝換
			int i = ConvertHelp.getIntegerSomeBit(state, n);
			switch (n) {
			case 3:
				// 电压是否正常
				voltagestate = i > 0 ? true : false;
				break;
			case 2:
				// logjg.calibration = i > 0 ? true : false;
				break;
			case 1:
				// logjg.keepalive = i > 0 ? true : false;
				break;
			case 0:
				// 井盖闭合
				devstate = i > 0 ? true : false;
				break;
			}
		}
		// 取第二段进行分析.电量
		byte[] btvoltage = new byte[1];
		System.arraycopy(jg_date, 1, btvoltage, 0, 1);
		String btvoltageStr = "";
		for (int n = 0; n < 1; n++) {
			btvoltageStr += String.format("%02x", new Integer(btvoltage[n] & 0xff));
		}
		// battery电量
		Integer battery = (int) (((Integer.parseInt(btvoltageStr, 16) / 10) / 3.6) * 100);
		// 温度
		byte[] tempbyte = new byte[1];
		System.arraycopy(jg_date, 2, btvoltage, 0, 1);
		String tempmsg = ConvertHelp.bytes2BinaryStr(tempbyte);
		// 2进制转10进制，温度值
		String tempvaule = String.valueOf(Integer.parseInt(tempmsg.substring(1), 2));
		String fuhao = "";
		if (!tempmsg.substring(0, 1).equals("0")) {
			fuhao = "-";
		}
		// 温度值
		tempvaule = fuhao + tempvaule;
		// 判断该条数据是否为报警数据
		if (devstate) {
			// 报警类型：开盖报警
			parseAfterMessage = "品牌：泛海三江；设备状态：井盖开启；";
		} else if (voltagestate) {
			// 报警类型：低电压报警
			parseAfterMessage = "品牌：泛海三江；设备状态：低电压报警；";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "电量：" + battery + ";温度：" + tempvaule);
		return data;*/
	}
}
