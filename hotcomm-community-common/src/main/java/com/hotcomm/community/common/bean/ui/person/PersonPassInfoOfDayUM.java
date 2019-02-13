package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonPassInfoOfDayUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5895210099782487284L;

	/**
	 * 小时
	 */
	private Integer hour;
	
	/**
	 * 人数
	 */
	private Integer peopleNum;
	
	/**
	 * 次数
	 */
	private Integer passTime;

	public PersonPassInfoOfDayUM(Integer hour, Integer peopleNum, Integer passTime) {
		super();
		this.hour = hour;
		this.peopleNum = peopleNum;
		this.passTime = passTime;
	}
	
	
}
