package com.hotcomm.community.resful.controller.front.posture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.device.FirePostureService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class FirePostureController {
	@Autowired
	private FirePostureService firePostureService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREPOSTURE_FIREALARMTYPE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREPOSTURE_FIREALARMTYPE_FUN)
	@LogEvent(code = "DEV00132")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlarmType(Integer communityId) {
		return ApiResult.success(firePostureService.FireAlarmType(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREPOSTURE_FIREALARMMSGMONTH }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREPOSTURE_FIREALARMMSGMONTH_FUN)
	@LogEvent(code = "DEV00133")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlarmMsgToMonth(Integer communityId) {
		return ApiResult.success(firePostureService.FireAlarmMsgToMonth(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEALARMMSGMONTH }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEALARMMSGMONTH_FUN)
	@LogEvent(code = "DEV00134")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireDeviceAlarmMsgToMonth(Integer communityId) {
		return ApiResult.success(firePostureService.FireDeviceAlarmMsgToMonth(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEYEARMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREDEVICEYEARMSG_FUN)
	@LogEvent(code = "DEV00135")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireDeviceYearMsg(Integer communityId) {
		return ApiResult.success(firePostureService.FireDeviceYearMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMDISPOSEMSG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMDISPOSEMSG_FUN)
	@LogEvent(code = "DEV00136")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlaemDisposeMsg(Integer communityId) {
		return ApiResult.success(firePostureService.FireAlaemDisposeMsg(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMTOMONTH }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMTOMONTH_FUN)
	@LogEvent(code = "DEV00137")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlarmToMonth(Integer communityId) {
		return ApiResult.success(firePostureService.FireAlarmToMonth(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMTOYEAR }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_FIREFELL_FIREALARMTOYEAR_FUN)
	@LogEvent(code = "DEV00138")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult FireAlarmToYear(Integer communityId) {
		return ApiResult.success(firePostureService.FireAlarmToYear(communityId));
	}
}