package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ForecastUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 342917899462874074L;
	private String date; // 年月日 （新增）
	private String ymd; // 星期 （新增）
	private String week;
	private String sunrise;
	private String high;
	private String low;
	private String sunset;
	private int aqi;
	private String quality;// 空气质量
	private String fx;
	private String fl;
	private String type;
	private String notice;
	private String dayName;

	public void setHigh(String high) {
		this.high = high.replace("高温", "").trim();
	}

	public void setLow(String low) {
		this.low = low.replace("低温", "").trim();
	}

	public void setAqi(int aqi) {
		this.aqi = aqi;
		if (aqi > 0 && aqi < 51) {
			setQuality("优");
		} else if (aqi >= 51 && aqi < 101) {
			setQuality("良");
		} else if (aqi >= 101 && aqi < 151) {
			setQuality("轻度污染");
		} else if (aqi >= 151 && aqi < 201) {
			setQuality("中度污染");
		} else if (aqi >= 251 && aqi < 301) {
			setQuality("重度污染");
		} else if (aqi >= 301 && aqi < 501) {
			setQuality("严重污染");
		} else if (aqi >= 501) {
			setQuality("爆表");
		}
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

}
