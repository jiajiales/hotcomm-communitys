package com.hotcomm.community.resful.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.HomeService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class HomeController {

	@Autowired
	HomeService homeService;
	@Autowired
	DeviceService deviceService;

	@Function(key = RestfulUrlRecord.HOME_GET_KPI_FUN)
	@RequestMapping(value = { RestfulUrlRecord.HOME_GET_KPI }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "HOME00101")
	public ApiResult getKPI(Integer cid) throws HKException {
		return ApiResult.success(homeService.getKPI(cid));
	}

	@Function(key = RestfulUrlRecord.HOME_GET_PENDINGSITUATION_FUN)
	@RequestMapping(value = { RestfulUrlRecord.HOME_GET_PENDINGSITUATION }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "HOME00102")
	public ApiResult getPendingSituation(Integer cid) throws HKException {
		return ApiResult.success(homeService.getPendingSituation(cid));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOME_GET_SELECTDEVICEALARMCOUNT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOME_GET_SELECTDEVICEALARMCOUNT_FUN)
	@LogEvent(code = "HOME00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectDeviceAlarmCount(Integer communityId) {
		return ApiResult.success(deviceService.selectDeviceAlarmCount(communityId));
	}
}
