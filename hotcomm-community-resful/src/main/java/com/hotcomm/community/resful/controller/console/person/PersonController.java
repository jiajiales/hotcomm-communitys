package com.hotcomm.community.resful.controller.console.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.person.AddPersonPA;
import com.hotcomm.community.common.bean.pa.person.PersonPagePA;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.person.PersonStrangerPagePA;
import com.hotcomm.community.common.bean.pa.person.PopulationRoomPagePA;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonController {

	@Autowired
	private PersonService person;

	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_DATA }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_DATA_FUN)
	@LogEvent(code = "PM00201")
	public ApiResult personSituation(Integer communityId) {
		return ApiResult.success(person.getPopulationSituation(communityId));

	}

	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_LABLE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_LABLE_FUN)
	@LogEvent(code = "PM00202")
	public ApiResult lablePopulationSituation(Integer communityId) {
		return ApiResult.success(person.getLablePopulationSituation(communityId));

	}

	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_CLASSIFICATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_CLASSIFICATION_FUN)
	@LogEvent(code = "PM00203")
	public ApiResult PersonClassification(Integer communityId) {
		return ApiResult.success(person.getPersonClassification(communityId));

	}
	
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_ALARMCONDITION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_ALARMCONDITION_FUN)
	@LogEvent(code = "PM00204")
	public ApiResult PersonAlarmCondition(Integer communityId) {
		return ApiResult.success(person.getAlarmCondition(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFMON }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFMON_FUN)
	@LogEvent(code = "PM00205")
	public ApiResult PersonPassInfoOfMon(Integer communityId) {
		return ApiResult.success(person.getPassInfoOfMon(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFDAY }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFDAY_FUN)
	@LogEvent(code = "PM00206")
	public ApiResult PersonPassInfoOfDay(Integer communityId) {
		return ApiResult.success(person.getPassInfoOfDay(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.HOME_POPULATION_SITUATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOME_POPULATION_SITUATION_FUN)
	@LogEvent(code = "PM00213")
	public ApiResult getThePopulation(Integer communityId) {
		return ApiResult.success(person.getThePopulation(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_MESSAGE_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_MESSAGE_PAGE_FUN)
	@LogEvent(code = "PM00301")
	public ApiResult PersonPageList(PersonPagePA params) {
		return ApiResult.success(person.getPersonPageList(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_MESSAGE_INFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_MESSAGE_INFO_FUN)
	@LogEvent(code = "PM00302")
	public ApiResult PersonPageList(Integer communityId,Integer pId) {
		return ApiResult.success(person.getPersonInfo(communityId, pId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_MESSAGE_DELETE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_MESSAGE_DELETE_FUN)
	@LogEvent(code = "PM00303")
	public ApiResult deletePersonMessage(Integer communityId,Integer pId) {
		return ApiResult.success(person.deletePersonMessage(communityId, pId));
	}
	
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_MESSAGE_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_MESSAGE_ADD_FUN)
	@LogEvent(code = "PM00304")
	public ApiResult addPersonMessage(AddPersonPA params) {
		return ApiResult.success(person.addPersonMessage(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_MESSAGE_UPDATE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_MESSAGE_UPDATE_FUN)
	@LogEvent(code = "PM00305")
	public ApiResult updatePersonMessage(AddPersonPA params,Integer pId) {
		return ApiResult.success(person.updatePersonMessage(params,pId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_ALARMTYPE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_ALARMTYPE_FUN)
	@LogEvent(code = "PM00306")
	public ApiResult PersonAlarmOfTypeToMonth(Integer communityId) {
		return ApiResult.success(person.getAlarmOfTypeToMonth(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_STRANGER_INFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_STRANGER_INFO_FUN)
	@LogEvent(code = "PM00307")
	public ApiResult StrangerInfo(Integer communityId,String faceNo) {
		return ApiResult.success(person.StrangerInfo(communityId, faceNo));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_STRANGER_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_STRANGER_PAGE_FUN)
	@LogEvent(code = "PM00308")
	public ApiResult PersonStrangerList(PersonStrangerPagePA pa) {
		return ApiResult.success(person.PersonStrangerPage(pa));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_STRANGER_DEL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_STRANGER_DEL_FUN)
	@LogEvent(code = "PM00309")
	public ApiResult delPersonStranger(Integer communityId,Integer id) {
		return ApiResult.success(person.delPersonStranger(communityId,id));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_STRANGER_TRANSITION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_STRANGER_TRANSITION_FUN)
	@LogEvent(code = "PM00310")
	public ApiResult SetStrangerToPerosn(AddPersonPA params,Integer id) {
		return ApiResult.success(person.SetStrangerToPerson(params, id));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ROOM_RE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ROOM_RE_FUN)
	@LogEvent(code = "PM00311")
	public ApiResult PersonRoomRe(PersonRoomPA pa) {
		return ApiResult.success(person.addPersonReRoom(pa));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ROOM_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ROOM_PAGE_FUN)
	@LogEvent(code = "PM00312")
	public ApiResult PersonRoomRePage(PopulationRoomPagePA pa) {
		return ApiResult.success(person.PeopleRoomRePage(pa));
	}

	@RequestMapping(value = { RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFWEEk }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_SITUATION_PASSINFOOFWEEk_FUN)
	@LogEvent(code = "PM00214")
	public ApiResult PassInfoOfWeek(Integer communityId) { return ApiResult.success(person.getPassInfoOfWeek(communityId)); }
}
