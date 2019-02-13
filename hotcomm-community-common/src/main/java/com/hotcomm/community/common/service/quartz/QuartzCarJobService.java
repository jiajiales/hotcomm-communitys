package com.hotcomm.community.common.service.quartz;

import com.hotcomm.framework.web.exception.HKException;

public interface QuartzCarJobService {
	
	/**
	 * 车辆多次进入定时报警任务
	 * @throws HKException
	 */
	public void CarManyEnter() throws HKException;
	
	/**
	 * 车辆长时间停留报警任务
	 * @throws HKException
	 */
	public void  CarLongParking() throws HKException;
	
	/**
	 * 车辆过夜车报警任务
	 * @throws HKException
	 */
	public void  CarOverNight() throws HKException;
}
