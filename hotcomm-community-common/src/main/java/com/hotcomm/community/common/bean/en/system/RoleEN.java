package com.hotcomm.community.common.bean.en.system;

public class RoleEN {

	public enum RoleType {
		SUPER_ADMIN(1, "超级管理员"), 
		COMMUNITY_LEADER(2, "小区负责人"), 
		COMMUNITY_ASSISTANTS(3, "辅助人员"), 
		COMMUNITY_OPERATIONS(4, "运维人员");

		private Integer value;
		private String name;

		RoleType(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static RoleType getByValue(Integer value) {
			for (RoleType obj : RoleType.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum RoleLevel {
		SYSTEM(1, "系统"), 
		COMMUNITY(2, "社区");

		private Integer value;
		private String name;

		RoleLevel(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static RoleLevel getByValue(Integer value) {
			for (RoleLevel obj : RoleLevel.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

	public enum RoleStatus {
		ENABLE(1, "有效"), 
		DISABLE(2, "无效");

		private Integer value;
		private String name;

		RoleStatus(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static RoleStatus getByValue(Integer value) {
			for (RoleStatus obj : RoleStatus.values()) {
				if (obj.getValue().equals(value)) {
					return obj;
				}
			}
			return null;
		}
	}

}
