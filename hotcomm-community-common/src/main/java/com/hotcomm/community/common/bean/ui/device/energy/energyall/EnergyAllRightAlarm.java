package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.HashMap;
import java.util.Map;

public class EnergyAllRightAlarm {

	private Map<String, Integer> all = new HashMap<String, Integer>();
	private Map<String, Integer> Electricity = new HashMap<String, Integer>();
	private Map<String, Integer> Water = new HashMap<String, Integer>();
	private Map<String, Integer> Flammable = new HashMap<String, Integer>();

	public EnergyAllRightAlarm() {
		all.put("accomplish", 391);
		all.put("unfinished", 274);
		all.put("percentageComplete", 58);

		Electricity.put("accomplish", 126);
		Electricity.put("unfinished", 44);
		Electricity.put("percentageComplete", 74);

		Water.put("accomplish", 181);
		Water.put("unfinished", 38);
		Water.put("percentageComplete", 37);

		Flammable.put("accomplish", 84);
		Flammable.put("unfinished", 192);
		Flammable.put("percentageComplete", 30);
	}

	public Map<String, Integer> getAll() {
		return all;
	}

	public void setAll(Map<String, Integer> all) {
		this.all = all;
	}

	public Map<String, Integer> getElectricity() {
		return Electricity;
	}

	public void setElectricity(Map<String, Integer> electricity) {
		Electricity = electricity;
	}

	public Map<String, Integer> getWater() {
		return Water;
	}

	public void setWater(Map<String, Integer> water) {
		Water = water;
	}

	public Map<String, Integer> getFlammable() {
		return Flammable;
	}

	public void setFlammable(Map<String, Integer> flammable) {
		Flammable = flammable;
	}

}
