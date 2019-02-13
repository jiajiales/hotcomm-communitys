package com.hotcomm.community.resful.controller.console.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.WorkerPA;
import com.hotcomm.community.common.bean.pa.system.WorkerPagePA;
import com.hotcomm.community.common.service.system.WorkerService;
import com.hotcomm.community.resful.comm.AbstractController;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class WorkerController extends AbstractController {

	@Autowired
	WorkerService workerService;

	@Function(key = RestfulUrlRecord.WORKER_ADD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_ADD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "WK_F09"), 
									   @Param(key = "userName", code = "WK_F03"), 
									   @Param(key = "password", code = "WK_F04"), 
									   @Param(key = "realName", code = "WK_F06"), 
									   @Param(key = "email", code = "WK_F07"), 
									   @Param(key = "telephone", code = "WK_F08") })
	@LogEvent(code = "WK00101")
	public ApiResult addWorker(WorkerPA params, HttpServletRequest request) throws HKException {
		params.setCreateUser(getLogin(request).getUserName());
		workerService.addWorker(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.WORKER_DEL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_DEL }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "workerId", code = "WK_F01") })
	@LogEvent(code = "WK00102")
	public ApiResult delWorker(Integer workerId) throws HKException {
		workerService.delWorker(workerId);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.WORKER_UPDATE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_UPDATE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "workerId", code = "WK_F01"), 
									   @Param(key = "roleId", code = "WK_F09"), 
									   @Param(key = "userName", code = "WK_F03"), 
									   @Param(key = "status", code = "WK_F05"), 
									   @Param(key = "realName", code = "WK_F06"), 
									   @Param(key = "email", code = "WK_F07"), 
									   @Param(key = "telephone", code = "WK_F08") })
	@LogEvent(code = "WK00103")
	public ApiResult updateWorker(WorkerPA params, HttpServletRequest request) throws HKException {
		params.setUpdateUser(getLogin(request).getUserName());
		workerService.updateWorker(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.WORKER_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "workerId", code = "WK_F01") })
	@LogEvent(code = "WK00104")
	public ApiResult getWorker(Integer workerId) throws HKException {
		return ApiResult.success(workerService.getWorker(workerId));
	}

	@Function(key = RestfulUrlRecord.WORKER_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_LIST }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "WK_F02") })
	@LogEvent(code = "WK00105")
	public ApiResult queryListWorker(Integer cid) throws HKException {
		return ApiResult.success(workerService.queryListWorker(cid));
	}

	@Function(key = RestfulUrlRecord.WORKER_PAGE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.WORKER_PAGE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "WK_F02"),
									   @Param(key = "pageIndex", code = "PAGE_F01"), 
            						   @Param(key = "pageSize", code = "PAGE_F02") })
	@LogEvent(code = "WK00106")
	@LogSkip
	public ApiResult queryPageWorker(WorkerPagePA params) throws HKException {
		return ApiResult.success(workerService.queryPageWorker(params));
	}

}
