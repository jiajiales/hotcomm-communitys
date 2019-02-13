package com.hotcomm.community.common.bean.en.device;

public class DeviceEN {
	public enum DeviceState {
		NORMAL(0, "正常"), OFFLINE(1, "离线"), FAULT(2, "故障"), ALARM(3, "报警");

		private Integer value;
		private String name;

		private DeviceState(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static DeviceState getByValue(Integer value) {
			for (DeviceState obj : DeviceState.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum Device_Gz {
		OFFLINE("offline", "离线时间"), BATTERY("battery", "电量低于"), SYMAX("symax", "水压最大值"), SYMIN("symin",
				"水压最小值"), DLMAX("dlmax", "电流最大值"), DLMIN("dlmin", "电流最小值");

		private String name;
		private String value;

		private Device_Gz(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public static Device_Gz getByValue(Integer value) {
			for (Device_Gz obj : Device_Gz.values()) {
				if (obj.getName().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum DevDoorAlarm {
		FIREALARM(501, "消防报警"), BREAKIN(601, "非法进入报警"), TAMPERALARM(701, "防拆报警"), OPENALARM(801, "常开门报警");

		private Integer name;
		private String value;

		private DevDoorAlarm(Integer name, String value) {
			this.name = name;
			this.value = value;
		}

		public Integer getName() {
			return name;
		}

		public void setName(Integer name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static DevDoorAlarm getByName(Integer name) {
			for (DevDoorAlarm obj : DevDoorAlarm.values()) {
				if (obj.getName() == name) {
					return obj;
				}
			}
			return null;
		}
	}
}
