package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceAlarmCount implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 2827468618985949672L;
	private Integer moduleId;
	private String model;
	private Integer alarmCount;
	private Double percent = 0.00;
}
