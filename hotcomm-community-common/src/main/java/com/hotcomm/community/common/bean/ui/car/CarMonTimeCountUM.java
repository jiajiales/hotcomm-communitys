package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarMonTimeCountUM  implements Serializable {


	private static final long serialVersionUID = 5231335023547048788L;
	
	private  String  hours;
	private Integer hoursCount;
	private Integer preHoursCount;

}
