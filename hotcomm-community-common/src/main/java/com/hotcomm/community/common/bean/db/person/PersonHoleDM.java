package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonHoleDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7183281424667612398L;

	/**
	 * 编号
	 */
	private Integer holeId;

	/**
	 * 布控主题
	 */
	private String holeTitle;

	/**
	 * 布控类型 1 群里 2 单人
	 */
	private Integer holeType;

	/**
	 * 布控个人|群体 使用json type=1 -->>p_id type=2
	 * {sex:boy，age:[20，50]，nationality:china，people=han，lable=01}
	 */
	private String holeObj;

	/**
	 * 报警 级别
	 */
	private Integer alarmLv;

	/**
	 * 状态 0 未开启 1 已开启 2 已结束
	 */
	private Integer holeStatus;

	/**
	 * 登记时间
	 */
	private String createTime;

	/**
	 * 登入人员
	 */
	private String createUser;

	/**
	 * 最新修改时间
	 */
	private String beginTime;

	/**
	 * 最新修改人员
	 */
	private String endTime;

	/**
	 * 布控对象id
	 */
	private Integer pId;
	
	/**
	 * 布控对象名称
	 */
	private String pName;
	
	private Integer lableId;
	
	private String lableName;
}
