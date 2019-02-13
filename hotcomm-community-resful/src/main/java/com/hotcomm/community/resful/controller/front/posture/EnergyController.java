package com.hotcomm.community.resful.controller.front.posture;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAirQualityRank;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllLeft;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllMiddle;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllMsgCount;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyAllRight;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyCurveOfAqiByWeek;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyCurveOfHumidityByHour;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyCurveOfNoiseByHour;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyCurveOfPmByHour;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.EnergyCurveOfTemperatureByHour;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.LongLatitude;
import com.hotcomm.community.common.bean.ui.device.energy.energyall.LongLatitudeF;
import com.hotcomm.community.common.bean.ui.device.energy.energyelectricity.EnergyElectricityLeft;
import com.hotcomm.community.common.bean.ui.device.energy.energyelectricity.EnergyElectricityRight;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class EnergyController {

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLLEFT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLLEFT_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult energyAllLeft() {
		return ApiResult.success(new EnergyAllLeft());
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLRIGHT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLRIGHT_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult energyAllRight(String name) {
		return ApiResult.success(new EnergyAllRight(name));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENERGY_ENERGYELECTRICITYLEFT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENERGY_ENERGYELECTRICITYLEFT_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult energyElectricityLeft() {
		return ApiResult.success(new EnergyElectricityLeft());
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENERGY_ENERGYELECTRICITYRIGHT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENERGY_ENERGYELECTRICITYRIGHT_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult energyElectricityRight() {
		return ApiResult.success(new EnergyElectricityRight());
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLMIDDLE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_ENERGY_ENERGYALLMIDDLE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult energyAllMiddle(@RequestBody String data) {
		LongLatitudeF geoCoordinateListF = new Gson().fromJson(data, LongLatitudeF.class);
		LongLatitude latitude = GetCenterPointFromListOfCoordinates(geoCoordinateListF.getData());
		return ApiResult
				.success(new EnergyAllMiddle(geoCoordinateListF.getName(), latitude.getLat(), latitude.getLon()));
	}

	/**
	 * 计算多个经纬度的中间点经纬度 -为简化方法（400km以内）
	 * 
	 * @param geoCoordinateList
	 * @return
	 */
	public LongLatitude GetCenterPointFromListOfCoordinates(List<LongLatitude> geoCoordinateList) {
		int total = geoCoordinateList.size();
		double lat = 0, lon = 0;
		for (LongLatitude g : geoCoordinateList) {
			lat += g.getLat() * Math.PI / 180;
			lon += g.getLon() * Math.PI / 180;
		}
		lat /= total;
		lon /= total;
		return new LongLatitude(lat * 180 / Math.PI, lon * 180 / Math.PI);
	}

	@RequestMapping(value = { RestfulUrlRecord.AIR_QUALITY_RANK }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.AIR_QUALITY_RANK_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult airQualityRank() {
		return ApiResult.success(new EnergyAirQualityRank());
	}

	@RequestMapping(value = { RestfulUrlRecord.PM_HOUR_CURVE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PM_HOUR_CURVE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult curveOfPmByHour() {
		return ApiResult.success(new EnergyCurveOfPmByHour());
	}

	@RequestMapping(value = { RestfulUrlRecord.TEMPERATURE_CURVE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.TEMPERATURE_CURVE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult curveOfTemperatureByHour() {
		return ApiResult.success(new EnergyCurveOfTemperatureByHour());
	}

	@RequestMapping(value = { RestfulUrlRecord.HUMIDITY_CURVE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HUMIDITY_CURVE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult curveOfHumidityByHour() {
		return ApiResult.success(new EnergyCurveOfHumidityByHour());
	}

	@RequestMapping(value = { RestfulUrlRecord.NOISE_CURVE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.NOISE_CURVE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult curveOfNoiseByHour() {
		return ApiResult.success(new EnergyCurveOfNoiseByHour());
	}

	@RequestMapping(value = { RestfulUrlRecord.AQI_CURVE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.AQI_CURVE_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult curveOfAqiByWeek() {
		return ApiResult.success(new EnergyCurveOfAqiByWeek());
	}

	@RequestMapping(value = { RestfulUrlRecord.ENERGY_ENERGYMSGCOUNT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ENERGY_ENERGYMSGCOUNT_FUN)
	@LogEvent(code = "")
	@LogSkip
	public ApiResult EnergyAllMsgCount() throws Throwable {
		return ApiResult.success(new EnergyAllMsgCount());
	}

}
