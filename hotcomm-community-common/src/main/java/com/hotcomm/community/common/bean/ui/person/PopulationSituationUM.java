package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PopulationSituationUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5875744918707237354L;

	/**
	 * 区域总人数
	 */
	private Integer TotalPopulation;

	/**
	 * 今日通行人数
	 */
	private Integer TotalNumberOfPass;

	/**
	 * 今日通行总人次数
	 */
	private Integer TotalTimeOfPass;

	/**
	 * 关爱人群数
	 */
	private Integer CarePopulation;

	/**
	 * 黑名单人数
	 */
	private Integer BlacklistPopulation;

	/**
	 * 本月警报次数
	 */
	private Integer AlarmOfTimeToMonth;
}
