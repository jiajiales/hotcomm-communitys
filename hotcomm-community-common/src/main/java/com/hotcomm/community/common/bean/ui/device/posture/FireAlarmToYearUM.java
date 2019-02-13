package com.hotcomm.community.common.bean.ui.device.posture;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlarmToYearUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5666472122039340031L;
	private Integer month;// 当月日期
	private Integer alarmNum = 0;// 报警数
}
