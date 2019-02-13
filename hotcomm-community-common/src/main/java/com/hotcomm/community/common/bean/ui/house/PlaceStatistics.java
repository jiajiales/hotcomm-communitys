package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PlaceStatistics implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6133443854592131462L;
	
	private String placeType;
	private Integer placeNum;          //场所数量
	private Double ratio;              //占比比率
}
