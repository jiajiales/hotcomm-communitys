package com.hotcomm.community.accesscontrol.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPA;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPagePA;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevAttrUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorDetailsUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorsUM;

public interface DevDoorMapper {
	Page<DevDoorsUM> pageData(@Param("tableParams") Map<String, Object> tableParams,
			@Param("params") DevDoorPagePA params);

	/*void addData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") DevDoorPA params);*/

	void updateData(@Param("tableParams") Map<String, Object> tableParams, @Param("params") DevDoorPA params);

	DevAttrUM selectAttr(@Param("tableParams") Map<String, Object> tableParams);

	DevDoorDetailsUM detailsData(@Param("tableParams") Map<String, Object> tableParams);
}
