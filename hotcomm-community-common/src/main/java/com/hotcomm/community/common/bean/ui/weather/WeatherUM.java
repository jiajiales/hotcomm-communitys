package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WeatherUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5206187250788963918L;
	private String time;// 系统更新时间
	private CityInfoUM cityInfo;
	private String date;// 当前天气的当天日期
	private String message;// 返回message
	private int status;// 返回状态
	private DataUM data;
}
