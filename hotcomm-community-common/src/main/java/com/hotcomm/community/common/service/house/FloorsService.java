package com.hotcomm.community.common.service.house;

import java.util.List;

import com.hotcomm.community.common.bean.pa.house.FloorsPA;
import com.hotcomm.community.common.bean.pa.house.FloorsPagePA;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface FloorsService {

	/**
	 * 楼层分页信息
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	public PageInfo<FloorsUM> pageData(FloorsPagePA params) throws HKException;

	/**
	 * 查看楼层详情
	 * 
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public FloorsUM detailsData(Integer id, Integer communityId) throws HKException;

	/**
	 * 新增楼层
	 * 
	 * @param params
	 * @throws HKException
	 */
	public void addData(FloorsPA params) throws HKException;

	/**
	 * 修改楼层信息
	 * 
	 * @param params
	 * @throws HKException
	 */
	public void updateData(FloorsPA params) throws HKException;

	/**
	 * 删除楼层信息
	 * 
	 * @param id
	 * @param communityId
	 * @throws HKException
	 */
	public void deleteData(Integer id, Integer communityId) throws HKException;

	/**
	 * 查询指定楼栋的楼层列表
	 * 
	 * @param buildingId
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<FloorsListUM> getFloorsList(Integer buildingId, Integer communityId) throws HKException;

	/**
	 * 楼层是否存在关联房间
	 * 
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Boolean isRommexist(Integer id, Integer communityId) throws HKException;
	
	/**
	 * 楼层是否存在
	 * @param floorName
	 * @param floorNum
	 * @param buildingId
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Boolean isFloorExist(Integer id,Integer unitId, Integer floorNum, Integer buildingId, Integer communityId)
			throws HKException;

}
