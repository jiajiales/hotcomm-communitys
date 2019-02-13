package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LablePopulationUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6922888171007139824L;

	/**
	 * 关爱人群
	 */
	private Integer carePerson;

	/**
	 * 危险人群
	 */
	private Integer riskPerson;

	/**
	 * 服务人群
	 */
	private Integer serverPerson;

	/**
	 * 黑名单人数
	 */
	private Integer blacklistPerson;
}
