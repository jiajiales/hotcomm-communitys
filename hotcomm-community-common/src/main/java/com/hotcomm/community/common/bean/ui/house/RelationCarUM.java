package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RelationCarUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -528548406043310269L;
	
	private Integer carId;
	private String uuid;
	private String num;
	private String personName;
	private String color;
	private String brand;
	private String model;
	private String type;

}
