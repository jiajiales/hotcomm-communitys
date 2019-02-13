package com.hotcomm.community.common.service.car;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.car.CarAlarmRecordsDM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmLevelCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmMonCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmTypeCountUM;
import com.hotcomm.framework.web.exception.HKException;

public interface CarAlarmRecordsService {

	/**
	 * 新增车辆预警规则
	 * @param carAlarmRecordsDM
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CarAlarmRecordsDM carAlarmRecordsDM)  throws HKException;

	/**
	 * 查看车辆报警记录详情
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarAlarmRecordsUM detail(Integer id ,Integer communityId) throws HKException;
	
	/**
	 * 查询指定月份的报警记录数
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public Integer selectMonthCount(Map<String , Object> map) throws HKException;
	
	/**
	 * 本月车辆预警级别统计
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<CarAlarmLevelCountUM> selectAlarmLevelCount(Integer communityId) throws HKException;
	
	/**
	 * 月报警统计（报警趋势）
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarAlarmMonCountUM selectAlarmDayCount(Integer  communityId)  throws HKException;
	
	/**
	 * 查询当天不同报警类型的车辆数
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public CarFeelAlarmTypeCountUM selectFeelAlarmTypeCount(Integer ruleId,String dynamicDbname) throws HKException;
	
	/**
	 * 查询车辆报警记录
	 * @param dynamicDbname
	 * @param weekStart
	 * @return
	 * @throws HKException
	 */
	public List<CarAlarmListUM> selectAlarmList(String dynamicDbname,String weekStart) throws HKException;
}
