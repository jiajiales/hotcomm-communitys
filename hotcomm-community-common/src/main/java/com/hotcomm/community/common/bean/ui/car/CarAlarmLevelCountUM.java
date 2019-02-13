package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmLevelCountUM implements Serializable {

	private static final long serialVersionUID = 326150603048441944L;
	
	private String alarmLeve;
	private Integer count;

}
