package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceStateMsgUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4274498041876943485L;

	private Integer normal;
	private Integer offLine;
	private Integer fault;
	private double normalPercent = 0;// 正常设备数百分比
	private double offlinePercent = 0;// 离线设备数百分比
	private double faultPercent = 0;// 故障设备数百分比
}
