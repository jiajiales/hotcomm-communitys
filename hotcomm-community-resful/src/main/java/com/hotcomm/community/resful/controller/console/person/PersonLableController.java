package com.hotcomm.community.resful.controller.console.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.person.PersonLablePagePA;
import com.hotcomm.community.common.service.person.PersonLableService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonLableController {
	
	@Autowired
	PersonLableService personLableService;
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_PAGE},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_PAGE_FUN)
	@LogEvent(code="PM00101")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult page(PersonLablePagePA pa) {
		return ApiResult.success(personLableService.page(pa));
	}
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_INFO},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_INFO_FUN)
	@LogEvent(code="PM00102")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult PersonLableInfo(Integer communityId,Integer id) {
		return ApiResult.success(personLableService.getPersonLableInfo(communityId, id));
	}
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_DELETE},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_DELETE_FUN)
	@LogEvent(code="PM00103")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult delPersonLable(Integer communityId,Integer id) {
		return ApiResult.success(personLableService.delPersonLable(communityId, id));
	}
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_UPDATA},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_UPDATA_FUN)
	@LogEvent(code="PM00104")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult updatePersonLable(Integer communityId,Integer id,Integer typeCode,String name,String description) {
		return ApiResult.success(personLableService.updatePersonLable(communityId, id, typeCode, name, description));
	}
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_INSERT},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_INSERT_FUN)
	@LogEvent(code="PM00105")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult insertPersonLable(Integer communityId,Integer createUser,Integer typeCode,String name,String description) {
		return ApiResult.success(personLableService.insertPersonLable(communityId, createUser, typeCode, name, description));
	}
	
	
	@RequestMapping(value= {RestfulUrlRecord.POPULATION_LABLE_SELECT},method= {RequestMethod.POST})
	@Function(key=RestfulUrlRecord.POPULATION_LABLE_SELECT_FUN)
	@LogEvent(code="PM00106")
	@ParamsValidate(validateParams= {@Param(key = "communityId", code = "UA00102")})
	public ApiResult selectPersonLable(Integer communityId) {
		return ApiResult.success(personLableService.selectLable(communityId));
	}
}
