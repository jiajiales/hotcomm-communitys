package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateDevUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7345693508607121490L;
	private String moduleName;// 设备名称
	private Integer normal;// 正常设备数
	private Integer offLine;// 离线设备数
	private Integer fault;// 故障设备数
}
