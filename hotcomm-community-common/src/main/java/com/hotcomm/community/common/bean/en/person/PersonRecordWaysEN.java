package com.hotcomm.community.common.bean.en.person;

public enum PersonRecordWaysEN {
	NO_RETURN(1),NO_GO(2),TRIP(3),CONTINUOUS_TRIP(4);
	private Integer index;
	
	private PersonRecordWaysEN(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
