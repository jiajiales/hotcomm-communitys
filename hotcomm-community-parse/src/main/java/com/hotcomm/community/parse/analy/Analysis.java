package com.hotcomm.community.parse.analy;

import com.hotcomm.community.common.bean.db.parse.ReceiveModel;

public class Analysis {
	public static Integer GetBelongFun(ReceiveModel data) {
		int moduleid = 0;
		try {
			String mac = data.getMacAddr().substring(8, 10);
			switch (mac) {
			case "22":
			case "0a":
			case "0A":
				// 烟感
				moduleid = 1;
				break;
			case "29":
				// 可燃气
				moduleid = 2;
				break;
			case "39":
				// 三相电流
				moduleid = 4;
				break;
			case "41":
			case "45":
				// 水压
				moduleid = 3;
				break;
			}
			return moduleid;
		} catch (Exception e) {
			e.printStackTrace();
			return moduleid;
		}
	}
}
