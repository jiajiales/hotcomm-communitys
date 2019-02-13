package com.hotcomm.community.resful.controller.console.system;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.ResourcePA;
import com.hotcomm.community.common.service.system.ResourceService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class ResourceController {

	@Resource
	private ResourceService resourceService;

	@Function(key = RestfulUrlRecord.RESOURCE_ADD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_ADD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "name", code = "RES_F02"), 
									   @Param(key = "path", code = "RES_F03"), 
									   @Param(key = "type", code = "RES_F04"), 
									   @Param(key = "weight", code = "RES_F05"), 
									   @Param(key = "status", code = "RES_F06"), 
									   @Param(key = "key", code = "RES_F07") })
	@LogEvent(code = "RES00101")
	public ApiResult add(ResourcePA resourcePa) throws HKException {
		resourceService.addResource(resourcePa);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.RESOURCE_DEL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_DEL }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "resId", code = "RES_F01") })
	@LogEvent(code = "RES00102")
	public ApiResult del(Integer resId) throws HKException {
		resourceService.delResource(resId);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.RESOURCE_UPDATE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_UPDATE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "resId", code = "RES_F01"), 
									   @Param(key = "name", code = "RES_F02"), 
									   @Param(key = "path", code = "RES_F03"), 
									   @Param(key = "type", code = "RES_F04"), 
									   @Param(key = "weight", code = "RES_F05"), 
									   @Param(key = "status", code = "RES_F06"), 
									   @Param(key = "key", code = "RES_F07") })
	@LogEvent(code = "RES00103")
	public ApiResult update(ResourcePA resourcePa) throws HKException {
		resourceService.updateResource(resourcePa);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.RESOURCE_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "resId", code = "RES_F01") })
	@LogEvent(code = "RES00104")
	public ApiResult get(Integer resId) throws HKException {
		return ApiResult.success(resourceService.getResource(resId));
	}

	@Function(key = RestfulUrlRecord.RESOURCE_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_LIST }, method = { RequestMethod.POST })
	@LogEvent(code = "RES00105")
	@LogSkip
	public ApiResult list() throws HKException {
		return ApiResult.success(resourceService.getResourdTree());
	}

	@Function(key = RestfulUrlRecord.RESOURCE_MENUS_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCE_MENUS }, method = { RequestMethod.POST })
	@LogEvent(code = "RES00106")
	public ApiResult getMenus() throws HKException {
		return ApiResult.success(resourceService.getMenus());
	}

	@Function(key = RestfulUrlRecord.RESOURCES_GRADING_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.RESOURCES_GRADING_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "RES_F08") })
	@LogEvent(code = "RES00107")
	public ApiResult getGradingResources(Integer userId) throws HKException {
		return ApiResult.success(resourceService.getGradingResources(userId));
	}

}
