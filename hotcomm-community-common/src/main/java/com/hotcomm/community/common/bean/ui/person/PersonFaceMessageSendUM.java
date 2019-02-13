package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonFaceMessageSendUM implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4797026033717450281L;

	private Integer communityId;//社区id  (必填)
	private String recordImgs;//人脸图片
	private String 	address;//地址		
	private String recordTime;//通行时间		
	private Integer recordType;//通行类型	(必填)
	private String lableType; //(必填)
	private Integer matchingRate;//匹配度	
	private String deviceMac;//设备mac
	private String lat;
	private String lng;
	private Integer recordId;//通行记录id

	private String pName;//用户名称		(必填)
	private String faceImgs;//用户图片
	private Integer sex;//性别 1男2女
	private Integer age;//年龄
	
	
	private Integer personNum;//添加的人数		(不填)
	private Integer personTime;//添加的人次数	(默认填1)
	private Integer careAlarmNum;//添加的关爱人口报警		(不填)
	private Integer blackListAlarmNum;//添加的黑名单人群报警	(不填)
	

	private String TypeName;//通行方式名称
	private Integer pId;
	private Integer lableId;	
	private String lableName;//标签名称
	
	private String code;//face 人脸感知  record 通行  population 人口态式
}
