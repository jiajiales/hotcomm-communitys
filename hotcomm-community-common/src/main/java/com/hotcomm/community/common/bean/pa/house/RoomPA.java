package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RoomPA extends CommunityParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -86881362234228963L;
	
	private Integer id;
	private Integer buildingId;
	private Integer unitId;
	private Integer floorId;
	private String roomName;
	private String detailedAddr;
	private Double constructArea;
	private Double useArea;
	private Integer attribute;
	private Integer rentOrSale;
	private String createUser;

}
