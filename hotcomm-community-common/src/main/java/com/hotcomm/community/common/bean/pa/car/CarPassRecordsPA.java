package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassRecordsPA extends PageParams implements Serializable {

	private static final long serialVersionUID = 6176580706007013441L;

	 //记录ID
    private  Integer id;
   
	 //车辆标签关联ID（uuid）
    private  String uuid;
   
	 //车辆类型（0:小区车辆，1：单位车辆，2:其他登记车,3：陌生车）
    private  Integer carType;
   
	 //出入类型(0:驶入，1:驶出，2:经过)
    private  Integer passType;
   
	 //出现地点
    private  String adress;
   
    //颜色
    private  String  color;
    
    //品牌
    private String brand;
    
    //型号
    private String model;
    
    //车型
    private String modelType;
    
    //摄像头的mac地址
    private  String videoCode;
    
    //摄像头抓拍车辆
    private String carImgBase64;
    
    //摄像头抓拍车牌号
    private String carNumImgBase64;
       
    private String firstImgPath;
   
    private String  ImgPath;
    
	 //车牌号码
    private  String carNum;
   
	 //创建时间
    private  String createTime;
    
    //标签ID
    private Integer labelId;
    
    //动态库名称
    private  String dynamicDbname;
}
