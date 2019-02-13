package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPostureParkingCountUM implements Serializable {
	
	private static final long serialVersionUID = -6233197980729871516L;
	
	private List<CarMonParkCountUM> regCarList;
	private List<CarMonParkCountUM> strCarList;
	
	private List<CarPosturePakingYearUM> regYearCarList;
	private List<CarPosturePakingYearUM> strYearCarList;

}
