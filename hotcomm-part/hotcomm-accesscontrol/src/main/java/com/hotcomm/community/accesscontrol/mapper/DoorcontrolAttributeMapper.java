package com.hotcomm.community.accesscontrol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;

public interface DoorcontrolAttributeMapper {

	void insertBatch(@Param("tableParams") Map<String, Object> tableParams,
			@Param("list") List<DoorcontrolAttributeDM> params);

	void updateBatch(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") List<DoorcontrolAttributeDM> params);

	void delBatch(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") List<String> params);

	// 查询所有的门禁设备mac
	List<String> selectDevMac(@Param("tableParams") Map<String, Object> tableParams);

}
