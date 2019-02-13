package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.UnitPA;
import com.hotcomm.community.common.bean.pa.house.UnitPagePA;
import com.hotcomm.community.common.bean.ui.house.UnitUM;

public interface UnitMapper {

	Page<UnitUM> page(@Param("tableParams") Map<String, Object> tableParams, @Param("params") UnitPagePA params);

	List<UnitUM> dataList(@Param("tableParams") Map<String, Object> tableParams);

	UnitUM detailsData(@Param("tableParams") Map<String, Object> tableParams);
	
	Integer queryDoorduBuildingId(@Param("tableParams") Map<String, Object> tableParams);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") UnitPA params);

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") UnitPA params);

	void updateDoorduUnitId(@Param("tableParams") Map<String, Object> tableParams);

	void delData(@Param("tableParams") Map<String, Object> tableParams);

	Integer isExist(@Param("tableParams") Map<String, Object> tableParams);

	Integer isExistRelation(@Param("tableParams") Map<String, Object> tableParams);

}
