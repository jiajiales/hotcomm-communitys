package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PopulationByLableUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6630967416184417740L;

	private Integer populationNum;
	/**
	 * 总人口比例
	 */
	private float allRatio;
	
	private Integer careNum;
	
	/**
	 * 关爱人群比例
	 */
	private float careRatio;
	
	private Integer riskNum;
	
	/**
	 * 潜在危险人群比例
	 */
	private float riskRatio;
	
	private Integer serviceNum;
	
	/**
	 * 服务人群比例
	 */
	private float serviceRatio;
	
	private Integer blackListNum;
	
	/**
	 * 黑名单比例
	 */
	private float blackListRatio;
}
