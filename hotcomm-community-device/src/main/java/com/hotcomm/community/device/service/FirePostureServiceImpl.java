package com.hotcomm.community.device.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.device.FireAlarmTypeDM;
import com.hotcomm.community.common.bean.db.device.FireDeviceYearMsgDM;
import com.hotcomm.community.common.bean.ui.device.fell.FireAlarmMsgUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlaemDisposeMsgUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmToMonthUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmToYearUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireAlarmTypeUM;
import com.hotcomm.community.common.bean.ui.device.posture.FireDeviceYearMsgUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.FirePostureService;
import com.hotcomm.community.device.mapper.FireFellMapper;
import com.hotcomm.community.device.mapper.FirePostureMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class FirePostureServiceImpl extends BaseService implements FirePostureService {

	@Autowired
	private FirePostureMapper firePostureMapper;

	@Autowired
	private FireFellMapper fireFellMapper;

	/**
	 * 本月设备所有报警类型
	 * 
	 */
	@Override
	public FireAlarmTypeUM FireAlarmType(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 查询出当月消防报警数，以及当月设备报警总数
		FireAlarmTypeDM fireAlarmTypeDM = firePostureMapper.FireAlarmType(tableParams);

		FireAlarmTypeUM fireAlarmTypeUM = new FireAlarmTypeUM();

		// 其他设备报警数
		fireAlarmTypeUM.setOtherAlarmNum(fireAlarmTypeDM.getAlarmNum() - fireAlarmTypeDM.getFireAlarmNum());
		// 消防设备报警数
		fireAlarmTypeUM.setFireAlarmNum(fireAlarmTypeDM.getFireAlarmNum());
		if (fireAlarmTypeDM.getAlarmNum() != 0) {
			fireAlarmTypeUM.setOtherAlarmNumPercent(Double.parseDouble(
					df.format(((double) fireAlarmTypeUM.getOtherAlarmNum() / fireAlarmTypeDM.getAlarmNum()) * 100)));
			fireAlarmTypeUM.setFireAlarmNumPercent(Double.parseDouble(
					df.format(((double) fireAlarmTypeUM.getFireAlarmNum() / fireAlarmTypeDM.getAlarmNum()) * 100)));
		}

		return fireAlarmTypeUM;
	}

	/**
	 * 本月各种消防警报数量
	 */
	@Override
	public List<FireAlarmMsgUM> FireAlarmMsgToMonth(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 报警总数
		Integer alarmNum = 0;
		// 获取报警类型，以及各类型报警数
		List<FireAlarmMsgUM> fireAlarmMsgUMs = fireFellMapper.FireAlarmMsg(tableParams, "m");

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
	 * 本月各类设备消防报警情况
	 * 
	 */
	@Override
	public List<FireAlarmMsgUM> FireDeviceAlarmMsgToMonth(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 报警总数
		Integer alarmNum = 0;
		// 获取报警设备类型，以及各设备类型报警数
		List<FireAlarmMsgUM> fireAlarmMsgUMs = fireFellMapper.FireDeviceAlarmMsg(tableParams, "m");

		// 计算报警总数
		for (FireAlarmMsgUM f : fireAlarmMsgUMs)
			alarmNum += f.getAlarmNum();

		// 如果总数等于0则没必要计算百分比
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
	 * 设备概况
	 * 
	 */
	@Override
	public Map<String, Object> FireDeviceYearMsg(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		Map<String, Object> map = new HashMap<String, Object>();
		List<FireDeviceYearMsgUM> fireDeviceYearMsgUMs = new ArrayList<FireDeviceYearMsgUM>();
		Integer devcount = 0;

		// 获取每个设备去年数量，以及现在数量
		List<FireDeviceYearMsgDM> deviceYearMsgDMs = firePostureMapper.FireDeviceYearMsg(tableParams);

		// 计算增长
		for (int i = 0; i < deviceYearMsgDMs.size(); i++) {

			devcount += deviceYearMsgDMs.get(i).getNowYearNum();

			FireDeviceYearMsgUM Umson = new FireDeviceYearMsgUM();
			Umson.setModuleName(deviceYearMsgDMs.get(i).getModuleName());
			Umson.setNowYearNum(deviceYearMsgDMs.get(i).getNowYearNum());
			Umson.setModuleId(deviceYearMsgDMs.get(i).getModuleId());

			if (deviceYearMsgDMs.get(i).getLastYearNum() != 0)
				Umson.setYearOnYear(Double.parseDouble(df.format(
						((double) (deviceYearMsgDMs.get(i).getNowYearNum() - deviceYearMsgDMs.get(i).getLastYearNum())
								/ deviceYearMsgDMs.get(i).getLastYearNum()) * 100)));
			else
				Umson.setYearOnYear(deviceYearMsgDMs.get(i).getNowYearNum() * 100);

			fireDeviceYearMsgUMs.add(Umson);
		}
		map.put("toTal", devcount);
		map.put("deviceMsg", fireDeviceYearMsgUMs);
		return map;
	}

	/**
	 * 本月消费报警处理情况
	 * 
	 */
	@Override
	public FireAlaemDisposeMsgUM FireAlaemDisposeMsg(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");
		// 报警总数
		Integer alarmCount = 0;

		// 获取未处理，处理中，已处理数量
		FireAlaemDisposeMsgUM fireAlaemDisposeMsgUM = firePostureMapper.FireAlaemDisposeMsg(tableParams);

		alarmCount = fireAlaemDisposeMsgUM.getProcessed() + fireAlaemDisposeMsgUM.getProcessedIng()
				+ fireAlaemDisposeMsgUM.getUntreated();
		// 如果报警总数为零则不需要计算百分比
		if (alarmCount == 0)
			return fireAlaemDisposeMsgUM;

		// 计算百分比
		fireAlaemDisposeMsgUM.setUntreatedPercent(
				Double.parseDouble(df.format(((double) fireAlaemDisposeMsgUM.getUntreated() / alarmCount) * 100)));
		fireAlaemDisposeMsgUM.setProcessedIngPercent(
				Double.parseDouble(df.format(((double) fireAlaemDisposeMsgUM.getProcessedIng() / alarmCount) * 100)));
		fireAlaemDisposeMsgUM.setProcessedPercent(
				Double.parseDouble(df.format(((double) fireAlaemDisposeMsgUM.getProcessed() / alarmCount) * 100)));
		return fireAlaemDisposeMsgUM;
	}

	/**
	 * 消防月度报警趋势
	 * 
	 */
	@Override
	public Map<String, Object> FireAlarmToMonth(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Map<String, Object> map = new HashMap<String, Object>();

		// 查询今年当月的报警趋势
		List<FireAlarmToMonthUM> nowYear = firePostureMapper.FireAlarmToMonth(tableParams, "now");
		// 查询去年当月的报警趋势
		List<FireAlarmToMonthUM> lastYear = firePostureMapper.FireAlarmToMonth(tableParams, "last");

		for (int i = 1; i <= lastToMonth(); i++) {
			FireAlarmToMonthUM son = new FireAlarmToMonthUM();
			son.setDay(i);
			if (i <= nowYear.size()) {
				if (!nowYear.get(i - 1).getDay().equals(i))
					nowYear.add(i - 1, son);
			} else {
				nowYear.add(i - 1, son);
			}

			if (i <= lastYear.size()) {
				if (!lastYear.get(i - 1).getDay().equals(i))
					lastYear.add(i - 1, son);
			} else {
				lastYear.add(i - 1, son);
			}
		}

		map.put("nowYear", nowYear);
		map.put("lastYear", lastYear);
		return map;
	}

	/**
	 * 消防年度报警趋势
	 * 
	 */
	@Override
	public Map<String, Object> FireAlarmToYear(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Map<String, Object> map = new HashMap<String, Object>();

		// 今年
		List<FireAlarmToYearUM> now = firePostureMapper.FireAlarmToYear(tableParams, "now");
		// 去年
		List<FireAlarmToYearUM> last = firePostureMapper.FireAlarmToYear(tableParams, "last");
		// 前年
		List<FireAlarmToYearUM> next = firePostureMapper.FireAlarmToYear(tableParams, "next");
		for (int i = 1; i <= 12; i++) {
			FireAlarmToYearUM son = new FireAlarmToYearUM();
			son.setMonth(i);

			if (i <= now.size()) {
				if (!now.get(i - 1).getMonth().equals(i))
					now.add(i - 1, son);
			} else {
				now.add(i - 1, son);
			}

			if (i <= last.size()) {
				if (!last.get(i - 1).getMonth().equals(i))
					last.add(i - 1, son);
			} else {
				last.add(i - 1, son);
			}

			if (i <= next.size()) {
				if (!next.get(i - 1).getMonth().equals(i))
					next.add(i - 1, son);
			} else {
				next.add(i - 1, son);
			}
		}

		map.put("now", now);
		map.put("last", last);
		map.put("next", next);
		return map;
	}

	/**
	 * 获取当月最后一天方法
	 * 
	 * @return
	 */
	private Integer lastToMonth() {
		// 获取当月最后一天方法
		SimpleDateFormat format = new SimpleDateFormat("dd");
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return Integer.parseInt(format.format(cale.getTime()));
	}

}
