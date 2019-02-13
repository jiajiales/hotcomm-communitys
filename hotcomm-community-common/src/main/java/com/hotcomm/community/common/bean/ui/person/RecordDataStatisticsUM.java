package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RecordDataStatisticsUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8144447755589530669L;

	/**
	 * 楼栋数量
	 */
	private Integer houseNum;
	
	/**
	 * 房间数量
	 */
	private Integer roomNum;
	
	/**
	 * 门禁数量
	 */
	private Integer MJDeviceNum;
	
	/**
	 * 人口数量
	 */
	private Integer population;

	/**
	 * 关爱人口
	 */
	private Integer careNum;
	
	/**
	 * 潜在危险人口
	 */
	private Integer riskNum;
	
	/**
	 * 黑名单人群
	 */
	private Integer blackListNum;
}
