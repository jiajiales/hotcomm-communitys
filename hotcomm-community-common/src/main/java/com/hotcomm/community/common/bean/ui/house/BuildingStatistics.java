package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuildingStatistics implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7474894529818041868L;

	private String attribute;             //属性
	private Integer buildingNum;          //栋数
	private Double ratio;                 //占比比率
}
