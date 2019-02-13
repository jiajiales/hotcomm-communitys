package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.UnitPA;
import com.hotcomm.community.common.bean.pa.house.UnitPagePA;
import com.hotcomm.community.common.service.house.UnitService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class UnitController {
	@Autowired
	UnitService unitService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_PAGE_FUN)
	@LogEvent(code = "HU00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102")})
	public ApiResult pageData(UnitPagePA params) throws HKException {
		return ApiResult.success(unitService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_LIST_FUN)
	@LogEvent(code = "HU00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingId", code = "HS_P01") })
	public ApiResult getDataList(Integer buildingId, Integer communityId) throws HKException {
		return ApiResult.success(unitService.getDataList(buildingId, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_DETAILS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_DETAILS_FUN)
	@LogEvent(code = "HU00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P016") })
	public ApiResult detailsData(Integer id, Integer communityId) throws HKException {
		return ApiResult.success(unitService.detailsData(id, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_ADD_FUN)
	@LogEvent(code = "HU00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingId", code = "HS_P01"), @Param(key = "unitNo", code = "HS_P017") })
	public ApiResult addData(UnitPA params) throws Exception {
		unitService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_UPDATE_FUN)
	@LogEvent(code = "HU00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingId", code = "HS_P01"), @Param(key = "id", code = "HS_P016") })
	public ApiResult updateData(UnitPA params) throws HKException {
		unitService.updateData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_UNIT_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_UNIT_DEL_FUN)
	@LogEvent(code = "HU00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P016") })
	public ApiResult delData(Integer id, Integer communityId) throws HKException {
		unitService.delData(id, communityId);
		return ApiResult.success();
	}
}
