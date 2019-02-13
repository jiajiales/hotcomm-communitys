package com.hotcomm.community.person.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.system.OperateLogDM;
import com.hotcomm.community.common.bean.ui.person.DeviceMacByRecord;
import com.hotcomm.community.common.service.system.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonRecordDM;
import com.hotcomm.community.common.bean.db.person.PersonRecordInfoDM;
import com.hotcomm.community.common.bean.db.person.RecordNumDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.en.person.RecordTypeEN;
import com.hotcomm.community.common.bean.pa.person.PersonRecordPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.device.DeviceMacDataUM;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonLast50RecordUM;
import com.hotcomm.community.common.bean.ui.person.PersonStrangerListUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.person.mapper.PersonAlarmMapper;
import com.hotcomm.community.person.mapper.PersonMapper;
import com.hotcomm.community.person.mapper.PersonRecordMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class PersonRecordServiceImpl extends BaseService implements	PersonRecordService {

	@Autowired
	private MsgServiceImpl msgService;
	@Autowired
	private PersonRecordMapper record;
	@Autowired
	private DeviceService device;
	@Autowired
	private ProscessService alarmService;
	@Autowired
	private PersonAlarmMapper alarm;
	@Autowired
	private PersonMapper person;
	@Autowired
	private LogService log;
	@Override
	public PageInfo<PersonRecordDM> PersonRecordPage(PersonRecordPA params) {
		Map<String, Object> tableParams = super.getTableParams(
				params.getCommunityId());
		tableParams.put("recordType", params.getRecordType());
		tableParams.put("lableId", params.getLableId());
		tableParams.put("startTime", params.getStartTime());
		tableParams.put("endTime", params.getEndTime());
		tableParams.put("content", params.getContent());
		tableParams.put("pId", params.getPId());
		tableParams.put("faceNo", params.getFaceNo());

		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<PersonRecordDM> page = record.PersonRecordPage(tableParams);
		List<PersonRecordDM> personLableList = page;
		return new PageInfo<>(page.getTotal(), personLableList,
				params.getPageSize(), params.getPageIndex());
	}

	@Override
	public PersonRecordInfoDM PersonRecordInfo(Integer id,Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		PersonRecordInfoDM result = record.PersonRecordInfo(tableParams, id);
		return result;
	}

	@Override
	public Integer delPersonRecord(Integer id, Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		Integer result;
		try {
			result = record.delPersonRecord(tableParams, id);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PRE0001",e);
		}
		return result;
	}

	@Override
	public PersonRecordDM getPersonRecordByPId(Integer communityId, Integer pId,String beginTime, String endTime) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		return record.getPersonRecordByPId(tableParams, pId, beginTime,endTime);
	}

	@Override
	public List<RecordNumDM> selectRecordNum(Integer communityId, Integer pId,String beginTime, String endTime) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		return record.selectRecordNum(tableParams, beginTime, endTime, pId);
	}

	@Override
	public Integer addPersonRecord( Integer type,String faceNo, String record_time,  String img,String video, Integer record_type, String deviceMac) {
		String loginUserName="Python_facePoject";
		String logDesc="添加通行记录";
		String logCode="PM00504";
		Integer communityId=null;
		if (type == 1) {//记录方式为摄像头
			DeviceMacDataUM deviceCommunityId = device.deviceCommunityId(deviceMac);//获取设备所在的小区信息
			SelectVideoDeviceOnMacUM videoDevice = device.selectVideoDeviceOnMac(deviceMac, deviceCommunityId.getCommunityId());//获取摄像头的信息
			communityId=deviceCommunityId.getCommunityId();
			Map<String, Object> tableParams = super.getTableParams(communityId);
			String setIMG[]=img.split(",");
			PersonDM personDM = person.PersonInfoByFaceNo(tableParams, faceNo);//获取居民信息
			if (personDM == null) {// 判断是否为陌生人
				PersonStrangerListUM strangerinfo = person.getStrangerByNo(tableParams, faceNo);// 获取陌生人信息
				record.addRecord(tableParams, null, record_time,videoDevice.getDevAddress(), null, img, video, faceNo,1, deviceMac);// 添加通行记录
				Integer RecordId= record.getLastRecordId(tableParams,record_time,img);
				String TheImgUrl="";
				if(strangerinfo==null) {//判断此陌生人是否没有登记过
					person.addStrangerMessage(tableParams, faceNo, setIMG[1], videoDevice.getDevAddress());//添加陌生人信息
					TheImgUrl=setIMG[1];
				}else {
					TheImgUrl=strangerinfo.getHeadimg();
					Integer frequency = strangerinfo.getFrequency() + 1;// 陌生人通行频率加1
					if(frequency>10) {//出入次数大于10次
						alarm.addPersonAlarm(tableParams, record_time, videoDevice.getDevAddress(), 2, null, img, video, null,faceNo, 1, null, "陌生人进出", null);//添加报警记录
						AlarmInsertPA pa = new AlarmInsertPA();
						pa.setCommunityId(deviceCommunityId.getCommunityId());
						pa.setAlarmNatureOfVehicle(RecordId.toString());
						pa.setAlarmType(3);
						pa.setAlarmSourceType(0);
						pa.setAlarmSourceId(Integer.parseInt(faceNo));
						pa.setAlarmSource("陌生人");
						pa.setAlarmMessage("陌生人进出");
						pa.setAlarmLevel(2);
						pa.setAlarmAddress(videoDevice.getDevAddress());
						pa.setAlarmNatureOfPerson("陌生人");
						pa.setHandelUser("0");
						pa.setAlarmImg(img);
						pa.setAlarmVideo(deviceMac);
						alarmService.insertAlarmToMain(pa);
					}
					person.updateStrangerMessage(tableParams, faceNo, frequency);// 修改陌生人表
				}
				PersonFaceMessageSendUM mes = new PersonFaceMessageSendUM();
				mes.setRecordId(RecordId);
				mes.setCommunityId(deviceCommunityId.getCommunityId());
				mes.setFaceImgs(TheImgUrl);
				mes.setRecordImgs(setIMG[1]);
				mes.setPName("");
				mes.setAddress(videoDevice.getDevAddress());
				mes.setRecordTime(record_time);
				mes.setRecordType(1);
				mes.setLableType("F05");
				mes.setMatchingRate(80);
				mes.setDeviceMac(deviceMac);
				mes.setLng(videoDevice.getLon());
				mes.setLat(videoDevice.getLat());
				Integer result = record.getRecordByTodayByNo(tableParams,faceNo)>0?1:0;
				mes.setPersonNum(result);
				mes.setPersonTime(1);
				mes.setCode("face");
				this.RecordMessageSend(mes);//人脸感知消息发送
				mes.setCode("record");
				this.RecordMessageSend(mes);//通行感知消息发送
				mes.setCode("population");
				this.RecordMessageSend(mes);//人口态式消息发送
			} else {
				record.addRecord(tableParams, personDM.getPId(), record_time, videoDevice.getDevAddress(), personDM.getLableId(), img, video, faceNo, 1, deviceMac);// 不是陌生人则直接添加通行记录
				Integer RecordId= record.getLastRecordId(tableParams,record_time,img);
				if(personDM.getLableType().equals(PersonLableEN.BLACKLIST_PERSON.getKey())) {
					alarm.addPersonAlarm(tableParams, record_time, videoDevice.getDevAddress(), 1, personDM.getLableId(), img, video, null,faceNo, 1, personDM.getPId(), "黑名单人员:"+personDM.getName()+"进出", null);//添加报警记录
					AlarmInsertPA pa = new AlarmInsertPA();
					pa.setCommunityId(deviceCommunityId.getCommunityId());
					pa.setAlarmNatureOfVehicle(RecordId.toString());
					pa.setAlarmType(3);
					pa.setAlarmSourceType(personDM.getLableId());
					pa.setAlarmSourceId(personDM.getPId());
					pa.setAlarmSource(personDM.getName());
					pa.setAlarmMessage(personDM.getLableName()+" "+personDM.getName()+"出入");
					pa.setAlarmLevel(1);
					pa.setAlarmAddress(videoDevice.getDevAddress());
					pa.setAlarmNatureOfPerson(personDM.getLableName());
					pa.setHandelUser("0");
					pa.setAlarmImg(img);
					pa.setAlarmVideo(deviceMac);
					alarmService.insertAlarmToMain(pa);
					person.updatePersonAlarmTime(tableParams,  personDM.getPId());//修改居民信息列表里的报警次数
				}
				PersonFaceMessageSendUM mes = new PersonFaceMessageSendUM();
				mes.setRecordId(RecordId);
				mes.setCommunityId(deviceCommunityId.getCommunityId());
				mes.setFaceImgs(personDM.getHeadimg());
				mes.setRecordImgs(setIMG[1]);
				mes.setPId(personDM.getPId());
				mes.setPName(personDM.getName());
				mes.setAge(personDM.getAge());
				mes.setSex(personDM.getSex());
				mes.setAddress(videoDevice.getDevAddress());
				mes.setRecordTime(record_time);
				mes.setRecordType(1);
				mes.setLableType(personDM.getLableType());
				mes.setLableId(personDM.getLableId());
				mes.setMatchingRate(80);
				mes.setDeviceMac(deviceMac);
				mes.setLng(videoDevice.getLon());
				mes.setLat(videoDevice.getLat());
				Integer result = record.getRecordByToday(tableParams,personDM.getPId())>0?1:0;
				mes.setPersonNum(result);
				mes.setPersonTime(1);
				mes.setCode("face");
				this.RecordMessageSend(mes);//人脸感知消息发送
				mes.setCode("record");
				this.RecordMessageSend(mes);//通行感知消息发送
				mes.setCode("population");
				this.RecordMessageSend(mes);//人口态式消息发送
			}
			OperateLogDM logDm = new OperateLogDM(loginUserName, logDesc, logCode, new Date(), "0.0.0.0:8283", communityId);
			log.recordOperateLog(logDm);
		}else{
			System.out.println("**********start**********");
			communityId=1;
			Map<String, Object> tableParams = super.getTableParams(communityId);
			PersonDM personDM = person.PersonInfoByFaceNo(tableParams, faceNo);
			if(personDM.getLableType().equals(PersonLableEN.BLACKLIST_PERSON.getKey())) {
				alarm.addPersonAlarm(tableParams, record_time, "301航康", 1, personDM.getLableId(), img, null, null,null, 2, personDM.getPId(), "黑名单人员:"+personDM.getName()+"进出", null);//添加报警记录
				AlarmInsertPA pa = new AlarmInsertPA();
				pa.setCommunityId(communityId);
				pa.setAlarmType(3);
				pa.setAlarmSourceType(personDM.getLableId());
				pa.setAlarmSourceId(personDM.getPId());
				pa.setAlarmSource(personDM.getName());
				pa.setAlarmMessage(personDM.getLableName() + " " + personDM.getName() + "出入");
				pa.setAlarmLevel(1);
				pa.setAlarmAddress("301航康");
				pa.setAlarmNatureOfPerson(personDM.getLableName());
				pa.setHandelUser("0");
				pa.setAlarmImg(img);
				pa.setAlarmVideo("DMJ6001812-01740");
				alarmService.insertAlarmToMain(pa);
			}
			record.addRecord(tableParams, personDM.getPId(), record_time, "301航康", personDM.getLableId(), img, "", null, 2, "DMJ6001812-01740");
			Integer RecordId= record.getLastRecordId(tableParams,record_time,img);
			PersonFaceMessageSendUM mes = new PersonFaceMessageSendUM();
			mes.setRecordId(RecordId);
			mes.setCommunityId(communityId);
			mes.setFaceImgs(personDM.getHeadimg());
			mes.setRecordImgs(img);
			mes.setPName(personDM.getName());
			mes.setAge(personDM.getAge());
			mes.setSex(personDM.getSex());
			mes.setAddress("301航康");
			mes.setRecordTime(record_time);
			mes.setRecordType(1);
			mes.setLableType(personDM.getLableType());
			mes.setLableId(personDM.getLableId());
			mes.setMatchingRate(80);
			mes.setDeviceMac(deviceMac);
			mes.setPId(personDM.getPId());
			mes.setLng("113.756821");
			mes.setLat("23.002722");
			Integer result = record.getRecordByToday(tableParams,personDM.getPId())>0?1:0;
			mes.setPersonNum(result);
			mes.setPersonTime(1);
			mes.setCode("face");
			this.RecordMessageSend(mes);//人脸感知消息发送
//			mes.setCode("record");
//			this.RecordMessageSend(mes);//通行感知消息发送
			mes.setCode("population");
			this.RecordMessageSend(mes);//人口态式消息发送
			System.out.println("**********end**********");

		}
		return 1;
	}

	@Override
	public List<PersonLast50RecordUM> getRecordLast50(Integer communityId,Integer type) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		List<PersonLast50RecordUM> result = record.getRecordLast50(tableParams,type);
		if (result.toString()=="[]") {
			return null;
		}
		for (PersonLast50RecordUM data : result) {
			if(data.getImgs().split(",").length>1){
				data.setImgs(data.getImgs().split(",")[1]);
			}
			data.setTypeName(RecordTypeEN.getNameByIndex(data.getRecordType()).getName());
		}
		return result;
	}

	@Override
	public void RecordMessageSend(PersonFaceMessageSendUM mes) {

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

	@Override
	public List<DeviceMacByRecord> getDeviceMacByRecord(Integer communityId, String faceNo, Integer pId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		List<DeviceMacByRecord> result = record.getDeviceMacByRecord(tableParams, faceNo, pId);
		return result;
	}

}
