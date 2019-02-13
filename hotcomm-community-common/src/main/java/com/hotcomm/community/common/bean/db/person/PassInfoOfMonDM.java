package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PassInfoOfMonDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4709836431028447480L;

	/**
	 * 日期
	 */
	private Integer d;

	/**
	 * 次数/人
	 */
	private Integer num;

}
