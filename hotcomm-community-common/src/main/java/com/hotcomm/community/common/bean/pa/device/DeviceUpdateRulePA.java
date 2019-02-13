package com.hotcomm.community.common.bean.pa.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceUpdateRulePA implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7259270342570363587L;
	private Integer id;// 预警方案id
	private Integer alarmLevel;// 级别
	private String alarmVaule;// 预设报警值
	private Integer isOpen;// 是否开启，0关闭，1开启
}
