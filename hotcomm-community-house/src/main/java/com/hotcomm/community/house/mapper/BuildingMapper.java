package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.BuildingPA;
import com.hotcomm.community.common.bean.pa.house.BuildingPagePA;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.BuildingUM;

public interface BuildingMapper {
	Page<BuildingUM> page(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") BuildingPagePA params);

	BuildingUM detailsData(@Param("tableParams") Map<String, Object> tableParams);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") BuildingPA params);

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") BuildingPA params);

	void updateDoorduId(@Param("tableParams") Map<String, Object> tableParams);

	void deleteData(@Param("tableParams") Map<String, Object> tableParams);

	List<BuildingListUM> getBuildingList(@Param("tableParams") Map<String, Object> tableParams);

	Integer isExist(@Param("tableParams") Map<String, Object> tableParams, @Param("id") Integer id,
			@Param("buildingName") String buildingName);

	List<Map<String, Object>> getBuildings(@Param("tableParams") Map<String, Object> tableParams);
}
