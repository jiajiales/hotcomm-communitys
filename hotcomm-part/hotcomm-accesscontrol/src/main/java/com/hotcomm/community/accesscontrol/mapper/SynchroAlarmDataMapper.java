package com.hotcomm.community.accesscontrol.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.process.AlarmStateDM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DoorDevUM;

public interface SynchroAlarmDataMapper {
	List<String> selectDevMac(@Param("tableParams") Map<String, Object> tableParams);

	List<AlarmStateDM> selectAlarmState();

	DoorDevUM selectByMac(@Param("tableParams") Map<String, Object> tableParams,@Param("mac") String mac);
}
