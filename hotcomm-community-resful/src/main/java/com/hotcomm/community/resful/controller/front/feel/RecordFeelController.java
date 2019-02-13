package com.hotcomm.community.resful.controller.front.feel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.person.PersonAlarmService;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.person.service.RecordReactionService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class RecordFeelController {

	@Autowired
	private RecordReactionService reaction;
	@Autowired
	private PersonAlarmService alarm;
	@Autowired
	private PersonRecordService record;
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_FEEl_REATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_FEEl_REATION_FUN)
	@LogEvent(code = "PM00801")
	public ApiResult RecordDataStatistics(Integer communityId) {
		return ApiResult.success(reaction.getRecordDataStatistics(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_DAY_REATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_DAY_REATION_FUN)
	@LogEvent(code = "PM00802")
	public ApiResult RecordNumberByDay(Integer communityId) {
		return ApiResult.success(reaction.getRecordNumber(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_TYPE_REATION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_TYPE_REATION_FUN)
	@LogEvent(code = "PM00803")
	public ApiResult RecordTotalByType(Integer communityId) {
		return ApiResult.success(reaction.getRecordTotalByType(communityId));
	}
	
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_ALARM_LASTWEEK }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_ALARM_LASTWEEK_FUN)
	@LogEvent(code = "PM00804")
	public ApiResult getRecordAlarmLastWeek(Integer communityId) {
		return ApiResult.success(alarm.getAlarmLast50(communityId, 2));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_FACE_LAST50 }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_FACE_LAST50_FUN)
	@LogEvent(code = "PM00805")
	public ApiResult getRecordLast50(Integer communityId) {
		Map<String, Object> result=new HashMap<>();
		result.put("personStatistics",reaction.getRecordNumber(communityId));
		result.put("RecordData",record.getRecordLast50(communityId, 2));
		return ApiResult.success(result);
	}
	
	@RequestMapping(value = { RestfulUrlRecord.RECORD_MESSAGE_SEND }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.RECORD_MESSAGE_SEND_FUN)
	@LogEvent(code = "PM00806")
	public ApiResult recordMesSend() {
		reaction.RecordFeelMessageSend(null);
		return ApiResult.success();
	}
}