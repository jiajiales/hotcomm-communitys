package com.hotcomm.community.common.service.car;

import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CarAlarmRuleService {
	
	/**
	 * 分页查询车辆预警规则列表
	 * @param carAlarmRulePagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CarAlarmRuleUM> page(CarAlarmRulePagePA carAlarmRulePagePA) throws HKException;
	
	/**
	 * 新增车辆预警规则
	 * @param carAlarmRulePA
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CarAlarmRulePA carAlarmRulePA)  throws HKException;
	
	/**
	 * 删除车辆预警规则
	 * @param communityId,ruleId
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer ruleId) throws HKException;
	
	/**
	 * 修改车辆预警规则
	 * @param carAlarmRulePA
	 * @return
	 * @throws HKException
	 */
	public boolean update(CarAlarmRulePA carAlarmRulePA) throws HKException;
	
	/**
	 * 查询车辆预警规则详情
	 * @param carAlarmRulePA
	 * @return
	 * @throws HKException
	 */
	public CarAlarmRuleUM detail(CarAlarmRulePA carAlarmRulePA) throws HKException;
	
}
