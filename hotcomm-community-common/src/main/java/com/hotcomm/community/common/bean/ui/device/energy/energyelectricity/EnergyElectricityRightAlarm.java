package com.hotcomm.community.common.bean.ui.device.energy.energyelectricity;

public class EnergyElectricityRightAlarm {
	private Integer month;// 月份
	private Integer num;// 数量

	public Integer getAlarmNumr(Integer month) {
		Integer yearNum = 1500;
		switch (month) {
		case 1:
			yearNum -= 457;
			break;
		case 2:
			yearNum -= 425;
			break;
		case 3:
			yearNum -= 225;
			break;
		case 4:
			yearNum += 300;
			break;
		case 5:
			yearNum += 250;
			break;
		case 6:
			yearNum += 305;
			break;
		case 7:
			yearNum += 427;
			break;
		case 8:
			yearNum += 325;
			break;
		case 9:
			yearNum += 200;
			break;
		case 10:
			yearNum += 525;
			break;
		case 11:
			yearNum -= 311;
			break;
		case 12:
			yearNum -= 302;
			break;
		default:
			break;
		}
		return yearNum;
	}

	public Integer getAlarmNumb(Integer month) {
		Integer yearNum = 1500;
		switch (month) {
		case 1:
			yearNum -= 432;
			break;
		case 2:
			yearNum -= 514;
			break;
		case 3:
			yearNum -= 354;
			break;
		case 4:
			yearNum += 254;
			break;
		case 5:
			yearNum += 351;
			break;
		case 6:
			yearNum += 416;
			break;
		case 7:
			yearNum += 372;
			break;
		case 8:
			yearNum += 215;
			break;
		case 9:
			yearNum += 420;
			break;
		case 10:
			yearNum += 463;
			break;
		case 11:
			yearNum -= 232;
			break;
		case 12:
			yearNum -= 472;
			break;
		default:
			break;
		}
		return yearNum;
	}

	public Integer getAlarmNumc(Integer month) {
		Integer yearNum = 1500;
		switch (month) {
		case 1:
			yearNum -= 201;
			break;
		case 2:
			yearNum -= 488;
			break;
		case 3:
			yearNum -= 322;
			break;
		case 4:
			yearNum += 425;
			break;
		case 5:
			yearNum += 257;
			break;
		case 6:
			yearNum += 228;
			break;
		case 7:
			yearNum += 306;
			break;
		case 8:
			yearNum += 311;
			break;
		case 9:
			yearNum += 402;
			break;
		case 10:
			yearNum += 541;
			break;
		case 11:
			yearNum -= 355;
			break;
		case 12:
			yearNum -= 254;
			break;
		default:
			break;
		}
		return yearNum;
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
