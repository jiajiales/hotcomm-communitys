package com.hotcomm.community.common.bean.db.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlarmTypeDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7280782991502764455L;
	private Integer alarmNum = 0;
	private Integer fireAlarmNum = 0;
}
