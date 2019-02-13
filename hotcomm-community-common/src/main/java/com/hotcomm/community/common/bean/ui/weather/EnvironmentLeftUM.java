package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EnvironmentLeftUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3283516957182522838L;
	private Map<String, Object> toDay = new HashMap<String, Object>();// 今天天气
	private List<ForecastUM> fiveDay;// 今天和未来5天空气质量预报

	public void setFiveDay(List<ForecastUM> fiveDay) {
		for (int i = 0; i < fiveDay.size(); i++) {

			fiveDay.get(i).setHigh(fiveDay.get(i).getHigh().replace("高温", "").trim());
			fiveDay.get(i).setLow(fiveDay.get(i).getLow().replace("低温", "").trim());

			int aqi = fiveDay.get(i).getAqi();
			if (aqi > 0 && aqi < 51) {
				fiveDay.get(i).setQuality("优");
			} else if (aqi >= 51 && aqi < 101) {
				fiveDay.get(i).setQuality("良");
			} else if (aqi >= 101 && aqi < 151) {
				fiveDay.get(i).setQuality("轻度污染");
			} else if (aqi >= 151 && aqi < 201) {
				fiveDay.get(i).setQuality("中度污染");
			} else if (aqi >= 251 && aqi < 301) {
				fiveDay.get(i).setQuality("重度污染");
			} else if (aqi >= 301 && aqi < 501) {
				fiveDay.get(i).setQuality("严重污染");
			} else if (aqi >= 501) {
				fiveDay.get(i).setQuality("爆表");
			}

			String dayName = "";
			switch (i) {
			case 0:
				dayName = "今天";
				break;
			case 1:
				dayName = "明天";
				break;
			case 2:
				dayName = "后天";
				break;
			case 3:
			case 4:
				dayName = fiveDay.get(i).getWeek();
				break;
			default:
				break;
			}
			fiveDay.get(i).setDayName(dayName);
		}
		this.fiveDay = fiveDay;
	}

}
