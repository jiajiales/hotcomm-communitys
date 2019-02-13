package com.hotcomm.community.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceStateMsgUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceThisWeekAlarmUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceTodayDataMsgUM;
import com.hotcomm.community.common.bean.ui.device.fell.SelectDeviceAllStateUM;

public interface DeviceFellMapper {

	public List<DeviceMsgCountUM> DeviceMsgCount(@Param("tableParams") Map<String, Object> tableParams,
			@Param("useType") Integer useType);

	public Integer DeviceMsgCountOnVideo(@Param("tableParams") Map<String, Object> tableParams);

	public List<DeviceTodayDataMsgUM> DeviceTodayDataMsg(@Param("tableParams") Map<String, Object> tableParams);

	public DeviceStateMsgUM DeviceStateMsg(@Param("tableParams") Map<String, Object> tableParams);

	public Integer DeviceThisWeekAlarmNum(@Param("tableParams") Map<String, Object> tableParams,
			@Param("use_type") Integer use_type);

	public List<DeviceThisWeekAlarmUM> DeviceThisWeekAlarm(@Param("tableParams") Map<String, Object> tableParams,
			@Param("use_type") Integer use_type);

	public List<DeviceMapUM> DeviceMap(@Param("tableParams") Map<String, Object> tableParams,
			@Param("use_type") Integer use_type, @Param("list") List<String> list);

	public List<DeviceMapUM> DeviceMapOnVideo(@Param("tableParams") Map<String, Object> tableParams,
			@Param("devType") Integer devType);

	public List<SelectDeviceAllStateUM> selectDeviceAllState(@Param("tableParams") Map<String, Object> tableParams,
			@Param("moduleId") Integer moduleId);
}
