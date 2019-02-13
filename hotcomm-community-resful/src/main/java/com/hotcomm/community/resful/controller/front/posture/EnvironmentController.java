package com.hotcomm.community.resful.controller.front.posture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.ui.weather.EnvironmentLeftUM;
import com.hotcomm.community.common.bean.ui.weather.WeatherUM;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.utils.RedisHelper;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class EnvironmentController {

	@Autowired
	RedisHelper redisHelper;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENVIRONMENT_ENVIRONMENTLEFT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENVIRONMENT_ENVIRONMENTLEFT_FUN)
	@LogEvent(code = "")
	public ApiResult environmentLeft(String cityId) {
		String weather = redisHelper.get(cityId);
		Gson gson = new Gson();
		WeatherUM weatherUM = new WeatherUM();
		if (weather != null) {// 该城市天气数据存在
			// 将数据转为实体类
			weatherUM = gson.fromJson(weather, WeatherUM.class);
		} else {// 该城市天气数据不存在
			// 获取城市天气数据
			weather = Weather(cityId);
			// 如果获取城市数据等于null，则获取失败
			if (weather == null)
				return ApiResult.success("请求天气数据失败");
			// 将数据转为实体类
			weatherUM = gson.fromJson(weather, WeatherUM.class);
			// 状态等于200才是请求成功
			if (weatherUM.getStatus() != 200)
				return ApiResult.success("请求天气数据失败");
			// 请求数据成功，将数据存进redis，存活30分钟
			redisHelper.set(cityId, weather, 1800);
		}
		EnvironmentLeftUM eLeftUM = new EnvironmentLeftUM();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wendu", weatherUM.getData().getWendu());
		map.put("high", weatherUM.getData().getForecast().get(0).getHigh().replace("高温", "").trim());
		map.put("low", weatherUM.getData().getForecast().get(0).getLow().replace("低温", "").trim());
		map.put("quality", weatherUM.getData().getQuality());
		map.put("type", weatherUM.getData().getForecast().get(0).getType());
		map.put("shidu", weatherUM.getData().getShidu());
		map.put("pm25", weatherUM.getData().getPm25());
		map.put("pm10", weatherUM.getData().getPm10());
		map.put("aqi", weatherUM.getData().getForecast().get(0).getAqi());

		eLeftUM.setToDay(map);
		eLeftUM.setFiveDay(weatherUM.getData().getForecast());
		return ApiResult.success(eLeftUM);
	}

	/**
	 * 请求天气接口
	 * 
	 * @throws IOException
	 */
	public static String Weather(String cityName) {
		String result = null;
		try {
			// 参数url化
			String city = URLEncoder.encode(cityName, "utf-8");
			// 拼地址
			String apiUrl = String.format("http://t.weather.sojson.com/api/weather/city/%s", city);
			// 开始请求
			URL url = new URL(apiUrl);
			URLConnection open = url.openConnection();
			InputStream input = open.getInputStream();
			// 这里转换为String
			result = IOUtils.toString(input, "utf-8");
			// 输出
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
	}

	public static void main(String[] args) {
		Weather("101030100");
	}
}
