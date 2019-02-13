package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.WatchPlacPA;
import com.hotcomm.community.common.bean.pa.house.WatchPlacePagePA;
import com.hotcomm.community.common.service.house.WatchPlaceService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class WatchPlaceController {
	@Autowired
	WatchPlaceService watchPlaceService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_WATCH_PLACE_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_WATCH_PLACE_PAGE_FUN)
	@LogEvent(code = "HW00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "ways", code = "HS_P010") })
	public ApiResult pageData(WatchPlacePagePA params) throws HKException {
		return ApiResult.success(watchPlaceService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_WATCH_PLACE_DETAILS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_WATCH_PLACE_DETAILS_FUN)
	@LogEvent(code = "HW00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P011"), @Param(key = "ways", code = "HS_P010") })
	public ApiResult detailsData(Integer id, Integer communityId, Integer ways) throws HKException {
		return ApiResult.success(watchPlaceService.detailsData(id, communityId, ways));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_WATCH_PLACE_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_WATCH_PLACE_ADD_FUN)
	@LogEvent(code = "HW00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "ways", code = "HS_P010"), @Param(key = "placeName", code = "HS_P012"),
			@Param(key = "buildingId", code = "HS_P01"), @Param(key = "createUser", code = "HS_P04") })
	public ApiResult addData(WatchPlacPA params) throws HKException {
		watchPlaceService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_WATCH_PLACE_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_WATCH_PLACE_UPDATE_FUN)
	@LogEvent(code = "HW00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P011") })
	public ApiResult updateData(WatchPlacPA params) throws HKException {
		watchPlaceService.updateData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_WATCH_PLACE_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_WATCH_PLACE_DEL_FUN)
	@LogEvent(code = "HW00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P011") })
	public ApiResult delData(Integer id, Integer communityId) throws HKException {
		watchPlaceService.delData(id, communityId);
		return ApiResult.success();
	}

}
