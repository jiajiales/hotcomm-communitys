package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMsgWorkUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7483031999481724016L;
	private Integer id;// 设备id
	private String name;// 设备类型
	private String mac;// mac地址
	private String devAddress;// 设备地址
	private String lat;// 纬度
	private String lon;// 经度
	private Integer isAlarm;// 0没报警，1报警
}
