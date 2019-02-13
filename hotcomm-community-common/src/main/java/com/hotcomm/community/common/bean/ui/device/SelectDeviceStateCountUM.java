package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateCountUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3828546868307434159L;
	private Integer devCount;// 设备总数
	private Integer alarmCount;// 本月设备报警数
	private Integer offLineCount;// 当前离线设备数
	private Integer faultCount;// 本月故障设备数
}
