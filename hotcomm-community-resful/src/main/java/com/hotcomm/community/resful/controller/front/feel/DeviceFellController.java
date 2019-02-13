package com.hotcomm.community.resful.controller.front.feel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.device.DeviceFellService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class DeviceFellController {

	@Autowired
	private DeviceFellService deviceFellService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICEMSGCOUNT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICEMSGCOUNT_FUN)
	@LogEvent(code = "DEV00122")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult DeviceMsgCount(Integer communityId) {
		return ApiResult.success(deviceFellService.DeviceMsgCount(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICETODAYDATAMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICETODAYDATAMSG_FUN)
	@LogEvent(code = "DEV00123")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult DeviceTodayDataMsg(Integer communityId) {
		return ApiResult.success(deviceFellService.DeviceTodayDataMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICESTATEMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICESTATEMSG_FUN)
	@LogEvent(code = "DEV00124")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult DeviceStateMsg(Integer communityId) {
		return ApiResult.success(deviceFellService.DeviceStateMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICETHISWEEKALARM }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICETHISWEEKALARM_FUN)
	@LogEvent(code = "DEV00125")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult DeviceThisWeekAlarm(Integer communityId) {
		return ApiResult.success(deviceFellService.DeviceThisWeekAlarm(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICEMAP }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_DEVICEMAP_FUN)
	@LogEvent(code = "DEV00126")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "moduleIds", code = "DEVP0001") })
	public ApiResult DeviceMap(Integer communityId, String moduleIds, Integer devType) {
		return ApiResult.success(deviceFellService.DeviceMap(communityId, moduleIds, devType));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEFELL_SELECTDEVICEALLSTATE }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEFELL_SELECTDEVICEALLSTATE_FUN)
	@LogEvent(code = "DEV00144")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectDeviceAllState(Integer communityId, Integer moduleId) {
		return ApiResult.success(deviceFellService.selectDeviceAllState(communityId, moduleId));
	}
}