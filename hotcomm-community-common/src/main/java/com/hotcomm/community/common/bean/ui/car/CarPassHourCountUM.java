package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassHourCountUM implements Serializable{

	private static final long serialVersionUID = 3281868449024548590L;
	private List<CarFeelPassHoursCountUM>  carEnterCount;
	private List<CarFeelPassHoursCountUM>  carOutCount;
	
}
