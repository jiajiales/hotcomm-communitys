package com.hotcomm.community.common.bean.db.device;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.ui.device.Device_GzUM;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceAlarmGzDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3273712435180595365L;
	private Integer id;
	private Integer moduleId;// 模块ID
	private String alarmMac;// mac地址
	private String alarmTypeName;// 报警名称
	private Integer alarmLevel;// 级别
	private String alarmVaule;// 报警值
	private Integer alarmMiddle;// 间隔时间
	private String innerUserid;// 相关人员
	private String informUserid;// 通知人员
	private Integer useType;// 0 系统 1 自定义
	private Integer isOpen;// 是否开启

	private Map<String, Integer> alarmVauleMap;
	private List<Integer> innerUseridMap;
	private List<Integer> informUseridMap;

	public void setinnerUseridMap(List<Integer> innerUseridMap) {
		this.innerUseridMap = innerUseridMap;
	}

	public void setinformUseridMap(List<Integer> informUseridMap) {
		this.informUseridMap = informUseridMap;
	}

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
		if (device_GzPA.getTime() != null)
			map.put("time", device_GzPA.getTime());
		setAlarmVauleMap(map);
		this.alarmVaule = alarmVaule;
	}

	public void setInnerUserid(String inner_userid) {
		Gson gson = new Gson();
		Device_GzUM device_GzPA = gson.fromJson(inner_userid, Device_GzUM.class);

		setinnerUseridMap(device_GzPA.getUserid());
		this.innerUserid = inner_userid;
	}

	public void setInformUserid(String inform_userid) {
		Gson gson = new Gson();
		Device_GzUM device_GzPA = gson.fromJson(inform_userid, Device_GzUM.class);

		setinformUseridMap(device_GzPA.getUserid());
		this.informUserid = inform_userid;
	}

	public void setAlarmVauleMap(Map<String, Integer> alarm_vaule_map) {
		this.alarmVauleMap = alarm_vaule_map;
	}

}
