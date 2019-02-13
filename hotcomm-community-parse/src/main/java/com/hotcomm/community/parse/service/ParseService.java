package com.hotcomm.community.parse.service;

import java.util.Map;

import com.hotcomm.community.common.bean.db.parse.AlarmGz;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.db.parse.SelectCommunityDM;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @author Lktao
 *
 */
public interface ParseService {

	/**
	 * 查找设备所在的社区id
	 * 
	 * @param mac
	 * @return
	 */
	public SelectCommunityDM selectCommunity(String mac) throws HKException;

	/**
	 * 更新设备数据上传时间,和设备电量
	 * 
	 * @param mac
	 * @return
	 */
	public Map<String, Object> updateDeviceTime(String mac, Double battery, Integer community) throws HKException;

	/**
	 * 根据设备mac和规则名称查询设备规则
	 * 
	 * @param mac
	 * @return
	 */
	public AlarmGz selectAlarmGz(String mac, String alarmName) throws HKException;

	/**
	 * 根据mac查询设备id
	 * 
	 * @param mac
	 * @return
	 * @throws HKException
	 */
	public DeviceSendMsg selectDeviceId(String mac) throws HKException;

}
