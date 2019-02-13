package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PassRealTimeUM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2938415837657680210L;
	
	List<String> Imgs;
	/**
	 * 今日感知总人次数
	 */
	private Integer personNum;
	
	/**
	 * 关爱人口本月警报次数
	 */
	private Integer careAlarmNum;
	
	/**
	 * 黑名单本月警报次数
	 */
	private Integer blackListAlarmNum;

}
