package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class VideoDevicePageUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8308311533083042528L;
	private Integer id;
	private String mac;
	private String devNum;
	private String code;
	private String lat;
	private String lon;
	private String userName;
	private Integer state;
	private String devType;
	private String installTime;
}
