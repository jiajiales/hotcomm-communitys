package com.hotcomm.community.person.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.common.bean.db.person.PassInfoOfDayDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.en.person.RecordTypeEN;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.person.PeopleFaceStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfDayUM;
import com.hotcomm.community.common.bean.ui.person.SpecialCrowdFaceRecUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.DeviceFellService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.person.mapper.PersonLableMapper;
import com.hotcomm.community.person.mapper.PersonMapper;
import com.hotcomm.community.person.mapper.PersonRecordMapper;
import com.hotcomm.framework.web.exception.HKException;

@Transactional
@Service
public class FaceRecognitionServiceImpl extends BaseService implements FaceRecognitionService {

	@Autowired
	private PersonMapper person;
	@Autowired 
	private DeviceFellService device;
	@Autowired
	private PersonRecordMapper record;
	@Autowired
	private MsgServiceImpl msgService;
	@Autowired
	private PersonLableMapper lable;
	@Override
	public PeopleFaceStatisticsUM getPersonFaceRec(Integer communityId) throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);
		
		PeopleFaceStatisticsUM result=new PeopleFaceStatisticsUM();
		result.setPeopleNum(SetNullTo0(person.getTotalPopulation(params)));
		result.setCarePeopleNum(SetNullTo0(person.getLablePopulation(params, PersonLableEN.getValueByIndex(1).getKey())));
		result.setRiskPeopleNum(SetNullTo0(person.getLablePopulation(params, PersonLableEN.getValueByIndex(2).getKey())));
		result.setBlacklistNum(SetNullTo0(person.getLablePopulation(params, PersonLableEN.getValueByIndex(4).getKey())));
		result.setStrangerNum(SetNullTo0(person.getStrangerNum(params)));
		List<DeviceMsgCountUM> deviceMsgCount = device.DeviceMsgCount(communityId);
		Integer deviceNum=0;
		for (DeviceMsgCountUM deviceMsgCountUM : deviceMsgCount) {
			if(deviceMsgCountUM.getModuleId()==5) {
				deviceNum=deviceMsgCountUM.getDevNum();
			}
		}
		result.setCameraNum(deviceNum);
		result.setFaceNum(person.FaceInduction(params, null));
		result.setFaceReactionNum(person.FaceInductionNum(params, null));
		return result;
	}

	@Override
	public SpecialCrowdFaceRecUM getSpecialPersonRec(Integer communityId) throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);
		
		SpecialCrowdFaceRecUM result=new SpecialCrowdFaceRecUM();
		result.setCarePersonNum(SetNullTo0(person.FaceInduction(params, PersonLableEN.CARE_PERSON.getKey())));
		result.setCarePersonTime(SetNullTo0(person.FaceInductionNum(params, PersonLableEN.CARE_PERSON.getKey())));
		result.setRiskPersonNum(SetNullTo0(person.FaceInduction(params, PersonLableEN.RISK_PERSON.getKey())));
		result.setRiskPersonTime(SetNullTo0(person.FaceInductionNum(params, PersonLableEN.RISK_PERSON.getKey())));
		result.setBlackListNum(SetNullTo0(person.FaceInduction(params, PersonLableEN.BLACKLIST_PERSON.getKey())));
		result.setBlackListTime(SetNullTo0(person.FaceInductionNum(params, PersonLableEN.BLACKLIST_PERSON.getKey())));
		result.setStrangerNum(SetNullTo0(person.StrangerFaceInductionNum(params,null)));
		result.setStrangerTime(SetNullTo0(person.StrangerFaceInduction(params,null)));
		return result;
	}

	/**
	 * 若Integer类型为null 则赋值为0
	 * @param a
	 * @return
	 */
	private Integer SetNullTo0(Integer a ) {
		if(a!=null ) {
			return a;
		}
		return 0;
	}

	@Override
	public List<PersonPassInfoOfDayUM> getFaceFellInfoOfDay(Integer communityId)
			throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);

		List<PassInfoOfDayDM> DBdata1 = person.getPassInfoOfdayByNum(params,1);// 今日通行人数
		List<PassInfoOfDayDM> DBdata2 = person.getPassInfoOfdayByTime(params,1);// 今日通行人次数
		int i = 1;
		int z = 1;
		List<PersonPassInfoOfDayUM> result = new ArrayList<>();
		for (PassInfoOfDayDM data : DBdata1) {
			if(data==null) {
				while(i<25) {
					result.add(new PersonPassInfoOfDayUM(i, 0, 0));
				}
			}else{
				while (i < 25) {
					if (data.getH() == i) {
						result.add(new PersonPassInfoOfDayUM(i, data.getNum(), 0));
						i++;
						if (DBdata1.size() == z) {
							continue;
						}
						z++;
						break;
					} else {
						result.add(new PersonPassInfoOfDayUM(i, 0, 0));
						i++;
					}
				}
			}
		}

		for (PassInfoOfDayDM data : DBdata2) {
			result.get(data.getH()-1).setPassTime(data.getNum());
		}
		if(DBdata1.toString()=="[]" || DBdata2.toString()=="[]") {
			while (i < 25) {
				result.add(new PersonPassInfoOfDayUM(i, 0, 0));
				i++;
			}
		}
		return result;
	}

	@Override
	public void FaceFeelMessageSend(PersonFaceMessageSendUM mes) {
		Map<String, Object> params = super.getTableParams(1);

		if (mes==null) {
			mes=new PersonFaceMessageSendUM();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d =new Date();
			PersonDM personDM = person.PersonInfo(params, 1);
			
			mes.setCommunityId(1);
			mes.setFaceImgs("http://39.108.54.252:8185/hotcomm-community/person/20181214400245_26e2dcf4707e41e98e625cf5271f5258.jpg");
			mes.setRecordImgs("http://39.108.54.252:8185/hotcomm-community/person/20181214480250_68e8c5b36801473e82897112533f67eb.jpg");
			mes.setPId(personDM.getPId());
			mes.setPName(personDM.getName());
			mes.setAddress("东门");
			mes.setRecordTime(sdf.format(d));
			mes.setRecordType(1);
			mes.setLableId(personDM.getLableId());
			mes.setPersonTime(1);
			Integer result = record.getRecordByToday(params,1)>0?1:0;
			mes.setPersonNum(result);
			mes.setMatchingRate(75);
			mes.setLat("22.93961");
			mes.setLng("113.392125");
			mes.setDeviceMac("3M05329PAKED676");
			PersonLableUM lableInfo = lable.PersonLableInfo(params, mes.getLableId());
			mes.setLableType(lableInfo.getTypeCode());
			mes.setAge(33);
			mes.setSex(1);
			mes.setCode("face");
		}
		
		mes.setLableName(PersonLableEN.getIndexByKey(mes.getLableType()).getValue());
		mes.setTypeName(RecordTypeEN.getNameByIndex(mes.getRecordType()).getName());
		
		if(mes.getLableType().equals("F04")) {
			mes.setBlackListAlarmNum(1);
			mes.setCareAlarmNum(0);
		}else {
			mes.setCareAlarmNum(0);
			mes.setBlackListAlarmNum(0);
		}
		
		
		Message msg = new Message();
		msg.setCommunityId(mes.getCommunityId().toString());
		msg.setSource("0");
		msg.setCategory("C02");
		if (mes.getCode().equals("face")) {
			msg.setCode("face");msg.setCategory("C02");
		}
		else if (mes.getCode().equals("record")) {
			msg.setCode("record");msg.setCategory("C02");
		}
		else {
			msg.setCode("population");msg.setCategory("C01");
		}
		msg.setData(mes);
		msgService.sendMessage(msg);
	}
}
