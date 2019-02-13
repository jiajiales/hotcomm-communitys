package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyCurveOfHumidityByHour {
	
	private List<Map<String, Object>> energyCurveOfHumidityByHourInfo;

	public EnergyCurveOfHumidityByHour() {
		String[] timeInfo= {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
		Integer[] humidityInfoA= {60,60,60,60,59,58,57,58,59,53,56,55,54,55,56,55,58,58,59,59,60,61,62,64};
		Integer[] humidityInfoB= {30,40,50,60,59,59,60,59,60,62,50,38,50,53,53,54,56,59,57,53,52,51,40,28};
		Integer[] humidityInfoC= {42,40,39,39,40,40,40,39,38,35,30,35,38,40,36,32,26,26,27,30,35,36,37,40};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 24; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("timeInfo", timeInfo[i]);
			map.put("humidityInfoA", humidityInfoA[i]);
			map.put("humidityInfoB", humidityInfoB[i]);
			map.put("humidityInfoC", humidityInfoC[i]);
			info.add(map);
		}
		energyCurveOfHumidityByHourInfo=info;
	}

}
