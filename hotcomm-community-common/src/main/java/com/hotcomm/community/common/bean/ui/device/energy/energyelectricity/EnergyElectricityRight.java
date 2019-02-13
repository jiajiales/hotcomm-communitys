package com.hotcomm.community.common.bean.ui.device.energy.energyelectricity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotcomm.framework.utils.RandomTime;

public class EnergyElectricityRight {
	private Map<String, Double> all = new HashMap<String, Double>();
	private Map<Integer, List<EnergyElectricityRightAlarm>> eMap = new HashMap<Integer, List<EnergyElectricityRightAlarm>>();
	private EnergyElectricityRightAlarmDispose electricityRightAlarmDispose = new EnergyElectricityRightAlarmDispose();

	public EnergyElectricityRight() {
		// all
		all.put("accomplish", 67.33);
		all.put("unfinished", 32.67);

		// eMap
		List<EnergyElectricityRightAlarm> eMapr = new ArrayList<EnergyElectricityRightAlarm>();
		List<EnergyElectricityRightAlarm> eMapb = new ArrayList<EnergyElectricityRightAlarm>();
		List<EnergyElectricityRightAlarm> eMapc = new ArrayList<EnergyElectricityRightAlarm>();
		for (int i = 0; i < 12; i++) {// 设置月份
			EnergyElectricityRightAlarm eMaprs = new EnergyElectricityRightAlarm();
			eMaprs.setMonth(i + 1);
			eMaprs.setNum(eMaprs.getAlarmNumr(i + 1));
			eMapr.add(eMaprs);
			eMaprs.setNum(eMaprs.getAlarmNumb(i + 1));
			eMapb.add(eMaprs);
			eMaprs.setNum(eMaprs.getAlarmNumc(i + 1));
			eMapc.add(eMaprs);
		}
		int nowYear = RandomTime.nowYear();
		eMap.put(nowYear, eMapr);
		eMap.put(nowYear - 1, eMapb);
		eMap.put(nowYear - 2, eMapc);
	}

	public Map<String, Double> getAll() {
		return all;
	}

	public void setAll(Map<String, Double> all) {
		this.all = all;
	}

	public Map<Integer, List<EnergyElectricityRightAlarm>> geteMap() {
		return eMap;
	}

	public void seteMap(Map<Integer, List<EnergyElectricityRightAlarm>> eMap) {
		this.eMap = eMap;
	}

	public EnergyElectricityRightAlarmDispose getElectricityRightAlarmDispose() {
		return electricityRightAlarmDispose;
	}

	public void setElectricityRightAlarmDispose(EnergyElectricityRightAlarmDispose electricityRightAlarmDispose) {
		this.electricityRightAlarmDispose = electricityRightAlarmDispose;
	}

}
