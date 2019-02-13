package com.hotcomm.community.common.bean.ui.weather;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CityInfoUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2884179926312341567L;
	private String city;// 请求城市
	private String cityId;// 请求ID
	private String parent;// 上级，一般是省份
	private String updateTime;// 天气更新时间
}
