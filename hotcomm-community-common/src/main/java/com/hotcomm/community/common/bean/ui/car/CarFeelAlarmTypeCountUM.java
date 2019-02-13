package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelAlarmTypeCountUM implements Serializable {
	
	private static final long serialVersionUID = 511187180951652270L;

	private String labelName;
	private Integer numCount;
}
