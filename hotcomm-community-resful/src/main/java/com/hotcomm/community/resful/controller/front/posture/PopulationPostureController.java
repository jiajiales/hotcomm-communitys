package com.hotcomm.community.resful.controller.front.posture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.person.service.PopulationPostureService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PopulationPostureController {

	@Autowired
	private PopulationPostureService population;
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFYEAR }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFYEAR_FUN)
	@LogEvent(code = "PM00207")
	public ApiResult getPassInfoOfYear(Integer communityId) {
		return ApiResult.success(population.getPassInfoOfYear(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_TREND }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_TREND_FUN)
	@LogEvent(code = "PM00208")
	public ApiResult getPopulationTrend(Integer communityId) {
		return ApiResult.success(population.getPopulationTrend(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_RATIO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_RATIO_FUN)
	@LogEvent(code = "PM00209")
	public ApiResult getLalePopulationRatio(Integer communityId) {
		return ApiResult.success(population.getLalePopulationRatio(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_FLOORS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_FLOORS_FUN)
	@LogEvent(code = "PM00210")
	public ApiResult getPersonNumByfloors(Integer communityId) {
		return ApiResult.success(population.getPersonNumByfloors(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_PASS_REALTIME }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_PASS_REALTIME_FUN)
	@LogEvent(code = "PM00211")
	public ApiResult getPassRealTime(Integer communityId) {
		return ApiResult.success(population.getPassRealTime(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_PASS_MESSEND }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_PASS_MESSEND_FUN)
	@LogEvent(code = "PM00212")
	public ApiResult PersonMesSend() {
		population.PersonMessageSend(null);
		return ApiResult.success();
	}
}
