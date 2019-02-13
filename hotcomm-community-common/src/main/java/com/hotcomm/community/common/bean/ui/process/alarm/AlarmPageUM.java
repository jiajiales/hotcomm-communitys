package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmPageUM implements Serializable {
	
	private static final long serialVersionUID = -4183203483316184170L;
	private String alarmMessage;
	private String alarmType;
	private String alarmLevel;
	private String createTime;
	private String alarmSource;
	private String alarmAddress;
	private String handleUserName;
	private String state;
	private String handleResult;
	private String isDispatch;
	private String handleEnd;
	private String worderNo;
	private Integer alarmID;
	private Integer pageState;
	private Integer canvaType;
	private Integer canvaProgress;
	private String lat;
	private String lng;
	private String alarmTypeOfWorder;
	private String descOfWorder;
	private String worderPageState;
	private String worderId;
	private Integer reportType;
	
}
