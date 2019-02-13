package com.hotcomm.community.car.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotcomm.community.common.bean.db.car.CarTypeCountDM;
import com.hotcomm.community.common.bean.ui.car.CarToalCountUM;
import com.hotcomm.community.common.bean.ui.car.CarTypeCountUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRecordsService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarToalCaseService;
import com.hotcomm.framework.utils.DateUtils;

/**
 * @Description: 车辆总况
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarToalCaseServiceImpl extends BaseService implements CarToalCaseService {

	@Autowired
	private CarRegService carRegService;
	
	@Autowired
	private CarPassRecordsService carPassRecordsService;
	
	@Autowired
	private CarAlarmRecordsService carAlarmRecordsService;
	
	/**查询车辆总况统计数（区域登记车辆、今日通行记录、本月报警、敏感时段车辆总数）*/
	@Override
	public CarToalCountUM selectToalCount(Integer communityId) {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());
		map.put("communityId", communityId);
		//区域登记车辆
		map.put("pageSource",0);
		Integer regCarCount = carRegService.selectCount(map);
		//查询敏感时段出入车辆总数
		Integer senTimePassCount = carPassRecordsService.selectSenTimeCount(map);
		map.put("month", DateUtils.getYearMon(new Date()));
		//本月报警次数
		Integer monAlarmCount = carAlarmRecordsService.selectMonthCount(map);
		map.put("day", DateUtils.getTime());
		//今日通行车辆总数
		Integer dayPassCount = carPassRecordsService.selectDayCount(map);
		CarToalCountUM carToalCountUM = new CarToalCountUM();
		carToalCountUM.setDayPassCount(dayPassCount);
		carToalCountUM.setMonAlarmCount(monAlarmCount);
		carToalCountUM.setRegCarCount(regCarCount);
		carToalCountUM.setSenTimePassCount(senTimePassCount);
		return carToalCountUM;
	}

	/**查询登记车辆结构总数（单位、小区、外来）*/
	@Override
	public CarTypeCountUM selectTypeCount(Integer communityId) {
		Map<String, Object> map = new HashMap<>();
		map.put("dynamicDbname", super.getTableParams(communityId).get("dynamic_dbname").toString());		
		Integer totalRegCar = carRegService.selectCount(map);
		CarTypeCountUM  carTypeCountUM = new CarTypeCountUM();
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");   
		Optional<List<CarTypeCountDM>> optional =  Optional.ofNullable(carRegService.selectTypeCount(map));
		optional.ifPresent(carTypeCountDMs -> {
			carTypeCountDMs.stream().forEach(carTypeCountDM -> {
				if (carTypeCountDM.getCarType().equals("小区车辆")) {
					carTypeCountUM.setCommunityCarCount(carTypeCountDM.getCount());
					carTypeCountUM.setCommunityCarPer(Double.parseDouble(df.format((carTypeCountDM.getCount()*1.00)/totalRegCar)));
				}else if (carTypeCountDM.getCarType().equals("单位车辆")) {
					carTypeCountUM.setCompanyCarCount(carTypeCountDM.getCount());
					carTypeCountUM.setCompanyCarPer(Double.parseDouble(df.format((carTypeCountDM.getCount()*1.00)/totalRegCar)));
				}else {
					//其他登记车辆
					carTypeCountUM.setOtherCarCount(carTypeCountDM.getCount());
					carTypeCountUM.setOtherCarPer(Double.parseDouble(df.format((carTypeCountDM.getCount()*1.00)/totalRegCar)));
				}
			});
		});
		return carTypeCountUM;	
	}
}
