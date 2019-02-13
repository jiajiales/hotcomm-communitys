package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarTypeCountUM implements Serializable {
	
	private static final long serialVersionUID = -4451189524590308003L;

	private Integer companyCarCount;
	private double companyCarPer;
	private Integer communityCarCount;
	private double communityCarPer;
	private Integer otherCarCount;
	private double otherCarPer;
}
