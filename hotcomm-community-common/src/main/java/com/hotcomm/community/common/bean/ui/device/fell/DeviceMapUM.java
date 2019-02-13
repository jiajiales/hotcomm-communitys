package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMapUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -565511006797890685L;
	private Integer id;
	private String devType;
	private String devNum;
	private String mac;
	private String lat;
	private String lon;
	private String devAddress;
	private String installTime;
	private Integer moduleId = 5;// 默认摄像头信息
	private Integer state = 0;// 默认摄像头信息
	private String moduleName = "摄像头";// 默认摄像头信息
	private String userName;
	private String telephone;
}
