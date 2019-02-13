package com.hotcomm.community.common.service.house;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.pa.house.BuildingPA;
import com.hotcomm.community.common.bean.pa.house.BuildingPagePA;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.BuildingUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface BuildingService {

	/**
	 * 楼栋管理分页
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	public PageInfo<BuildingUM> pageData(BuildingPagePA params) throws HKException;

	/**
	 * 查看楼栋详情
	 * 
	 * @param id
	 * @param communityId
	 * @param source
	 * @return
	 * @throws HKException
	 */
	public BuildingUM detailsData(Integer id, Integer communityId) throws HKException;

	/**
	 * 添加楼栋
	 * 
	 * @param params
	 * @throws HKException
	 */
	public void addData(BuildingPA params) throws Exception;

	/**
	 * 修改楼栋信息
	 * 
	 * @param params
	 * @throws HKException
	 */
	public void updateData(BuildingPA params) throws HKException;

	/**
	 * 删除楼栋信息
	 * 
	 * @param id
	 * @throws HKException
	 */
	public void deleteData(Integer id, Integer communityId) throws HKException;

	/**
	 * 当前小区的楼栋列表
	 * 
	 * @param communityId
	 * @param source
	 * @return
	 * @throws HKException
	 */
	public List<BuildingListUM> getBuildingList(Integer communityId) throws HKException;
	
	/**
	 * 判断楼栋名称是否存在
	 * @param id
	 * @param communityId
	 * @param buildingName
	 * @return
	 * @throws HKException
	 */
	Boolean isExist(Integer id, Integer communityId, String buildingName) throws HKException;
	
	/**
	 * 在综合作业楼栋信息
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<Map<String, Object>> getBuildings(Integer communityId)throws HKException;
}
