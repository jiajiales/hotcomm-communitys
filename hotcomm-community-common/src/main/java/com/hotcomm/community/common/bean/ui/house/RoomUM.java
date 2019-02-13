package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RoomUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8413636166911896413L;
	
	private  Integer id;
	private  String	roomName;
	private Integer buildingId;
	private  String buildingName;
	private Integer unitId;
	private String unitName;
	private Integer floorId;
	private  String floorName;
	private String detailedAddr;
	private  Double constructArea;
	private  Double useArea;
	private  String attribute;
	private  Integer attributeId;
	private  String rentOrSale;
	private  Integer rentOrSaleId;
	private  String createUser;
	private  String createTime;
}
