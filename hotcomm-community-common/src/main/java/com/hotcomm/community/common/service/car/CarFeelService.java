package com.hotcomm.community.common.service.car;

import java.util.List;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassWeekCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmListUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelAlarmTypeCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassCountListUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelTotalCountUM;
import com.hotcomm.framework.web.exception.HKException;

public interface CarFeelService {

	/**
	 * 车辆基础信息统计
	 * @return
	 * @throws HKException
	 */
	public CarFeelTotalCountUM  selectCarTotalCount(Integer communityId) throws HKException;
	
	/**
	 * 按小时统计今日车辆通行次数、车辆数
	 * @return
	 * @throws HKException
	 */
	public CarFeelPassCountListUM  selectCarPassHoursCount(Integer communityId) throws HKException;
	
	/**
	 * 查询车辆当天不同报警类型数
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<CarFeelAlarmTypeCountUM>   selectAlarmTypeCount(Integer communityId) throws HKException; 
	
	/**
	 * 查询当天通行车辆数、通行次数、通行记录/PassInfoOfWeek
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarFeelPassWeekCountUM selectCarPassDayCount(Integer communityId) throws HKException; 
	
	
	/**
	 * 查询车辆报警记录、本周通行次数
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarFeelAlarmListUM selectAlarmList(Integer communityId)throws HKException; 
}
