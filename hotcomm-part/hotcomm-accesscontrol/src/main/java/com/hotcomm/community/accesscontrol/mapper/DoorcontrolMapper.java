package com.hotcomm.community.accesscontrol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.pa.device.doorcontrol.DoorcontrolDevicedata;

public interface DoorcontrolMapper {
	void insertBatch(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") List<DoorcontrolDevicedata> params);

	void updateBatch(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") List<DoorcontrolDevicedata> params);

	void delBatch(@Param("tableParams") Map<String, Object> tableParams, @Param("params") List<String> params);

	List<String> selectDevMac(@Param("tableParams") Map<String, Object> tableParams);

}
