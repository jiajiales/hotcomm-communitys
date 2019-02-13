package com.hotcomm.community.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.bean.ui.car.CarmMonEnterCountUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarAlarmRuleService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.common.service.quartz.QuartzCarJobService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 车辆定时任务
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class QuartzCarJobServiceImpl extends BaseService implements QuartzCarJobService {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private  CarPassRecordsService carPassRecordsService;
	
	@Autowired
	private CarAlarmRecordsService carAlarmRecordsService;
	
	@Autowired
	private CarStrService carStrService;
	
	@Autowired
	private CarAlarmRuleService carAlarmRuleService;
	
	@Autowired
	private CarLabelRelationService carLabelRelationService;
	
	@Autowired
	private ProscessService alarmService;
	
	@Autowired
	private MsgServiceImpl msgServiceImpl;
	
	/**车辆多次进入定时报警任务*/
	@Override
	public void CarManyEnter() throws HKException {
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		for (int i = 0; i < communityList.size(); i++) {
			CommunityListAllUM communityListAllUM = communityList.get(i);
			
			CarAlarmRulePA alarmRule = new CarAlarmRulePA();
			alarmRule.setCommunityId(communityListAllUM.getCid());
			alarmRule.setRuleId(2);
			alarmRule.setState(0);
			//查询报警规则
			CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(alarmRule);
			
			if (carAlarmRuleUM != null) {
				//先测试001社区，后期此处放开
				if (communityListAllUM.getCid()==1) {
					//查询超过预警次数的车辆
					List<CarmMonEnterCountUM> passList = carPassRecordsService.selectMonEnterCount(communityListAllUM.getCid(),carAlarmRuleUM);
					if (passList!=null) {
						for (int j = 0; j < passList.size(); j++) {
							String num = passList.get(j).getCarNum();
							//拿到最新的通行地点
							CarPassRecordsUM carPassRecordsUM = carPassRecordsService.selectNewCarAddress(communityListAllUM.getCid(),num);
							
							CarAlarmRecordsDM carAlarmRecordsDM = new CarAlarmRecordsDM();
							carAlarmRecordsDM.setCommunityId(communityListAllUM.getCid());
							carAlarmRecordsDM.setNum(num);
							//多次出入
							carAlarmRecordsDM.setRuleId(2);
							//陌生车辆
							carAlarmRecordsDM.setType(3);
							carAlarmRecordsDM.setCreateTime(DateUtils.getNowTime());
							carAlarmRecordsDM.setPhoto(passList.get(j).getCarImg());
							carAlarmRecordsDM.setAddress(carPassRecordsUM.getAdress());
							//新增车辆报警记录(本地记录表)
							carAlarmRecordsService.insert(carAlarmRecordsDM);
							
							JSONArray jsonArray =	JSONArray.fromObject(JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person"));
							StringBuilder strBuilder= new StringBuilder();
							for (int k = 0; k < jsonArray.size(); k++) {
								strBuilder.append(jsonArray.get(k)+",");
							}
							
							AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
							alarmInsertPA.setCommunityId(communityListAllUM.getCid());
							alarmInsertPA.setAlarmType(2);
							//车辆黑名单ID(规则表中的)
							alarmInsertPA.setAlarmSourceType(2);
							alarmInsertPA.setAlarmSource(num);
							//陌生车辆id
							alarmInsertPA.setAlarmSourceId(passList.get(j).getId());
							alarmInsertPA.setAlarmVideo(carPassRecordsUM.getVideoCode());
							alarmInsertPA.setAlarmLevel(carAlarmRuleUM.getAlarmLeve());
							alarmInsertPA.setAlarmMessage("多次出入车辆 "+num+"入侵");
							alarmInsertPA.setAlarmNatureOfVehicle("陌生车");
							//处理人
							alarmInsertPA.setHandelUser(strBuilder.substring(0, strBuilder.length()-1));
							alarmInsertPA.setAlarmImg(passList.get(j).getCarImg());
							alarmInsertPA.setAlarmAddress(carPassRecordsUM.getAdress());
							alarmInsertPA.setAlarmNatureOfPerson(carAlarmRecordsDM.getId()+"");
							alarmService.insertAlarmToMain(alarmInsertPA);//插入报警总表		
							
							JSONObject object = new JSONObject();
							object.put("carType", "陌生车");
							object.put("alarmLevel", carAlarmRuleUM.getAlarmLeve());
							object.put("adress", carPassRecordsUM.getAdress());
							object.put("num", num);
							object.put("carImgPath", carPassRecordsUM.getCarImgPath());
							object.put("createTime", carPassRecordsUM.getCreateTime());
							object.put("lon", carPassRecordsUM.getLon());
							object.put("lat", carPassRecordsUM.getLat());
							object.put("deviceId", carPassRecordsUM.getVideoCode());
							this.insertCarLabel(communityListAllUM.getCid(),num,4,object);//新增车辆标签并推送消息
						}
					}
				}
			}
		}
	}

	/**车辆长时间停留报警任务*/
	@Override
	public void CarLongParking() throws HKException {
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		for (int i = 0; i < communityList.size(); i++) {
			CommunityListAllUM communityListAllUM = communityList.get(i);
			
			CarAlarmRulePA alarmRule = new CarAlarmRulePA();
			alarmRule.setCommunityId(communityListAllUM.getCid());
			alarmRule.setRuleId(3);
			alarmRule.setState(0);
			//查询报警规则
			CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(alarmRule);
			
			if (carAlarmRuleUM !=null) {
				//先测试001社区，后期此处放开
				if (communityListAllUM.getCid()==1) {
					CarStrDM carStrDM = new CarStrDM();
					carStrDM.setCommunityId(communityListAllUM.getCid());
					//在小区中的车辆
					carStrDM.setState(0);
					List<CarStrUM> list = carStrService.selectLongParkingCarList(carStrDM,carAlarmRuleUM);
					if (list!=null) {
						for (int j = 0; j < list.size(); j++) {
							CarStrUM carStrUM = list.get(j);
							//拿到最新的通行地点
							CarPassRecordsUM carPassRecordsUM = carPassRecordsService.selectNewCarAddress(communityListAllUM.getCid(),carStrUM.getNum());
							CarAlarmRecordsDM carAlarmRecordsDM = new CarAlarmRecordsDM();
							carAlarmRecordsDM.setCommunityId(communityListAllUM.getCid());
							carAlarmRecordsDM.setNum(carStrUM.getNum());
							//长时间停留
							carAlarmRecordsDM.setRuleId(3);
							carAlarmRecordsDM.setType(3);
							carAlarmRecordsDM.setCreateTime(DateUtils.getNowTime());
							carAlarmRecordsDM.setPhoto(carStrUM.getCarImg());
							carAlarmRecordsDM.setAddress(carPassRecordsUM.getAdress());
							//新增车辆报警记录
							carAlarmRecordsService.insert(carAlarmRecordsDM);
							
							JSONArray jsonArray =	JSONArray.fromObject(JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person"));
							StringBuilder strBuilder= new StringBuilder();
							for (int k = 0; k < jsonArray.size(); k++) {
								strBuilder= strBuilder.append(jsonArray.get(k)+",");
							}
							AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
							alarmInsertPA.setCommunityId(communityListAllUM.getCid());
							alarmInsertPA.setAlarmType(2);//车辆
							alarmInsertPA.setAlarmSourceType(3);//车辆黑名单ID(规则表中的)
							alarmInsertPA.setAlarmSource(carStrUM.getNum());
							alarmInsertPA.setAlarmVideo(carPassRecordsUM.getVideoCode());//mac地址
							alarmInsertPA.setAlarmSourceId(list.get(j).getId());
							alarmInsertPA.setAlarmLevel(carAlarmRuleUM.getAlarmLeve());
							alarmInsertPA.setAlarmMessage("长时间停留车辆 "+carStrUM.getNum()+"入侵");
							alarmInsertPA.setAlarmNatureOfVehicle("陌生车");//车辆性质
							alarmInsertPA.setHandelUser(strBuilder.substring(0, strBuilder.length()-1));//处理人
							alarmInsertPA.setAlarmImg(carStrUM.getCarImg());
							alarmInsertPA.setAlarmAddress(carPassRecordsUM.getAdress());
							alarmInsertPA.setAlarmNatureOfPerson(carAlarmRecordsDM.getId()+"");
							alarmService.insertAlarmToMain(alarmInsertPA);//插入报警总表	
							
							JSONObject object = new JSONObject();
							object.put("carType", "陌生车");
							object.put("alarmLevel", carAlarmRuleUM.getAlarmLeve());
							object.put("adress", carPassRecordsUM.getAdress());
							object.put("num", carStrUM.getNum());
							object.put("carImgPath", carPassRecordsUM.getCarImgPath());
							object.put("createTime", carPassRecordsUM.getCreateTime());
							object.put("lon", carPassRecordsUM.getLon());
							object.put("lat", carPassRecordsUM.getLat());
							object.put("deviceId", carPassRecordsUM.getVideoCode());
							this.insertCarLabel(communityListAllUM.getCid(),carStrUM.getNum(),6,object);//新增车辆标签并推送消息
						}
					}
				}
			}
		}
	}

	/**过夜车预警任务*/
	@Override
	public void CarOverNight() throws HKException {
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		for (int i = 0; i < communityList.size(); i++) {
			CommunityListAllUM communityListAllUM = communityList.get(i);
			//查询报警规则
			CarAlarmRulePA alarmRule = new CarAlarmRulePA();
			alarmRule.setCommunityId(communityListAllUM.getCid());
			alarmRule.setRuleId(1);
			alarmRule.setState(0);
			CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleService.detail(alarmRule);
			
			if (carAlarmRuleUM!=null ) {
				//先测试001社区，后期此处放开
				if (communityListAllUM.getCid()==1) {
					CarStrDM carStrDM = new CarStrDM();
					//拿到最新的通行地点
					CarPassRecordsUM carPassRecordsUM = carPassRecordsService.selectNewCarAddress(communityListAllUM.getCid(),carStrDM.getNum());
					carStrDM.setCommunityId(communityListAllUM.getCid());
					//在小区中的车辆
					carStrDM.setState(0);
					List<CarStrUM> list = carStrService.selectNightCarList(carStrDM,carAlarmRuleUM);
					if (list!=null) {
						for (int j = 0; j < list.size(); j++) {
							CarStrUM carStrUM = list.get(j);
							CarAlarmRecordsDM carAlarmRecordsDM = new CarAlarmRecordsDM();
							//新增车辆报警记录
							carAlarmRecordsDM.setCommunityId(communityListAllUM.getCid());
							carAlarmRecordsDM.setNum(carStrUM.getNum());
							carAlarmRecordsDM.setRuleId(1);
							carAlarmRecordsDM.setType(3);
							carAlarmRecordsDM.setCreateTime(DateUtils.getNowTime());
							carAlarmRecordsDM.setPhoto(carStrUM.getCarImg());
							carAlarmRecordsDM.setAddress(carPassRecordsUM.getAdress());
							carAlarmRecordsService.insert(carAlarmRecordsDM);
									
							JSONArray jsonArray =	JSONArray.fromObject(JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person"));
							StringBuilder strBuilder= new StringBuilder();
							for (int k = 0; k < jsonArray.size(); k++) {
								strBuilder.append(jsonArray.get(k)+",");
							}
							//插入报警总表
							AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
							alarmInsertPA.setCommunityId(communityListAllUM.getCid());
							alarmInsertPA.setAlarmType(2);//车辆
							alarmInsertPA.setAlarmSourceType(1);//车辆黑名单ID(规则表中的)
							alarmInsertPA.setAlarmSource(carStrUM.getNum());
							alarmInsertPA.setAlarmVideo(carPassRecordsUM.getVideoCode());//mac地址
							alarmInsertPA.setAlarmSourceId(carStrUM.getId());
							alarmInsertPA.setAlarmLevel(carAlarmRuleUM.getAlarmLeve());
							alarmInsertPA.setAlarmMessage("过夜车 "+carStrUM.getNum()+"入侵");
							alarmInsertPA.setAlarmNatureOfVehicle("陌生车");//车辆性质
							alarmInsertPA.setHandelUser(strBuilder.substring(0, strBuilder.length()-1));//处理人
							alarmInsertPA.setAlarmImg(carStrUM.getCarImg());
							alarmInsertPA.setAlarmAddress(carPassRecordsUM.getAdress());
							alarmInsertPA.setAlarmNatureOfPerson(carAlarmRecordsDM.getId()+"");
							alarmService.insertAlarmToMain(alarmInsertPA);
							//新增车辆标签并推送消息
							JSONObject object = new JSONObject();
							object.put("carType", "陌生车");
							object.put("alarmLevel", carAlarmRuleUM.getAlarmLeve());
							object.put("adress", carPassRecordsUM.getAdress());
							object.put("num", carStrUM.getNum());
							object.put("carImgPath", carPassRecordsUM.getCarImgPath());
							object.put("createTime", carPassRecordsUM.getCreateTime());
							object.put("lon", carPassRecordsUM.getLon());
							object.put("lat", carPassRecordsUM.getLat());
							object.put("deviceId", carPassRecordsUM.getVideoCode());
							this.insertCarLabel(communityListAllUM.getCid(),carStrUM.getNum(),5,object);
						}
					}
				}
			}
		}
	}

	private void insertCarLabel(Integer communityId ,String carNum,Integer label,JSONObject object) {
		CarLabelRelationDM carLabelRelationDM = new CarLabelRelationDM();
		carLabelRelationDM.setCommunityId(communityId);
		carLabelRelationDM.setCarNum(carNum);
		carLabelRelationDM.setLabelId(label);
		carLabelRelationService.insert(carLabelRelationDM);

        //消息推送
		Message msg = new Message();
		msg.setCommunityId(communityId+"");
		msg.setSource("0");
		msg.setCategory("C02");
		msg.setCode("car");
		msg.setData(object);
		msgServiceImpl.sendMessage(msg);
	}
}
