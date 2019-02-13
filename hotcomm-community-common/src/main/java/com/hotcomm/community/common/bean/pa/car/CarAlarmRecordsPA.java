package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRecordsPA extends PageParams implements Serializable {

	private static final long serialVersionUID = -3374429034475996428L;
	
	private Integer id;
    private  Integer carType;//车辆类型（0:小区车辆，1：单位车辆，2:其他登记车,3：陌生车）
    private String startTime;
    private String endTime;
    private String  state;//报警状态（0：待处理，1：处理中，2：已处理，3：已关闭，4：已完成)
    private String  num;
    private Integer personId;
    
}
