package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.pa.system.WorkerPA;
import com.hotcomm.community.common.bean.pa.system.WorkerPagePA;
import com.hotcomm.community.common.bean.ui.system.WorkerListUM;
import com.hotcomm.community.common.bean.ui.system.WorkerUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface WorkerService {

	/**
	 * 新增工作人员
	 * @param workerPA
	 * @return Integer
	 * @throws HKException
	 */
	public Integer addWorker(WorkerPA workerPA) throws HKException;

	/**
	 * 删除工作人员
	 * @param workerId
	 * @throws HKException
	 */
	public void delWorker(Integer workerId) throws HKException;

	/**
	 * 更新工作人员
	 * @param workerPA
	 * @throws HKException
	 */
	public void updateWorker(WorkerPA workerPA) throws HKException;

	/**
	 * 查看工作人员
	 * @param workerId
	 * @return WorkerUM
	 * @throws HKException
	 */
	public WorkerUM getWorker(Integer workerId) throws HKException;

	/**
	 * 工作人员列表
	 * @param cid
	 * @return List
	 * @throws HKException
	 */
	public List<WorkerListUM> queryListWorker(Integer cid) throws HKException;

	/**
	 * 工作人员分页查询
	 * @param workerPagePA
	 * @return PageInfo
	 * @throws HKException
	 */
	public PageInfo<WorkerUM> queryPageWorker(WorkerPagePA workerPagePA) throws HKException;

}
