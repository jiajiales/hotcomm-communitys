package com.hotcomm.community.common.bean.ui.device.posture;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireDeviceYearMsgUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7617556450027457505L;
	private String moduleName;// 设备名称
	private Integer nowYearNum;// 今年设备数
	private double yearOnYear = 0;// 同比增长
	private Integer moduleId;
}
