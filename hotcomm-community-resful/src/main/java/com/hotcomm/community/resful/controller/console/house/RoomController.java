package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.RoomPA;
import com.hotcomm.community.common.bean.pa.house.RoomPagePA;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class RoomController {
	@Autowired
	RoomService roomService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_PAGE_FUN)
	@LogEvent(code = "HR00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult pageData(RoomPagePA params) throws HKException {
		return ApiResult.success(roomService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_DETAILS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_DETAILS_FUN)
	@LogEvent(code = "HR00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P08") })
	public ApiResult detailsData(Integer id, Integer communityId) throws HKException {
		return ApiResult.success(roomService.detailsData(id, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_ADD_FUN)
	@LogEvent(code = "HR00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "buildingId", code = "HS_P01"), @Param(key = "floorId", code = "HS_P05"),
			@Param(key = "roomName", code = "HS_P09"), @Param(key = "createUser", code = "HS_P04") })
	public ApiResult addData(RoomPA params) throws Exception {
		roomService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_UPDATE_FUN)
	@LogEvent(code = "HR00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P08") })
	public ApiResult updateData(RoomPA params) throws HKException {
		roomService.updateData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_DEL_FUN)
	@LogEvent(code = "HR00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P08") })
	public ApiResult delData(Integer id, Integer communityId) throws HKException {
		roomService.delData(id, communityId);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_LIST_FUN)
	@LogEvent(code = "HR00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "floorId", code = "HS_P05") })
	public ApiResult getRoomList(Integer floorId, Integer communityId) throws HKException {
		return ApiResult.success(roomService.getRoomList(floorId, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_LIST_BY_PID }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_LIST_BY_PID_FUN)
	@LogEvent(code = "HR00107")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "pId", code = "HS_PO14") })
	public ApiResult getRoomListByPid(Integer pId, Integer communityId) throws HKException {
		return ApiResult.success(roomService.getRoomListByPid(pId, communityId));
	}

	// 查询所有的房间列表
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_ALL_LIST }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_ALL_LIST_FUN)
	@LogEvent(code = "HR00107")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getAllList(Integer pId, Integer communityId) throws HKException {
		return ApiResult.success(roomService.getAllList(communityId));
	}
}
