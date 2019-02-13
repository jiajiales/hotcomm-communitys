package com.hotcomm.community.common.service.device;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.fell.FireAlarmMsgUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlaemDisposeMsgUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmTypeUM;
import com.hotcomm.framework.web.exception.HKException;

public interface FirePostureService {

	/**
	 * 本月设备所有报警类型
	 * 
	 * @param communityId
	 * @return
	 */
	public FireAlarmTypeUM FireAlarmType(Integer communityId) throws HKException;

	/**
	 * 本月各种消防警报数量
	 * 
	 * @param communityId
	 * @return
	 */
	public List<FireAlarmMsgUM> FireAlarmMsgToMonth(Integer communityId) throws HKException;

	/**
	 * 本月各类设备消防报警情况
	 * 
	 * @param communityId
	 * @return
	 */
	public List<FireAlarmMsgUM> FireDeviceAlarmMsgToMonth(Integer communityId) throws HKException;

	/**
	 * 较去年设备概况
	 * 
	 * @param communityId
	 * @return
	 */
	public Map<String, Object> FireDeviceYearMsg(Integer communityId) throws HKException;

	/**
	 * 本月消防报警处理情况
	 * 
	 * @param communityId
	 * @return
	 */
	public FireAlaemDisposeMsgUM FireAlaemDisposeMsg(Integer communityId) throws HKException;

	/**
	 * 消防月度报警趋势
	 * 
	 * @param communityId
	 * @return
	 */
	public Map<String, Object> FireAlarmToMonth(Integer communityId) throws HKException;

	/**
	 * 消防年度报警趋势
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public Map<String, Object> FireAlarmToYear(Integer communityId) throws HKException;
}
