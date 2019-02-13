package com.hotcomm.community.common.bean.en.system;

public class UserEN {

	public enum UserType {
		SYSTEM(1, "系统"), 
		COMMUNITY(2, "社区");

		private Integer value;
		private String name;

		UserType(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static UserType getByValue(Integer value) {
			for (UserType obj : UserType.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum UserStatus {
		VALID(1, "有效"), 
		INVALID(2, "无效");

		private Integer value;
		private String name;

		UserStatus(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static UserStatus getByValue(Integer value) {
			for (UserStatus obj : UserStatus.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

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

}
