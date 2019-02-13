package com.hotcomm.community.resful.controller.console.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.person.PersonAlarmPA;
import com.hotcomm.community.common.service.person.PersonAlarmService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonAlarmController {

	@Autowired
	private PersonAlarmService alarm;
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ALARM_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ALARM_PAGE_FUN)
	@LogEvent(code = "PM00401")
	public ApiResult PersonAlarmPage(PersonAlarmPA params) {
		return ApiResult.success(alarm.PersonAlarmPage(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ALARM_INFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ALARM_INFO_FUN)
	@LogEvent(code = "PM00402")
	public ApiResult PersonAlarmPage(Integer communityId,Integer id) {
		return ApiResult.success(alarm.PersonAlarmInfo(id, communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ALARM_DELETE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ALARM_DELETE_FUN)
	@LogEvent(code = "PM00403")
	public ApiResult deletePersonAlarm(Integer communityId,Integer id) {
		return ApiResult.success(alarm.delPersonAlarm(id, communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_ALARM_ADD }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_ALARM_ADD_FUN)
	@LogEvent(code = "PM00404")
	public ApiResult addPersonAlarm(Integer communityId) {
		alarm.addPersonAlarm();
		return ApiResult.success(1);
	}
}
