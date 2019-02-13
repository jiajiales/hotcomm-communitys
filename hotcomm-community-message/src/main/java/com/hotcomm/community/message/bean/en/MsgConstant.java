package com.hotcomm.community.message.bean.en;

public class MsgConstant {

	enum CategoryEnum {

		posture("C01", "态势"), perceive("C02", "感知");

		private String key;
		private String value;

		private CategoryEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static CategoryEnum getByKey(String key) {
			for (CategoryEnum c : CategoryEnum.values()) {
				if (c.getKey().equals(key)) {
					return c;
				}
			}
			return null;
		}
	}

	enum PostureType {

		population("P01", "人脸");

		private String key;
		private String value;

		private PostureType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static PostureType getByKey(String key) {
			for (PostureType p : PostureType.values()) {
				if (p.getKey().equals(key)) {
					return p;
				}
			}
			return null;
		}
	}

	enum PerceiveType {

		traffic("T01", "通行"), face("F01", "人脸"), device("D01", "设备"), fire("F02", "消防"), car("C01", "车辆");

		private String key;
		private String value;

		private PerceiveType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static PerceiveType getByKey(String key) {
			for (PerceiveType p : PerceiveType.values()) {
				if (p.getKey().equals(key)) {
					return p;
				}
			}
			return null;
		}
	}
}
