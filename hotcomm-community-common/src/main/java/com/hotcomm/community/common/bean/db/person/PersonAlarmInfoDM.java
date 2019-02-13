package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonAlarmInfoDM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6952511103018540420L;

	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 证件号码
	 */
	private String cardNo;
	/**
	 * 警报等级
	 */
	private Integer alarmLevel;
	/**
	 * 创建时间
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
	private Integer recordType;
	/**
	 * 视频
	 */
	private String video;
	/**
	 * 图片
	 */
	private String imgs;
	
}
