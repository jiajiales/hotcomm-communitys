package com.hotcomm.community.common.bean.ui.device.energy.energyelectricity;

public class EnergyElectricityLeftAgingRate {

	private Integer year;// 年份
	private Integer num;// 数量

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getYearNum(Integer Year) {
		Integer yearNum = 300;
		switch (Year) {
		case 1:
			yearNum -= 56;
			break;
		case 2:
			yearNum -= 33;
			break;
		case 3:
			yearNum += 77;
			break;
		case 4:
			yearNum += 0;
			break;
		case 5:
			yearNum += 31;
			break;
		case 6:
			yearNum += 52;
			break;
		case 7:
			yearNum -= 72;
			break;
		default:
			break;
		}
		return yearNum;
	}
}
