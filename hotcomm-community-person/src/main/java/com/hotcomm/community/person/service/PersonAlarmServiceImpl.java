package com.hotcomm.community.person.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.service.device.DeviceService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.hotcomm.community.common.bean.db.person.HoleAndDetailInfoDM;
import com.hotcomm.community.common.bean.db.person.HoleObjDM;
import com.hotcomm.community.common.bean.db.person.HolePushDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmInfoDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonIDAndNoDM;
import com.hotcomm.community.common.bean.db.person.PersonRecordDM;
import com.hotcomm.community.common.bean.db.person.PopulationAlarmDM;
import com.hotcomm.community.common.bean.db.person.RecordNumDM;
import com.hotcomm.community.common.bean.en.person.PersonRecordWaysEN;
import com.hotcomm.community.common.bean.pa.person.PersonAlarmPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.person.PersonLast50AlarmUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.person.PersonAlarmService;
import com.hotcomm.community.common.service.person.PersonHoleService;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.person.mapper.PersonAlarmMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class PersonAlarmServiceImpl extends BaseService implements PersonAlarmService {

	private Date d;
	private SimpleDateFormat sdf;
	private long nd = 1000 * 24 * 60 * 60;
	private long nh = 1000 * 60 * 60;

	@Autowired
	private CommunityService communityService;
	@Autowired
	private PersonAlarmMapper alarm;
	@Autowired
	private PersonHoleService hole;
	@Autowired
	private PersonRecordService record;
	@Autowired
	private PersonService person;
	@Autowired
	private ProscessService alarmService;

	@Override
	public PageInfo<PopulationAlarmDM> PersonAlarmPage(PersonAlarmPA params) {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		tableParams.put("recordType", params.getRecordType());
		tableParams.put("alarmLv", params.getAlarmLv());
		tableParams.put("lableId", params.getLableId());
		tableParams.put("startTime", params.getStartTime());
		tableParams.put("endTime", params.getEndTime());
		tableParams.put("content", params.getContent());
		tableParams.put("pId", params.getPId());

		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<PopulationAlarmDM> page = alarm.PersonAlarmPage(tableParams);
		List<PopulationAlarmDM> personLableList = page;
		return new PageInfo<>(page.getTotal(), personLableList, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public PersonAlarmInfoDM PersonAlarmInfo(Integer id, Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		PersonAlarmInfoDM result = alarm.PersonAlarmInfo(tableParams, id);
		return result;
	}

	@Override
	public Integer delPersonAlarm(Integer id, Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer result;
		try {
			result = alarm.delPersonAlarm(tableParams, id);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PAE0001", e);
		}
		return result;
	}

	@Override
	public void addPersonAlarm() {
		// 查询所有社区
		System.out.println("执行人口预警布控定时任务-----------");
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		for (CommunityListAllUM communityListAllUM : communityList) {
			if(communityListAllUM.getCid()==1) {
				d=new Date();
				sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


				Map<String, Object> tableParams = super.getTableParams(communityListAllUM.getCid());

				try {
					List<HoleAndDetailInfoDM> list = hole.getHoleAndDetail(communityListAllUM.getCid(), sdf.format(d));// 获取可以开始的布控和规则
					for (HoleAndDetailInfoDM data : list) {// 循环布控
						HoleObjDM obj = new Gson().fromJson(data.getHolePopulations(), HoleObjDM.class);// 把数据库中的布控对象json转java对象
						HolePushDM[] fromJson = new Gson().fromJson(data.getPush(), HolePushDM[].class);
						List<HolePushDM>pushList=Arrays.asList(fromJson);
						String push="";
						for (HolePushDM holePushDM : pushList) {
							push=push+holePushDM.getP_id()+",";
						}
						push = push.substring(0,push.length() - 1);
						data.setPush(push);
						if (data.getHoleType() == 1) {// 判断布控是群体还是个人
							List<PersonIDAndNoDM> populationData = person.getPersonIDAndNo(communityListAllUM.getCid(), obj.getAge().get(0),obj.getAge().get(1), obj.getSex(), obj.getLable_id(), obj.getPeople(), obj.getNationality(),null);// 根据布控对象的特征 筛选出 居民id
							for (PersonIDAndNoDM personList : populationData) {// 循环已筛选的居民

								PersonRecordDM recordData=record.getPersonRecordByPId(1, personList.getPId(), data.getBeginTime(),data.getEndTime());// 获取居民在布控规则时间内的最后一条通行记录

								String time=null;
								if(recordData ==null || recordData.getRecordTime() ==null) {//判断是否存在最后一条通行记录
									time=data.getBeginTime();//若不存在  则赋予时间为开始布控规则的时间
								}
								else {
									time=recordData.getRecordTime();
								}

								String lastAlarmTime=alarm.selectLastAlarmTime(tableParams, personList.getPId(), data.getBeginTime(), sdf.format(d),data.getWay()); //获取最后一次报警时间
								if(lastAlarmTime==null) {//若不存在 则获取布控规则开始时间
									lastAlarmTime=data.getBeginTime();
								}
								List<RecordNumDM> selectRecordNum = record.selectRecordNum(communityListAllUM.getCid(), personList.getPId(), lastAlarmTime, sdf.format(d));//获取当前用户,从上一个报警记录后,到现在之间的 通行记录
								PersonDM personInfo = person.getPersonInfo(communityListAllUM.getCid(), personList.getPId());
								Date timedate=sdf.parse(time);
								Date lastAlarmTimeDate=sdf.parse(lastAlarmTime);
								Integer test2;
								if(timedate.compareTo(lastAlarmTimeDate)!=1){
									test2= holeToAlarm(tableParams,communityListAllUM.getCid(),lastAlarmTime, recordData, data,personInfo,selectRecordNum);//自定义方法  用于4中不同分支的操作
								}else {
									test2= holeToAlarm(tableParams,communityListAllUM.getCid(),time, recordData, data,personInfo,selectRecordNum);//自定义方法  用于4中不同分支的操作
								}
								if(test2>0) {
									person.updatePersonAlarmTime(communityListAllUM.getCid(),  personList.getPId());//修改居民信息列表里的报警次数
								}
							}
						} else {//个人
							PersonRecordDM recordData = record.getPersonRecordByPId(communityListAllUM.getCid(), obj.getP_id(), data.getBeginTime(),data.getEndTime());// 获取居民在布控规则时间内的最后一条通行记录
							String time=null;
							if(recordData ==null || recordData.getRecordTime() ==null) //判断是否存在最后一条通行记录
								time=data.getBeginTime();//若不存在  则赋予时间为开始布控规则的时间
							else
								time=recordData.getRecordTime();
							String lastAlarmTime=alarm.selectLastAlarmTime(tableParams, obj.getP_id(), data.getBeginTime(), sdf.format(d),data.getWay());//获取最后一次报警时间

							if(lastAlarmTime==null) lastAlarmTime=data.getBeginTime(); //若不存在 则获取布控规则开始时间

							List<RecordNumDM> selectRecordNum = record.selectRecordNum(communityListAllUM.getCid(), obj.getP_id(), lastAlarmTime, sdf.format(d));//获取当前用户,从上一个通行记录后,到现在之间的 通行记录
							PersonDM personInfo = person.getPersonInfo(communityListAllUM.getCid(), obj.getP_id());
							Date timedate=sdf.parse(time);
							Date lastAlarmTimeDate=sdf.parse(lastAlarmTime);
							Integer test2;
							if(timedate.compareTo(lastAlarmTimeDate)!=1){
								test2= holeToAlarm(tableParams,communityListAllUM.getCid(),lastAlarmTime, recordData, data,personInfo,selectRecordNum);//自定义方法  用于4中不同分支的操作
							}else {
								test2= holeToAlarm(tableParams,communityListAllUM.getCid(),time, recordData, data,personInfo,selectRecordNum);//自定义方法  用于4中不同分支的操作
							}
							if(test2>0) {
								person.updatePersonAlarmTime(communityListAllUM.getCid(), obj.getP_id());//修改居民信息列表里的报警次数
							}
						}
					}
				} catch (Exception e) {
					throw this.exceptionManager.configLog(error).errorRecord("PAE0002", e);
				}
			}
		}
	}

	/**
	 * 警报分类 以及添加警报
	 * @param tableParams
	 * @param time
	 * @param recordDM
	 * @param holeInfo
	 * @param recordNum
	 * @return
	 */
	private Integer holeToAlarm(Map<String, Object> tableParams,Integer communityId,String time, PersonRecordDM recordDM, HoleAndDetailInfoDM holeInfo,PersonDM personInfo,List<RecordNumDM> recordNum) {
		Integer result=0;
		switch (holeInfo.getWay()) {
		case 1://未归
			try {
				Date parse = sdf.parse(time);
				long diff = d.getTime() - parse.getTime();
				long day = diff / nd;
				long hour = diff % nd / nh;
				long pd = day * 24 + hour;
				if (pd >= holeInfo.getNoReturn()) {
					String recordAddress=null;
					String Imgs=null;
					String Video=null;
					String deviceMac=null;
					if(recordDM==null)
						result = alarm.addPersonAlarm(tableParams, sdf.format(d), null, holeInfo.getAlarmLv(), personInfo.getLableId(), null, null, personInfo.getPNo(), personInfo.getFaceNo(), null, personInfo.getPId(), holeInfo.getHoleTitle(), PersonRecordWaysEN.NO_RETURN.getIndex());
					else {
						recordAddress = recordDM.getRecordAddress()==null?"":recordDM.getRecordAddress();
						Imgs=recordDM.getImgs()==null?"":recordDM.getImgs();
						Video=recordDM.getVideo()==null?"":recordDM.getVideo();
						deviceMac=recordDM.getDeviceMac()==null?"":recordDM.getDeviceMac();
						Integer RecordType=recordDM.getRecordType()==null?0:recordDM.getRecordType();
						result = alarm.addPersonAlarm(tableParams, sdf.format(d), recordAddress, holeInfo.getAlarmLv(),personInfo.getLableId(), Imgs, Video, personInfo.getPNo(),personInfo.getFaceNo(),RecordType, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.NO_RETURN.getIndex());
					}

					AlarmInsertPA pa = new AlarmInsertPA();
					pa.setCommunityId(communityId);
					pa.setAlarmType(3);
					pa.setAlarmSourceType(personInfo.getLableId());
					pa.setAlarmSourceId(personInfo.getPId());
					pa.setAlarmSource(personInfo.getName());
					pa.setAlarmMessage(holeInfo.getHoleTitle()+personInfo.getLableName()+personInfo.getName());
					pa.setAlarmLevel(holeInfo.getAlarmLv());
					pa.setAlarmAddress(recordAddress);
					pa.setAlarmNatureOfPerson(personInfo.getLableName());
					pa.setHandelUser(holeInfo.getPush());
					pa.setAlarmImg(Imgs);
					pa.setAlarmVideo(deviceMac);
					pa.setAlarmNatureOfVehicle(recordDM.getUid().toString());
					alarmService.insertAlarmToMain(pa);
				}
			} catch (ParseException e) {
				throw this.exceptionManager.configLog(error).errorRecord("PAE0002", e);
			}
			break;
		case 2://未出
			try {
				Date parse = sdf.parse(time);
				long diff = d.getTime() - parse.getTime();
				long day = diff / nd;
				long hour = diff % nd / nh;
				long pd = day * 24 + hour;
				if (pd >= holeInfo.getNoGo()) {
					String recordAddress=null;
					String Imgs=null;
					String Video=null;
					String deviceMac=null;
					if(recordDM==null)
						result = alarm.addPersonAlarm(tableParams, sdf.format(d), null, holeInfo.getAlarmLv(),personInfo.getLableId(), null, null, personInfo.getPNo(), personInfo.getFaceNo(),null, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.NO_GO.getIndex());
					else {
						recordAddress = recordDM.getRecordAddress()==null?"":recordDM.getRecordAddress();
						Imgs=recordDM.getImgs()==null?"":recordDM.getImgs();
						Video=recordDM.getVideo()==null?"":recordDM.getVideo();
						deviceMac=recordDM.getDeviceMac()==null?"":recordDM.getDeviceMac();
						Integer RecordType=recordDM.getRecordType()==null?0:recordDM.getRecordType();
						result = alarm.addPersonAlarm(tableParams, sdf.format(d), recordAddress, holeInfo.getAlarmLv(),personInfo.getLableId(), Imgs, Video, personInfo.getPNo(), personInfo.getFaceNo(),RecordType, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.NO_GO.getIndex());
					}
					AlarmInsertPA pa = new AlarmInsertPA();
					pa.setCommunityId(communityId);
					pa.setAlarmType(3);
					pa.setAlarmSourceType(personInfo.getLableId());
					pa.setAlarmSourceId(personInfo.getPId());
					pa.setAlarmSource(personInfo.getName());
					pa.setAlarmMessage(holeInfo.getHoleTitle()+personInfo.getLableName()+personInfo.getName());
					pa.setAlarmLevel(holeInfo.getAlarmLv());
					pa.setAlarmAddress(recordAddress);
					pa.setAlarmNatureOfPerson(personInfo.getLableName());
					pa.setHandelUser(holeInfo.getPush());
					pa.setAlarmImg(Imgs);
					pa.setAlarmVideo(deviceMac);
					pa.setAlarmNatureOfVehicle(recordDM.getUid().toString());
					alarmService.insertAlarmToMain(pa);
				}
			} catch (ParseException e) {
				throw this.exceptionManager.configLog(error).errorRecord("PAE0002", e);
			}
			break;
		case 3://出行频率
			int i=0;
			try {
				if(recordNum.size()!=0) {
					for (RecordNumDM num : recordNum) {
						if(num.getNums()>=holeInfo.getNums()) {
							i++;
						}
					}
					if(i>=holeInfo.getDays()) {
						String recordAddress=null;
						String Imgs=null;
						String Video=null;
						String deviceMac=null;
						if(recordDM==null)
							result = alarm.addPersonAlarm(tableParams, sdf.format(d), null, holeInfo.getAlarmLv(),personInfo.getLableId(), null, null, personInfo.getPNo(), personInfo.getFaceNo(),null, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.TRIP.getIndex());
						else{
							recordAddress = recordDM.getRecordAddress()==null?"":recordDM.getRecordAddress();
							Imgs=recordDM.getImgs()==null?"":recordDM.getImgs();
							Video=recordDM.getVideo()==null?"":recordDM.getVideo();
							deviceMac=recordDM.getDeviceMac()==null?"":recordDM.getDeviceMac();
							Integer RecordType=recordDM.getRecordType()==null?0:recordDM.getRecordType();
							result = alarm.addPersonAlarm(tableParams, sdf.format(d), recordAddress, holeInfo.getAlarmLv(),personInfo.getLableId(), Imgs, Video, personInfo.getPNo(), personInfo.getFaceNo(),RecordType, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.TRIP.getIndex());
						}
						AlarmInsertPA pa = new AlarmInsertPA();
						pa.setCommunityId(communityId);
						pa.setAlarmType(3);
						pa.setAlarmSourceType(personInfo.getLableId());
						pa.setAlarmSourceId(personInfo.getPId());
						pa.setAlarmSource(personInfo.getName());
						pa.setAlarmMessage(holeInfo.getHoleTitle()+personInfo.getLableName()+personInfo.getName());
						pa.setAlarmLevel(holeInfo.getAlarmLv());
						pa.setAlarmAddress(recordAddress);
						pa.setAlarmNatureOfPerson(personInfo.getLableName());
						pa.setHandelUser(holeInfo.getPush());
						pa.setAlarmImg(Imgs);
						pa.setAlarmVideo(deviceMac);
						pa.setAlarmNatureOfVehicle(recordDM.getUid().toString());
						alarmService.insertAlarmToMain(pa);
					}
				}
			} catch (Exception e) {
				throw this.exceptionManager.configLog(error).errorRecord("PAE0002", e);
			}
			break;
		case 4://出行频率 --连续
			System.out.println(recordNum.size());
			if(recordNum.size()!=0) {
				int i2=0;
				int x=recordNum.get(0).getNums()>=holeInfo.getNums()? 1 : 0 ;//先对比第一个日期的通行次数
				for(int z =1;z<recordNum.size();z++) {
					try {
						x=recordNum.get(z).getNums()>=holeInfo.getNums()? x+1 : 0 ;//对比第二个日期的通行次数
						if(x>=2) {
							Date date1 = sdf.parse(recordNum.get(z-1).getTime()+" 00:00:00");
							Date date2 = sdf.parse(recordNum.get(z).getTime()+" 00:00:00");
							long day=(date1.getTime()-date2.getTime())/nd;
							i2= day == 1 ? i2+1 : 0;
							if(i2>=holeInfo.getDays()) {
								String recordAddress=null;
								String Imgs=null;
								String Video=null;
								String deviceMac=null;
								if(recordDM==null)
									result = alarm.addPersonAlarm(tableParams, sdf.format(d), null, holeInfo.getAlarmLv(),personInfo.getLableId(), null, null, personInfo.getPNo(), personInfo.getFaceNo(),null, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.CONTINUOUS_TRIP.getIndex());
								else {
									recordAddress = recordDM.getRecordAddress()==null?"":recordDM.getRecordAddress();
									Imgs=recordDM.getImgs()==null?"":recordDM.getImgs();
									Video=recordDM.getVideo()==null?"":recordDM.getVideo();
									deviceMac=recordDM.getDeviceMac()==null?"":recordDM.getDeviceMac();
									Integer RecordType=recordDM.getRecordType()==null?0:recordDM.getRecordType();
									result = alarm.addPersonAlarm(tableParams, sdf.format(d), recordAddress, holeInfo.getAlarmLv(),personInfo.getLableId(), Imgs, Video, personInfo.getPNo(), personInfo.getFaceNo(),RecordType, personInfo.getPId(), holeInfo.getHoleTitle(),PersonRecordWaysEN.CONTINUOUS_TRIP.getIndex());
								}
								AlarmInsertPA pa = new AlarmInsertPA();
								pa.setCommunityId(communityId);
								pa.setAlarmType(3);
								pa.setAlarmSourceType(personInfo.getLableId());
								pa.setAlarmSourceId(personInfo.getPId());
								pa.setAlarmSource(personInfo.getName());
								pa.setAlarmMessage(holeInfo.getHoleTitle()+personInfo.getLableName()+personInfo.getName());
								pa.setAlarmLevel(holeInfo.getAlarmLv());
								pa.setAlarmAddress(recordAddress);
								pa.setAlarmNatureOfPerson(personInfo.getLableName());
								pa.setHandelUser(holeInfo.getPush());
								pa.setAlarmImg(Imgs);
								pa.setAlarmVideo(deviceMac);
								pa.setAlarmNatureOfVehicle(recordDM.getUid().toString());
								alarmService.insertAlarmToMain(pa);
								break;
							}
						}
					} catch (ParseException e) {
						throw this.exceptionManager.configLog(error).errorRecord("PAE0002", e);
					}
				}
			}
			break;
		}
		return result;
	}

	@Override
	public List<Object> getAlarmLast50(Integer communityId,
			Integer type) throws HKException{
		Map<String, Object> tableParams = super.getTableParams(communityId);

		List<Object> result = Lists.newArrayList();
		Integer num = alarm.getAlarmLastWeekNum(tableParams, type);
		List<PersonLast50AlarmUM> data = alarm.getAlarmLast50(tableParams, type);
		for (PersonLast50AlarmUM a:data){
			if (a.getImgs().split(",").length > 1) {
				a.setImgs(a.getImgs().split(",")[1]);
			}
		}
		if(data.toString()=="[]") {
			data=null;
		}
		result.add(num);
		result.add(data);
		return result;
	}


}
