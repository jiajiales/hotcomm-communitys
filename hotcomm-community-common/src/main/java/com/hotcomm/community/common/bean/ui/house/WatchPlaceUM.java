package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WatchPlaceUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8800305224943746696L;

	private Integer id;
	private String placeName;
	private Integer buildingId;
	private String buildingName;
	private Integer unitId;
	private String unitName;
	private Integer floorId;
	private String floorName;
	private Integer roomId;
	private String roomName;
	private String placeAddress;
	private String lon;
	private String lat;
	private Integer placeTypeId;
	private String placeType;
	private Integer dangerLevel;
	private String createUser;
	private String createTime;

}
