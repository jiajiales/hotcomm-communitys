package com.hotcomm.community.common.bean.db.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassRecordsDM extends CommunityParams  implements Serializable {

	private static final long serialVersionUID = -6214814650560175955L;

	 //记录ID
    private  Integer id;
   
	 //车辆标签关联ID（uuid）
    private  String uuid;
   
	 //车辆类型（0:小区车辆，1：单位车辆，2:其他登记车,3：陌生车）
    private  Integer carType;
   
	 //出入类型(0:驶入，1:驶出，2:经过)
    private  Integer passType;
   
	 //颜色
    private  String color;
   
	 //型号
    private  String model;
   
	 //品牌
    private  String brand;
   
	 //车型
    private  String modelType;
   
	 //出现地点
    private  String adress;
   
	 //关联摄像机编号
    private  String videoCode;
   
	 //抓拍车辆图片
    private  String imgPath;
   
	 //车辆登记照片（陌生车辆为第一次抓拍）
    private  String firstImgPath;
   
	 //车牌号码
    private  String carNum;
   
	 //创建时间
    private  String createTime;
    
    //动态库名称
    private  String dynamicDbname;
}
