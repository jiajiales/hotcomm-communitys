package com.hotcomm.community.resful.controller.front.feel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.service.car.CarFeelService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarFeelController {

	@Autowired
	private CarFeelService carFeelService;
	
	/**车辆基础信息统计*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_FEEL_INFO_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_FEEL_INFO_COUNT_FUN)
	@LogEvent(code = "CAR00132")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCarTotalCount(CommunityParams communityParams) {
		return ApiResult.success(carFeelService.selectCarTotalCount(communityParams.getCommunityId()));
	}
	
	/**按小时统计今日车辆通行次数、车辆数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_FEEL_CAR_PASS_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_FEEL_INFO_COUNT_FUN)
	@LogEvent(code = "CAR00133")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCarPassHoursCount(CommunityParams communityParams) {
		return ApiResult.success(carFeelService.selectCarPassHoursCount(communityParams.getCommunityId()));
	}
	
	/**查询车辆当天不同报警类型数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_FEEL_AlARM_TYPE_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_FEEL_AlARM_TYPE_COUNT_FUN)
	@LogEvent(code = "CAR00134")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectAlarmTypeCount(CommunityParams communityParams) {
		return ApiResult.success(carFeelService.selectAlarmTypeCount(communityParams.getCommunityId()));
	}
		
	
	/**查询当天通行车辆数、通行次数、通行记录*/
	@Deprecated
	@RequestMapping(value = { RestfulUrlRecord.CAR_FEEL_PASS_DAY_COUNT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_FEEL_PASS_DAY_COUNT_FUN)
	@LogEvent(code = "CAR00136")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectCarPassDayCount(CommunityParams communityParams) {
		return ApiResult.success(carFeelService.selectCarPassDayCount(communityParams.getCommunityId()));
	}
	
	/**查询本周报警记录、本周通行次数*/
	@RequestMapping(value = { RestfulUrlRecord.CAR_FEEL_ALARM_LIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_FEEL_ALARM_LIST_FUN)
	@LogEvent(code = "CAR00137")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult selectAlarmList(CommunityParams communityParams) {
		return ApiResult.success(carFeelService.selectAlarmList(communityParams.getCommunityId()));
	}
}
