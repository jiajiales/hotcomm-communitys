package com.hotcomm.community.resful.controller.console.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.person.PersonRecordPA;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class PersonRecordController {

	@Autowired
	PersonRecordService record;
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_RECORD_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_RECORD_PAGE_FUN)
	@LogEvent(code = "PM00501")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult PersonRecordPage(PersonRecordPA params) {
		return ApiResult.success(record.PersonRecordPage(params));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_RECORD_INFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_RECORD_INFO_FUN)
	@LogEvent(code = "PM00502")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult PersonRecordPage(Integer communityId,Integer id) {
		return ApiResult.success(record.PersonRecordInfo(id, communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_RECORD_DELETE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.POPULATION_RECORD_DELETE_FUN)
	@LogEvent(code = "PM00503")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult deletePersonRecord(Integer communityId,Integer id) {
		return ApiResult.success(record.delPersonRecord(id, communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.POPULATION_RECORD_ADD }, method = { RequestMethod.POST })
//	@Function(key = RestfulUrlRecord.POPULATION_RECORD_ADD_FUN)
	@LogEvent(code = "PM00504")
	public ApiResult addPersonRecord(Integer type,String pNo,String record_time,String img,String video,Integer record_type,String deviceMac) {
		return ApiResult.success(record.addPersonRecord(type, pNo, record_time, img, video, record_type, deviceMac));
	}

	@RequestMapping(value = { RestfulUrlRecord.POPULATION_RECORD_DEVICEMAC }, method = { RequestMethod.POST })
//	@Function(key = RestfulUrlRecord.POPULATION_RECORD_ADD_FUN)
	@LogEvent(code = "PM00505")
	public ApiResult getDeviceMacByRecord(Integer communityId,Integer pId,String faceNo) {
		return ApiResult.success(record.getDeviceMacByRecord(communityId,faceNo,pId));
	}
}
