package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAttentionCountUM implements Serializable {
	
	private static final long serialVersionUID = -5442157606822736674L;
	
	private String name;
	private Integer count;
	private double countPer;

}
