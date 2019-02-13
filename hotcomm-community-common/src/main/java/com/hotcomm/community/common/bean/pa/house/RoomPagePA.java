package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RoomPagePA extends PageParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2289039048305585000L;
	
	private Integer buildingId;
	private Integer unitId;
	private Integer floorId;
	private Integer attribute;
	private Integer rentOrSale;
	private String content;
}
