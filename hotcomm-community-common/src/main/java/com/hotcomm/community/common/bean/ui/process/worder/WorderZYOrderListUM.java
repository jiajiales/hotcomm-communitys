package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderZYOrderListUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5689775803507681867L;
	
	//每一天的日期
	private String dateInfo;
	
	//统计每天的工单及时处理率
	private Integer percent;
	
	//统计每天的工单总数
	private Integer worderCount;

}
