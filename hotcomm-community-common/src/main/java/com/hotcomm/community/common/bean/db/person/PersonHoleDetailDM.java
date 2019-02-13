
package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonHoleDetailDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5976892936891587418L;

	/**
	 * 编号
	 */
	private Integer id;

	/**
	 * 布控id
	 */
	private Integer holeId;

	/**
	 * 布控开始时间
	 */
	private String begintime;

	/**
	 * 布控结束时间
	 */
	private String endtime;

	/**
	 * 未归时间 单位为小时
	 */
	private Integer noReturn;

	/**
	 * 未出时间 单位未小时
	 */
	private Integer noGo;

	/**
	 * 天数
	 */
	private Integer days;

	/**
	 * 次数
	 */
	private Integer nums;

	/**
	 * 是否连续，1:连续，2不连续
	 */
	private Integer rowKey;

	/**
	 * 触发方式 1 未归 2 未出 3 出行频率 4 出现频率-连续
	 */
	private Integer way;
}
