package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassRecordsPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -1631620805567045279L;

    //车辆类型（0:小区车辆，1：单位车辆，2:其他登记车,3：陌生车）
    private  Integer carType;
    private  Integer labelId;
    private String startTime;
    private String endTime;
    private String num;
    private  Integer passType;
    private String adress ;
    private String  color;
    private String brand;
    private String modelType;

}
