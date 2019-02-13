package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.house.RelationRoomUM;

public interface PersonRoomMapper {

	Page<PersonRoomUM> page(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") PersonRoomPagePA params);

	void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") PersonRoomPA params);

	void delData(@Param("tableParams") Map<String, Object> tableParams, @Param("id") Integer id);

	List<RelationRoomUM> getRelationRoomUMs(@Param("tableParams") Map<String, Object> tableParams);
	
	/**
	 * 关联的数据是否存在
	 * @param tableParams
	 * @return
	 */
	Integer isExist(@Param("tableParams") Map<String, Object> tableParams);
}
