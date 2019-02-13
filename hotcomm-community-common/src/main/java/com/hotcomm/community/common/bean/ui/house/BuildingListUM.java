package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuildingListUM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7545084930113846933L;
	private Integer id;                                     //楼栋ID
	private String buildingName;                            //楼栋名称

}
