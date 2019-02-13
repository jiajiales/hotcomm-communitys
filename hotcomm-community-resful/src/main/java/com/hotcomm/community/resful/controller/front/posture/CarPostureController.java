package com.hotcomm.community.resful.controller.front.posture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.car.CarPassCountPA;
import com.hotcomm.community.common.service.car.CarPostureServcie;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarPostureController {

	@Autowired
	private CarPostureServcie carPostureServcie;
	
	/**统计今日车辆出、入次数、外来车辆总数、登记车辆总数、今日黑名单总数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_TODAY_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_TODAY_COUNT_FUN)
	@LogEvent(code = "CAR00138")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCarTotalCount(CommunityParams communityParams) {
		return ApiResult.success(carPostureServcie.selectTodayCarCount(communityParams.getCommunityId()));
	}
	
	/**按小时查询最一个月、一年车辆进、出次数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_ENTER_OUT_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_ENTER_OUT_COUNT_FUN)
	@LogEvent(code = "CAR00139")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCarTotalCount(CarPassCountPA carPassCountPA) {
		return ApiResult.success(carPostureServcie.selectMonYearCarTotalPassCount(carPassCountPA));
	}
	
	/**按天数查询最一个月、一年停车车辆数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_PARKING_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_PARKING_COUNT_FUN)
	@LogEvent(code = "CAR00140")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectParkingCount(CarPassCountPA carPassCountPA) {
		return ApiResult.success(carPostureServcie.selectPostureParkingCount(carPassCountPA));
	}
	
	
	/**统计关注车辆次数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_ATTENTION_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_ATTENTION_COUNT_FUN)
	@LogEvent(code = "CAR00142")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCentralCount(CarPassCountPA carPassCountPA) {
		return ApiResult.success(carPostureServcie.selectAttentionCount(carPassCountPA));
	}

	/**报警级别统计*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_ALARM_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_ALARM_COUNT_FUN)
	@LogEvent(code = "CAR00143")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectAlarmLevelCount(CarPassCountPA carPassCountPA) {
		return ApiResult.success(carPostureServcie.selectAlarmLevelCount(carPassCountPA));
	}

	/**查询本周通行车辆数、通行次数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_POS_WEEK_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_POS_WEEK_COUNT_FUN)
	@LogEvent(code = "CAR00144")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectPosCarWeekCount(Integer communityId) {
		return ApiResult.success(carPostureServcie.selectPosCarWeekCount(communityId));
	}

}

