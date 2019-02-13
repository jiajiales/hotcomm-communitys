package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonPassInfoOfMonUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3015076731867110648L;

	/**
	 * 日
	 */
	private Integer day;
	
	/**
	 * 人数
	 */
	private Integer peopleNum;
	
	/**
	 * 次数
	 */
	private Integer passTime;

	public PersonPassInfoOfMonUM(Integer day, Integer peopleNum, Integer passTime) {
		super();
		this.day = day;
		this.peopleNum = peopleNum;
		this.passTime = passTime;
	}
	
	
}
