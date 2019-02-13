package com.hotcomm.community.common.bean.en.house;

public class WatchPlaceEN {
	public enum WaysEnum {
		DANGER(1, "W01"), SERVICE(2, "W02");

		private Integer value;
		private String name;

		private WaysEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static WaysEnum getByValue(Integer value) {
			for (WaysEnum obj : WaysEnum.values()) {
				if (obj.getValue() == value) {
					return obj;
				}
			}
			return null;
		}
	}
}
