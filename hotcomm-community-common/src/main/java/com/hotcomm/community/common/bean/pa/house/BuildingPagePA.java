package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;
import com.hotcomm.framework.comm.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuildingPagePA extends PageParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -462997782095566855L;

	private Integer attribute;
	private Integer rentOrSale;
	private Integer minFloors = 0;
	private Integer maxFloors;
	private String buildingName;
}
