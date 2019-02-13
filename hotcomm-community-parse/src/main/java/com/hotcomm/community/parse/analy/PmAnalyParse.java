package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;
import com.hotcomm.community.parse.utils.ConvertHelp;

public class PmAnalyParse {
	String deviceType = "PM2.5";

	/**
	 * pm2.5 品牌：大洋
	 * 
	 * @param data
	 * @return
	 */
	public void pm_AnalysisFun_DaYang(ReceiveModel data) {
		String parseAfterMessage = "品牌：Hotcomm；";
		String type = "心跳数据";

		byte[] pm_byte = ConvertHelp.strToToHexByte(data.getData());
		// 判断回传数据类型
		String bthead = String.format("%02x", new Integer(pm_byte[2] & 0xff));
		int BatteryDou = 0;
		boolean lowbattery = false;
		String pm10_01, pm25_01 = "0", pm100_01 = "0";
		String pm10_02, pm25_02 = "0", pm100_02 = "0";
		String pm03_03, pm05_03, pm10_03 = "";
		String pm25_04, pm50_04, pm100_04 = "";
		String pmo3_05, pmco_05, pmno_05 = "";
		String pmno2_06, pmso2_06, pmh2s_06 = "";
		String pmco2_07, pmnh3_07, pmnoise_07 = "0";
		String pmph_08, pmTemperaturewithPH_08, pmORP_08 = "";
		String pmNTU_09, pmTemperaturewithNTU_09, pmEC5SoildHumidtiy_09 = "";
		String pm5TESoildHumidtiy_0a, pm5TESoildTemp_0a, pmWaterLevel_0a = "";
		String pmTemperaturewithLDO_0b, pmLDODOValue_0b, pmLDOSatValue_0b = "";
		String pmTemperature_0c = "0", pmHumidity_0c = "0", pmWindSpeed_0c = "0";
		String pmWindDirection_0d, pmAtomsphere_0d = "";
		byte[] a1 = new byte[2];
		byte[] a3 = new byte[2];
		byte[] a4 = new byte[2];
		byte[] a6 = new byte[2];
		byte[] a8 = new byte[2];
		System.arraycopy(pm_byte, 1, a1, 0, 2);
		System.arraycopy(pm_byte, 3, a3, 0, 2);
		System.arraycopy(pm_byte, 4, a4, 0, 2);
		System.arraycopy(pm_byte, 6, a6, 0, 2);
		if (!bthead.equalsIgnoreCase("0D")) {
			System.arraycopy(pm_byte, 8, a8, 0, 2);
		}
		// 判断消息类型
		switch (bthead) {
		case "01":
			// 检查是否低电压报警
			lowbattery = ConvertHelp.getIntegerSomeBit(
					Integer.parseInt(String.format("%02x", new Integer(pm_byte[3] & 0xff)), 16), 7) == 1 ? true : false;
			double battery7bit = ConvertHelp.setIntegerSomeBit(7,
					Integer.parseInt(String.format("%02x", new Integer(pm_byte[3] & 0xff)), 16), false);
			BatteryDou = Integer.parseInt(String.valueOf(battery7bit), 16);// 16进制转10进制
			pm10_01 = ConvertHelp.StringParseByte(a4);
			pm25_01 = ConvertHelp.StringParseByte(a6);
			pm100_01 = ConvertHelp.StringParseByte(a8);
			break;
		case "02":
			pm10_02 = ConvertHelp.StringParseByte(a4);
			String pm25_01_2 = ConvertHelp.StringParseByte(a6);
			if (pm25_01_2.equalsIgnoreCase("FFFF")) {
				pm25_02 = "0";
			} else {
				pm25_02 = String.valueOf(Integer.parseInt(pm25_01_2, 16));
			}
			pm100_02 = ConvertHelp.StringParseByte(a8);
			break;
		case "03":
			pm03_03 = ConvertHelp.StringParseByte(a4);
			pm05_03 = ConvertHelp.StringParseByte(a6);
			pm10_03 = ConvertHelp.StringParseByte(a8);
			break;
		case "04":
			pm25_04 = ConvertHelp.StringParseByte(a4);
			pm50_04 = ConvertHelp.StringParseByte(a6);
			pm100_04 = ConvertHelp.StringParseByte(a8);
			break;
		case "05":
			pmo3_05 = ConvertHelp.StringParseByte(a4);
			pmco_05 = ConvertHelp.StringParseByte(a6);
			pmno_05 = ConvertHelp.StringParseByte(a8);
			break;
		case "06":
			pmno2_06 = ConvertHelp.StringParseByte(a4);
			pmso2_06 = ConvertHelp.StringParseByte(a6);
			pmh2s_06 = ConvertHelp.StringParseByte(a8);
			break;
		case "07":
			pmco2_07 = ConvertHelp.StringParseByte(a4);
			pmnh3_07 = ConvertHelp.StringParseByte(a6);
			String pmnoise_07_1 = ConvertHelp.StringParseByte(a8);
			if (pmnoise_07_1.equalsIgnoreCase("FFFF")) {
				pmnoise_07 = "0";
			} else {
				pmnoise_07 = String.valueOf((Integer.parseInt(pmnoise_07_1, 16)));
			}
			break;
		case "08":
			pmph_08 = ConvertHelp.StringParseByte(a4);
			pmTemperaturewithPH_08 = ConvertHelp.StringParseByte(a6);
			pmORP_08 = ConvertHelp.StringParseByte(a8);
			break;
		case "09":
			pmNTU_09 = ConvertHelp.StringParseByte(a4);
			pmTemperaturewithNTU_09 = ConvertHelp.StringParseByte(a6);
			pmEC5SoildHumidtiy_09 = ConvertHelp.StringParseByte(a8);
			break;
		case "0A":
		case "0a":
			pm5TESoildHumidtiy_0a = ConvertHelp.StringParseByte(a4);
			pm5TESoildTemp_0a = ConvertHelp.StringParseByte(a6);
			pmWaterLevel_0a = ConvertHelp.StringParseByte(a8);
			break;
		case "0B":
		case "0b":
			pmTemperaturewithLDO_0b = ConvertHelp.StringParseByte(a4);
			pmLDODOValue_0b = ConvertHelp.StringParseByte(a6);
			pmLDOSatValue_0b = ConvertHelp.StringParseByte(a8);
			break;
		case "0C":
		case "0c":
			pmTemperature_0c = ConvertHelp.StringParseByte(a4);
			if (pmTemperature_0c.equalsIgnoreCase("FFFF")) {
				pmTemperature_0c = "0";
			} else {
				pmTemperature_0c = String.valueOf(Integer.parseInt(pmTemperature_0c, 16) * 0.01);
			}
			pmHumidity_0c = ConvertHelp.StringParseByte(a6);
			if (pmHumidity_0c.equalsIgnoreCase("FFFF")) {
				pmHumidity_0c = "0";
			} else {
				pmHumidity_0c = String.valueOf(Integer.parseInt(pmHumidity_0c, 16) * 0.01);
			}
			pmWindSpeed_0c = ConvertHelp.StringParseByte(a8);
			if (pmWindSpeed_0c.equalsIgnoreCase("FFFF")) {
				pmWindSpeed_0c = "0";
			} else {
				pmWindSpeed_0c = String.valueOf(Integer.parseInt(pmWindSpeed_0c, 16) * 0.01);
			}
			break;
		case "0D":
		case "0d":
			pmWindDirection_0d = ConvertHelp.StringParseByte(a4);
			pmAtomsphere_0d = ConvertHelp.StringParseByte(a6);
			break;
		}
		// 温度
		String TemperatureStr = ConvertHelp.StringParseByte(a1);
		double TemperatureInt = Integer.parseInt(TemperatureStr, 16) * 0.01;// 16进制转10进制
		// 光照
		String illuminanceStr = ConvertHelp.StringParseByte(a3);
		double illuminanceInt = Integer.parseInt(illuminanceStr, 16) * 0.01;// 16进制转10进制
		// 判断该条数据是否为报警数据
		if (lowbattery) {
			// 电量低报警
			type = "报警数据";
		}
		/*data.setType(type);
		data.setDeviceType(deviceType);
		data.setParseAfterMessage(parseAfterMessage + "温度：" + TemperatureInt + "；光照：" + illuminanceInt + "；PM2.5"
				+ pm25_02 + "；噪音" + pmnoise_07);
		return data;*/
	}
}
