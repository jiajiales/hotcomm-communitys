package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderListPA extends PageParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241970397011785278L;
	
	//工单编号
	private Integer id;
	
	//工单编号 系统按规则自定义
	private String orderNo;
	
	//工单标题
	private String orderTitle;
	
	//要求处理时间
	private String orderInfo;
	
	//创建时间
	private String startTime;
	
	//处理完成时间
	private String handleEnd;
	
	//地址
	private String sourceAddress;
	
	//创建人姓名
	private String creatUser;
	
	//处理人姓名
	private String handleUser;
	
	//工单状态 1:待处理 2:处理中 3:已处理 4:挂起
	private String orderStatus;
	
	//工单关联 1.报警 2.事件 3.设备
	//private String sourceType;
	
	//关联信息(报警编号,事件编号之类的)
	private String information;
	
	//显示列表类型(0.列表 1.日志---工单历史记录)
	private Integer pageType;
	
	private Integer pageState;

}
