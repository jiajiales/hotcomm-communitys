package com.hotcomm.community.common.service.system;

import com.hotcomm.community.common.bean.db.system.OperateLogDM;
import com.hotcomm.community.common.bean.db.system.PerformanceDM;
import com.hotcomm.community.common.bean.pa.system.OperateLogPagePA;
import com.hotcomm.community.common.bean.pa.system.PerformanceLogPagePA;
import com.hotcomm.community.common.bean.ui.system.OperateLogUM;
import com.hotcomm.community.common.bean.ui.system.PerformanceLogUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface LogService {

	/**
	 * 记录操作日志
	 * @param operateLogDM
	 * @throws HKException
	 */
	public void recordOperateLog(OperateLogDM operateLogDM) throws HKException;

	/**
	 * 记录性能日志
	 * @param performanceDM
	 * @throws HKException
	 */
	public void recordPerformanceLog(PerformanceDM performanceDM) throws HKException;

	/**
	 * 操作日志分页查询
	 * @param operateLogPagePA
	 * @return PageInfo
	 * @throws HKException
	 */
	public PageInfo<OperateLogUM> queryPageOperateLog(OperateLogPagePA operateLogPagePA) throws HKException;

	/**
	 * 性能日志分页查询
	 * @param performanceLogPagePA
	 * @return PageInfo
	 * @throws HKException
	 */
	public PageInfo<PerformanceLogUM> queryPagePerformanceLog(PerformanceLogPagePA performanceLogPagePA) throws HKException;

	/**
	 * 清理历史记录(操作日志)
	 * @throws HKException
	 */
	public void clearHistoryOfOperateLog() throws HKException;

	/**
	 * 清理历史记录(性能日志)
	 * @throws HKException
	 */
	public void clearHistoryOfPerformanceLog() throws HKException;

}
