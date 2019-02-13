package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderGkProRateUM implements Serializable {
	//工单处理及时率趋势
	/**
	 * 
	 */
	private static final long serialVersionUID = -1916597227260164745L;

	//本月工单及时处理率
	private String percent;
	
	//每一天的日期
	private String orderDate;
}
