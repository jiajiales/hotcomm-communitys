package com.hotcomm.community.common.bean.ui.device.postureSynthesize;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceNumUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7544706564096924874L;
	private Integer Num;
	private Integer offNum;
	private Integer alarmNum;
}
