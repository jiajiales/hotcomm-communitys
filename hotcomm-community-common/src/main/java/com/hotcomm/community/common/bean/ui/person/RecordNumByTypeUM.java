package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RecordNumByTypeUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8766632095142562399L;

	/**
	 * 通行类型
	 */
	private Integer type;
	
	/**
	 * 类型名称
	 */
	private String name;
	
	/**
	 * 次数
	 */
	private Integer Num;

	public RecordNumByTypeUM(Integer type, String name, Integer num) {
		super();
		this.type = type;
		this.name = name;
		Num = num;
	}
	
	
}
