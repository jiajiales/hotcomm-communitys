package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlarmMsgUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4122295209146900919L;
	private String stateName;
	private Integer alarmNum;
	private double alarmPercent = 0;// 报警占比
}
