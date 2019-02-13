package com.hotcomm.community.common.bean.en.person;

public enum PersonLableEN {

	GENERAL_PERSON(0,"F00","普通人群"),CARE_PERSON(1, "F01", "关爱人群"), RISK_PERSON(2, "F02", "危险人群"), SERVER_PERSON(3, "F03", "服务人群"),
	BLACKLIST_PERSON(4, "F04", "黑名单人群"),STRANGER_PERSON(-1,"F05","陌生人");

	private PersonLableEN(Integer index, String key, String value) {
		this.index = index;
		this.key = key;
		this.value = value;
	}

	private Integer index;
	private String key;
	private String value;

	public Integer getIndex() {
		return index;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static PersonLableEN getValueByIndex(Integer index) {
		for (PersonLableEN obj : PersonLableEN.values()) {
			if(obj.getIndex().equals(index)) {
				return obj;
			}
		}
		return null;
		
	}
	public static PersonLableEN getIndexByKey(String key) {
		for (PersonLableEN obj : PersonLableEN.values()) {
			if(obj.getKey().equals(key)) {
				return obj;
			}
		}
		return null;
		
	}
}
