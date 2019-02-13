package com.hotcomm.community.common.bean.pa.process.event;



import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EventPagePA extends PageParams implements Serializable {
	//事件列表
	/**
	 * 
	 */
	private static final long serialVersionUID = 406547789145554930L;
	
	//事件id
	private Integer id;
	
	//事件编号(根据自己定义)
	private String eventNo;
	
	//事件描述
	private String eventDesc;
	
	//事件类型
	private String sourceType;
	
	//紧急程度(由关联时间带出来)
	private String eventGrade;
	
	//上报人
	private String reporteName;
	
	//上报时间
	private String startTime;
	
	//处理完成时间
	private String handleEnd;
	
	//地址
	private String sourceAddress;
	
	//处理人
	private Integer handleUser;
	
	//事件状态
	private Integer eventStatus;
	
	//显示列表类型(0.列表 1.日志---工单历史记录)
	private Integer pageType;
	
	//事件已处理的关联工单编号
	private String worderNo;
	
	private Integer pageState;
	
	private String eventState;

}
