package com.hotcomm.community.common.bean.db.process;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmTypeStateDM implements Serializable{

	private static final long serialVersionUID = -2053231418925581224L;

	private Integer alarmType;
	private Integer handelState;
	private Integer isDispatch;
	private String worderNo;

}
