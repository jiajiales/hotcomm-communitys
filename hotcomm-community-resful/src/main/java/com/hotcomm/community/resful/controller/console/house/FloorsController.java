package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.FloorsPA;
import com.hotcomm.community.common.bean.pa.house.FloorsPagePA;
import com.hotcomm.community.common.service.house.FloorsService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class FloorsController {
	@Autowired
	FloorsService floorsService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_PAGE_FUN)
	@LogEvent(code = "HF00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult pageData(FloorsPagePA params) throws HKException {
		return ApiResult.success(floorsService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_DETAILS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_DETAILS_FUN)
	@LogEvent(code = "HF00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P05") })
	public ApiResult detailsData(Integer id, Integer communityId) throws HKException {
		return ApiResult.success(floorsService.detailsData(id, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_ADD_FUN)
	@LogEvent(code = "HF00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingId", code = "HS_P01"), @Param(key = "floorName", code = "HS_P06") })
	public ApiResult addData(FloorsPA params) throws HKException {
		floorsService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_UPDATE_FUN)
	@LogEvent(code = "HF00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P05") })
	public ApiResult updateData(FloorsPA params) throws HKException {
		floorsService.updateData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_DEL_FUN)
	@LogEvent(code = "HF00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P05") })
	public ApiResult delData(Integer id, Integer communityId) throws HKException {
		floorsService.deleteData(id, communityId);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_FLOOR_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_FLOOR_DEL_FUN)
	@LogEvent(code = "HF00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "unitId", code = "HS_P01") })
	public ApiResult getFloorList(Integer unitId, Integer communityId) throws HKException {
		return ApiResult.success(floorsService.getFloorsList(unitId, communityId));
	}
}
