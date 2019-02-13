package com.hotcomm.community.common.bean.ui.device.postureSynthesize;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectRecentlySevenDaysUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8630409031206037760L;
	private Integer alarmNum = 0;
	private String week;

	public void setWeek(String week) {
		switch (week) {
		case "0":
			week = "周日";
			break;
		case "1":
			week = "周一";
			break;
		case "2":
			week = "周二";
			break;
		case "3":
			week = "周三";
			break;
		case "4":
			week = "周四";
			break;
		case "5":
			week = "周五";
			break;
		case "6":
			week = "周六";
			break;
		default:
			break;
		}
		this.week = week;
	}

}
