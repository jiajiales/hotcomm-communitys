package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.service.car.CarAlarmRuleService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarAlarmRuleController {

	@Autowired
	private CarAlarmRuleService carAlarmRuleService;
	
	//分页查询车辆预警规则列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_RULE_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_RULE_PAGELIST_FUN)
	@LogEvent(code = "CAR00118")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult pageList(CarAlarmRulePagePA carAlarmRulePagePA) {
		PageInfo<CarAlarmRuleUM> pageInfo = carAlarmRuleService.page(carAlarmRulePagePA);
		return ApiResult.success(pageInfo);
	}
	
	@Deprecated
	//新增车辆预警规则
	@RequestMapping(value = { RestfulUrlRecord.CAR_RULE_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_RULE_INSERT_FUN)
	@LogEvent(code = "CAR00119")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult insert(CarAlarmRulePA carAlarmRulePA) {
		try {
			Integer id = carAlarmRuleService.insert(carAlarmRulePA);
			return ApiResult.success(id);
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//删除车辆预警规则
	@RequestMapping(value = { RestfulUrlRecord.CAR_RULE_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_RULE_DELETE_FUN)
	@LogEvent(code = "CAR00120")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult delete(CarAlarmRulePA carAlarmRulePA) {
		try {
			return ApiResult.success(carAlarmRuleService.delete(carAlarmRulePA.getCommunityId(),carAlarmRulePA.getRuleId()));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//修改车辆预警规则
	@RequestMapping(value = { RestfulUrlRecord.CAR_RULE_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_RULE_UPDATE_FUN)
	@LogEvent(code = "CAR00121")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult update(CarAlarmRulePA carAlarmRulePA) {
		try {
			return ApiResult.success(carAlarmRuleService.update(carAlarmRulePA));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查询车辆预警规则详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_RULE_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_RULE_DETAIL_FUN)
	@LogEvent(code = "CAR00122")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult detail(CarAlarmRulePA carAlarmRulePA) {
		CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(carAlarmRulePA);
		return ApiResult.success(carAlarmRuleUM);
	}
}
