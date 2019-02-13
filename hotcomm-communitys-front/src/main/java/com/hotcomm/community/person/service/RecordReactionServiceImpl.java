package com.hotcomm.community.person.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.service.device.DeviceFellService;
import com.hotcomm.community.common.service.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.en.person.RecordTypeEN;
import com.hotcomm.community.common.bean.ui.house.HouseStatistics;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.community.common.bean.ui.person.RecordDataStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.RecordNumByTypeUM;
import com.hotcomm.community.common.bean.ui.person.RecordStatisticsUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.HouseSummarizingService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.person.mapper.PersonLableMapper;
import com.hotcomm.community.person.mapper.PersonMapper;
import com.hotcomm.community.person.mapper.PersonRecordMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class RecordReactionServiceImpl extends BaseService implements RecordReactionService {

	@Autowired
	private PersonMapper person;
	
	@Autowired
	private HouseSummarizingService house;
	@Autowired
	private PersonLableMapper lable;
	@Autowired
	private MsgServiceImpl msgService;
	@Autowired
	private PersonRecordMapper record;
	@Autowired
	private DeviceFellService deviceFellService;

	@Override
	public RecordDataStatisticsUM getRecordDataStatistics(Integer communityId)
			throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);
		
		RecordDataStatisticsUM result =new RecordDataStatisticsUM();
		HouseStatistics houseStatistics = house.getHouseStatistics(communityId);
		
		result.setHouseNum(IfNullTo0(houseStatistics.getBuildingNum()));
		result.setRoomNum(IfNullTo0(houseStatistics.getRoomNum()));
		result.setPopulation(IfNullTo0(person.getTotalPopulation(params)));
		result.setCareNum(IfNullTo0(person.getLablePopulation(params, PersonLableEN.CARE_PERSON.getKey())));
		result.setRiskNum(IfNullTo0(person.getLablePopulation(params, PersonLableEN.RISK_PERSON.getKey())));
		result.setBlackListNum(IfNullTo0(person.getLablePopulation(params, PersonLableEN.BLACKLIST_PERSON.getKey())));
		result.setMJDeviceNum(deviceFellService.DeviceMap(communityId, "6", null).size());
		
		return result;
	}

	@Override
	public List<RecordNumByTypeUM> getRecordTotalByType(Integer communityId)
			throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);
		
		List<RecordNumByTypeUM> result = person.getRecordTotalByType(params);
		for (RecordTypeEN en : RecordTypeEN.values()) {
			if(en.getIndex()==1) continue;
			int i=0;
			for (RecordNumByTypeUM res : result) {
				if (res.getType() == en.getIndex()) {
					res.setName(en.getName());
					i=1;
					break;
				}
			}
			if(i!=1) {
				result.add(new RecordNumByTypeUM(en.getIndex(),en.getName(),0));
			}
		}
		return result;
	}

	@Override
	public RecordStatisticsUM getRecordNumber(Integer communityId)
			throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);
		
		RecordStatisticsUM result=new RecordStatisticsUM();
		result.setNum(person.getRecordNumberOfPass(params));
		result.setTime(person.getRecordTotalTimeOfPass(params));
		return result;
	}

	private Integer IfNullTo0(Integer a) {
		return a == null ? 0 : a;
	}

	@Override
	public void RecordFeelMessageSend(PersonFaceMessageSendUM mes) {
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
			mes.setRecordType(2);
			mes.setLableId(personDM.getLableId());
			mes.setPersonTime(1);
			Integer result = record.getRecordByToday(params,1)>0?1:0;
			mes.setPersonNum(result);
			mes.setMatchingRate(75);
			mes.setDeviceMac("3M05329PAKED676");
			PersonLableUM lableInfo = lable.PersonLableInfo(params, mes.getLableId());
			mes.setLableType(lableInfo.getTypeCode());
			mes.setLat("22.93961");
			mes.setLng("113.392125");
			mes.setAge(33);
			mes.setSex(1);
		}
		
		if(mes.getLableType().equals("F04")) {
			mes.setBlackListAlarmNum(1);
			mes.setCareAlarmNum(0);
		}else {
			mes.setCareAlarmNum(0);
			mes.setBlackListAlarmNum(0);
		}
		
		
		mes.setLableName(PersonLableEN.getIndexByKey(mes.getLableType()).getValue());
		mes.setTypeName(RecordTypeEN.getNameByIndex(mes.getRecordType()).getName());
				
		Message msg = new Message();
		msg.setCommunityId(mes.getCommunityId().toString());
		msg.setSource("0");
		msg.setCategory("C02");
		msg.setCode("record");
		msg.setData(mes);
		msgService.sendMessage(msg);
	}
}
