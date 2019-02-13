package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.service.device.DeviceFellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.car.CarTypeCountDM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmTypeCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassHoursCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassWeekCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelTotalCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarFeelService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 车辆神经感知
 * @author: lhx
 * @date: 2019-01-25 10:34
 **/
@Service
public class CarFeelServiceImpl extends BaseService implements CarFeelService {

	@Autowired
	private CarRegService carRegService;
	
	@Autowired
	private CarStrService carStrService;
	
	@Autowired
	private CarLabelRelationService carLabelRelationService;
	
	@Autowired
	private CarPassRecordsService carPassRecordsService;
	
	@Autowired
	private CarAlarmRecordsService carAlarmRecordsService;

	@Autowired
	private DeviceFellService deviceFellService;
	
	/**车辆基础信息统计*/
	@Override
	public CarFeelTotalCountUM selectCarTotalCount(Integer communityId) throws HKException {
		Map<String, Object> map = new HashMap<>();
		CarFeelTotalCountUM carFeelTotalCountUM = new CarFeelTotalCountUM();
		String  dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		map.put("dynamicDbname", dynamicDbname);
		//登记车辆
		map.put("pageSource",0);
		Integer  regCarCount = carRegService.selectCount(map);
		carFeelTotalCountUM.setRegCarCount(regCarCount);
		
		carFeelTotalCountUM.setComCarCount(0);
		carFeelTotalCountUM.setCorCarCount(0);
		carFeelTotalCountUM.setOtherRegCarCount(0);
		List<CarTypeCountDM> list = carRegService.selectTypeCount(map);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				CarTypeCountDM carTypeCountDM = list.get(i);
				if (carTypeCountDM.getCarType().equals("单位车辆")) {
					carFeelTotalCountUM.setCorCarCount(carTypeCountDM.getCount());
				}else if (carTypeCountDM.getCarType().equals("小区车辆")) {
					carFeelTotalCountUM.setComCarCount(carTypeCountDM.getCount());
				}else {
					carFeelTotalCountUM.setOtherRegCarCount(carTypeCountDM.getCount());
				}
			}
		}
		//最近一年的陌生车辆
		Integer strCarCount =  carStrService.selectYearCount(dynamicDbname);
		carFeelTotalCountUM.setStrCarCount(strCarCount);
		//黑名单车辆
		Integer blackCarCount = carLabelRelationService.selectBlackCarByLabelId(dynamicDbname,null);
		carFeelTotalCountUM.setBlacklistCarCount(blackCarCount);
		List<DeviceMapUM> deviceList = deviceFellService.DeviceMap(communityId,"5",1);
		if (deviceList != null) {
			carFeelTotalCountUM.setCarRelDevCount(deviceList.size());
		} else {
			carFeelTotalCountUM.setCarRelDevCount(0);
		}
		return carFeelTotalCountUM;
	}

	/**按小时统计今日车辆通行次数、车辆数*/
	@Override
	public CarFeelPassCountListUM selectCarPassHoursCount(Integer communityId) throws HKException {
		CarFeelPassCountListUM carFeelPassCountListUM = new CarFeelPassCountListUM();
		List<CarFeelPassHoursCountUM> carPassCount =carPassRecordsService.selectCarPassCount(super.getTableParams(communityId).get("dynamic_dbname").toString());
		//通行车辆次数
		carFeelPassCountListUM.setCarPassCount(carPassCount);
		List<CarFeelPassHoursCountUM> carCount =   carPassRecordsService.selectDistinctCarPassCount(super.getTableParams(communityId).get("dynamic_dbname").toString());
		//通行车辆数
		carFeelPassCountListUM.setCarCount(carCount);
		return carFeelPassCountListUM;
	}

	/**查询车辆当天不同报警类型数*/
	@Override
	public List<CarFeelAlarmTypeCountUM> selectAlarmTypeCount(Integer communityId) throws HKException {
		List<CarFeelAlarmTypeCountUM> list = new ArrayList<CarFeelAlarmTypeCountUM>();
		String dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		//过夜车
		CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM1 = carAlarmRecordsService.selectFeelAlarmTypeCount(1,dynamicDbname);
		if (carFeelAlarmTypeCountUM1==null) {
			CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM = new CarFeelAlarmTypeCountUM();
			carFeelAlarmTypeCountUM.setLabelName("过夜车");
			carFeelAlarmTypeCountUM.setNumCount(0);
			list.add(carFeelAlarmTypeCountUM);
		}else {
			list.add(carFeelAlarmTypeCountUM1);
		}
		//多次出入
		CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM2 = carAlarmRecordsService.selectFeelAlarmTypeCount(2,dynamicDbname);
		if (carFeelAlarmTypeCountUM2==null) {
			CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM = new CarFeelAlarmTypeCountUM();
			carFeelAlarmTypeCountUM.setLabelName("多次出入");
			carFeelAlarmTypeCountUM.setNumCount(0);
			list.add(carFeelAlarmTypeCountUM);
		}else {
			list.add(carFeelAlarmTypeCountUM2);
		}
		//长时间停留
		CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM3 = carAlarmRecordsService.selectFeelAlarmTypeCount(3,dynamicDbname);
		if (carFeelAlarmTypeCountUM3==null) {
			CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM = new CarFeelAlarmTypeCountUM();
			carFeelAlarmTypeCountUM.setLabelName("长时间停留");
			carFeelAlarmTypeCountUM.setNumCount(0);
			list.add(carFeelAlarmTypeCountUM);
		}else {
			list.add(carFeelAlarmTypeCountUM3);
		}
		//黑名单
		CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM4 = carAlarmRecordsService.selectFeelAlarmTypeCount(5,dynamicDbname);
		if (carFeelAlarmTypeCountUM4==null) {
			CarFeelAlarmTypeCountUM carFeelAlarmTypeCountUM = new CarFeelAlarmTypeCountUM();
			carFeelAlarmTypeCountUM.setLabelName("黑名单");
			carFeelAlarmTypeCountUM.setNumCount(0);
			list.add(carFeelAlarmTypeCountUM);
		}else {
			list.add(carFeelAlarmTypeCountUM4);
		}
		return list;
	}

	/**查询当天通行车辆数、通行次数、通行记录*/
	@Override
	public CarFeelPassWeekCountUM selectCarPassDayCount(Integer communityId) throws HKException {
		//获取当天日期
		String today = DateUtils.getTime();
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		map.put("day", today);
		//当天车辆出入总次数
		Integer carPassTotalCount = carPassRecordsService.selectDayCount(map);
		//当天车辆出入总次数
		Integer carPassCarCount =  carPassRecordsService.selectDayCarCount(map);
		map.put("limtData", 50);
		List<CarPassRecordsUM> list = carPassRecordsService.selectPassRecordList(map);
		CarFeelPassWeekCountUM carFeelPassWeekCountUM = new CarFeelPassWeekCountUM();
		carFeelPassWeekCountUM.setCarTotalPassCount(carPassTotalCount);
		carFeelPassWeekCountUM.setCarTotalCount(carPassCarCount);
		carFeelPassWeekCountUM.setCarPassRecords(list);
		return carFeelPassWeekCountUM;
	}


	/**查询车辆未处理的报警记录、本周报警记录数*/
	@Override
	public CarFeelAlarmListUM selectAlarmList(Integer communityId) throws HKException {
		CarFeelAlarmListUM  carFeelAlarmListUM = new CarFeelAlarmListUM();
		List<CarAlarmListUM> list =carAlarmRecordsService.selectAlarmList(super.getTableParams(communityId).get("dynamic_dbname").toString(),DateUtils.getTimesWeekmorning());
		carFeelAlarmListUM.setCarAlarmLists(list);
		Map< String, Object> map  = new  HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		//本周周一零点
		map.put("weekStart",DateUtils.getTimesWeekmorning());
		Integer weekCount = carAlarmRecordsService.selectMonthCount(map);
		carFeelAlarmListUM.setAlarmWeekCount(weekCount);
		return carFeelAlarmListUM;
	}

}
