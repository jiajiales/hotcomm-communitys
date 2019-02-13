package com.hotcomm.community.common.service.device;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceStateMsgUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceTodayDataMsgUM;
import com.hotcomm.framework.web.exception.HKException;

public interface DeviceFellService {

	/**
	 * 设备基础信息统计
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<DeviceMsgCountUM> DeviceMsgCount(Integer communityId) throws HKException;

	/**
	 * 今日数据回传感知情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<DeviceTodayDataMsgUM> DeviceTodayDataMsg(Integer communityId) throws HKException;

	/**
	 * 设备在线感知情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public DeviceStateMsgUM DeviceStateMsg(Integer communityId) throws HKException;

	/**
	 * 本周设备警报情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Map<String, Object> DeviceThisWeekAlarm(Integer communityId) throws HKException;

	/**
	 * 设备感知地图设备展示
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<DeviceMapUM> DeviceMap(Integer communityId, String moduleIds, Integer devType) throws HKException;

	/**
	 * 综合作业查询所有设备各个状态数量
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Object selectDeviceAllState(Integer communityId, Integer moduleId) throws HKException;
}
