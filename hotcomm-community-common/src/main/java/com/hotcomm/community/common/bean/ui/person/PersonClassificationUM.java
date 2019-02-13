package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonClassificationUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8755151492559003134L;

	/**
	 * 小孩
	 */
	private Integer children;
	
	/**
	 * 年轻人
	 */
	private Integer youngpeople;
	
	/**
	 * 老人
	 */
	private Integer oldpeople;
	
	/**
	 * 本地户口
	 */
	private Integer localaccount;
	
	/**
	 * 非本地户口
	 */
	private Integer unlocalaccount;
	
	/**
	 * 总人口
	 */
	private Integer personNum;
}
