package com.hotcomm.community.resful.controller.front.feel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.device.FireFellService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class FireFellController {

	@Autowired
	private FireFellService fireFellService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREMSGCOUNT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREMSGCOUNT_FUN)
	@LogEvent(code = "DEV00127")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireMsgCount(Integer communityId) {
		return ApiResult.success(fireFellService.FireMsgCount(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMMSG_FUN)
	@LogEvent(code = "DEV00128")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlarmMsg(Integer communityId) {
		return ApiResult.success(fireFellService.FireAlarmMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEALARMMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEALARMMSG_FUN)
	@LogEvent(code = "DEV00129")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireDeviceAlarmMsg(Integer communityId) {
		return ApiResult.success(fireFellService.FireDeviceAlarmMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIRETHISWEEKALARM }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIRETHISWEEKALARM_FUN)
	@LogEvent(code = "DEV00130")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireThisWeekAlarm(Integer communityId) {
		return ApiResult.success(fireFellService.FireThisWeekAlarm(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEMAP }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEMAP_FUN)
	@LogEvent(code = "DEV00131")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "moduleIds", code = "DEVP0001") })
	public ApiResult FireDeviceMap(Integer communityId, String moduleIds) {
		return ApiResult.success(fireFellService.FireDeviceMap(communityId, moduleIds));
	}
}
