package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPosturePakingYearUM implements Serializable  {
	
	private static final long serialVersionUID = 4630749865265525585L;
	
	private Integer mon;
	private Integer monCount;

}
