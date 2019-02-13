package com.hotcomm.community.common.bean.en.system;

public class CommunityEN {

	public enum IsDelete {
		NO(1, "否"), 
		YES(2, "是");

		private Integer value;
		private String name;

		IsDelete(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static IsDelete getByValue(Integer value) {
			for (IsDelete obj : IsDelete.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum IsDefault {
		NO(1, "非默认"), 
		YES(2, "默认");

		private Integer value;
		private String name;

		IsDefault(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static IsDefault getByValue(Integer value) {
			for (IsDefault obj : IsDefault.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

}
