package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyCurveOfPmByHour {
	
	private List<Map<String, Object>> energyCurveOfPmByHourInfo;

	public EnergyCurveOfPmByHour() {
		String[] timeInfo= {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
		Integer[] countInfoA= {22,31,41,42,42,39,26,23,21,27,26,25,23,23,39,45,60,23,21,20,20,23,22,21};
		Integer[] countInfoB= {22,40,46,85,84,76,60,42,38,35,50,70,80,78,75,60,56,50,43,48,50,48,45,30};
		Integer[] countInfoC= {37,43,51,62,78,80,83,76,62,50,50,79,83,79,67,55,48,43,49,59,63,60,45,29};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 24; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("timeInfo", timeInfo[i]);
			map.put("countInfoA", countInfoA[i]);
			map.put("countInfoB", countInfoB[i]);
			map.put("countInfoC", countInfoC[i]);
			info.add(map);
		}
		energyCurveOfPmByHourInfo=info;
	}

}
