package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.FloorsPA;
import com.hotcomm.community.common.bean.pa.house.FloorsPagePA;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsUM;

public interface FloorsMapper {

	Page<FloorsUM> page(@Param("tableParams") Map<String, Object> tableParams, @Param("params") FloorsPagePA params);
	
	FloorsUM detailsData(@Param("tableParams") Map<String, Object> tableParams);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") FloorsPA params);

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") FloorsPA params);

	void deleteData(@Param("tableParams") Map<String, Object> tableParams);

	List<FloorsListUM> getFloorsList(@Param("tableParams") Map<String, Object> tableParams);
	
	/**
	 * 是否存在关联房间
	 * @param tableParams
	 * @return
	 */
	Integer isRoomExist(@Param("tableParams") Map<String, Object> tableParams);
	/**
	 * 是否存在此楼层
	 * @param tableParams
	 * @return
	 */
	Integer isFloorExist(@Param("tableParams") Map<String, Object> tableParams);

}
