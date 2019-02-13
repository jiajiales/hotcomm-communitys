package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarMonTimeCountListUM implements Serializable {
	
	private static final long serialVersionUID = 8609666809630471748L;
	
	private List<CarMonTimeCountUM> carEnterCount;
	private List<CarMonTimeCountUM> carOutCount;

}
