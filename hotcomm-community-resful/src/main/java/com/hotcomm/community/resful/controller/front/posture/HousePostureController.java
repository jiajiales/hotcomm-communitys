package com.hotcomm.community.resful.controller.front.posture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.common.service.house.WatchPlaceService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class HousePostureController {

	@Autowired
	WatchPlaceService watchPlaceService;
	@Autowired
	BuildingService buildingService;
	@Autowired
	RoomService roomService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_POSTURE_PLACE_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_POSTURE_PLACE_LIST_FUN)
	@LogEvent(code = "HP00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getPlaceList(Integer communityId) {
		return ApiResult.success(watchPlaceService.getPlaceList(communityId));
	}

	/**
	 * 综合作业/态势楼栋信息
	 * 
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDINGS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDINGS_FUN)
	@LogEvent(code = "HP00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getBuildings(Integer communityId) {
		return ApiResult.success(buildingService.getBuildings(communityId));
	}
	
	/**
	 * 综合态势房间分类统计
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_STATISTICS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_STATISTICS_FUN)
	@LogEvent(code = "HP00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult roomStatistics(Integer communityId) {
		return ApiResult.success(roomService.roomStatistics(communityId));
	}
}
