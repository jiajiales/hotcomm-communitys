package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class YesterdayUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7102021275119818218L;
	private String date;
	private String ymd;
	private String week;
	private String sunrise;
	private String high;
	private String low;
	private String sunset;
	private int aqi;
	private String fx;
	private String fl;
	private String type;
	private String notice;
}
