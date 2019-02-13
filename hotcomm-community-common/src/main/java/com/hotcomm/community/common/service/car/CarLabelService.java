package com.hotcomm.community.common.service.car;

import java.util.List;

import com.hotcomm.community.common.bean.db.car.CarLabelDM;
import com.hotcomm.community.common.bean.pa.car.CarLabelPA;
import com.hotcomm.community.common.bean.pa.car.CarLabelPagePA;
import com.hotcomm.community.common.bean.ui.car.CarLabelLayeredList;
import com.hotcomm.community.common.bean.ui.car.CarLabelUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CarLabelService {
	
	/**
	 * 分页查询车辆标签列表
	 * @param carLabelPagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CarLabelUM> page(CarLabelPagePA carLabelPagePA) throws HKException;
	
	/**
	 * 查询车辆标签列表
	 * @param carLabelDM
	 * @return
	 * @throws HKException
	 */
	public List<CarLabelUM>  labelList(CarLabelDM carLabelDM)  throws HKException;
	
	/**
	 *  查询车辆标签、标签类型列表
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<CarLabelLayeredList> labellayeredList(Integer communityId) throws HKException;
	
	/**
	 * 新增车辆标签
	 * @param carLabelPA
	 * @return
	 * @throws HKException
	 */
	public boolean insert(CarLabelPA carLabelPA) throws HKException;

	/**
	 * 删除车辆标签（硬删除）
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer id) throws HKException;
	
	/**
	 * 修改车辆标签
	 * @param carLabelPA
	 * @return
	 * @throws HKException
	 */
	public boolean update(CarLabelPA carLabelPA) throws HKException;

	/**
	 * 查看车辆标签详情
	 * @param communityId
	 * @param id
	 * @param name
	 * @return
	 * @throws HKException
	 */
	public CarLabelUM detail(Integer communityId ,Integer id,String name) throws HKException;

	/**
	 * 通过标签类型查询标签id
	 * @param dynamicDbname
	 * @param labelTypeId
	 * @return
	 * @throws HKException
	 */
	public List<Integer>  selectLabelListByType(String dynamicDbname ,Integer labelTypeId) throws HKException;
}
