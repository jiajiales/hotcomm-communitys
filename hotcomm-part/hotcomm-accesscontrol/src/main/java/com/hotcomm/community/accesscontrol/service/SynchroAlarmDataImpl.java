package com.hotcomm.community.accesscontrol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.accesscontrol.mapper.SynchroAlarmDataMapper;
import com.hotcomm.community.common.bean.db.process.AlarmStateDM;
import com.hotcomm.community.common.bean.en.device.DeviceEN;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DoorDevUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.doorcontrol.SynchroAlarmData;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;

import net.sf.json.JSONObject;

@Service
public class SynchroAlarmDataImpl extends BaseService implements SynchroAlarmData {
	@Autowired
	SynchroAlarmDataMapper synchroAlarm;
	@Autowired
	CommunityService communityService;
	@Autowired
	ProscessService alarmService;

	@Override
	public void synchroAlarmData() {
		/*CommunityListAllUM communityListAllUM=new CommunityListAllUM();//测试代码
		communityListAllUM.setCid(31);
		communityListAllUM.setDoorduDepId(6062);*/
		
		List<CommunityListAllUM> queryListCommunityAll = communityService.queryListCommunityAll();
		List<AlarmStateDM> alarmState = synchroAlarm.selectAlarmState();
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> level = new HashMap<>();
		for (AlarmStateDM alarmStateDM : alarmState) {
			map.put(alarmStateDM.getMatchValue(), alarmStateDM.getId());
			level.put(alarmStateDM.getMatchValue(), alarmStateDM.getLevel());

		}
		if (queryListCommunityAll.size() != 0) {
			for (CommunityListAllUM communityListAllUM : queryListCommunityAll) {
				Map<String, Object> tableParams = super.getTableParams(communityListAllUM.getCid());
				List<String> macs = synchroAlarm.selectDevMac(tableParams);
				if (macs.size() != 0) {
					for (String mac : macs) {
						List<HotHttpEntity> params = new ArrayList<>();
						params.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
						params.add(new HotHttpEntity("guid", EntityEnum.TEXT, mac));
						params.add(new HotHttpEntity("beginTime", EntityEnum.TEXT, DateUtils.getYesterDay()));
						params.add(new HotHttpEntity("endTime", EntityEnum.TEXT, DateUtils.getTime()));
						params.add(new HotHttpEntity("limit", EntityEnum.TEXT, 100));
						params.add(new HotHttpEntity("page", EntityEnum.TEXT, 1));
						HotHttpResponse response = HttpClientUtils.doPost(params,
								"http://ddflow.doordu.com/open_api/d_d/alertList/v1");
						JSONObject object = JSONObject.fromObject(response.getReturnJson());
						if (object.get("code").equals("200")) {
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) object.get("date");
							if (list != null) {
								for (Object obj : list) {
									JSONObject data = JSONObject.fromObject(obj);
									AlarmInsertPA alarm = new AlarmInsertPA();
									alarm.setLib(tableParams);
									alarm.setAlarmType(1);
									alarm.setAlarmSourceType(map.get("alertType"));
									DoorDevUM dev = synchroAlarm.selectByMac(tableParams, data.getString("guid"));
									alarm.setAlarmSourceId(dev.getId());
									alarm.setAlarmSource(dev.getMac());
									alarm.setAlarmMessage(
											DeviceEN.DevDoorAlarm.getByName(data.getInt("alertType")).getValue());
									alarm.setAlarmAddress(data.getString("position"));
									alarm.setAlarmLevel(level.get(data.get("alertType")));
									alarm.setTimeNum(DateUtils.dateToStamp((String) data.getString("createTime")));
									alarmService.insertAlarmToMainDevice(alarm);
								}
							}
						}
					}
				}
			}
		}
	}
}
