package com.hotcomm.community.common.service.car;

import com.hotcomm.community.common.bean.ui.car.CarToalCountUM;
import com.hotcomm.community.common.bean.ui.car.CarTypeCountUM;
import com.hotcomm.framework.web.exception.HKException;

public interface CarToalCaseService {
	
	/**
	 * 查询车辆总况统计数（区域登记车辆、今日通行记录、本月报警、敏感时段车辆总数）
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarToalCountUM selectToalCount(Integer communityId) throws HKException;
	
	/**
	 * 查询登记车辆结构总数（单位、小区、外来）
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public CarTypeCountUM selectTypeCount(Integer communityId) throws HKException;
}
