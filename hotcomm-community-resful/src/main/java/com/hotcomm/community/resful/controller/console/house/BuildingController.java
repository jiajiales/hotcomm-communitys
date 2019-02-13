package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.BuildingPA;
import com.hotcomm.community.common.bean.pa.house.BuildingPagePA;
import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class BuildingController {
	@Autowired
	BuildingService buildingService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_PAGE_FUN)
	@LogEvent(code = "HO00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult pageData(BuildingPagePA params) throws HKException {
		return ApiResult.success(buildingService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_DETAILSDATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_DETAILSDATA_FUN)
	@LogEvent(code = "HB00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P01") })
	public ApiResult detailsData(Integer id, Integer communityId) throws HKException {
		return ApiResult.success(buildingService.detailsData(id, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_ADDDATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_ADDDATA_FUN)
	@LogEvent(code = "HB00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingName", code = "HS_P02"), @Param(key = "detailedAddr", code = "HS_P03"),
			@Param(key = "createUser", code = "HS_P04") })
	public ApiResult addData(BuildingPA params) throws Exception {
		buildingService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_UPDATEDATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_UPDATEDATA_FUN)
	@LogEvent(code = "HB00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P01") })
	public ApiResult updateData(BuildingPA params) throws HKException {
		buildingService.updateData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_DELDATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_DELDATA_FUN)
	@LogEvent(code = "HB00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P01") })
	public ApiResult deleteData(Integer id, Integer communityId) throws HKException {
		buildingService.deleteData(id, communityId);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_LIST_FUN)
	@LogEvent(code = "HB00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getBuildingList(Integer communityId) throws HKException {
		return ApiResult.success(buildingService.getBuildingList(communityId));
	}
}
