package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelPassCountListUM implements Serializable {
	
	private static final long serialVersionUID = -8772359870444667120L;
	
	private List<CarFeelPassHoursCountUM>  carCount;
	private List<CarFeelPassHoursCountUM>  carPassCount;
}
