package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelAlarmListUM implements Serializable {
	
	private static final long serialVersionUID = 5892680497038722796L;
	 
	private Integer alarmWeekCount;
	private List<CarAlarmListUM> carAlarmLists;

}
