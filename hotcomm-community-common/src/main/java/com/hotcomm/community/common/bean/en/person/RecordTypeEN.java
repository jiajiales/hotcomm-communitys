package com.hotcomm.community.common.bean.en.person;

import java.util.HashMap;
import java.util.Map;


public enum RecordTypeEN {
	FACE(1,"人脸感知"),MJFACE(2,"门禁摄像头"),CARD(3,"门卡开门"),APP(4,"手机开门"),PASSWORD(5,"密码开门");
	private Integer index;
	private String name;
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private RecordTypeEN(Integer index,String name) {
		this.index=index;
		this.name=name;
	}
	public static RecordTypeEN getNameByIndex(Integer index) {
		for (RecordTypeEN obj : RecordTypeEN.values()) {
			if(obj.getIndex().equals(index)) {
				return obj;
			}
		}
		return null;
		
	}
	public static Map<Integer, String> toMap() {
		 Map<Integer, String> map = new HashMap<Integer, String>();
        for (RecordTypeEN airlineTypeEnum : RecordTypeEN.values()) {
            map.put(airlineTypeEnum.getIndex(), airlineTypeEnum.getName());
        }
        return map;
	}
}
