package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarLabelPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = 4247954295074570123L;


	private  Integer typeId;//13:黑名单车辆,14:关注车辆,15:服务车辆,16:其他车辆
	private Integer labelSource;//0:系统，1:自定义
	private  Integer state;//0:启用，1:废弃
}
