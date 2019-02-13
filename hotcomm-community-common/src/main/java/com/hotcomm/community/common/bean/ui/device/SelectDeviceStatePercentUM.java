package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStatePercentUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2909131020083801050L;
	private Integer normal;// 正常设备数
	private Integer offLine;// 离线设备数
	private Integer fault;// 故障设备数
	private double normalPercent = 0;// 正常设备数百分比
	private double offlinePercent = 0;// 离线设备数百分比
	private double faultPercent = 0;// 故障设备数百分比
}
