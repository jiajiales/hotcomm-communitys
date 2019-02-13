package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;
import java.util.Date;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class BuildingPA extends CommunityParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 491288215356157369L;
	
	private Integer id;
	private String buildingName;
	private String detailedAddr;
	private Integer attribute;
	private String lon;
	private String lat;
	private Integer rentOrSale;
	private Date createTime;  
	private String createUser;
	private Date updateTime;
	private String unitName;

}
