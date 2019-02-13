package com.hotcomm.community.resful.controller.console.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPA;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPagePA;
import com.hotcomm.community.common.service.device.doorcontrol.DevDoorService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class DevDoorController {
	@Autowired
	DevDoorService devDoorService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DOOR_PAGEDATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DOOR_PAGEDATA_FUN)
	@LogEvent(code = "DEV00139")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "moduleId", code = "DEVP0001") })
	public ApiResult pageData(DevDoorPagePA params) throws HKException {
		return ApiResult.success(devDoorService.pageData(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DOOR_INSATALL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DOOR_INSATALL_FUN)
	@LogEvent(code = "DEV00140")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult install(DevDoorPA params) throws HKException {
		devDoorService.install(params);
		return ApiResult.success();
	}
	
	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DOOR_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DOOR_UPDATE_FUN)
	@LogEvent(code = "DEV00141")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult updateData(DevDoorPA params) throws HKException {
		devDoorService.update(params);
		return ApiResult.success();
	}
	
	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DOOR_QUERY_ATTR }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DOOR_QUERY_ATTR_FUN)
	@LogEvent(code = "DEV00142")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult queryAttr(Integer communityId, String mac) throws HKException {
		return ApiResult.success(devDoorService.selectAttr(communityId, mac));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DOOR_DETAILS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DOOR_DETAILS_FUN)
	@LogEvent(code = "DEV00143")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult detailsData(Integer communityId, String mac) throws HKException {
		return ApiResult.success(devDoorService.detailsData(communityId, mac));
	}
}
