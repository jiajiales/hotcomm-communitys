package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hotcomm.framework.utils.RandomTime;

public class EnergyAllRight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6835701243172544143L;
	private EnergyAllRightAccomplish energyAllRightAccomplish = new EnergyAllRightAccomplish();
	private EnergyAllRightAlarm energyAllRightAlarm = new EnergyAllRightAlarm();
	private List<EnergyAllRightRealTimeAlarm> energyAllRightRealTimeAlarms = new ArrayList<EnergyAllRightRealTimeAlarm>();

	public EnergyAllRight(String name) {// name小区名字
		// 电表3条
		for (int i = 1; i <= 3; i++) {
			EnergyAllRightRealTimeAlarm eson = new EnergyAllRightRealTimeAlarm();
			eson.setModelid(7);
			eson.setModel("电表");
			eson.setDevNum("DB_00" + (i + 2));
			eson.setLevel(3);
			eson.setAlarmName("用电异常");
			eson.setCode(name + i + "栋" + (i + 2) + "楼");
			eson.setAlarmTime(RandomTime.randomDate(RandomTime.getPastDate(7), RandomTime.nowTime()));
			energyAllRightRealTimeAlarms.add(eson);
		} // 水表2条
		for (int i = 1; i < 2; i++) {
			EnergyAllRightRealTimeAlarm eson = new EnergyAllRightRealTimeAlarm();
			eson.setModelid(8);
			eson.setModel("水表");
			eson.setDevNum("SB_00" + (i + 3));
			switch (i) {
			case 1:
				eson.setAlarmName("电池缺点");
				eson.setLevel(i + 1);
				break;
			case 2:
				eson.setAlarmName("用水异常");
				eson.setLevel(i + 3);
				break;
			default:
				break;
			}
			eson.setCode(name + (i + 1) + "栋" + (i + 1) + "楼");
			eson.setAlarmTime(RandomTime.randomDate(RandomTime.getPastDate(7), RandomTime.nowTime()));
			energyAllRightRealTimeAlarms.add(eson);
		} // 可燃气表3条
		for (int i = 1; i <= 3; i++) {
			EnergyAllRightRealTimeAlarm eson = new EnergyAllRightRealTimeAlarm();
			eson.setModelid(9);
			eson.setModel("可燃气表");
			eson.setDevNum("KRQB_00" + (i + 1));
			switch (i) {
			case 1:
				eson.setAlarmName("用气异常");
				eson.setLevel(i + 2);
				break;
			case 2:
			case 3:
				eson.setAlarmName("失联时间超过72h");
				eson.setLevel(i + 1);
				break;
			default:
				break;
			}
			eson.setCode(name + (i + 2) + "栋" + i + "楼");
			eson.setAlarmTime(RandomTime.randomDate(RandomTime.getPastDate(7), RandomTime.nowTime()));
			energyAllRightRealTimeAlarms.add(eson);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 数据排序
		int j, k;
		int flag = energyAllRightRealTimeAlarms.size();// flag来记录最后交换的位置，也就是排序的尾边界
		while (flag > 0) {// 排序未结束标志
			k = flag; // k 来记录遍历的尾边界
			flag = 0;
			for (j = 1; j < k; j++) {
				try {
					if (sdf.parse(energyAllRightRealTimeAlarms.get(j).getAlarmTime()).getTime() > sdf
							.parse(energyAllRightRealTimeAlarms.get(j - 1).getAlarmTime()).getTime()) {// 前面的数字大于后面的数字就交换
						// 交换a[j-1]和a[j]
						EnergyAllRightRealTimeAlarm temp;
						temp = energyAllRightRealTimeAlarms.get(j - 1);
						energyAllRightRealTimeAlarms.set(j - 1, energyAllRightRealTimeAlarms.get(j));
						energyAllRightRealTimeAlarms.set(j, temp);
						// 表示交换过数据;
						flag = j;// 记录最新的尾边界.
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		// 排序结束
	}

	public EnergyAllRightAccomplish getEnergyAllRightAccomplish() {
		return energyAllRightAccomplish;
	}

	public void setEnergyAllRightAccomplish(EnergyAllRightAccomplish energyAllRightAccomplish) {
		this.energyAllRightAccomplish = energyAllRightAccomplish;
	}

	public EnergyAllRightAlarm getEnergyAllRightAlarm() {
		return energyAllRightAlarm;
	}

	public void setEnergyAllRightAlarm(EnergyAllRightAlarm energyAllRightAlarm) {
		this.energyAllRightAlarm = energyAllRightAlarm;
	}

	public List<EnergyAllRightRealTimeAlarm> getEnergyAllRightRealTimeAlarms() {
		return energyAllRightRealTimeAlarms;
	}

	public void setEnergyAllRightRealTimeAlarms(List<EnergyAllRightRealTimeAlarm> energyAllRightRealTimeAlarms) {
		this.energyAllRightRealTimeAlarms = energyAllRightRealTimeAlarms;
	}
}
