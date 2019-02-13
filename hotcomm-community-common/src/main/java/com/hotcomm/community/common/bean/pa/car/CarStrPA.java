package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarStrPA extends PageParams implements Serializable {

	private static final long serialVersionUID = -6353323258130540698L;

	 //陌生车辆ID
    private  Integer id;  
   
	 //车牌号
    private  String num;
   
	 //颜色
    private  String color;
   
	 //品牌
    private  String brand;
    
	 //车型
    private  String modelType;
   
	 //车辆状态(0：驶入，1：离开)
    private  Integer state;
    
	 //标签ID
    private  Integer labelId;
    
    private String dynamicDbname;
    
}
 