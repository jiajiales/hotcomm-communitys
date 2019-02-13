package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.system.WorkerDM;
import com.hotcomm.community.common.bean.pa.system.WorkerPagePA;
import com.hotcomm.community.common.bean.ui.system.WorkerListUM;
import com.hotcomm.community.common.bean.ui.system.WorkerUM;

public interface WorkerMapper {

	public Integer addWorker(WorkerDM workerDM);

	public void delWorker(WorkerDM workerDM);

	public void updateWorker(WorkerDM workerDM);

	public WorkerUM getWorker(@Param("workerId") Integer workerId);

	public List<WorkerListUM> queryListWorker(@Param("cid") Integer cid);

	public List<WorkerUM> queryPageWorker(@Param("params") WorkerPagePA workerPagePA);

	public Long queryPageWorkerCount(@Param("params") WorkerPagePA workerPagePA);

	public Integer isUserNameUsed(@Param("userName") String userName);

	public Integer isEmailUsed(@Param("email") String email);

	public Integer isTelephoneUsed(@Param("telephone") String telephone);

}
