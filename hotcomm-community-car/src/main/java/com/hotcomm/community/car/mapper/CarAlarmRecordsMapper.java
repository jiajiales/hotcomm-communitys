package com.hotcomm.community.car.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmDayUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmLevelCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmTypeCountUM;

public interface CarAlarmRecordsMapper {

	public Page<CarAlarmRecordsUM> pagelist(@Param("pa")Map<String, Object> params);
	
	public Integer insert(CarAlarmRecordsDM carAlarmRecordsDM);
	
	public CarAlarmRecordsUM detail(@Param("id")Integer id,@Param("dynamicDbname") String dynamicDbname);
	
	public boolean delete(Map<String, Object> map);
	
	public boolean update(CarAlarmRecordsDM carAlarmRecordsDM);
	
	public Integer selectMonthCount(Map<String, Object> map);
	
	public List<CarAlarmLevelCountUM> selectAlarmLevelCount(Map<String, Object> map);
	
	public List<CarAlarmDayUM> selectAlarmDayCount(Map<String, Object> map);
	
	public CarFeelAlarmTypeCountUM selectFeelAlarmTypeCount(@Param("ruleId")Integer ruleId,@Param("dynamicDbname") String dynamicDbname ,@Param("today")String today);
	
	public List<CarAlarmListUM> selectAlarmList(@Param("dynamicDbname") String dynamicDbname ,@Param("weekStart")String weekStart);
}
