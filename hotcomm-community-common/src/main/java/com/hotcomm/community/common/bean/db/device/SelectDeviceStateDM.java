package com.hotcomm.community.common.bean.db.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceStateDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6473532563619051025L;
	private String moduleName;// 设备名称
	private Integer devNum = 0;// 设备总数
	private Integer normal = 0;// 正常设备数
	private Integer offLine = 0;// 离线设备数
	private Integer fault = 0;// 故障设备数
	private Integer alarm = 0;// 报警数
}
