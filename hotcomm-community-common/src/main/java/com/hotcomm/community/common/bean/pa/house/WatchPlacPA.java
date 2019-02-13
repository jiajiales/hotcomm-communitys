package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class WatchPlacPA extends CommunityParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4073847738070828478L;
	
	private Integer id;
	private Integer ways;
	private String placeName;
	private Integer placeType;
	private Integer dangerLevel;
	private Integer buildingId;
	private Integer unitId;
	private Integer floorId;
	private Integer roomId;
	private String lon;
	private String lat;
	private String createUser;
	private String placeAddress;
}
