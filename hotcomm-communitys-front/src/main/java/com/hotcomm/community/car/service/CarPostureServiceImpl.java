package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotcomm.community.car.mapper.CarPassRecordsMapper;
import com.hotcomm.community.common.bean.ui.car.*;
import com.hotcomm.community.common.service.car.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.car.mapper.CarAlarmRecordsMapper;
import com.hotcomm.community.common.bean.pa.car.CarPassCountPA;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 车辆态势分析
 * @author: lhx
 * @date: 2019-01-25 10:34
 **/
@Service
public class CarPostureServiceImpl extends BaseService implements CarPostureServcie {

	@Autowired
	private CarPassRecordsService carPassRecordsService;

	@Autowired
	private CarRegService carRegService;

	@Autowired
	private CarLabelRelationService carLabelRelationService;

	@Autowired
	private CarStrService carStrService;

	@Autowired
	private CarAlarmRecordsMapper carAlarmRecordsMapper;

	@Autowired
	private CarPassRecordsMapper carPassRecordsMapper;

	/**统计今日车辆出、入次数、外来车辆总数、登记车辆总数、今日黑名单总数*/
	@Override
	public CarPostureTodayCountUM selectTodayCarCount(Integer communityId) throws HKException {
		CarPostureTodayCountUM carPostureTodayCountUM = new CarPostureTodayCountUM();
		Map<String, Object> map  = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		String dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		//登记车
		map.put("pageSource",0);
		Integer carRegCount = carRegService.selectCount(map);
		carPostureTodayCountUM.setRegCarCount(carRegCount);

		map.put("day", DateUtils.getTime());
		//驶入
		map.put("passType", 0);
		Integer enterCount = carPassRecordsService.selectDayCount(map);
		carPostureTodayCountUM.setEnterCount(enterCount);
		//驶出
		map.put("passType", 1);
		Integer outCount = carPassRecordsService.selectDayCount(map);
		carPostureTodayCountUM.setOutCount(outCount);
		//陌生车今日通行次数
		map.put("carType", 3);
		Integer strCarTodayCount = carPassRecordsService.selectDayCarCount(map);
		carPostureTodayCountUM.setStrCarTodayCount(strCarTodayCount);
		//黑名单车辆今日通行次数
		Integer blackCarCount = carLabelRelationService.selectBlackCarByLabelId(dynamicDbname,DateUtils.getTime());
		carPostureTodayCountUM.setBalckCarTodayCount(blackCarCount);

		return carPostureTodayCountUM;
	}

	/**按小时统计指定时间内车辆数出、入次数*/
	@Override
	public CarPassHourCountUM selectMonYearCarTotalPassCount(CarPassCountPA carPassCountPA) throws HKException {
		return carPassRecordsService.selectMonYearCarPassCount(super.getTableParams(carPassCountPA.getCommunityId()).get("dynamic_dbname").toString(),carPassCountPA.getTimeType());
	}

	/**按天数查询最一个月、一年停车车辆数*/
	@Override
	public CarPostureParkingCountUM selectPostureParkingCount(CarPassCountPA carPassCountPA) throws HKException {
		return carPassRecordsService.selectPostureParkingCount(super.getTableParams(carPassCountPA.getCommunityId()).get("dynamic_dbname").toString(), carPassCountPA.getTimeType());
	}

	/**统计关注车辆次数*/
	@Override
	public List<CarAttentionCountUM> selectAttentionCount(CarPassCountPA carPassCountPA) throws HKException {
		List<CarAttentionCountUM> carList = carPassRecordsService.selectPosAttentionCount( super.getTableParams(carPassCountPA.getCommunityId()).get("dynamic_dbname").toString(), carPassCountPA.getTimeType());
		int totalCount =0;
		for (int i = 0; i < carList.size(); i++) {
			totalCount+=carList.get(i).getCount();
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		for (int i = 0; i < carList.size(); i++) {
			CarAttentionCountUM carAttentionCountUM = carList.get(i);
			if (totalCount!=0) {
				carAttentionCountUM.setCountPer(Double.parseDouble(df.format((carAttentionCountUM.getCount()* 1.00)/totalCount)));
			}else {
				carAttentionCountUM.setCountPer(0);
			}
		}
		return carList;
	}

	/**统计关注车辆次数*/
	@Override
	public List<CarAlarmLevelCountUM> selectAlarmLevelCount(CarPassCountPA carPassCountPA) throws HKException {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(carPassCountPA.getCommunityId()).get("dynamic_dbname").toString());
		//timeType  0:近30天 1:近一年 2:近一周
		if (carPassCountPA.getTimeType()==0) {
			map.put("time", DateUtils.getBeforeNdayTime1(30));
		}else if(carPassCountPA.getTimeType()==1){
			map.put("time", DateUtils.getBeforeNdayTime1(365));
		}else if (carPassCountPA.getTimeType()==2) {
			map.put("time", DateUtils.getBeforeNdayTime1(7));
		}
		List<CarAlarmLevelCountUM> list = carAlarmRecordsMapper.selectAlarmLevelCount(map);
		List<CarAlarmLevelCountUM> alarmList = new ArrayList<>();
		for(int i=1;i<4;i++){
			CarAlarmLevelCountUM carAlarmLevelCount = new CarAlarmLevelCountUM();
			carAlarmLevelCount.setAlarmLeve(i+"级警报");
			carAlarmLevelCount.setCount(0);
			alarmList.add(carAlarmLevelCount);
		}
		list.forEach(carAlarmLevelCount -> {
			String alarmLeve = carAlarmLevelCount.getAlarmLeve();
			Integer count = carAlarmLevelCount.getCount();
			alarmList.stream().filter(item -> item.getAlarmLeve().equals(alarmLeve)).findFirst().ifPresent(item -> item.setCount(count));
		});
		return alarmList;
	}

	/**统计车辆信息（实有车辆、黑名单、陌生车..）*/
	@Override
	public Map<String,Object> selectPosCarCount(Integer communityId) throws HKException {
		Map<String,Object> map = new HashMap<>();
		String  dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		//黑名单车辆
		Integer blackCarCount = carLabelRelationService.selectBlackCarByLabelId(dynamicDbname,null);
		map.put("blackCarCount",blackCarCount);
		//登记车辆
		Map<String,Object> regCarMap = new HashMap<>();
		regCarMap.put("dynamicDbname",dynamicDbname);
		regCarMap.put("pageSource",0);
		Integer  regCarCount = carRegService.selectCount(regCarMap);
		map.put("regCarCount",regCarCount);
		//实有车辆
		map.put("totalCarCount",regCarCount);
		//通行总数
		Integer carPassCount = carPassRecordsService.selectDayCount(regCarMap);
		map.put("carPassCount",carPassCount);
		//陌生车辆
		Integer strCarCount = carStrService.selectYearCount(dynamicDbname);
		map.put("strCarCount",strCarCount);
		//求取平均数
		int count = regCarCount + strCarCount + blackCarCount;
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		map.put("regCarPer",Double.parseDouble(df.format((regCarCount* 1.00)/count)));
		map.put("strCarPer",Double.parseDouble(df.format((strCarCount* 1.00)/count)));
		map.put("blackCarPer",Double.parseDouble(df.format((blackCarCount* 1.00)/count)));
		return map;
	}

	/**查询本周通行车辆数、通行次数*/
	@Override
	public List<Object> selectPosCarWeekCount(Integer communityId) throws HKException {
		Map<String,Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		String  dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		List<CarMonParkCountUM> carCountForWeek = carPassRecordsMapper.selectPosCarWeekCount(dynamicDbname);
		List<CarMonParkCountUM> allCarCountForWeek = carPassRecordsMapper.selectPosAllCarWeekCount(dynamicDbname);
		for (int i=0;i<carCountForWeek.size();i++){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("day",carCountForWeek.get(i).getDay());
			jsonObject.put("carCount",carCountForWeek.get(i).getDayCount());
			jsonObject.put("allCarCount",allCarCountForWeek.get(i).getDayCount());
			list.add(jsonObject);
		}
		return list;
	}
}
