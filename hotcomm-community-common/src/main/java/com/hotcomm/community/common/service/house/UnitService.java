package com.hotcomm.community.common.service.house;

import java.util.List;

import com.hotcomm.community.common.bean.pa.house.UnitPA;
import com.hotcomm.community.common.bean.pa.house.UnitPagePA;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface UnitService {

	/**
	 * 单元分页列表
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<UnitUM> pageData(UnitPagePA params) throws HKException;

	/**
	 * 单元列表
	 * 
	 * @return
	 */
	List<UnitUM> getDataList(Integer buildingId, Integer communityId) throws HKException;

	/**
	 * 详情
	 * 
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	UnitUM detailsData(Integer id, Integer communityId) throws HKException;

	/**
	 * 添加单元
	 * 
	 * @param params
	 */
	void addData(UnitPA params) throws Exception;

	/**
	 * 修改单元
	 * 
	 * @param params
	 */
	void updateData(UnitPA params) throws HKException;

	/**
	 * 删除单元
	 */
	void delData(Integer id, Integer communityId) throws HKException;

	/**
	 * 判断单元是否存在
	 * 
	 * @return
	 */
	Boolean isExist(Integer id,Integer buildingId, String unitNo, Integer communityId) throws HKException;
	
	/**
	 * 判断是否存在关联绑定
	 * @return
	 */
	Boolean isExistRelation(Integer id, Integer communityId);

}
