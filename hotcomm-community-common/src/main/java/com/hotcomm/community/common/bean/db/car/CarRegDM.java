package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarRegDM extends CommunityParams  implements Serializable {

	private static final long serialVersionUID = 2537725385448655348L;

	//车辆ID
    private  Integer id;
   
	 //车辆图片
    private  String photo;
   
	 //车牌号
    private  String num;
   
	 //颜色
    private  String color;
   
	 //品牌
    private  String brand;
   
    //车型
    private String modelType;
    
	 //型号
    private  String model;
   
	 //车辆类型（0:小区车辆，1：单位车辆，2：其他登记车）
    private  Integer type;
    
    //人员ID
    private  Integer personId;
   
	 //车主房号ID
    private  Integer  roomId;
   
	 //本月出入次数
    private  Integer enterCount;
   
	 //本月报警次数
    private  Integer alarmCount;
   
	 //车辆所属单位ID
    private  Integer  companyId;

    //来源页面（0：登记车辆，1：黑名单车辆）
    private Integer pageSource;
   
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
