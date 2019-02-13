package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class StrangerObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7256628171319798612L;

	/**
	 * 出现次数
	 */
	private Integer missTime;
	
	/**
	 * 首次出现地址
	 */
	private String missAddress;
}
