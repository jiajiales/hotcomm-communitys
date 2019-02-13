package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarStrDM extends CommunityParams   implements Serializable{
		
	private static final long serialVersionUID = 2054888501966666576L;
	
	 //陌生车辆ID
    private  Integer id;
   
	 //车辆标签关联ID（uuid）
    private  String uuid;
   
	 //车辆图片
    private  String photo;
   
	 //车牌号
    private  String num;
   
	 //颜色
    private  String color;
   
	 //品牌
    private  String brand;
   
	 //型号
    private  String model;
    
	 //车型
    private  String modelType;
   
	 //车辆状态(0：驶入，1：离开)
    private  Integer state;
    
    //出入时间
    private String enterOutTime;
   
	 //本月出入次数
    private  Integer enterCount;
   
	 //本月报警次数
    private  Integer alarmCount;
   
	 //创建时间
    private  String createTime;
   
	 //创建人
    private  String createUser;
   
	 //更新时间
    private  String updateTime;
   
	 //更改人
    private  String updateUser;
    
    //动态库名称
    private  String dynamicDbname;
    
}