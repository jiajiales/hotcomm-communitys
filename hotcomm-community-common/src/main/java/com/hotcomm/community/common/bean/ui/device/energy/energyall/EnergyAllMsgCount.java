package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EnergyAllMsgCount {
	List<EnergyAllMsgCountAll> Electricity = new ArrayList<EnergyAllMsgCountAll>();
	List<EnergyAllMsgCountAll> Water = new ArrayList<EnergyAllMsgCountAll>();
	List<EnergyAllMsgCountAll> Flammable = new ArrayList<EnergyAllMsgCountAll>();

	public EnergyAllMsgCount() throws Throwable {
		List<String> days = new ArrayList<String>();

		List<String> dates = getDatePeriod(new Date(), 7);
		for (int i = 0; i < dates.size(); i++)
			days.add(dayForWeek(dates.get(i)));

		List<EnergyAllMsgCountAll> s = new ArrayList<EnergyAllMsgCountAll>();
		for (int j = 0; j < days.size(); j++) {
			EnergyAllMsgCountAll sson = new EnergyAllMsgCountAll();
			sson.setWeek(days.get(j));
			s.add(sson);
		}
		setElectricity(s);
		setWater(s);
		setFlammable(s);
	}

	public List<EnergyAllMsgCountAll> getElectricity() {
		return Electricity;
	}

	public void setElectricity(List<EnergyAllMsgCountAll> electricity) {
		for (int i = 0; i < electricity.size(); i++) {
			switch (i) {
			case 0:
				electricity.get(i).setAlarmNum(1325);
				break;
			case 1:
				electricity.get(i).setAlarmNum(1465);
				break;
			case 2:
				electricity.get(i).setAlarmNum(1425);
				break;
			case 3:
				electricity.get(i).setAlarmNum(1599);
				break;
			case 4:
				electricity.get(i).setAlarmNum(2000);
				break;
			case 5:
				electricity.get(i).setAlarmNum(1915);
				break;
			case 6:
				electricity.get(i).setAlarmNum(1453);
				break;
			default:
				break;
			}
		}
		Electricity = electricity;
	}

	public List<EnergyAllMsgCountAll> getWater() {
		return Water;
	}

	public void setWater(List<EnergyAllMsgCountAll> water) {
		for (int i = 0; i < water.size(); i++) {
			switch (i) {
			case 0:
				water.get(i).setAlarmNum(965);
				break;
			case 1:
				water.get(i).setAlarmNum(725);
				break;
			case 2:
				water.get(i).setAlarmNum(1227);
				break;
			case 3:
				water.get(i).setAlarmNum(1757);
				break;
			case 4:
				water.get(i).setAlarmNum(1242);
				break;
			case 5:
				water.get(i).setAlarmNum(1572);
				break;
			case 6:
				water.get(i).setAlarmNum(1728);
				break;
			default:
				break;
			}
		}
		Water = water;
	}

	public List<EnergyAllMsgCountAll> getFlammable() {
		return Flammable;
	}

	public void setFlammable(List<EnergyAllMsgCountAll> flammable) {
		for (int i = 0; i < flammable.size(); i++) {
			switch (i) {
			case 0:
				flammable.get(i).setAlarmNum(1278);
				break;
			case 1:
				flammable.get(i).setAlarmNum(1182);
				break;
			case 2:
				flammable.get(i).setAlarmNum(1257);
				break;
			case 3:
				flammable.get(i).setAlarmNum(1127);
				break;
			case 4:
				flammable.get(i).setAlarmNum(1452);
				break;
			case 5:
				flammable.get(i).setAlarmNum(1527);
				break;
			case 6:
				flammable.get(i).setAlarmNum(1450);
				break;
			default:
				break;
			}
		}
		Flammable = flammable;
	}

	public static String dayForWeek(String pTime) throws Throwable {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = format.parse(pTime);
		Calendar cal = Calendar.getInstance();
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		try {
			cal.setTime(tmpDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static List<String> getDatePeriod(Date date, int beforeDays) {
		List<String> DateListUM = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		for (int i = beforeDays - 1; i >= 0; i--) {
			cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - i);
			DateListUM.add(df.format(cal.getTime()));
		}
		return DateListUM;
	}
}
