package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonAlarmByLevelDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2674817263105290001L;
	/**
	 * 月份
	 */
	private Integer mon;

	/**
	 * 1级警报次数
	 */
	private Integer Lv1num;

	/**
	 * 2级警报次数
	 */
	private Integer Lv2num;

	/**
	 * 3级警报次数
	 */
	private Integer Lv3num;

	public PersonAlarmByLevelDM(Integer mon, Integer lv1num, Integer lv2num, Integer lv3num) {
		super();
		this.mon = mon;
		Lv1num = lv1num;
		Lv2num = lv2num;
		Lv3num = lv3num;
	}

}
