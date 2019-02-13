package com.hotcomm.community.device.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceStateMsgUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceThisWeekAlarmUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceTodayDataMsgUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.DeviceFellService;
import com.hotcomm.community.device.mapper.DeviceFellMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class DeviceFellServiceImpl extends BaseService implements DeviceFellService {

	@Autowired
	private DeviceFellMapper devicefellmapper;

	/**
	 * 设备基础信息统计
	 * 
	 */
	@Override
	public List<DeviceMsgCountUM> DeviceMsgCount(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		// 查出摄像头数量
		List<DeviceMsgCountUM> dList = devicefellmapper.DeviceMsgCount(tableParams, null);
		// 查出摄像头数量
		DeviceMsgCountUM dson = new DeviceMsgCountUM();
		dson.setModuleId(5);
		dson.setModuleName("摄像头");
		dson.setDevNum(devicefellmapper.DeviceMsgCountOnVideo(tableParams));
		dList.add(dson);
		return dList;
	}

	/**
	 * 今日数据回传感知情况
	 * 
	 */
	@Override
	public List<DeviceTodayDataMsgUM> DeviceTodayDataMsg(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		// 查出感知设备数据
		List<DeviceTodayDataMsgUM> dataMsgUMs = devicefellmapper.DeviceTodayDataMsg(tableParams);
		// 查出摄像头数据
		DeviceTodayDataMsgUM dv = new DeviceTodayDataMsgUM();
		dv.setModuleName("摄像头");
		dv.setNormal(devicefellmapper.DeviceMsgCountOnVideo(tableParams));
		// 整合感知数据和摄像头数据
		dataMsgUMs.add(dv);
		return dataMsgUMs;
	}

	/**
	 * 设备在线感知情况
	 * 
	 */
	@Override
	public DeviceStateMsgUM DeviceStateMsg(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		// 统计设备各个状态数
		DeviceStateMsgUM deviceStateMsgUMs = devicefellmapper.DeviceStateMsg(tableParams);
		deviceStateMsgUMs
				.setNormal(deviceStateMsgUMs.getNormal() + devicefellmapper.DeviceMsgCountOnVideo(tableParams));

		// double保留后两位
		DecimalFormat df = new DecimalFormat("#0.00");
		// 设备总数
		Integer devcount = deviceStateMsgUMs.getNormal() + deviceStateMsgUMs.getFault()
				+ deviceStateMsgUMs.getOffLine();

		if (devcount == 0)
			return deviceStateMsgUMs;

		// 计算设备状态数百分比
		deviceStateMsgUMs.setNormalPercent(
				Double.parseDouble(df.format(((double) deviceStateMsgUMs.getNormal() / devcount) * 100)));
		deviceStateMsgUMs.setFaultPercent(
				Double.parseDouble(df.format(((double) deviceStateMsgUMs.getFault() / devcount) * 100)));
		deviceStateMsgUMs.setOfflinePercent(
				Double.parseDouble(df.format(((double) deviceStateMsgUMs.getOffLine() / devcount) * 100)));
		return deviceStateMsgUMs;
	}

	/**
	 * 本周设备警报情况
	 * 
	 */
	@Override
	public Map<String, Object> DeviceThisWeekAlarm(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		// 本周报警数
		Integer alarmNum = devicefellmapper.DeviceThisWeekAlarmNum(tableParams, null);
		// 本周报警
		List<DeviceThisWeekAlarmUM> deviceThisWeekAlarmUM = devicefellmapper.DeviceThisWeekAlarm(tableParams, null);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alarmNum", alarmNum);
		map.put("alarmMsg", deviceThisWeekAlarmUM);
		return map;
	}

	/**
	 * 设备感知地图设备展示
	 * 
	 */
	@Override
	public List<DeviceMapUM> DeviceMap(Integer communityId, String moduleIds, Integer devType) throws HKException {
		String[] moduleids = moduleIds.split(",");
		List<String> moduleid = Arrays.asList(moduleids);
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<DeviceMapUM> dList = devicefellmapper.DeviceMap(tableParams, null, moduleid);

		if (moduleid.contains("5")) {
			List<DeviceMapUM> dvideo = devicefellmapper.DeviceMapOnVideo(tableParams, devType);
			dList.addAll(dvideo);
		}
		return dList;
	}

	@Override
	public Object selectDeviceAllState(Integer communityId, Integer moduleId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		if (moduleId == null || moduleId == 0)
			// 如果不是查询单个设备的信息就返回全部
			return devicefellmapper.selectDeviceAllState(tableParams, moduleId);
		return devicefellmapper.selectDeviceAllState(tableParams, moduleId).get(0);
	}

}
