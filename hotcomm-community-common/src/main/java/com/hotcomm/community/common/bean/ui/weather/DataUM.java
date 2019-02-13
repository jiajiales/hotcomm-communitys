package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DataUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6399137871919311113L;
	private String shidu;// 湿度
	private int pm25;// pm2.5
	private int pm10;// pm10
	private String quality;// 空气质量
	private String wendu;// 温度
	private String ganmao;// 感冒提醒（指数）
	private YesterdayUM yesterday;// 昨天天气
	private List<ForecastUM> forecast;// 今天+未来4天
}
