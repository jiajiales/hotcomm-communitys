package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.util.HashMap;
import java.util.Map;

public class EnergyAllRightAccomplish {

	private Map<String, Double> all = new HashMap<String, Double>();
	private Map<String, Double> Electricity = new HashMap<String, Double>();
	private Map<String, Double> Water = new HashMap<String, Double>();
	private Map<String, Double> Flammable = new HashMap<String, Double>();

	public EnergyAllRightAccomplish() {
		all.put("accomplish", 67.33);
		all.put("unfinished", 32.67);
		Electricity.put("accomplish", 56.0);
		Electricity.put("unfinished", 44.0);
		Water.put("accomplish", 62.0);
		Water.put("unfinished", 38.0);
		Flammable.put("accomplish", 84.0);
		Flammable.put("unfinished", 16.0);
	}

	public Map<String, Double> getAll() {
		return all;
	}

	public void setAll(Map<String, Double> all) {
		this.all = all;
	}

	public Map<String, Double> getElectricity() {
		return Electricity;
	}

	public void setElectricity(Map<String, Double> electricity) {
		Electricity = electricity;
	}

	public Map<String, Double> getWater() {
		return Water;
	}

	public void setWater(Map<String, Double> water) {
		Water = water;
	}

	public Map<String, Double> getFlammable() {
		return Flammable;
	}

	public void setFlammable(Map<String, Double> flammable) {
		Flammable = flammable;
	}

}
