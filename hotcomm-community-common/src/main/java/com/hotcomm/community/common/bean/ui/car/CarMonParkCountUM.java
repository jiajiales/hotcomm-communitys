package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarMonParkCountUM implements Serializable {
	
	private static final long serialVersionUID = -3591784162630163972L;
	
	private Integer day;
	private Integer dayCount;
}
