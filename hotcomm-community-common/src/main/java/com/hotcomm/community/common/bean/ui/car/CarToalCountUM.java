package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarToalCountUM implements Serializable {
	
	private static final long serialVersionUID = -3236684101618017317L;

	private Integer regCarCount;
	private Integer dayPassCount;
	private Integer monAlarmCount;
	private Integer senTimePassCount;
}
