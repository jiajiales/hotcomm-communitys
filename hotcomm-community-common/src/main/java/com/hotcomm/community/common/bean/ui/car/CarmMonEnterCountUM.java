package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarmMonEnterCountUM implements Serializable {
	
	private static final long serialVersionUID = -2317878130608810684L;

	private Integer id;
	private String carNum;
	private String carImg;
	private Integer count;
}
