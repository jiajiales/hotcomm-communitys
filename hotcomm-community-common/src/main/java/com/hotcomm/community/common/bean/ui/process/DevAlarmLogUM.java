package com.hotcomm.community.common.bean.ui.process;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevAlarmLogUM implements Serializable {
	
	private static final long serialVersionUID = -4183203483316184170L;
	private String errorCause;//故障原因
	private String alarmLevel;//报警等级
	private String alarmTime;//报警事件
	private String alarmState;//报警状态
	private String alarmValue;//报警值
	
	private String handleTime;//处理时间
	private String handleMode;//处理方式
}
