package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateTypeUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1719202243530691089L;
	private String moduleName;// 设备名称
	private Integer devNum;// 设备总数
}
