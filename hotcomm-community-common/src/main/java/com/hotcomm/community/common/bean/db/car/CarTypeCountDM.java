package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarTypeCountDM implements Serializable {

	private static final long serialVersionUID = 3883895983908008412L;
	private String carType;
	private Integer count;
}
