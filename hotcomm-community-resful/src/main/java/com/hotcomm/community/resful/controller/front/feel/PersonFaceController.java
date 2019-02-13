package com.hotcomm.community.resful.controller.front.feel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfDayUM;
import com.hotcomm.community.common.bean.ui.person.RecordStatisticsUM;
import com.hotcomm.community.common.service.person.PersonAlarmService;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.person.service.FaceRecognitionService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.result.ApiResult;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;

@RestController
public class PersonFaceController {

	@Autowired
	private FaceRecognitionService face;
	
	@Autowired
	private PersonAlarmService alarm;
	
	@Autowired
	private PersonRecordService record;
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_FACE_REACTION }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_FACE_REACTION_FUN)
	@LogEvent(code = "PM00701")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getPersonFaceRec(Integer communityId) {
		return ApiResult.success(face.getPersonFaceRec(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_SPECIAL_REACTION}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_SPECIAL_REACTION_FUN)
	@LogEvent(code = "PM00702")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getSpecialPersonRec(Integer communityId) {
		return ApiResult.success(face.getSpecialPersonRec(communityId));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_FACE_FEEL}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_FACE_FEEL_FUN)
	@LogEvent(code = "PM00703")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getFaceFellInfoOfDay(Integer communityId) {
		return ApiResult.success(face.getFaceFellInfoOfDay(communityId));
	}
	
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_ALARM_LASTWEEK }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_ALARM_LASTWEEK_FUN)
	@LogEvent(code = "PM00704")
	public ApiResult getFaceAlarmLastWeek(Integer communityId) {
		return ApiResult.success(alarm.getAlarmLast50(communityId, 1));
	}
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_RECORD_LAST50 }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_RECORD_LAST50_FUN)
	@LogEvent(code = "PM00705")
	public ApiResult getRecordLast50(Integer communityId) {
		Map<String, Object> result=new HashMap<>();
		
		RecordStatisticsUM personData=new RecordStatisticsUM();
		int sum=0;
		int num=0;
		List<PersonPassInfoOfDayUM> list = face.getFaceFellInfoOfDay(communityId);
		if (list.toString() != "[]") {
			for (PersonPassInfoOfDayUM a : list) {
				sum += a.getPeopleNum();
				num += a.getPassTime();
			}
		}
		personData.setNum(sum);
		personData.setTime(num);
		result.put("personData", personData);
		result.put("faceData", record.getRecordLast50(communityId, 1));		
		return ApiResult.success(result);
	}
	
	@RequestMapping(value = { RestfulUrlRecord.PERSON_FACE_MESSEND }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.PERSON_FACE_MESSEND_FUN)
	@LogEvent(code = "PM00706")
	public ApiResult faceMesSend() {
		face.FaceFeelMessageSend(null);
		return ApiResult.success();
	}
}
