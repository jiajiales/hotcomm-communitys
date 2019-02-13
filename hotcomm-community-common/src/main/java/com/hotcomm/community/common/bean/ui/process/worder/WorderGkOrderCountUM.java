package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;

 

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderGkOrderCountUM implements Serializable {
	//本月工单数量
	/**
	 * 
	 */
	private static final long serialVersionUID = 301345588636088196L;
	
	//统计每天的工单数
	private Integer orderCount;
	
	//每一天的日期
	private String timeInfo;

}
