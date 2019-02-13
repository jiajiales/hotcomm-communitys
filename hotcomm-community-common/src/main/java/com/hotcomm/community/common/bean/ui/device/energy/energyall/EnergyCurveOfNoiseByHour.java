package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyCurveOfNoiseByHour {
	
	private List<Map<String, Object>> energyCurveOfNoiceByHourInfo;

	public EnergyCurveOfNoiseByHour() {
		String[] timeInfo= {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
		Integer[] noiceA= {23,25,26,23,38,38,40,40,41,39,42,42,40,41,42,43,45,46,45,40,30,20,5,5};
		Integer[] noiceB= {42,39,42,49,58,62,75,78,79,81,81,95,100,98,96,96,83,83,85,85,65,61,60,48};
		Integer[] noiceC= {53,59,59,59,66,67,66,67,67,70,77,80,81,83,81,75,66,66,68,70,79,60,55,40};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 24; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("timeInfo", timeInfo[i]);
			map.put("noiceA", noiceA[i]);
			map.put("noiceB", noiceB[i]);
			map.put("noiceC", noiceC[i]);
			info.add(map);
		}
		energyCurveOfNoiceByHourInfo=info;
	}

}
