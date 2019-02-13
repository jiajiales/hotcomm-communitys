package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PeopleFaceStatisticsUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328379544588466878L;

	/**
	 * 人口总数
	 */
	private Integer peopleNum;
	
	/**
	 * 人脸人数
	 */
	private Integer faceNum;
	
	/**
	 * 人脸感知次数
	 */
	private Integer faceReactionNum;
	
	/**
	 * 摄像头数
	 */
	private Integer cameraNum;
	
	/**
	 * 关爱人群数
	 */
	private Integer carePeopleNum;
	
	/**
	 * 陌生人群数
	 */
	private Integer strangerNum;
	
	/**
	 * 潜在危险人群数
	 */
	private Integer riskPeopleNum;
	
	/**
	 * 黑名单数
	 */
	private Integer blacklistNum;
}
