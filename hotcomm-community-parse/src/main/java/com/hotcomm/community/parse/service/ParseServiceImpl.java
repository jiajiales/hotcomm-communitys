package com.hotcomm.community.parse.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.parse.AlarmGz;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.db.parse.SelectCommunityDM;
import com.hotcomm.community.parse.mapper.ParseMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class ParseServiceImpl implements ParseService {

	@Autowired
	private ParseMapper parseMapper;

	@Value(value = "${spring.datasource.dbname}")
	public String baseDbName;

	@Override
	public SelectCommunityDM selectCommunity(String mac) throws HKException {
		try {
			return parseMapper.selectCommunity(mac);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Map<String, Object> updateDeviceTime(String mac, Double battery, Integer community) throws HKException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (community == null)
				return null;
			SelectCommunityDM dataBaseName = parseMapper.selectCommunityName(community);
			int code = parseMapper.updateDeviceTime(dataBaseName.getDataBaseNo(), mac, battery);
			map.put("dynamic_dbname", dataBaseName.getDataBaseNo());
			map.put("base_dbname", baseDbName);
			map.put("code", code);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AlarmGz selectAlarmGz(String mac, String alarmName) throws HKException {
		try {
			SelectCommunityDM dataBaseName = parseMapper.selectCommunityName(this.selectCommunity(mac).getCId());
			if (dataBaseName == null)
				return null;
			return parseMapper.selectAlarmGz(dataBaseName.getDataBaseNo(), mac, alarmName);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public DeviceSendMsg selectDeviceId(String mac) throws HKException {
		try {
			SelectCommunityDM dataBaseName = parseMapper.selectCommunityName(this.selectCommunity(mac).getCId());
			if (dataBaseName.getDataBaseNo().isEmpty())
				return null;
			return parseMapper.selectDeviceId(baseDbName, dataBaseName.getDataBaseNo(), mac);
		} catch (Exception e) {
			return null;
		}
	}

}
