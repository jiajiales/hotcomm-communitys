package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmMonCountUM implements Serializable {
	
	private static final long serialVersionUID = 6927476648067227167L;
	
	private List<CarAlarmDayUM> curMonAlarmCountList;
	private List<CarAlarmDayUM> lastMonAlarmCountList;

}
