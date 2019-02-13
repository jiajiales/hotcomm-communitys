package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;

 

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderGkUM implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4584984727472312042L;
	
	//本月工单总数
	private Integer orderCount;
	
	//本月待处理工单
	private Integer waitingOrder;
	
	//本月工单及时处理率
	private String percent;
	
}
