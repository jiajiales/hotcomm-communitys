package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarRegPagePA extends PageParams  implements Serializable {

	private static final long serialVersionUID = 4619642560747532458L;
	
	//车辆类型（0:小区车辆，1：单位车辆，2：其他登记车）
    private  Integer type;
    private  Integer  personId;
    private  Integer labelId;
    private String startTime;
    private String endTime;
    private String num;
    private Integer labelTypeId;
    private String color;
    private String modelType;
    private String brand;
    private Integer roomId;
    private Integer companyId;

}
