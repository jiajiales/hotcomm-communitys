package com.hotcomm.community.common.service.car;

import java.util.ArrayList;
import java.util.Map;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.framework.web.exception.HKException;

public interface CarLabelRelationService {
    
	/**
	 * 新增车辆标签关系
	 * @param carLabelRelationDM
	 * @return
	 * @throws HKException
	 */
	public boolean insert(CarLabelRelationDM carLabelRelationDM)  throws HKException;
	
	/**
	 * 修改车辆标签关系
	 * @param carLabelRelationDM
	 * @return
	 * @throws HKException
	 */
	public boolean delete(CarLabelRelationDM carLabelRelationDM) throws HKException;
	
	/**
	 * 修改车辆标签关系
	 * @param carLabelRelationDM
	 * @return
	 * @throws HKException
	 */
	public boolean update(CarLabelRelationDM carLabelRelationDM) throws HKException;
	
	/**
	 * 通过车牌号删除车辆标签关系
	 * @param carLabelRelationDM
	 * @return
	 * @throws HKException
	 */
	public boolean deleteByCarNum(CarLabelRelationDM carLabelRelationDM) throws HKException;
	
	/**
	 * 通过车牌号查询车辆标签
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public  ArrayList<String> selectLabelByCarNum(Map<String, Object> map) throws HKException;
	
	/**
	 * 通过车牌号查询车辆标签Id
	 * @param map
	 * @return
	 * @throws HKException
	 */
	public  ArrayList<Integer> selectLabelIdByCarNum(Map<String, Object> map) throws HKException;
	
	/**
	 * 通过标签Id统计车辆数
	 * @return
	 * @throws HKException
	 */
	public Integer selectBlackCarByLabelId(String dynamicDbname,String day) throws HKException;	
	
}
