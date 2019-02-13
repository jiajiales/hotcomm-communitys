package com.hotcomm.community.common.service.device;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.device.fell.FireAlarmMsgUM;
import com.hotcomm.framework.web.exception.HKException;

public interface FireFellService {

	/**
	 * 消防设备基础信息统计
	 * 
	 * @param communityId
	 * @return
	 */
	public List<DeviceMsgCountUM> FireMsgCount(Integer communityId);

	/**
	 * 今日各种消防警报情况
	 * 
	 * @param communityId
	 * @return
	 */
	public List<FireAlarmMsgUM> FireAlarmMsg(Integer communityId);

	/**
	 * 消防各类设备报警感知情况
	 * 
	 * @param communityId
	 * @return
	 */
	public List<FireAlarmMsgUM> FireDeviceAlarmMsg(Integer communityId);

	/**
	 * 本周消防警报情况
	 * 
	 * @param communityId
	 * @return
	 */
	public Map<String, Object> FireThisWeekAlarm(Integer communityId);

	/**
	 * 消防设备感知地图设备展示
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<DeviceMapUM> FireDeviceMap(Integer communityId, String moduleIds) throws HKException;
}
