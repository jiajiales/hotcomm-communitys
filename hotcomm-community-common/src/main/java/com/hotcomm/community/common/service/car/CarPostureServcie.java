package com.hotcomm.community.common.service.car;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.pa.car.CarPassCountPA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmLevelCountUM;
import com.hotcomm.community.common.bean.ui.car.CarAttentionCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassHourCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPostureParkingCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPostureTodayCountUM;
import com.hotcomm.framework.web.exception.HKException;

public interface CarPostureServcie {

	/**
	 * 统计今日车辆出、入次数、外来车辆总数、登记车辆总数、今日黑名单总数
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarPostureTodayCountUM selectTodayCarCount(Integer communityId) throws HKException;
	
	/**
	 * 按小时统计指定时间内车辆数出、入次数
	 * @param carPassCountPA
	 * @return
	 * @throws HKException
	 */
	public CarPassHourCountUM selectMonYearCarTotalPassCount(CarPassCountPA carPassCountPA) throws HKException;
	
	/**
	 * 按天数查询最一个月、一年停车车辆数(态势分析)
	 * @param carPassCountPA
	 * @return
	 * @throws HKException
	 */
	public CarPostureParkingCountUM selectPostureParkingCount(CarPassCountPA carPassCountPA)  throws HKException;
	
	
	/**
	 * 统计关注车辆次数
	 * @param carPassCountPA
	 * @return
	 * @throws HKException
	 */
	public List<CarAttentionCountUM> selectAttentionCount(CarPassCountPA carPassCountPA) throws HKException;
	
	/**
	 * 报警级别统计
	 * @param carPassCountPA
	 * @return
	 * @throws HKException
	 */
	public List<CarAlarmLevelCountUM> selectAlarmLevelCount(CarPassCountPA carPassCountPA) throws HKException;

	/**
	 * 统计车辆信息（实有车辆、黑名单、陌生车、登记车辆..）
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Map<String,Object> selectPosCarCount(Integer communityId) throws HKException;

	/**
	 * 查询本周通行车辆数、通行次数
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<Object> selectPosCarWeekCount(Integer communityId) throws HKException;

}
