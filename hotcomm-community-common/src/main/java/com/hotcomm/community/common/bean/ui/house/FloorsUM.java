package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FloorsUM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636921112630810161L;
	
	private Integer id;                        //楼层ID
	private String floorName;                  //楼层名称
	private Integer floorNum;                  //楼层序号
	private Integer unitId;
	private String unitName;                   //单元名称
	private Integer buildingId;
	private String buildingName;               //楼栋名称
	private Integer totalRomms;                //层房间总数
	private Double constructArea;              //建筑面积
	private Double useArea;                    //使用面积
	private String attribute;                  //属性
	private Integer attributeId;
	private String rentOrSale;					//租售类型
	private Integer rentOrSaleId;
	private String detailedAddr;				//地址
	private String layoutPath;                 //楼层布局图路径
	private String createUser;                 //登记人
	private String createTime;                 //登记时间
	private String remark;					   //备注
}
