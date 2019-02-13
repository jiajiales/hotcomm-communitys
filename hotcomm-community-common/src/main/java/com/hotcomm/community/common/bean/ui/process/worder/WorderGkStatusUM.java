package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;
 

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderGkStatusUM implements Serializable {
	//本月工单处理状态
	/**
	 * 
	 */
	private static final long serialVersionUID = 5600828413557257472L;
	
	//工单总数
	private Integer orderCount;
	
//	//工单状态 1:待处理 2:处理中 3:已处理 4:挂起
//	private String state;
//	
//	//工单状态统计计数
//	private Integer countByType;
	private Integer pendingCount;
	private Integer hungCount;
	private Integer processCount;

	

}
