package com.hotcomm.community.common.bean.db.house;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WatchPlaceDM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9106571655518088440L;

	private Integer id;
	private Integer buildingId;
	private Integer floorId;
	private Integer roomId;
	private Integer ways;
	private String placeName;
	private String placeAddress;
	private Integer placeType;
	private Integer dangerLevel;
	private String lon;
	private String lat;
	private String createUser;
	private Date createTime;
	private Date updateTime;

}
