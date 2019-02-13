package com.hotcomm.community.person.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.common.bean.db.person.PassInfoOfMonDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmTypeDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.en.person.RecordTypeEN;
import com.hotcomm.community.common.bean.ui.person.LablePopulationUM;
import com.hotcomm.community.common.bean.ui.person.PassRealTimeUM;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonFloorsStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfMonUM;
import com.hotcomm.community.common.bean.ui.person.PopulationByLableUM;
import com.hotcomm.community.common.bean.ui.person.PopulationTrendUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.person.mapper.PersonLableMapper;
import com.hotcomm.community.person.mapper.PersonMapper;
import com.hotcomm.community.person.mapper.PersonRecordMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class PopulationPostureServiceImpl extends BaseService implements PopulationPostureService {

	@Autowired
	private PersonMapper person;
	@Autowired
	private PersonService pservice;
	@Autowired
	private PersonRecordMapper record;
	@Autowired
	private MsgServiceImpl msgService;
	@Autowired
	private PersonLableMapper lable;

	@Override
	public List<PersonPassInfoOfMonUM> getPassInfoOfYear(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);


		List<PassInfoOfMonDM> DBdata1 = person.getPassInfoOfYearByNum(params);// 本年通行人数
		List<PassInfoOfMonDM> DBdata2 = person.getPassInfoOfYearByTime(params);// 本年通行人次数
		List<PersonPassInfoOfMonUM> result = new ArrayList<>();
		int i = 1;
		int z = 1;
		if(DBdata1.size()==0) {
			while (i < 13) {
				result.add(new PersonPassInfoOfMonUM(i,0,0));
				i++;
			}
			return result;
		}
		for (PassInfoOfMonDM data : DBdata1) {
			while (i < 13) {
				if (data.getD() == i) {
					result.add(new PersonPassInfoOfMonUM(i, data.getNum(), 0));
					i++;
					if (DBdata1.size() == z) {
						continue;
					}
					z++;
					break;
				} else {
					result.add(new PersonPassInfoOfMonUM(i, 0, 0));
					i++;
				}
			}
		}
		for (PassInfoOfMonDM data : DBdata2) {
			result.get(data.getD() - 1).setPassTime(data.getNum());
		}
		return result;
	}

	@Override
	public List<PopulationTrendUM> getPopulationTrend(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);

		List<PopulationTrendUM> result = new ArrayList<>();

		Calendar now = Calendar.getInstance();
		int i = 0;
		Integer month;
		while (i < 3) {
			int year = now.get(Calendar.YEAR) - i;
			if (i == 0) month = now.get(Calendar.MONTH) + 1;
			else month=-1;
			PopulationTrendUM um = person.getPopulationTrend(params, year, month);
			um.setYear(year);
			result.add(um);
			i++;
		}
		return result;
	}

	@Override
	public PopulationByLableUM getLalePopulationRatio(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);

		PopulationByLableUM result = person.getLastYearLablePopulation(params);

		LablePopulationUM lablePopulationUM = pservice.getLablePopulationSituation(communityId);
		Integer population = person.getTotalPopulation(params);

		int LastPopulation = result.getPopulationNum();
		int LastCare = result.getCareNum();
		int LastRisk = result.getRiskNum();
		int LastService = result.getServiceNum();
		int LastBlackList = result.getBlackListNum();

		float personRatio = (float) (population - LastPopulation) / (float) (LastPopulation == 0 ? 1 : LastPopulation) * 100;
		float careRatio = (float) (lablePopulationUM.getCarePerson() - LastCare) / (float) (LastCare == 0 ? 1 : LastCare) * 100;
		float riskRatio = (float) (lablePopulationUM.getRiskPerson() - LastRisk) / (float) (LastRisk == 0 ? 1 : LastRisk) * 100;
		float serviceRatio = (float) (lablePopulationUM.getServerPerson() - LastService) / (float) (LastService == 0 ? 1 : LastService) * 100;
		float blackListRatio = (float) (lablePopulationUM.getBlacklistPerson() - LastBlackList) / (float) (LastBlackList == 0 ? 1 : LastBlackList) * 100;

		result.setPopulationNum(population);
		result.setAllRatio(personRatio);
		result.setCareNum(lablePopulationUM.getCarePerson());
		result.setCareRatio(careRatio);
		result.setRiskNum(lablePopulationUM.getRiskPerson());
		result.setRiskRatio(riskRatio);
		result.setServiceNum(lablePopulationUM.getServerPerson());
		result.setServiceRatio(serviceRatio);
		result.setBlackListNum(lablePopulationUM.getBlacklistPerson());
		result.setBlackListRatio(blackListRatio);

		return result;
	}

	@Override
	public List<PersonFloorsStatisticsUM> getPersonNumByfloors(
			Integer communityId) throws HKException {
		Map<String, Object> params = super.getTableParams(communityId);

		List<PersonFloorsStatisticsUM> result = person.getPersonNumByfloors(params);
		return result;
	}

	@Override
	public PassRealTimeUM getPassRealTime(Integer communityId) {
		Map<String, Object> params = super.getTableParams(communityId);

		PassRealTimeUM result = new PassRealTimeUM();
		PersonAlarmTypeDM data = person.getAlarmOfTypeToMonth(params);
		result.setImgs(record.getRecordLast5Img(params));
		result.setPersonNum(person.getTotalTimeOfPass(params));
		result.setCareAlarmNum(data.getCarenum());
		result.setBlackListAlarmNum(data.getBlacknum());
		return result;
	}

	@Override
	public void PersonMessageSend(PersonFaceMessageSendUM mes) {
		Map<String, Object> params = super.getTableParams(1);

		if (mes == null) {
			mes = new PersonFaceMessageSendUM();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
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
			Integer result = record.getRecordByToday(params, 1) > 0 ? 1 : 0;
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

		if (mes.getLableType().equals("F04")) {
			mes.setBlackListAlarmNum(1);
			mes.setCareAlarmNum(0);
		} else {
			mes.setCareAlarmNum(0);
			mes.setBlackListAlarmNum(0);
		}


		mes.setLableName(PersonLableEN.getIndexByKey(mes.getLableType()).getValue());
		mes.setTypeName(RecordTypeEN.getNameByIndex(mes.getRecordType()).getName());

		Message msg = new Message();
		msg.setCommunityId(mes.getCommunityId().toString());
		msg.setSource("0");
		msg.setCategory("C01");
		msg.setCode("population");
		msg.setData(mes);
		msgService.sendMessage(msg);
	}

}
