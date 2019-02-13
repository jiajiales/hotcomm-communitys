package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PopulationAlarmDM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4935980462883120844L;

	/**
	 * 警报id
	 */
	private Integer id;
	/**
	 * 人id
	 */
	private Integer pId;
	/**
	 * 人物名称
	 */
	private String name;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 警报原因
	 */
	private String alarmReason;
	
	/**
	 * 警报等级
	 */
	private Integer alarmLevel;
	
	/**
	 * 警报时间
	 */
	private String createTime;
	
	/**
	 * 警报地址
	 */
	private String recordAddress;
	
	/**
	 * 标签名称
	 */
	private String lableName;
	
	/**
	 * 记录方式
	 */
	private String  recordType;
	
	/**
	 * 图片
	 */
	private String imgs;
	
	/**
	 * 视频
	 */
	private String video;

	/**
	 * 报警原因
	 */
	private String alarmResaon;
}
