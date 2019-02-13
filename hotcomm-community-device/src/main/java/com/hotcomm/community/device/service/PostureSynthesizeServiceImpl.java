package com.hotcomm.community.device.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectDeviceNumUM;
import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectRecentlySevenDaysUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarPostureServcie;
import com.hotcomm.community.common.service.device.PostureSynthesizeService;
import com.hotcomm.community.common.service.house.HouseSummarizingService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.device.mapper.DeviceFellMapper;
import com.hotcomm.community.device.mapper.PostureSynthesizeMapper;
import com.hotcomm.framework.comm.CommunityParams;

@Service
public class PostureSynthesizeServiceImpl extends BaseService implements PostureSynthesizeService {

	@Autowired
	private PostureSynthesizeMapper postureSynthesizeMapper;

	@Autowired
	private DeviceFellMapper devicefellmapper;

	@Autowired
	private ProscessService proscessService;

	@Autowired
	private PersonService personService;

	@Autowired
	private HouseSummarizingService houseSummarizingService;

	@Autowired
	private CarPostureServcie carPostureServcie;

	@Override
	public SelectDeviceNumUM selectDeviceNum(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		SelectDeviceNumUM s = postureSynthesizeMapper.selectDeviceNum(tableParams);
		s.setNum(s.getNum() + devicefellmapper.DeviceMsgCountOnVideo(tableParams));
		return s;
	}

	@Override
	public List<SelectRecentlySevenDaysUM> selectRecentlySevenDays(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<SelectRecentlySevenDaysUM> son = postureSynthesizeMapper.selectRecentlySevenDays(tableParams);
		String[] days = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		List<SelectRecentlySevenDaysUM> s = new ArrayList<SelectRecentlySevenDaysUM>();
		for (int j = 0; j < days.length; j++) {
			SelectRecentlySevenDaysUM sson = new SelectRecentlySevenDaysUM();
			sson.setWeek(days[j]);
			for (SelectRecentlySevenDaysUM sons : son) {
				if (days[j].equals(sons.getWeek())) {
					sson = sons;
				}
			}
			s.add(sson);
		}
		return s;
	}

	@Override
	public Map<String, Object> fullStatistics(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Map<String, Object> map = new HashMap<String, Object>();

		// 总报警
		CommunityParams pa = new CommunityParams();
		pa.setCommunityId(communityId);
		map.put("alarmSTAlarmAllCount", proscessService.AlarmSTAlarmAllCount(pa));

		// 实有人口
		Map<String, Integer> personInfoTask = personService.personInfoTask(communityId);
		map.put("personInfoTask", personInfoTask);

		// 实有车辆
		Map<String, Object> selectPosCarCount = carPostureServcie.selectPosCarCount(communityId);
		map.put("selectPosCarCount", selectPosCarCount);

		// 实有房屋
		map.put("getHouseStatistics", houseSummarizingService.getHouseStatistics(communityId));

		// 实有设备
		map.put("selectFullStatistics", postureSynthesizeMapper.selectFullStatistics(tableParams));

		// 总能耗
		Map<String, Integer> energy = new HashMap<String, Integer>();
		energy.put("energyAll", 2653);
		energy.put("electricity", 16);
		map.put("energy", energy);

		// 最大AQI
		Map<String, Integer> aqi = new HashMap<String, Integer>();
		aqi.put("aqiAll", 83);
		aqi.put("environment", 3);
		map.put("aqi", aqi);

		// 通行次数
		Map<String, Integer> transitTimes = new HashMap<String, Integer>();
		transitTimes.put("goThroughCount", personInfoTask.get("recordNum") + Integer.parseInt(selectPosCarCount.get("carPassCount").toString()));
		transitTimes.put("strangerNumber", personInfoTask.get("strangerPersonNum"));
		transitTimes.put("strangerCount", personInfoTask.get("strangerPersonTime"));
		map.put("transitTimes", transitTimes);

		return map;
	}

}
