package com.hotcomm.community.common.bean.ui.device.posture;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlarmToMonthUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -436108919143142623L;
	private Integer day;// 当月日期
	private Integer alarmNum = 0;// 报警数
}
