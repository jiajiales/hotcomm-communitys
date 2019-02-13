package com.hotcomm.community.common.service.car;

import java.util.List;

import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.pa.car.CarStrPagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CarStrService {

	/**
	 * 分页查询陌生车辆列表
	 * @param carStrPagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CarStrUM> page(CarStrPagePA carStrPagePA) throws HKException;
	
	/**
	 * 新增陌生车辆
	 * @param carStrDM
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CarStrDM carStrDM) throws HKException;
	
	/**
	 * 查看陌生车辆详情
	 * @param carStrPA
	 * @return
	 * @throws HKException
	 */
	public CarStrUM detail(CarStrPA carStrPA) throws HKException;

	/**
	 * 删除陌生车辆
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer id ,Integer communityId) throws HKException;
	
	/**
	 * 修改陌生车辆
	 * @param carStrDM
	 * @return
	 * @throws HKException
	 */
	public boolean update(CarStrDM carStrDM) throws HKException;

	/**
	 * 查询长时间停留车辆列表
	 * @param carStrDM
	 * @param carAlarmRuleUM
	 * @return
	 * @throws HKException
	 */
	public List<CarStrUM> selectLongParkingCarList(CarStrDM carStrDM,CarAlarmRuleUM carAlarmRuleUM) throws HKException;

	/**
	 * 查询过夜车辆列表
	 * @param carStrDM
	 * @param carAlarmRuleUM
	 * @return
	 * @throws HKException
	 */
	public List<CarStrUM>  selectNightCarList(CarStrDM carStrDM,CarAlarmRuleUM carAlarmRuleUM) throws HKException;
	
	/**
	 * 查询陌生车辆数
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public Integer selectYearCount(String dynamicDbname) throws HKException;
}
