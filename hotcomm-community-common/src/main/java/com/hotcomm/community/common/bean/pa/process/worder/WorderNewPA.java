package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderNewPA extends CommunityParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 389465693262251842L;
	
	//工单来源 1:报警 2:事件 3:设备 4:其他
	private String sourceType;
	
	//工单标题
	private String orderTitle;
	
	//详细说明
	private String orderDesc;
	
	//时间限制 1:立即 2:1小时 3:2小时  参考字典表或者枚举
	private Integer timeConfine;

	//处理人姓名
	private String handleUserName;
	
	//报警标题
	private String alarmTitle;
	
	//报警地址
	private String alarmAddress;
	
	//报警时间
	private Integer alarmTime;
	
	//报警状态
	private String alarmState;
	
	//设备类型
	private String devType;
	
	//设备编号
	private String devCode;
	
	//设备地址
	private String devAddress;
	
	//时间标题
	private String eTitle;
	
	//事件地址
	private String eAddress;
	
	//事件上报时间
	private Integer eTime;
	
	//事件上报人
	private String eName;
	
	//事件状态
	private String eState;
	
	//工单-关联编号 例如事件编号或者报警编号
	private String sourceId;
	
	private Integer sourceIdToInsert;
	
}
