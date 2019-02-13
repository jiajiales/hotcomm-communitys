package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SpecialCrowdFaceRecUM implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 关爱人群人数
	 */
	private Integer CarePersonNum;
	
	/**
	 * 关爱人群人次数
	 */
	private Integer CarePersonTime;
	
	/**
	 * 潜在危险人群数
	 */
	private Integer RiskPersonNum;
	
	/**
	 * 潜在危险人群人次数
	 */
	private Integer RiskPersonTime;
	
	/**
	 *黑名单人群数 
	 */
	private Integer BlackListNum;
	
	/**
	 * 黑名单人群人次数
	 */
	private Integer BlackListTime;
	
	/**
	 * 陌生个人群人数
	 */
	private Integer StrangerNum;
	
	/**
	 * 陌生人群人次数
	 */
	private Integer StrangerTime;
}
