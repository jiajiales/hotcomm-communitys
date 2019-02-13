package com.hotcomm.community.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.device.DeviceAlarmGzDM;
import com.hotcomm.community.common.bean.db.device.SelectDeviceStateDM;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.pa.device.DeviceInstallPA;
import com.hotcomm.community.common.bean.pa.device.DevicePagePA;
import com.hotcomm.community.common.bean.pa.device.DeviceUpdateRuleFPA;
import com.hotcomm.community.common.bean.pa.device.DeviceUpdateRulePA;
import com.hotcomm.community.common.bean.pa.device.VideoDeviceInstallPA;
import com.hotcomm.community.common.bean.ui.device.DeviceMacDataUM;
import com.hotcomm.community.common.bean.ui.device.DeviceMsgWorkUM;
import com.hotcomm.community.common.bean.ui.device.DevicePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceAlarmCount;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.SelectRulePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.VideoDeviceListUM;
import com.hotcomm.community.common.bean.ui.device.VideoDevicePageUM;

public interface DeviceMapper {
	public Page<DevicePageUM> devicePage(@Param("tableParams") Map<String, Object> tableParams,
			@Param("devicePagePA") DevicePagePA devicePagePA);

	public Integer deviceInstall(DeviceInstallPA deviceInstallPA);

	public Integer deviceInstallUpdateNum(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac, @Param("dev_num") String dev_num);

	public Integer deviceInstallCommunity(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac, @Param("communityId") Integer communityId);

	public List<DeviceAlarmGzDM> deviceAlarmGz(@Param("tableParams") Map<String, Object> tableParams,
			@Param("module_id") Integer module_id, @Param("use_type") Integer use_type, @Param("mac") String mac);

	public Integer deviceAlarmGzInsert(@Param("tableParams") Map<String, Object> tableParams,
			@Param("deviceAlarmGzDM") List<DeviceAlarmGzDM> deviceAlarmGzDM, @Param("mac") String mac);

	public Integer updateDev(@Param("tableParams") Map<String, Object> tableParams,
			@Param("devmsg") DeviceInstallPA deviceInstallPA);

	public SelectDeviceOnMacUM selectDeviceOnMac(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac, @Param("module_id") Integer module_id);

	public DeviceMacDataUM deviceMac(@Param("mac") String mac);

	public List<SelectRulePageUM> selectRulePage(@Param("tableParams") Map<String, Object> tableParams);

	public List<String> selectRulePageUser(@Param("tableParams") Map<String, Object> tableParams,
			@Param("list") List<Integer> list);

	public List<SelectDeviceStateDM> selectDeviceState(@Param("tableParams") Map<String, Object> tableParams);

	public List<SelectDeviceStateDM> selectDeviceAlarm(@Param("tableParams") Map<String, Object> tableParams);

	public Integer updateSystemRule(@Param("tableParams") Map<String, Object> tableParams,
			@Param("rulepa") DeviceUpdateRulePA rulepa);

	public Integer updateSystemRuleUserid(@Param("tableParams") Map<String, Object> tableParams,
			@Param("rulepa") DeviceUpdateRuleFPA rulepa);

	public Integer updateRuleUserid(@Param("tableParams") Map<String, Object> tableParams,
			@Param("rulepa") DeviceUpdateRuleFPA rulepa);

	public List<DeviceMsgWorkUM> selectDeviceMsgWork(@Param("tableParams") Map<String, Object> tableParams,
			@Param("moduleid") Integer moduleid, @Param("mac") String mac, @Param("isAlarm") Integer isAlarm);

	public Integer videodeviceInstall(VideoDeviceInstallPA deviceInstallPA);

	public Integer videodeviceInstallUpdateNum(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac, @Param("dev_num") String dev_num);

	public Page<VideoDevicePageUM> videodevicePage(@Param("tableParams") Map<String, Object> tableParams,
			@Param("devicePagePA") DevicePagePA devicePagePA);

	public List<VideoDeviceListUM> videodeviceList(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac);

	public SelectVideoDeviceOnMacUM selectVideoDeviceOnMac(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac);

	public Integer updateVideoDev(@Param("tableParams") Map<String, Object> tableParams,
			@Param("devmsg") VideoDeviceInstallPA deviceInstallPA);

	public Integer selectFireFellAlarm(@Param("tableParams") Map<String, Object> tableParams,
			@Param("alarmid") Integer alarmid);

	public Integer selectAlarmLevel(@Param("alarmid") Integer alarmid);

	public List<SelectDeviceAlarmCount> selectDeviceAlarmCount(@Param("tableParams") Map<String, Object> tableParams);

	public List<SelectDeviceOnMacUM> selectOffLineDevice(@Param("tableParams") Map<String, Object> tableParams);

	public Integer updateOffLineDevice(@Param("tableParams") Map<String, Object> tableParams);

	public DeviceSendMsg selectDeviceIdOnMac(@Param("tableParams") Map<String, Object> tableParams,
			@Param("mac") String mac);
}
