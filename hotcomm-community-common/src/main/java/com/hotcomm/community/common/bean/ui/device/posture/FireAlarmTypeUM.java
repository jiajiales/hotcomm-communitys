package com.hotcomm.community.common.bean.ui.device.posture;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlarmTypeUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1616622811319881392L;
	private Integer otherAlarmNum;
	private Integer fireAlarmNum;
	private double otherAlarmNumPercent;
	private double fireAlarmNumPercent;
}
