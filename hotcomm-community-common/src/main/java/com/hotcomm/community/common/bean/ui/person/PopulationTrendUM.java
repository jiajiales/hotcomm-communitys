package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PopulationTrendUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7506879317889177928L;
	
	private Integer year;
	private Integer mon1;
	private Integer mon2;
	private Integer mon3;
	private Integer mon4;
	private Integer mon5;
	private Integer mon6;
	private Integer mon7;
	private Integer mon8;
	private Integer mon9;
	private Integer mon10;
	private Integer mon11;
	private Integer mon12;
}
