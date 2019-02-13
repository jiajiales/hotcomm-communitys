package com.hotcomm.community.common.bean.db.parse;

public class DeviceAlarmEN {
	public enum DeviceAlarmState {
		YGYANWU("1烟雾报警", 1), YGGUZHANG("1故障报警", 2), YGDIDIANYA("1低电压报警", 3), YGWENDU("1温度报警", 24), YGWENDUYICHANG("1温度波动异常", 24), // 烟感
		KRQXIELOU("2气体泄漏", 4), KRQGUZHANG("2故障报警", 5), KRQDIDIANYA("2低电压报警", 6), // 可燃气
		KRQXIELOUFANGCHAI("2气体泄漏和防拆同时报警", 7), KRQFANGCHAI("2防拆报警", 8), // 可燃气
		SYQINGXIE("3设备倾斜报警", 9), SYBEIDAO("3设备被盗报警", 10), SYYALIYICHANG("3压力异常报警", 11), SYDIDIANYA("3低电压报警", 12), // 水压
		SYGUZHANG("3故障报警", 13), // 水压
		SXDLDIANLIUYICHANG("4电流异常报警", 14), SXDLDIDIANYA("4低电压报警", 15), // 三相电流
		YGOFFLINE("1离线报警", 20), KRQOFFLINE("2离线报警", 21), SYOFFLINE("3离线报警", 22), SXDLOFFLINE("4离线报警", 23);
		private String name;
		private Integer id;

		private DeviceAlarmState(String name, Integer id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public Integer getId() {
			return id;
		}

		public static DeviceAlarmState getByName(String name) {
			for (DeviceAlarmState obj : DeviceAlarmState.values()) {
				if (obj.getName().equals(name)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum DeviceAlarmGz {
		YGYANWU(1, "烟雾报警"), YGGUZHANG(2, ""), YGDIDIANYA(3, "电池电量低"), // 烟感
		KRQXIELOU(4, "可燃气报警"), KRQGUZHANG(5, ""), KRQDIDIANYA(6, "电池电量低"), // 可燃气
		KRQXIELOUFANGCHAI(7, "可燃气报警"), KRQFANGCHAI(8, "防拆报警"), // 可燃气
		SYQINGXIE(9, "倾斜报警"), SYBEIDAO(10, "防偷报警"), SYYALIYICHANG(11, "压力异常报警"), SYDIDIANYA(12, "电池电量低"), // 水压
		SYGUZHANG(13, ""), // 水压
		SXDLDIANLIUYICHANG(14, "电流异常报警"), SXDLDIDIANYA(15, "电池电量低");// 三相电流
		private Integer id;
		private String name;

		private DeviceAlarmGz(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public static DeviceAlarmGz getById(Integer id) {
			for (DeviceAlarmGz obj : DeviceAlarmGz.values()) {
				if (obj.getId() == (id)) {
					return obj;
				}
			}
			return null;
		}
	}
}