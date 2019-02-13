package com.hotcomm.community.device.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.db.device.VideoDM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMapUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceThisWeekAlarmUM;
import com.hotcomm.community.common.bean.ui.device.fell.FireAlarmMsgUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.FireFellService;
import com.hotcomm.community.device.mapper.DeviceFellMapper;
import com.hotcomm.community.device.mapper.FireFellMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class FireFellServiceImpl extends BaseService implements FireFellService {

	@Autowired
	private DeviceFellMapper deviceFellMapper;

	@Autowired
	private FireFellMapper fireFellMapper;

	/**
	 * 消防设备基础信息统计
	 * 
	 */
	@Override
	public List<DeviceMsgCountUM> FireMsgCount(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Gson gson = new Gson();
		Map<String, String> num = new HashMap<String, String>();

		List<DeviceMsgCountUM> deviceMsgCountUMs = deviceFellMapper.DeviceMsgCount(tableParams, 1);

		// 统计消防摄像头
		List<String> videos = fireFellMapper.DeviceMsgCount(tableParams);
		for (String s : videos) {
			VideoDM v = gson.fromJson(s, VideoDM.class);
			// 去从
			if (v != null && !v.getVideo().equals(""))
				for (String d : v.getVideo())
					num.put(d, "");
		}

		DeviceMsgCountUM videonum = new DeviceMsgCountUM();
		videonum.setModuleName("摄像机");
		videonum.setModuleId(5);
		videonum.setDevNum(num.size());
		deviceMsgCountUMs.add(videonum);
		return deviceMsgCountUMs;
	}

	/**
	 * 今日各种消防警报情况
	 * 
	 */
	@Override
	public List<FireAlarmMsgUM> FireAlarmMsg(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 报警总数
		Integer alarmNum = 0;
		// 获取报警类型，以及各类型报警数
		List<FireAlarmMsgUM> fireAlarmMsgUMs = fireFellMapper.FireAlarmMsg(tableParams, "d");

		// 计算报警总数
		for (FireAlarmMsgUM f : fireAlarmMsgUMs)
			alarmNum += f.getAlarmNum();

		// 如果报警总数0则没必要计算百分比
		if (alarmNum == 0)
			return fireAlarmMsgUMs;

		// 计算百分比
		for (int i = 0; i < fireAlarmMsgUMs.size(); i++) {
			FireAlarmMsgUM son = fireAlarmMsgUMs.get(i);
			son.setAlarmPercent(Double.parseDouble(df.format(((double) son.getAlarmNum() / alarmNum) * 100)));
			fireAlarmMsgUMs.set(i, son);
		}

		return fireAlarmMsgUMs;
	}

	/**
	 * 消防各类设备报警感知情况
	 * 
	 */
	@Override
	public List<FireAlarmMsgUM> FireDeviceAlarmMsg(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 报警总数
		Integer alarmNum = 0;
		// 获取报警设备类型，以及各设备类型报警数
		List<FireAlarmMsgUM> fireAlarmMsgUMs = fireFellMapper.FireDeviceAlarmMsg(tableParams, null);

		// 计算报警总数
		for (FireAlarmMsgUM f : fireAlarmMsgUMs)
			alarmNum += f.getAlarmNum();

		if (alarmNum == 0)
			return fireAlarmMsgUMs;

		// 计算百分比
		for (int i = 0; i < fireAlarmMsgUMs.size(); i++) {
			FireAlarmMsgUM son = fireAlarmMsgUMs.get(i);
			son.setAlarmPercent(Double.parseDouble(df.format(((double) son.getAlarmNum() / alarmNum) * 100)));
			fireAlarmMsgUMs.set(i, son);
		}

		return fireAlarmMsgUMs;
	}

	/**
	 * 本周消防警报情况
	 * 
	 */
	@Override
	public Map<String, Object> FireThisWeekAlarm(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		// 本周报警数.1代表查询消防设备
		Integer alarmNum = deviceFellMapper.DeviceThisWeekAlarmNum(tableParams, 1);
		// 本周报警前五条.1代表查询消防设备
		List<DeviceThisWeekAlarmUM> deviceThisWeekAlarmUM = deviceFellMapper.DeviceThisWeekAlarm(tableParams, 1);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alarmNum", alarmNum);
		map.put("alarmMsg", deviceThisWeekAlarmUM);
		return map;
	}

	/**
	 * 消防设备感知地图设备展示
	 * 
	 */
	@Override
	public List<DeviceMapUM> FireDeviceMap(Integer communityId, String moduleIds) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Gson gson = new Gson();
		Map<String, String> num = new HashMap<String, String>();

		String[] moduleids = moduleIds.split(",");
		List<String> moduleid = Arrays.asList(moduleids);
		
		// 1.代表查询消防设备
		List<DeviceMapUM> dList = deviceFellMapper.DeviceMap(tableParams, 1, moduleid);
		
		if(moduleid.contains("5")){
			// 统计消防摄像头
			List<String> videos = fireFellMapper.DeviceMsgCount(tableParams);
			for (String s : videos) {
				VideoDM v = gson.fromJson(s, VideoDM.class);
				// 去从
				if (v != null && !v.getVideo().equals(""))
					for (String d : v.getVideo())
						num.put(d, "");
			}
			// 将map的摄像头id转为list
			List<String> Videoids = new ArrayList<String>(num.keySet());
			if (Videoids.size() > 0 && !Videoids.get(0).equals(""))
				// 查询出消防设备关联的视频信息
				dList.addAll(fireFellMapper.FireDeviceMapOnVideo(tableParams, Videoids));
		}
		
		return dList;
	}
}
