package com.hotcomm.community.common.bean.db.parse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.ui.device.Device_GzUM;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmGz implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4724693673751807756L;
	private Integer id;
	private String alarmVaule;
	private Integer isOpen;
	private String innerUserid;
	private String informUserid;
	private Map<String, Integer> alarmVauleMap;

	public void setAlarmVaule(String alarmVaule) {
		Gson gson = new Gson();
		Device_GzUM device_GzPA = gson.fromJson(alarmVaule, Device_GzUM.class);
		Map<String, Integer> map = new HashMap<>();
		if (device_GzPA.getOffline() != null)
			map.put("offline", device_GzPA.getOffline());
		if (device_GzPA.getBattery() != null)
			map.put("battery", device_GzPA.getBattery());
		if (device_GzPA.getDlmax() != null)
			map.put("dlmax", device_GzPA.getDlmax());
		if (device_GzPA.getDlmin() != null)
			map.put("dlmin", device_GzPA.getDlmin());
		if (device_GzPA.getSymax() != null)
			map.put("symax", device_GzPA.getSymax());
		if (device_GzPA.getSymin() != null)
			map.put("symin", device_GzPA.getSymin());
		setAlarmVauleMap(map);
		this.alarmVaule = alarmVaule;
	}

	public void setAlarmVauleMap(Map<String, Integer> alarmVauleMap) {
		this.alarmVauleMap = alarmVauleMap;
	}
}
