package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnergyAllMiddle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7190901499951562700L;
	private List<EnergyAllMiddleDevice> list = new ArrayList<EnergyAllMiddleDevice>();
	private Integer electricity = 52;
	private Integer water = 33;
	private Integer flammable = 51;

	public EnergyAllMiddle(String name, Double lat, Double lon) {

		for (int i = 0; i < 2; i++) {
			Double lats = lat;
			Double lons = lon;
			EnergyAllMiddleDevice eson = new EnergyAllMiddleDevice();
			eson.setAlarmName("用电异常");
			eson.setNum(2);
			eson.setLevel(3);

			eson.setAlarmState(1);
			eson.setCode(name + (i + 2) + "栋" + (i + 2) + "楼");
			eson.setDevNum("DB_00" + (i + 2));
			eson.setDevType("智能电表");
			eson.setInstallTime("2018-12-11 13:32:1" + (i + 5));
			switch (i) {
			case 0:
				lats -= 0.0001;
				lons -= 0.0001;
				break;
			case 1:
				lats += 0.0001;
				lons += 0.0001;
				break;
			case 2:
				lats += 0.0001;
				lons -= 0.0001;
				break;
			default:
				break;
			}
			eson.setLat(lats);
			eson.setLon(lons);
			eson.setModel("电表");
			eson.setModelid(7);
			list.add(eson);
		}

		for (int i = 0; i < 2; i++) {
			Double lats = lat;
			Double lons = lon;
			EnergyAllMiddleDevice eson = new EnergyAllMiddleDevice();

			eson.setAlarmState(0);
			eson.setCode(name + (i + 1) + "栋" + (i + 2) + "楼");
			eson.setDevNum("SB_00" + (i + 2));
			eson.setDevType("智能水表");
			eson.setInstallTime("2018-12-11 14:00:0" + (i + 5));
			switch (i) {
			case 0:
				lats += 0.00012;
				lons -= 0.00012;
				break;
			case 1:
				lats -= 0.00012;
				lons += 0.00012;
				break;
			case 2:
				lons += 0.00012;
				break;
			default:
				break;
			}
			eson.setLat(lats);
			eson.setLon(lons);
			eson.setModel("水表");
			eson.setModelid(8);
			list.add(eson);
		}

		for (int i = 0; i < 1; i++) {
			Double lats = lat;
			Double lons = lon;
			EnergyAllMiddleDevice eson = new EnergyAllMiddleDevice();

			eson.setAlarmState(0);
			eson.setCode(name + (i + 3) + "栋" + (i + 2) + "楼");
			eson.setDevNum("KRQB_00" + (i + 2));
			eson.setDevType("智能可燃气表");
			eson.setInstallTime("2018-12-11 14:30:5" + (i + 5));
			switch (i) {
			case 0:
				lats += 0.00014;
				break;
			case 1:
				lats -= 0.00014;
				lons += 0.00014;
				break;
			default:
				break;
			}
			eson.setLat(lats);
			eson.setLon(lons);
			eson.setModel("可燃气表");
			eson.setModelid(9);
			list.add(eson);
		}
	}

	public List<EnergyAllMiddleDevice> getList() {
		return list;
	}

	public void setList(List<EnergyAllMiddleDevice> list) {
		this.list = list;
	}

	public Integer getElectricity() {
		return electricity;
	}

	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getFlammable() {
		return flammable;
	}

	public void setFlammable(Integer flammable) {
		this.flammable = flammable;
	}

}
