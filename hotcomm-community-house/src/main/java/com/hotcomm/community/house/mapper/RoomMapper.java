package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.RoomPA;
import com.hotcomm.community.common.bean.pa.house.RoomPagePA;
import com.hotcomm.community.common.bean.ui.house.RoomUM;

public interface RoomMapper {

	Page<RoomUM> page(@Param("tableParams") Map<String, Object> tableParams, @Param("params") RoomPagePA params);

	RoomUM detailsData(@Param("tableParams") Map<String, Object> tableParams);
	
	Integer queryDoorduUnitId(@Param("tableParams") Map<String, Object> tableParams);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") RoomPA params);

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") RoomPA params);
	
	void updateDoorduRoomId(@Param("tableParams") Map<String, Object> tableParams);

	void delData(@Param("tableParams") Map<String, Object> tableParams);

	Integer isRoomExist(@Param("tableParams") Map<String, Object> tableParams, @Param("id") Integer id,
			@Param("buildingId") Integer buildingId,@Param("unitId") Integer unitId, @Param("floorId") Integer floorId,
			@Param("roomName") String roomName);

	List<Map<String, Object>> getRoomList(@Param("tableParams") Map<String, Object> tableParams);

	List<Map<String, Object>> getRoomListByPid(@Param("tableParams") Map<String, Object> tableParams);

	/**
	 * 是否存在关联
	 * 
	 * @param tableParams
	 * @return
	 */
	Integer isExistRelationRoom(@Param("tableParams") Map<String, Object> tableParams);
	
	/**
	 * 查询所有的房间列表
	 * @param tableParams
	 * @return
	 */
	List<Map<String, Object>> getAllList(@Param("tableParams") Map<String, Object> tableParams);
	
	/**
	 * （综合态势）总数、出租屋、购置、在住房、空置房
	 * @param tableParams
	 * @return
	 */
	Map<String, Integer> roomStatistics(@Param("tableParams") Map<String, Object> tableParams);
}
