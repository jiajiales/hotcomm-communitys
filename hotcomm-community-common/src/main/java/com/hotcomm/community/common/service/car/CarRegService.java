package com.hotcomm.community.common.service.car;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.car.CarRegDM;
import com.hotcomm.community.common.bean.db.car.CarTypeCountDM;
import com.hotcomm.community.common.bean.pa.car.CarRegPA;
import com.hotcomm.community.common.bean.pa.car.CarRegPagePA;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarRegUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CarRegService {

	/**
	 * 分页查询登记车辆列表(重点关注车辆、黑名单车辆、服务车辆、其他登记车辆)
	 * @param carRegPagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CarRegUM> page(CarRegPagePA carRegPagePA) throws HKException;
	
	/**
	 * 新增登记车辆
	 * @param carRegPA
	 * @return
	 * @throws HKException
	 */
	public boolean insert(CarRegPA carRegPA) throws HKException;

	/**
	 * 删除登记车辆
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer id) throws HKException;
	
	/**
	 * 修改登记车辆详情
	 * @param carRegPA
	 * @return
	 * @throws HKException
	 */
	public boolean update(CarRegPA carRegPA) throws HKException;
	
	/**
	 * 根据车辆ID修改车辆报警记录
	 * @param carRegDM
	 * @return
	 * @throws HKException
	 */
	public boolean updateAlarmCountById(CarRegDM carRegDM) throws HKException;

	/**
	 * 查询登记车辆详情
	 * @param communityId
	 * @param id
	 * @param num
	 * @return
	 * @throws HKException
	 */
	public CarRegDetailUM detail(Integer communityId,Integer id,String num) throws HKException;
	
	/**
	 *  查询小区登记车辆总数
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public Integer selectCount(Map<String, Object> map) throws HKException;
	
	/**
	 * 分类查询小区登记车辆数（单位、小区、外来）
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public List<CarTypeCountDM> selectTypeCount(Map<String, Object> map) throws HKException;
	
	/**
	 * 查询单位登记车辆数
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public Integer selectCorCarCount(String dynamicDbname) throws HKException;
	
}
