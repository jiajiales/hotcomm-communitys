package com.hotcomm.community.car.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.car.mapper.CarAlarmRecordsMapper;
import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.db.car.CarRegDM;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmDayUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmLevelCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmMonCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmTypeCountUM;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 车辆报警记录
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarAlarmRecordsServiceImpl extends BaseService implements CarAlarmRecordsService {
	
	@Autowired
	private CarAlarmRecordsMapper carAlarmRecordsMapper;
	
	@Autowired
	private CarRegService carRegService;
	
	@Autowired
	private CarStrService carStrService;

	/**新增车辆报警记录*/
	@Override
	@Transactional
	public Integer insert(CarAlarmRecordsDM carAlarmRecordsDM) throws HKException {
			carAlarmRecordsDM.setDynamicDbname(super.getTableParams(carAlarmRecordsDM.getCommunityId()).get("dynamic_dbname").toString());
			if (carAlarmRecordsDM.getCreateTime()==null) {
				carAlarmRecordsDM.setCreateTime(DateUtils.getNowTime());
			}
			//车牌号查登记车辆
			CarRegDM carRegDM = new CarRegDM();
			carRegDM.setCommunityId(carAlarmRecordsDM.getCommunityId());
			carRegDM.setNum(carAlarmRecordsDM.getNum());
			CarRegDetailUM carRegDetailUM = carRegService.detail(carAlarmRecordsDM.getCommunityId(),null,carAlarmRecordsDM.getNum());
			if (carRegDetailUM!=null) {
				//登记车辆报警次数加1
				carRegDM.setAlarmCount(carRegDetailUM.getAlarmCount()+1);
				carRegDM.setId(carRegDetailUM.getId());
				carRegService.updateAlarmCountById(carRegDM);
			}else {
				//车牌号查陌生车辆
				carAlarmRecordsDM.setType(3);
				CarStrPA carStrPA = new CarStrPA();
				carStrPA.setCommunityId(carAlarmRecordsDM.getCommunityId());
				carStrPA.setNum(carAlarmRecordsDM.getNum());
				CarStrUM carStrUM = carStrService.detail(carStrPA);
				if (carStrUM !=null) {
					//陌生车辆报警次数加1
					CarStrDM carStrDM = new CarStrDM();
					carStrDM.setCommunityId(carAlarmRecordsDM.getCommunityId());
					carStrDM.setAlarmCount(carStrUM.getAlarmCount()+1);
					carStrDM.setId(carStrUM.getId());
					carStrService.update(carStrDM);
				}
			}
			return carAlarmRecordsMapper.insert(carAlarmRecordsDM);
	}


	/**查看车辆报警记录详情*/
	@Override
	public CarAlarmRecordsUM detail(Integer id ,Integer communityId) throws HKException {	
		CarAlarmRecordsUM carAlarmRecords = carAlarmRecordsMapper.detail(id, super.getTableParams(communityId).get("dynamic_dbname").toString());
		//（0:小区车辆，1：单位车辆，2:外来登记车辆,3：陌生车）
		Integer carType = carAlarmRecords.getType();
		if (carType == 3) {
			CarStrPA carStrPA = new CarStrPA();
			carStrPA.setCommunityId(communityId);
			carStrPA.setNum(carAlarmRecords.getNum());
			CarStrUM carStrUM = carStrService.detail(carStrPA);
			carAlarmRecords.setColor(carStrUM.getColor());
			carAlarmRecords.setFirstImgPath(carStrUM.getCarImg());
			carAlarmRecords.setBrand(carStrUM.getBrand());
			carAlarmRecords.setAlarmCount(carStrUM.getAlarmCount());
			carAlarmRecords.setModel(carStrUM.getModel());
			carAlarmRecords.setModelType(carStrUM.getModelType());
		} else {
			CarRegDetailUM carRegDetailUM = carRegService.detail(communityId,null,carAlarmRecords.getNum());
			carAlarmRecords.setColor(carRegDetailUM.getColor());
			carAlarmRecords.setBrand(carRegDetailUM.getBrand());
			carAlarmRecords.setFirstImgPath(carRegDetailUM.getFrontPhoto());
			carAlarmRecords.setAlarmCount(carRegDetailUM.getAlarmCount());
			carAlarmRecords.setModel(carRegDetailUM.getModel());
			carAlarmRecords.setModelType(carRegDetailUM.getModelType());
		}
		return carAlarmRecords;
	}

	/**查询指定月份或指定星期后的报警记录数*/
	@Override
	public Integer selectMonthCount(Map<String, Object> map) throws HKException {
		return carAlarmRecordsMapper.selectMonthCount(map);
	}

	/**本月车辆预警级别统计*/
	@Override
	public List<CarAlarmLevelCountUM> selectAlarmLevelCount(Integer communityId) throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		map.put("month",DateUtils.getYearMon(new Date()));
		List<CarAlarmLevelCountUM> list = carAlarmRecordsMapper.selectAlarmLevelCount(map);
		List<CarAlarmLevelCountUM> alarmList = new ArrayList<>();
		for(int i=1;i<4;i++){
			CarAlarmLevelCountUM carAlarmLevelCount = new CarAlarmLevelCountUM();
			carAlarmLevelCount.setAlarmLeve(i+"级警报");
			carAlarmLevelCount.setCount(0);
			alarmList.add(carAlarmLevelCount);
		}
		list.forEach(carAlarmLevelCountUm->{
			String alarmLeve = carAlarmLevelCountUm.getAlarmLeve();
			Integer count = carAlarmLevelCountUm.getCount();
			alarmList.stream().filter(item -> item.getAlarmLeve().equals(alarmLeve)).findFirst().ifPresent(item -> item.setCount(count));;
		});
		return alarmList;
	}

	/**月报警统计（报警趋势）*/
	@Override
	public CarAlarmMonCountUM selectAlarmDayCount(Integer  communityId)  throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		CarAlarmMonCountUM carAlarmMonCountUM = new CarAlarmMonCountUM();
		//本月的天数
		int curMonDayCount =Integer.parseInt(DateUtils.getLastMonDay().get("curMonDay").toString());
		map.put("monFirstDay", DateUtils.initDateByDay().get("beginOfDate"));
		map.put("monDayCount", curMonDayCount);
		map.put("limitDay", curMonDayCount);
		map.put("month", DateUtils.getYearMon(new Date()));
		List<CarAlarmDayUM> curList = carAlarmRecordsMapper.selectAlarmDayCount(map);
		carAlarmMonCountUM.setCurMonAlarmCountList(curList);
		
		Map<String, Integer> dateMap1 = DateUtils.getLastMonDay();
		map.put("monFirstDay", DateUtils.getLastMaxMonthDate());
		map.put("monDayCount", dateMap1.get("lastMonDay"));
		map.put("limitDay", dateMap1.get("lastMonDay"));
		map.put("month",DateUtils.getLastYearMon());
		List<CarAlarmDayUM> lastList = carAlarmRecordsMapper.selectAlarmDayCount(map);
		carAlarmMonCountUM.setLastMonAlarmCountList(lastList);
		
		return carAlarmMonCountUM;
	}

	/**查询当天不同报警类型的车辆数*/
	@Override
	public CarFeelAlarmTypeCountUM selectFeelAlarmTypeCount(Integer ruleId,String dynamicDbname) throws HKException {
		return carAlarmRecordsMapper.selectFeelAlarmTypeCount(ruleId,dynamicDbname, DateUtils.getTime());
	}

	/**查询车辆报警记录*/
	@Override
	public List<CarAlarmListUM> selectAlarmList(String dynamicDbname, String weekStart) throws HKException {
		return carAlarmRecordsMapper.selectAlarmList(dynamicDbname, weekStart);
	}
}
