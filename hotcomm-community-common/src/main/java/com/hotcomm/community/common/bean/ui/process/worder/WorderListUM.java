package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderListUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 9034262597286404704L;

	// 工单编号
	private Integer id;
	private String orderNo;

	// 工单标题
	private String orderTitle;

	// 要求处理时间
	private String timeConfine;

	// 创建时间
	private String startTime;

	// 来源地址 楼栋名称,楼层名称,经纬度
	private String sourceAddress;

	// 工单状态 1:待处理 2:处理中 3:已处理 4:挂起
	private String orderStatus;

	// 处理完成时间
	private String handleEnd;

	// 处理人姓名
	private String handleUser;
	
	//关联信息(报警编号,事件编号之类的)
	private String information;
	
	private Integer pageState;
	
	private String lat;
	
	private String lng;
	
	private String creatUser;
	
	private String infoType;
	
	private String infoId;
	
	private String infoPageState;

}
