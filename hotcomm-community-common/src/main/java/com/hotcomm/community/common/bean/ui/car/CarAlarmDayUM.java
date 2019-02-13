package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmDayUM implements Serializable {
	
	private static final long serialVersionUID = -8555782898514289533L;
	
	private String alarmDay;
	private Integer dayCount;

}
