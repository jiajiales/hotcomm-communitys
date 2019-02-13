package com.hotcomm.community.common.service.house;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.pa.house.RoomPA;
import com.hotcomm.community.common.bean.pa.house.RoomPagePA;
import com.hotcomm.community.common.bean.ui.house.RoomUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface RoomService {

	/**
	 * 房间分页列表
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	public PageInfo<RoomUM> pageData(RoomPagePA params) throws HKException;

	/**
	 * 房间详情信息
	 * 
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public RoomUM detailsData(Integer id, Integer communityId) throws HKException;

	/**
	 * 添加房间信息
	 * 
	 * @param params
	 * @throws HKException
	 */
	public void addData(RoomPA params) throws Exception;

	/**
	 * 修改房间信息
	 * 
	 * @throws HKException
	 */
	public void updateData(RoomPA params) throws HKException;

	/**
	 * 删除房间信息
	 * 
	 * @throws HKException
	 */
	public void delData(Integer id, Integer communityId) throws HKException;

	/**
	 * 房间是否存在
	 * 
	 * @return
	 * @throws HKException
	 */
	public Boolean isRoomExist(Integer id, Integer unitId, Integer buildingId, Integer floorId, String roomName,
			Integer communityId) throws HKException;

	/**
	 * 获取房间列表
	 * 
	 * @param floorId
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<Map<String, Object>> getRoomList(Integer floorId, Integer communityId) throws HKException;

	/**
	 * 通过人口ID查询房间列表
	 * 
	 * @param pId
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<Map<String, Object>> getRoomListByPid(Integer pId, Integer communityId) throws HKException;

	/**
	 * 是否存在绑定关系
	 * 
	 * @param roomId
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	Boolean isExistRelationRoom(Integer roomId, Integer communityId) throws HKException;

	/**
	 * 查询所有的房间列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getAllList(Integer communityId);
	
	/**
	 * （综合态势）总数、出租屋、购置、在住房、空置房
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	Map<String, Integer> roomStatistics(Integer communityId) throws HKException;
}
