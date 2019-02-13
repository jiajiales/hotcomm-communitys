package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonHolePA extends PageParams  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5153237714817613618L;

	/**
	 * 开始年龄
	 */
	private Integer startAge;
	
	/**
	 * 结束年龄
	 */
	private Integer endAge;
	
	/**
	 * 1 群体 2 个人
	 */
	private Integer type;
	
	/**
	 * 1男 2 女
	 */
	private Integer sex;
	
	/**
	 * 人口标签id
	 */
	private Integer lableId;
	
	/**
	 * 民族
	 */
	private String people;
	
	/**
	 * 模糊搜索内容 (布控主题/布控对象名称)
	 */
	private String content;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
}
