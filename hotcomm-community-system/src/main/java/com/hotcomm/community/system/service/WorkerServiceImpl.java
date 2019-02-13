package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.WorkerDM;
import com.hotcomm.community.common.bean.en.system.UserEN.IsDelete;
import com.hotcomm.community.common.bean.en.system.UserEN.UserStatus;
import com.hotcomm.community.common.bean.en.system.UserEN.UserType;
import com.hotcomm.community.common.bean.pa.system.UserCommunityPA;
import com.hotcomm.community.common.bean.pa.system.WorkerPA;
import com.hotcomm.community.common.bean.pa.system.WorkerPagePA;
import com.hotcomm.community.common.bean.ui.system.WorkerListUM;
import com.hotcomm.community.common.bean.ui.system.WorkerUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.common.service.system.WorkerService;
import com.hotcomm.community.system.mapper.WorkerMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.CodeUtils;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class WorkerServiceImpl extends BaseService implements WorkerService {

	@Autowired
	WorkerMapper workerMapper;

	@Autowired
	RoleService roleService;

	@Autowired
	CommunityService communityService;

	@Override
	public Integer addWorker(WorkerPA workerPA) throws HKException {
		if (workerMapper.isUserNameUsed(workerPA.getUserName()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("WK0001", HKException.instance());

		if (workerMapper.isEmailUsed(workerPA.getEmail()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("WK0002", HKException.instance());

		if (workerMapper.isTelephoneUsed(workerPA.getTelephone()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("WK0003", HKException.instance());

		Integer workerId = workerPA.getWorkerId();
		WorkerDM workerDM = new WorkerDM();
		BeanUtils.copyProperties(workerPA, workerDM);
		workerDM.setPassword(CodeUtils.md5(workerDM.getPassword()));
		workerDM.setUserType(UserType.COMMUNITY.getValue());
		workerDM.setCreateTime(new Date());
		workerDM.setUpdateTime(new Date());
		workerMapper.addWorker(workerDM);
		workerId = workerDM.getWorkerId();
		roleService.addUserRole(workerId, workerPA.getRoleId());

		// 添加社区工作人员关联
		UserCommunityPA params = new UserCommunityPA();
		params.setUserId(workerId);
		params.setCids(workerPA.getCid().toString());
		params.setDefaultCid(workerPA.getCid());
		communityService.addUserCommunity(params);
		return workerId;
	}

	@Override
	public void delWorker(Integer workerId) throws HKException {
		WorkerDM workerDM = new WorkerDM();
		workerDM.setWorkerId(workerId);
		workerDM.setStatus(UserStatus.INVALID.getValue());
		workerDM.setIsDelete(IsDelete.YES.getValue());
		workerDM.setUpdateTime(new Date());
		workerMapper.delWorker(workerDM);
	}

	@Override
	public void updateWorker(WorkerPA workerPA) throws HKException {
		WorkerUM workerUM = workerMapper.getWorker(workerPA.getWorkerId());

		if (!workerUM.getUserName().equals(workerPA.getUserName())) {
			if (workerMapper.isUserNameUsed(workerPA.getUserName()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("WK0004", HKException.instance());
		}

		if (!workerUM.getEmail().equals(workerPA.getEmail())) {
			if (workerMapper.isEmailUsed(workerPA.getEmail()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("WK0005", HKException.instance());
		}

		if (!workerUM.getTelephone().equals(workerPA.getTelephone())) {
			if (workerMapper.isTelephoneUsed(workerPA.getTelephone()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("WK0006", HKException.instance());
		}

		WorkerDM workerDM = new WorkerDM();
		BeanUtils.copyProperties(workerPA, workerDM);
		workerDM.setUpdateTime(new Date());
		workerMapper.updateWorker(workerDM);
		roleService.updateUserRole(workerDM.getWorkerId(), workerPA.getRoleId());

		// 更新社区工作人员关联
		UserCommunityPA params = new UserCommunityPA();
		params.setUserId(workerPA.getWorkerId());
		params.setCids(workerPA.getCid().toString());
		params.setDefaultCid(workerPA.getCid());
		communityService.addUserCommunity(params);
	}

	@Override
	public WorkerUM getWorker(Integer workerId) throws HKException {
		return workerMapper.getWorker(workerId);
	}

	@Override
	public List<WorkerListUM> queryListWorker(Integer cid) throws HKException {
		return workerMapper.queryListWorker(cid);
	}

	@Override
	public PageInfo<WorkerUM> queryPageWorker(WorkerPagePA workerPagePA) throws HKException {
		int pageIndex = workerPagePA.getPageIndex();
		int pageSize = workerPagePA.getPageSize();
		workerPagePA.setStartIndex(((pageIndex - 1) * pageSize));
		workerPagePA.setEndIndex(pageSize);
		Long count  = workerMapper.queryPageWorkerCount(workerPagePA);
		return new PageInfo<>(count, count == 0 ? new ArrayList<>() : workerMapper.queryPageWorker(workerPagePA));
	}

}
