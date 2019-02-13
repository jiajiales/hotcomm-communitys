package com.hotcomm.community.common.bean.ui.device.doorcontrol;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DevAttrUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String devType;
	private String state;
	private String localType;
	private String devTrademark;
	private String devNum;
	private String mac;
	private String faceCaptureOnOff;
	private String isDoorLock;
	private String faceDetectSupport;
	private String faceOpenDoorOnOff;

}
