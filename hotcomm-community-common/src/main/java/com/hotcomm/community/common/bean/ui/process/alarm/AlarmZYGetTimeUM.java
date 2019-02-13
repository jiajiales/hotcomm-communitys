package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmZYGetTimeUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3262708911429124768L;
	
	private String alarmMonday;//时间段
	
	private String MondayCount;//时间段的数量统计

}
