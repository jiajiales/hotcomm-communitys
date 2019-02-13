package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class VideoDeviceListUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8308311533083042528L;
	private Integer id;
	private String mac;
	private String devNum;
	private String userName;
	private String passWord;
	private String videoIp;
	private String nvrIp;
	private String videoPort;
	private String port;
	private String channel;
	private String lat;
	private String lon;
	private Double m;
	private String code;
	private Integer state;
	private Integer devType;
}
