package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PlaceNumData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3744817145313976335L;

	private String ways;
	private Integer placeNum;

}
