package com.hotcomm.community.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.device.FireAlarmTypeDM;
import com.hotcomm.community.common.bean.db.device.FireDeviceYearMsgDM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlaemDisposeMsgUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmToMonthUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmToYearUM;

public interface FirePostureMapper {

	public FireAlarmTypeDM FireAlarmType(@Param("tableParams") Map<String, Object> tableParams);

	public List<FireDeviceYearMsgDM> FireDeviceYearMsg(@Param("tableParams") Map<String, Object> tableParams);

	public FireAlaemDisposeMsgUM FireAlaemDisposeMsg(@Param("tableParams") Map<String, Object> tableParams);

	public List<FireAlarmToMonthUM> FireAlarmToMonth(@Param("tableParams") Map<String, Object> tableParams,
			@Param("year") String time);

	public List<FireAlarmToYearUM> FireAlarmToYear(@Param("tableParams") Map<String, Object> tableParams,
			@Param("year") String time);
}
