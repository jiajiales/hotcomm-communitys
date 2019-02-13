package com.hotcomm.community.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.FireAlarmMsgUM;

public interface FireFellMapper {

	public List<String> DeviceMsgCount(@Param("tableParams") Map<String, Object> tableParams);

	public List<FireAlarmMsgUM> FireAlarmMsg(@Param("tableParams") Map<String, Object> tableParams,
			@Param("time") String time);

	public List<FireAlarmMsgUM> FireDeviceAlarmMsg(@Param("tableParams") Map<String, Object> tableParams,
			@Param("time") String time);

	public List<DeviceMapUM> FireDeviceMapOnVideo(@Param("tableParams") Map<String, Object> tableParams,
			@Param("list") List<String> list);
}
