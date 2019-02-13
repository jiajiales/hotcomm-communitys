package com.hotcomm.community.common.bean.db.house;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuildingDM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2053231418925581224L;

	private Integer id;                           //
	private Integer communityId;                  //小区ID
	private Integer doorduBuildingId;             //多度平台对应ID
	private String buildingName;                  //楼栋名称
	private String detailedAddr;                  //详细地址
	private Integer attribute;                    //属性
	private String lon;                           //经度
	private String lat;                           //维度
	private Integer rentOrSale;                   //租售类型
	private Date createTime;                      //创建时间
	private String createUser;                    //登记人
	private Date updateTime;                      //修改时间
	private Boolean isdelete;                     //是否删除
}
