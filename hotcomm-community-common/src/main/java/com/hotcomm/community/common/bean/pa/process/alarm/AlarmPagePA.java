package com.hotcomm.community.common.bean.pa.process.alarm;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private Integer alarmType;
	private Integer moduleId;
	private String startTime;
	private String endTime;
	private Integer handelState;
	private String alarmMessage;
	private Integer handelUser;
	private Integer isDispatch;
	private	Integer pageType;
	private Integer getCarInfo;
	
}
