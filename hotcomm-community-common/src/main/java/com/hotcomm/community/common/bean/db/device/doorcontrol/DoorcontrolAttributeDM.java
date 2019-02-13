package com.hotcomm.community.common.bean.db.device.doorcontrol;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DoorcontrolAttributeDM {
	
	public DoorcontrolAttributeDM(){}
	
	public DoorcontrolAttributeDM(Integer id, Integer installSate, Boolean faceCaptureOnOff, Boolean faceDetectSupport,
			Boolean faceOpenDoorOnOff, Integer isDoorLock, Date update_time, String guid, Integer localType) {
		super();
		this.id = id;
		this.installSate = installSate;
		this.faceCaptureOnOff = faceCaptureOnOff;
		this.faceDetectSupport = faceDetectSupport;
		this.faceOpenDoorOnOff = faceOpenDoorOnOff;
		this.isDoorLock = isDoorLock;
		this.update_time = update_time;
		this.guid = guid;
		this.localType = localType;
	}
	
	private Integer id;
	private	Integer installSate;
	private	Boolean faceCaptureOnOff;
	private	Boolean faceDetectSupport;	
	private	Boolean faceOpenDoorOnOff;
	private	Integer isDoorLock;	
	private	Date update_time;
	private String guid;
	private Integer localType;
	
}
