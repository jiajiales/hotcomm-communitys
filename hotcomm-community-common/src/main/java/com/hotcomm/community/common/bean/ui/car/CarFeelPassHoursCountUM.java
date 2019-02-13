package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelPassHoursCountUM implements Serializable {

	private static final long serialVersionUID = 8244808093012900434L;

	private String hours;
	private Integer hoursCount;
}
