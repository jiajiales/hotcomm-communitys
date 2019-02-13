package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PassInfoOfDayDM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6986903559170982117L;

	/**
	 * 小时
	 */
	private Integer h;
	
	/**
	 * 次数/人
	 */
	private Integer num;

	
	
}
