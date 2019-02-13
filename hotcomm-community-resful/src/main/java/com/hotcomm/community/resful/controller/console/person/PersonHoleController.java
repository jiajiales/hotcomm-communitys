package com.hotcomm.community.resful.controller.console.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.person.PersonHolePA;
import com.hotcomm.community.common.bean.pa.person.AddPersonHolePA;
import com.hotcomm.community.common.service.person.PersonHoleService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonHoleController {

	@Autowired
	private PersonHoleService hole;
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_PAGE_FUN)
	@LogEvent(code = "PM00601")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult PersonAlarmPage(PersonHolePA params) {
		return ApiResult.success(hole.PersonHolePage(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_ONOFF }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_ONOFF_FUN)
	@LogEvent(code = "PM00602")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult  PersonHoleOnOff(Integer id,Integer communityId,Integer onOff) {
		return ApiResult.success(hole.PersonHoleOnOff(id, communityId,onOff));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_DELETE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_DELETE_FUN)
	@LogEvent(code = "PM00603")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult  delPersonHole(Integer id,Integer communityId) {
		return ApiResult.success(hole.delPersonHole(id, communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_INFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_INFO_FUN)
	@LogEvent(code = "PM00604")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult  PersonHoleInfo(Integer id,Integer communityId,Integer pId,Integer type) {
		if(type==1) {
			return ApiResult.success(hole.PersonHoleInfoByAll(id, communityId));
		}else{
			return ApiResult.success(hole.PersonHoleInfoByOne(id, communityId, pId));
		}
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_ADD_FUN)
	@LogEvent(code = "PM00605")
	@ResponseBody
	public ApiResult  addPersonHole(@RequestBody AddPersonHolePA pa) {
		return ApiResult.success(hole.addPersonHole(pa));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_HOLE_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_HOLE_UPDATE_FUN)
	@LogEvent(code = "PM00606")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	@ResponseBody
	public ApiResult  updatePersonHole(@RequestBody AddPersonHolePA pa) {
		return ApiResult.success(hole.updatePersonHole(pa));
	}
	
}
