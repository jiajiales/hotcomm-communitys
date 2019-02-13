package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6473532563619051025L;
	private SelectDeviceStateCountUM count;// 设备总数,本月设备报警数,当前离线设备数,本月故障设备数统计
	private SelectDeviceStatePercentUM percent;// 设备状态分布
	private List<SelectDeviceStateDevUM> dev;// 各类设备状态分布
	private List<SelectDeviceStateTypeUM> type;// 设备类型分布
	private List<SelectDeviceStateAlarmUM> alarm;// 本月报警设备分布
}
