package com.hotcomm.community.common.bean.db.house;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoomDM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3148541757535497669L;
	
	private Integer id;                        //ID
	private Integer buildingId;                //楼栋ID
	private Integer floorId;                   //楼层ID
	private String roomName;                   //房间名
	private String detailedAddr;               //详细地址
	private Double constructArea;              //建筑面积
	private Double useArea;                    //使用面积
	private Integer attribute;                 //属性
	private Integer rentOrSale;                //租售类型
	private String createUser;                 //登记人
	private Date createTime;                   //登记时间
	private Date updateTime;                   //修改时间
	private Boolean isdelete;                  //是否删除
	private Integer doorduRoomId;              //多度平台对应ID
}
