package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarToalCaseService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarToalCaseController {
	
	@Autowired
	private CarToalCaseService carToalCaseService;
	
	@Autowired
	private CarPassRecordsService carPassRecordsService;
	
	@Autowired
	private CarAlarmRecordsService carAlarmRecordsService;
	
	//查询车辆总况统计数（区域登记车辆、今日通行记录、本月报警、敏感时段车辆总数）
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_COUNT_FUN)
	@LogEvent(code = "CAR00125")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalToalCount(CommunityParams communityParams) {
		return  ApiResult.success(carToalCaseService.selectToalCount(communityParams.getCommunityId()));
	}
	
	
	//查询登记车辆结构总数（单位、小区、外来）
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_TYPE_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_TYPE_COUNT_FUN)
	@LogEvent(code = "CAR00126")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalTypeCount(CommunityParams communityParams) {
		return  ApiResult.success(carToalCaseService.selectTypeCount(communityParams.getCommunityId()));
	}
	
	//查询上个月份各时段进出车辆总数(通行时段分析)
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_MONTIME_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_MONTIME_COUNT_FUN)
	@LogEvent(code = "CAR00127")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalMonTimeCount(CommunityParams communityParams) {
		return  ApiResult.success(carPassRecordsService.selectMonTimeCount(communityParams.getCommunityId()));
	}
	
	//查询本月停车情况
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_PARKING_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_PARKING_COUNT_FUN)
	@LogEvent(code = "CAR00128")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalParkingCount(CommunityParams communityParams) {
		return  ApiResult.success(carPassRecordsService.selectMonParkingCount(communityParams.getCommunityId()));
	}
	
	//本月关注车辆通行记录统计
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_ATTENTION_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_ATTENTION_COUNT_FUN)
	@LogEvent(code = "CAR00129")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalAttentionCount(CommunityParams communityParams) {
		return  ApiResult.success(carPassRecordsService.selectAttentionCount(communityParams.getCommunityId()));
	}
	
	//本月车辆预警级别统计
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_ALARMLEVEL_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_ALARMLEVEL_COUNT_FUN)
	@LogEvent(code = "CAR00130")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalAlarmLevelCount(CommunityParams communityParams) {
		return  ApiResult.success(carAlarmRecordsService.selectAlarmLevelCount(communityParams.getCommunityId()));
	}
	
	//月报警统计（报警趋势）
	@RequestMapping(value = { RestfulUrlRecord.CAR_STATISTICAL_ALARMDAY_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STATISTICAL_ALARMDAY_COUNT_FUN)
	@LogEvent(code = "CAR00131")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult statisticalAlarmDayCount(CommunityParams communityParams) {
		return  ApiResult.success(carAlarmRecordsService.selectAlarmDayCount(communityParams.getCommunityId()));
	}
}
