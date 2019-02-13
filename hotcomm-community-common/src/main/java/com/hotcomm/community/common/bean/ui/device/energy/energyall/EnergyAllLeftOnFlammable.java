package com.hotcomm.community.common.bean.ui.device.energy.energyall;

public class EnergyAllLeftOnFlammable {
	private Integer month;// 月份
	private Integer num;// 用水量

	public Integer getResidenceNum(Integer month) {
		Integer num = 2500;
		switch (month) {
		case 1:
			num -= 800;
			break;
		case 2:
			num -= 900;
			break;
		case 3:
			num -= 600;
			break;
		case 4:
			num += 400;
			break;
		case 5:
			num += 600;
			break;
		case 6:
			num += 800;
			break;
		case 7:
			num += 600;
			break;
		case 8:
			num += 550;
			break;
		case 9:
			num += 700;
			break;
		case 10:
			num += 800;
			break;
		case 11:
			num -= 500;
			break;
		case 12:
			num -= 700;
			break;
		default:
			break;
		}
		return num;
	}

	public Integer getBusinessNum(Integer month) {
		Integer num = 2500;
		switch (month) {
		case 1:
			num -= 900;
			break;
		case 2:
			num -= 800;
			break;
		case 3:
			num -= 400;
			break;
		case 4:
			num += 600;
			break;
		case 5:
			num += 550;
			break;
		case 6:
			num += 700;
			break;
		case 7:
			num += 800;
			break;
		case 8:
			num += 600;
			break;
		case 9:
			num += 500;
			break;
		case 10:
			num += 1000;
			break;
		case 11:
			num -= 700;
			break;
		case 12:
			num -= 700;
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
