package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FloorsPA extends CommunityParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8246297091663855466L;
	
	private Integer id;
	private Integer buildingId;
	private Integer unitId;
	private String floorName;
	private Integer floorNum;
	private Integer attribute;
	private Integer rentOrSale;
	private String detailedAddr;				
	private Double constructArea;
	private Double useArea;
	private String layoutPath;
	private String createUser;
	private String remark;
}
