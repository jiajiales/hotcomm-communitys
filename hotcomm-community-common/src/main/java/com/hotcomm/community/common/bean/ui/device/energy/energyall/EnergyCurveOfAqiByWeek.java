package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyCurveOfAqiByWeek {
	
	private List<Map<String, Object>> energyCurveOfAqiByHourInfo;

	public EnergyCurveOfAqiByWeek() {
		String[] timeInfo= {"周一","周二","周三","周四","周五","周六","周日"};
		Integer[] average= {45,55,55,75,42,100,90};
		Integer[] lastWeek= {153,146,158,160,135,152,150};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i <7; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("timeInfo", timeInfo[i]);
			map.put("average", average[i]);
			map.put("lastWeek", lastWeek[i]);
			info.add(map);
		}
		energyCurveOfAqiByHourInfo=info;
	}

}
