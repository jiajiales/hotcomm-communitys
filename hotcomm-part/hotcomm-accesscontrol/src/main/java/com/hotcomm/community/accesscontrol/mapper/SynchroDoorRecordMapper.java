package com.hotcomm.community.accesscontrol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.pa.device.doorcontrol.DoorRecordPA;

public interface SynchroDoorRecordMapper {
	/**
	 * 查询记录ID
	 * 
	 * @param tableParams
	 * @return
	 */
	List<Integer> queryLogId(@Param("tableParams") Map<String, Object> tableParams);

	/**
	 * 查询设备安装地址
	 * 
	 * @param tableParams
	 * @return
	 */
	Map<String, Object> queryAddrByMac(@Param("tableParams") Map<String, Object> tableParams);

	/**
	 * 批量添加记录
	 * 
	 * @param tableParams
	 */
	void insertBatch(@Param("tableParams") Map<String, Object> tableParams, @Param("list") List<DoorRecordPA> params);
}
