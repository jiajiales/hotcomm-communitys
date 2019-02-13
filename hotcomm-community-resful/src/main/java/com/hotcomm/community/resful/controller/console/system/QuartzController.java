package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.db.quartz.QuartzDM;
import com.hotcomm.community.common.bean.pa.quartz.QuartzPA;
import com.hotcomm.community.common.service.quartz.QuartzService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class QuartzController {

	@Autowired
	private QuartzService quartzService;
	
	//分页查询任务列表
	@RequestMapping(value = { RestfulUrlRecord.QT_PAGE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.QT_PAGE_FUN)
	@LogEvent(code = "QT00101")
//	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult QtPageList(QuartzPA quartzPA) {
		PageInfo<QuartzDM> pageInfo = quartzService.page(quartzPA);
		return ApiResult.success(pageInfo);
	}
	
	//新增或修改任务
	@RequestMapping(value = { RestfulUrlRecord.QT_INSERT_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.QT_INSERT_UPDATE_FUN)
	@LogEvent(code = "QT00102")
//	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult QtInsertOrUpdate(QuartzDM quartz) {
		try {
			return ApiResult.success(quartzService.insertOrUpdate(quartz));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//删除任务
	@RequestMapping(value = { RestfulUrlRecord.QT_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.QT_DELETE_FUN)
	@LogEvent(code = "QT00103")
//	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult QtDelete(QuartzDM quartz) {
		try {
			return ApiResult.success(quartzService.delete(quartz));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//暂停任务
	@RequestMapping(value = { RestfulUrlRecord.QT_PAUSE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.QT_PAUSE_FUN)
	@LogEvent(code = "QT00104")
//	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult QtPause(QuartzDM quartz) {
		try {
			return ApiResult.success(quartzService.pause(quartz));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//恢复任务
	@RequestMapping(value = { RestfulUrlRecord.QT_RESUME },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.QT_RESUME_FUN)
	@LogEvent(code = "QT00105")
//	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult QtResume(QuartzDM quartz) {
		try {
			return ApiResult.success(quartzService.resume(quartz));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
}
