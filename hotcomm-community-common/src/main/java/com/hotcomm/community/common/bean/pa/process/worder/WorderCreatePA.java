package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;
import java.util.Map;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderCreatePA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	//工单来源 1:报警 2:事件 3:设备 4:其他
	private Integer sourceType;
	//工单标题
	private String orderTitle;
	//详细说明
	private String orderDesc;
	//要求处理时间（关联表ID）
	private Integer orderTimeInfo;
	//处理人
	private Integer handleUserId;
	//创建人
	private Integer userId;
	
	private String alarmId;
	private String eventIdStr;
	private String deviceBeanJson;
	
	private String devType;
	private Integer devId;
	private String devIdStr;
	private String devNum;
	private Integer moduleId;
	private String devAddress;
	private String address;
	
	private Integer deviceModule;
	private String sourceId;
	private String sourceTitle;
	private String sourceInfo;
	private String sourceCreateTime;
	private String sourceStateName;
	
    private String eventCreateUser;
	
	private Map<String, Object> map;
	
	private Integer worderId;
	
	private String selectStartTime;
	private String selectEndTime;
	private String selectWord;
	private String mac;
	private Integer queryType;
	
	private Integer eventType;
	private Integer emergencyLevel;
	
	private String lng;
    private String lat;
	
}
