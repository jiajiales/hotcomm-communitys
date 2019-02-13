package com.hotcomm.community.common.bean.ui.device.energy.energyall;

public class EnergyAllLeftOnWater {
	private Integer month;// 月份
	private Integer num;// 用水量

	public Integer getResidenceNum(Integer month) {
		Integer num = 300;
		switch (month) {
		case 1:
			num -= 80;
			break;
		case 2:
			num -= 90;
			break;
		case 3:
			num -= 60;
			break;
		case 4:
			num += 40;
			break;
		case 5:
			num += 60;
			break;
		case 6:
			num += 80;
			break;
		case 7:
			num += 60;
			break;
		case 8:
			num += 55;
			break;
		case 9:
			num += 70;
			break;
		case 10:
			num += 80;
			break;
		case 11:
			num -= 50;
			break;
		case 12:
			num -= 70;
			break;
		default:
			break;
		}
		return num;
	}

	public Integer getBusinessNum(Integer month) {
		Integer num = 200;
		switch (month) {
		case 1:
			num -= 90;
			break;
		case 2:
			num -= 80;
			break;
		case 3:
			num -= 40;
			break;
		case 4:
			num += 60;
			break;
		case 5:
			num += 55;
			break;
		case 6:
			num += 70;
			break;
		case 7:
			num += 80;
			break;
		case 8:
			num += 60;
			break;
		case 9:
			num += 50;
			break;
		case 10:
			num += 100;
			break;
		case 11:
			num -= 70;
			break;
		case 12:
			num -= 70;
			break;
		default:
			break;
		}
		return num;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
