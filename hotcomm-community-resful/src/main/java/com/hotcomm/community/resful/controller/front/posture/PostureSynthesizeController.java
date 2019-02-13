package com.hotcomm.community.resful.controller.front.posture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.device.PostureSynthesizeService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PostureSynthesizeController {
	@Autowired
	private PostureSynthesizeService postureSynthesizeService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_SELECTDEVICENUM }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_SELECTDEVICENUM_FUN)
	@LogEvent(code = "")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectDeviceNum(Integer communityId) {
		return ApiResult.success(postureSynthesizeService.selectDeviceNum(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_SELECTRECENTLYSEVENDAYS }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_SELECTRECENTLYSEVENDAYS_FUN)
	@LogEvent(code = "")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectRecentlySevenDays(Integer communityId) {
		return ApiResult.success(postureSynthesizeService.selectRecentlySevenDays(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_FULLSTATISTICS }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_POSTURESYNTHESIZE_FULLSTATISTICS_FUN)
	@LogEvent(code = "")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult fullStatistics(Integer communityId) {
		return ApiResult.success(postureSynthesizeService.fullStatistics(communityId));
	}
}
