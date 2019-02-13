package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonAlarmPA extends PageParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2156022696797221371L;

	/**
	 * 记录方式
	 */
	private Integer recordType;
	/**
	 * 报警等级
	 */
	private Integer alarmLv;
	/**
	 * 标签id
	 */
	private Integer lableId;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 模糊搜索内容 [姓名,电话,身份证,报警原因]
	 */
	private String content;

	/**
	 * 报警id
	 */
	private Integer pId;
}
