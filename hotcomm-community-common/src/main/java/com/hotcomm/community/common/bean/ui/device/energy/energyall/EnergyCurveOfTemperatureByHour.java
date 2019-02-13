package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyCurveOfTemperatureByHour {
	
	private List<Map<String, Object>> energyCurveOfTemperatureByHourInfo;

	public EnergyCurveOfTemperatureByHour() {
		String[] timeInfo= {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
		Integer[] temperatureInfoA= {11,11,11,11,10,10,12,12,13,14,15,16,17,19,20,21,17,16,15,12,12,12,12,12};
		Integer[] temperatureInfoB= {12,12,12,12,12,12,12,13,14,15,16,20,20,19,17,17,16,16,16,15,15,14,14,13};
		Integer[] temperatureInfoC= {11,12,13,13,14,14,14,15,15,16,17,18,19,22,20,19,16,15,15,15,15,14,14,12};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 24; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("timeInfo", timeInfo[i]);
			map.put("temperatureInfoA", temperatureInfoA[i]);
			map.put("temperatureInfoB", temperatureInfoB[i]);
			map.put("temperatureInfoC", temperatureInfoC[i]);
			info.add(map);
		}
		energyCurveOfTemperatureByHourInfo=info;
	}

}
