package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevicePageUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4183203483316184170L;
	private Integer id;
	private String mac;
	private String devNum;
	private String code;
	private String lat;
	private String lon;
	private String userName;
	private Integer state;
	private double battery;
	private Integer videoNum;
	private String devType;
	private Integer alarmVaule;
	private String installTime;
}
