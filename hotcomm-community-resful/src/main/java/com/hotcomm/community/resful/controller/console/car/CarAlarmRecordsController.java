package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRecordsPA;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarAlarmRecordsController {

	@Autowired
	private CarAlarmRecordsService  carAlarmRecordsService;
	

	//新增车辆报警记录
	@Deprecated
	@RequestMapping(value = { RestfulUrlRecord.CAR_ARARM_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_ARARM_INSERT_FUN)
	@LogEvent(code = "CAR00124")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult ruleInsert(CarAlarmRecordsDM carAlarmRecordsDM) {
		try {
			Integer id = carAlarmRecordsService.insert(carAlarmRecordsDM);
			return ApiResult.success(id);
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//车辆预警详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_ARARM_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_ARARM_DETAIL_FUN)
	@LogEvent(code = "CAR00146")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult alarmDetail(CarAlarmRecordsPA carAlarmRecordsPA) {
		try {
			return ApiResult.success(carAlarmRecordsService.detail(carAlarmRecordsPA.getId(), carAlarmRecordsPA.getCommunityId()));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
}
