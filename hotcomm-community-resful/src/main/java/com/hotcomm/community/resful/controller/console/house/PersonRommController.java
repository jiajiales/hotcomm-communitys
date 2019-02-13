package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPagePA;
import com.hotcomm.community.common.service.house.PersonRoomService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonRommController {
	@Autowired
	PersonRoomService personRoomService;

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PERSON_ROOM_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PERSON_ROOM_PAGE_FUN)
	@LogEvent(code = "PR00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102")})
	public ApiResult pageData(PersonRoomPagePA params) throws HKException {
		return ApiResult.success(personRoomService.pageData(params));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PERSON_ROOM_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PERSON_ROOM_ADD_FUN)
	@LogEvent(code = "PR00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "pId", code = "HS_PO14"), @Param(key = "roomId", code = "HS_P013") })
	public ApiResult addData(PersonRoomPA params) throws HKException {
		personRoomService.addData(params);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PERSON_ROOM_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PERSON_ROOM_DEL_FUN)
	@LogEvent(code = "PR00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "id", code = "HS_P015") })
	public ApiResult delData(Integer id, Integer communityId) throws HKException {
		personRoomService.delData(id, communityId);
		return ApiResult.success();
	}
	
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PERSON_ROOM_RELATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PERSON_ROOM_RELATION_FUN)
	@LogEvent(code = "PR00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "name", code = "HS_P018") })
	public ApiResult getRelations(Integer communityId,String name)throws HKException{
		return ApiResult.success(personRoomService.getRelationRoomUMs(communityId, name));
	}
	
}
