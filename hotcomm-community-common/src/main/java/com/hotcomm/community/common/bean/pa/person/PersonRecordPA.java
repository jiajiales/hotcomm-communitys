package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonRecordPA extends PageParams implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898957211699099590L;

	/**
	 * 记录类型 1 监控 2 刷卡 3 布控 4 手机APP开门
	 */
	private Integer recordType;
	
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
	 * 模糊查询(身份证 ,姓名 , 电话)
	 */
	private String content;
	
	/**
	 * 居民id
	 */
	private Integer pId;

	/**
	 * 人脸编号
	 */
	private String faceNo;
}
