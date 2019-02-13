package com.hotcomm.community.common.bean.ui.device.energy.energyelectricity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllLeftOnElectricity;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllLeftOnUser;

public class EnergyElectricityLeft {
	EnergyAllLeftOnUser energyAllLeftOnUser = new EnergyAllLeftOnUser();
	Map<String, List<EnergyAllLeftOnElectricity>> electricity = new HashMap<String, List<EnergyAllLeftOnElectricity>>();
	EnergyElectricityLeftCount electricityLeftCount = new EnergyElectricityLeftCount();
	List<EnergyElectricityLeftAgingRate> electricityLeftAgingRate = new ArrayList<EnergyElectricityLeftAgingRate>();

	public EnergyElectricityLeft() {
		List<EnergyAllLeftOnElectricity> electricitySonr = new ArrayList<EnergyAllLeftOnElectricity>();
		List<EnergyAllLeftOnElectricity> electricitySonb = new ArrayList<EnergyAllLeftOnElectricity>();
		List<EnergyAllLeftOnElectricity> electricitySonc = new ArrayList<EnergyAllLeftOnElectricity>();
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
		}
		electricity.put("electricitySonr", electricitySonr);
		electricity.put("electricitySonb", electricitySonb);
		electricity.put("electricitySonc", electricitySonc);

		for (int i = 1; i <= 7; i++) {
			EnergyElectricityLeftAgingRate e = new EnergyElectricityLeftAgingRate();
			e.setYear(i);
			e.setNum(e.getYearNum(i));
			electricityLeftAgingRate.add(e);
		}
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

	public EnergyElectricityLeftCount getElectricityLeftCount() {
		return electricityLeftCount;
	}

	public void setElectricityLeftCount(EnergyElectricityLeftCount electricityLeftCount) {
		this.electricityLeftCount = electricityLeftCount;
	}

	public List<EnergyElectricityLeftAgingRate> getElectricityLeftAgingRate() {
		return electricityLeftAgingRate;
	}

	public void setElectricityLeftAgingRate(List<EnergyElectricityLeftAgingRate> electricityLeftAgingRate) {
		this.electricityLeftAgingRate = electricityLeftAgingRate;
	}

}
