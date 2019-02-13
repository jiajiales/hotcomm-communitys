package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnergyAllLeft implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6463432751138410649L;
	EnergyAllLeftOnUser energyAllLeftOnUser = new EnergyAllLeftOnUser();
	Map<String, List<EnergyAllLeftOnElectricity>> electricity = new HashMap<String, List<EnergyAllLeftOnElectricity>>();
	Map<String, List<EnergyAllLeftOnWater>> water = new HashMap<String, List<EnergyAllLeftOnWater>>();
	Map<String, List<EnergyAllLeftOnFlammable>> flammable = new HashMap<String, List<EnergyAllLeftOnFlammable>>();

	public EnergyAllLeft() {
		List<EnergyAllLeftOnElectricity> electricitySonr = new ArrayList<EnergyAllLeftOnElectricity>();
		List<EnergyAllLeftOnElectricity> electricitySonb = new ArrayList<EnergyAllLeftOnElectricity>();
		List<EnergyAllLeftOnElectricity> electricitySonc = new ArrayList<EnergyAllLeftOnElectricity>();
		List<EnergyAllLeftOnWater> waterSonr = new ArrayList<EnergyAllLeftOnWater>();
		List<EnergyAllLeftOnWater> waterSonb = new ArrayList<EnergyAllLeftOnWater>();
		List<EnergyAllLeftOnWater> waterSonc = new ArrayList<EnergyAllLeftOnWater>();
		List<EnergyAllLeftOnFlammable> flammableSonr = new ArrayList<EnergyAllLeftOnFlammable>();
		List<EnergyAllLeftOnFlammable> flammableSonb = new ArrayList<EnergyAllLeftOnFlammable>();
		List<EnergyAllLeftOnFlammable> flammableSonc = new ArrayList<EnergyAllLeftOnFlammable>();
		for (int i = 0; i < 12; i++) {// 设置月份

			EnergyAllLeftOnElectricity electricitySonsr = new EnergyAllLeftOnElectricity();
			electricitySonsr.setMonth(i + 1);
			electricitySonsr.setNum(electricitySonsr.getResidenceNum(i + 1));
			electricitySonr.add(electricitySonsr);
			EnergyAllLeftOnElectricity electricitySonsb = new EnergyAllLeftOnElectricity();
			electricitySonsb.setMonth(i + 1);
			electricitySonsb.setNum(electricitySonsb.getBusinessNum(i + 1));
			electricitySonb.add(electricitySonsb);
			EnergyAllLeftOnElectricity electricitySonsc = new EnergyAllLeftOnElectricity();
			electricitySonsc.setMonth(i + 1);
			electricitySonsc.setNum(electricitySonsc.getResidenceNum(i + 1) + electricitySonsc.getBusinessNum(i + 1));
			electricitySonc.add(electricitySonsc);

			EnergyAllLeftOnWater waterSonsr = new EnergyAllLeftOnWater();
			waterSonsr.setMonth(i + 1);
			waterSonsr.setNum(waterSonsr.getResidenceNum(i + 1));
			waterSonr.add(waterSonsr);
			EnergyAllLeftOnWater waterSonsb = new EnergyAllLeftOnWater();
			waterSonsb.setMonth(i + 1);
			waterSonsb.setNum(waterSonsb.getBusinessNum(i + 1));
			waterSonb.add(waterSonsb);
			EnergyAllLeftOnWater waterSonsc = new EnergyAllLeftOnWater();
			waterSonsc.setMonth(i + 1);
			waterSonsc.setNum(waterSonsc.getResidenceNum(i + 1) + waterSonsc.getBusinessNum(i + 1));
			waterSonc.add(waterSonsc);

			EnergyAllLeftOnFlammable flammableSonsr = new EnergyAllLeftOnFlammable();
			flammableSonsr.setMonth(i + 1);
			flammableSonsr.setNum(flammableSonsr.getResidenceNum(i + 1));
			flammableSonr.add(flammableSonsr);
			EnergyAllLeftOnFlammable flammableSonsb = new EnergyAllLeftOnFlammable();
			flammableSonsb.setMonth(i + 1);
			flammableSonsb.setNum(flammableSonsb.getBusinessNum(i + 1));
			flammableSonb.add(flammableSonsb);
			EnergyAllLeftOnFlammable flammableSonsc = new EnergyAllLeftOnFlammable();
			flammableSonsc.setMonth(i + 1);
			flammableSonsc.setNum(flammableSonsc.getResidenceNum(i + 1) + flammableSonsc.getBusinessNum(i + 1));
			flammableSonc.add(flammableSonsc);
		}
		electricity.put("electricitySonr", electricitySonr);
		electricity.put("electricitySonb", electricitySonb);
		electricity.put("electricitySonc", electricitySonc);
		water.put("waterSonr", waterSonr);
		water.put("waterSonb", waterSonb);
		water.put("waterSonc", waterSonc);
		flammable.put("flammableSonr", flammableSonr);
		flammable.put("flammableSonb", flammableSonb);
		flammable.put("flammableSonc", flammableSonc);
	}

	public EnergyAllLeftOnUser getEnergyAllLeftOnUser() {
		return energyAllLeftOnUser;
	}

	public void setEnergyAllLeftOnUser(EnergyAllLeftOnUser energyAllLeftOnUser) {
		this.energyAllLeftOnUser = energyAllLeftOnUser;
	}

	public Map<String, List<EnergyAllLeftOnElectricity>> getElectricity() {
		return electricity;
	}

	public void setElectricity(Map<String, List<EnergyAllLeftOnElectricity>> electricity) {
		this.electricity = electricity;
	}

	public Map<String, List<EnergyAllLeftOnWater>> getWater() {
		return water;
	}

	public void setWater(Map<String, List<EnergyAllLeftOnWater>> water) {
		this.water = water;
	}

	public Map<String, List<EnergyAllLeftOnFlammable>> getFlammable() {
		return flammable;
	}

	public void setFlammable(Map<String, List<EnergyAllLeftOnFlammable>> flammable) {
		this.flammable = flammable;
	}

}
