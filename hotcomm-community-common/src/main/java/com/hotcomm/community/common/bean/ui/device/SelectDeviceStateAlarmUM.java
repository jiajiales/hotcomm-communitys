package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateAlarmUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1500139076023273639L;
	private String moduleName;// 设备名称
	private Integer alarm;// 报警数
	private double alarmPercent = 0;// 报警数百分比
}
