package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonRecordInfoDM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 936790943050243742L;
	
	/**
	 * id
	 */
	private Integer uid;
	/**
	 * 头像
	 */
	private String headImg;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 *年龄 
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
	 * 记录时间
	 */
	private String recordTime;
	/**
	 * 记录地址
	 */
	private String recordAddress;
	/**
	 * 标签
	 */
	private String lableName;
	/**
	 * 记录方式
	 */
	private Integer recordType;
	/**
	 * 图片
	 */
	private String imgs;
	
	/**
	 * 视频
	 */
	private String video;

}
