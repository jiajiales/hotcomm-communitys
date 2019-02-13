package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.WatchPlacPA;
import com.hotcomm.community.common.bean.pa.house.WatchPlacePagePA;
import com.hotcomm.community.common.bean.ui.house.WatchPlaceUM;

public interface WatchPlaceMapper {

	Page<WatchPlaceUM> page(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") WatchPlacePagePA params);

	WatchPlaceUM detailsData(@Param("tableParams") Map<String, Object> tableParams);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") WatchPlacPA params);

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") WatchPlacPA params);

	void delData(@Param("tableParams") Map<String, Object> tableParams);

	Integer isExistPlace(@Param("tableParams") Map<String, Object> tableParams, @Param("params") WatchPlacPA params);

	List<WatchPlaceUM> getPlaceList(@Param("tableParams") Map<String, Object> tableParams);

}
