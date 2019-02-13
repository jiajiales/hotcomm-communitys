package com.hotcomm.community.system.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.OperateLogDM;
import com.hotcomm.community.common.bean.db.system.PerformanceDM;
import com.hotcomm.community.common.bean.pa.system.OperateLogPagePA;
import com.hotcomm.community.common.bean.pa.system.PerformanceLogPagePA;
import com.hotcomm.community.common.bean.ui.system.OperateLogUM;
import com.hotcomm.community.common.bean.ui.system.PerformanceLogUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.LogService;
import com.hotcomm.community.system.mapper.LogMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class LogServiceImpl extends BaseService implements LogService {

	@Autowired
	LogMapper logMapper;

	@Override
	public void recordOperateLog(OperateLogDM operateLogDM) throws HKException {
		try {
			logMapper.insertOperateLog(operateLogDM);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("LOG0001", e);
		}
	}

	@Override
	public void recordPerformanceLog(PerformanceDM performanceDM) throws HKException {
		try {
			logMapper.insertPerformanceLog(performanceDM);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("LOG0002", e);
		}
	}

	@Override
	public PageInfo<OperateLogUM> queryPageOperateLog(OperateLogPagePA operateLogPagePA) throws HKException {
		int pageIndex = operateLogPagePA.getPageIndex();
		int pageSize = operateLogPagePA.getPageSize();
		operateLogPagePA.setStartIndex(((pageIndex - 1) * pageSize));
		operateLogPagePA.setEndIndex(pageSize);
		Long count  = logMapper.queryPageOperateLogCount(operateLogPagePA);
		return new PageInfo<>(count, count == 0 ? new ArrayList<>() : logMapper.queryPageOperateLog(operateLogPagePA));
	}

	@Override
	public PageInfo<PerformanceLogUM> queryPagePerformanceLog(PerformanceLogPagePA performanceLogPagePA) throws HKException {
		int pageIndex = performanceLogPagePA.getPageIndex();
		int pageSize = performanceLogPagePA.getPageSize();
		performanceLogPagePA.setStartIndex(((pageIndex - 1) * pageSize));
		performanceLogPagePA.setEndIndex(pageSize);
		Long count  = logMapper.queryPagePerformanceLogCount(performanceLogPagePA);
		return new PageInfo<>(count, count == 0 ? new ArrayList<>() : logMapper.queryPagePerformanceLog(performanceLogPagePA));
	}

	@Override
	public void clearHistoryOfOperateLog() throws HKException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		console.info("当前时间:" + format.format(new Date()));
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.DAY_OF_MONTH, -15);
		String time = format.format(cale.getTime());
		console.info("删除操作日志截止至:" + time);
		logMapper.clearOperateLogBeforeTime(time);
	}

	@Override
	public void clearHistoryOfPerformanceLog() throws HKException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		console.info("当前时间:" + format.format(new Date()));
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.DAY_OF_MONTH, -15);
		String time = format.format(cale.getTime());
		console.info("删除性能日志截止至:" + time);
		logMapper.clearPerformanceLogBeforeTime(time);
	}

}
