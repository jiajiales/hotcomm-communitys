package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonRecordDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -814016359570636710L;

	/**
	 * uuid编号
	 */
	private String uid;

	private String pId;
	
	/**
	 * 多度id
	 */
	private Integer doorduLogId;
	/**
	 * 居民名称
	 */
	private String name;
	
	/**
	 * 联系电话
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
	 * 标签Id
	 */
	private Integer lableId;

	/**
	 * 图片
	 */
	private String imgs;

	/**
	 * 视频
	 */
	private String video;

	/**
	 * 记录类型 1 监控 2 刷卡 3 布控 4 手机app开门
	 */
	private Integer recordType;
	
	/**
	 * 人脸编号  
	 */
	private String faceNo;

	/**
	 * 设备mac
	 */
	private String deviceMac;
}
