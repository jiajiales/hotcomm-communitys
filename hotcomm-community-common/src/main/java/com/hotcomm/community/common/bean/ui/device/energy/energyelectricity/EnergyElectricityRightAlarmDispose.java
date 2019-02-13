package com.hotcomm.community.common.bean.ui.device.energy.energyelectricity;

import java.util.HashMap;
import java.util.Map;

public class EnergyElectricityRightAlarmDispose {
	private Integer num = 158;// 总数量
	private Map<String, Map<String, Integer>> eMap = new HashMap<String, Map<String, Integer>>();

	public EnergyElectricityRightAlarmDispose() {
		Map<String, Integer> list = new HashMap<String, Integer>();
		list.put("accomplish", 29);
		list.put("unfinished", 36);
		eMap.put("1级", list);

		list.put("accomplish", 17);
		list.put("unfinished", 28);
		eMap.put("2级", list);

		list.put("accomplish", 32);
		list.put("unfinished", 16);
		eMap.put("3级", list);
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Map<String, Map<String, Integer>> geteMap() {
		return eMap;
	}

	public void seteMap(Map<String, Map<String, Integer>> eMap) {
		this.eMap = eMap;
	}

}
