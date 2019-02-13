package com.hotcomm.community.common.bean.pa.car;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPassCountPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = 7358608649627047859L;
	
	private Integer  timeType;//0:最近30天，1：最近一年,2:最近一周

}
