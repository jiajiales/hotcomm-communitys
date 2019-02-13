package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EnergyAirQualityRank {
	
	private List<Map<String, Object>> airQualityRankInfo;

	public EnergyAirQualityRank() {
		String[] rankNameInfo= {"A","B","C"};
		Integer[] rankValueInfo= {42,58,93};
		List<Map<String, Object>> info=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("rankNum", i+1);
			map.put("rankName", rankNameInfo[i]);
			map.put("rankValue", rankValueInfo[i]);
			info.add(map);
		}
		airQualityRankInfo=info;
	}

}
