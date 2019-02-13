package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonAlarmTypeDM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 526626958080243445L;

	/**
	 * 关注人群警报次数
	 */
	private Integer carenum;

	/**
	 * 危险人群警报次数
	 */
	private Integer risknum;

	/**
	 * 黑名单人群警报次数
	 */
	private Integer blacknum;

	public PersonAlarmTypeDM(Integer carenum, Integer risknum, Integer blacknum) {
		super();
		this.carenum = carenum;
		this.risknum = risknum;
		this.blacknum = blacknum;
	}
	
	
}
