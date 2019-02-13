package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonPagePA extends PageParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1567631333964235308L;
	
	/**
	 * 性别 1 男 2女
	 */
	private Integer sex;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 人口标签
	 */
	private Integer lableId;
	
	/**
	 * 人口类型
	 */
	private Integer lableType;
	/**
	 * 开始报警次数
	 */
	private Integer beginAlarmTime;
	
	/**
	 * 结束报警次数
	 */
	private Integer endAlarmTime;
	
	/**
	 * 模糊查询内容 (名字,身份证,电话)
	 */
	private String content;

	/**
	 * 证件类型
	 */
	private Integer cardType;
	/**
	 * 数据类型
	 */
	private Integer dataSource;
}
