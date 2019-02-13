package com.hotcomm.community.common.bean.ui.house;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuildingUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3368736985664090069L;
	
	private Integer id;                                     //楼栋ID
	private String buildingName;                            //楼栋名称
	private String detailedAddr;                            //详细地址
	private String lon;                                     //经度
	private String lat;                                     //维度
	private Integer totalFloors;                            //总层数
	private Integer totalRomms;                             //总户数
	private String attribute;                               //属性
	private Integer attributeId;
	private String rentOrSale;                              //租售类型
	private Integer rentOrSaleId;
	private String createTime;                                //创建时间
	private String createUser;                              //创建人
	
}
