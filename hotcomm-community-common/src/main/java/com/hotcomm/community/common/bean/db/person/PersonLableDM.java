package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonLableDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7586446909924021674L;
	/**
	 * 编号
	 */
	private int id;

	/**
	 * 标签类型
	 */
	private String typeCode;

	/**
	 * 标签名称
	 */
	private String name;

	/**
	 * 标签说明
	 */
	private String description;

	/**
	 * 报警级别
	 */
	private int alarmLevel;

}
