package com.hotcomm.community.common.bean.ui.device.energy.energyall;

public class EnergyAllLeftOnElectricity {
	private Integer month;// 月份
	private Integer num;// 用水量

	public Integer getResidenceNum(Integer month) {
		Integer num = 3000;
		switch (month) {
		case 1:
			num -= 827;
			break;
		case 2:
			num -= 927;
			break;
		case 3:
			num -= 672;
			break;
		case 4:
			num += 420;
			break;
		case 5:
			num += 655;
			break;
		case 6:
			num += 804;
			break;
		case 7:
			num += 601;
			break;
		case 8:
			num += 550;
			break;
		case 9:
			num += 727;
			break;
		case 10:
			num += 877;
			break;
		case 11:
			num -= 587;
			break;
		case 12:
			num -= 708;
			break;
		default:
			break;
		}
		return num;
	}

	public Integer getBusinessNum(Integer month) {
		Integer num = 2000;
		switch (month) {
		case 1:
			num -= 952;
			break;
		case 2:
			num -= 825;
			break;
		case 3:
			num -= 427;
			break;
		case 4:
			num += 604;
			break;
		case 5:
			num += 555;
			break;
		case 6:
			num += 725;
			break;
		case 7:
			num += 842;
			break;
		case 8:
			num += 642;
			break;
		case 9:
			num += 522;
			break;
		case 10:
			num += 1007;
			break;
		case 11:
			num -= 725;
			break;
		case 12:
			num -= 724;
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
