package com.hotcomm.framework.comm;

public enum ContentType {
	
	JSON("application/json"),
	FORM("application/x-www-form-urlencoded");

	private String name;

	ContentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
