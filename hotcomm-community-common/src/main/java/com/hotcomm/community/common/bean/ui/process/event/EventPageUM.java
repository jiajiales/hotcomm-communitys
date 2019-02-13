package com.hotcomm.community.common.bean.ui.process.event;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EventPageUM implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7281671567753277653L;

	//事件描述
	private String eventDesc;
		
	//事件类型
	private String sourceType;
		
	//紧急程度(由关联时间带出来)
	private String eventGrade;
	
	//上报人
	private String reporteName;
		
	//上报时间
	private String reporteTime;
		
	//地址
	private String sourceAddress;
		
	//处理人
	private String handleUser;
		
	//事件状态
	private String eventState;
	
	//事件已处理的关联工单编号
	private String worderNo;
	
	private Integer id;
	private String eventNo;
	private String handleEnd;
	private String lat;
	private String lng;
	private String pageState;
	private String worderId;
	private String worderPageState;
	private Integer reportType;
}
