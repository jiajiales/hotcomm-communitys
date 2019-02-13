package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.OperateLogPagePA;
import com.hotcomm.community.common.bean.pa.system.PerformanceLogPagePA;
import com.hotcomm.community.common.service.system.LogService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class LogController {

	@Autowired
	LogService logService;

	@RequestMapping(value = { RestfulUrlRecord.LOG_OPERATE_PAGE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "pageIndex", code = "PAGE_F01"), 
			                           @Param(key = "pageSize", code = "PAGE_F02") })
	@LogSkip
	@LogEvent(code="LOG00101")
	public ApiResult queryPageOperateLog(OperateLogPagePA operateLogPagePA) throws HKException {
		return ApiResult.success(logService.queryPageOperateLog(operateLogPagePA));
	}

	@RequestMapping(value = { RestfulUrlRecord.LOG_PERFORMANCE_PAGE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "pageIndex", code = "PAGE_F01"), 
			   						   @Param(key = "pageSize", code = "PAGE_F02") })
	@LogSkip
	@LogEvent(code="LOG00102")
	public ApiResult queryPagePerformanceLog(PerformanceLogPagePA performanceLogPagePA) throws HKException {
		return ApiResult.success(logService.queryPagePerformanceLog(performanceLogPagePA));
	}

}
