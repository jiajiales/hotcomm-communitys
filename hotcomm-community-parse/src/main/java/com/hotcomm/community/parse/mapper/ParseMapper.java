package com.hotcomm.community.parse.mapper;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.parse.AlarmGz;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.db.parse.SelectCommunityDM;

public interface ParseMapper {

	public SelectCommunityDM selectCommunity(@Param("mac") String mac);

	public Integer updateDeviceTime(@Param("dataBaseName") String dataBaseName, @Param("mac") String mac,
			@Param("battery") Double battery);

	public AlarmGz selectAlarmGz(@Param("dataBaseName") String dataBaseName, @Param("mac") String mac,
			@Param("alarmName") String alarmName);

	public SelectCommunityDM selectCommunityName(@Param("communityid") Integer communityid);

	public DeviceSendMsg selectDeviceId(@Param("baseDbName") String baseDbName,
			@Param("dataBaseName") String dataBaseName, @Param("mac") String mac);
}
