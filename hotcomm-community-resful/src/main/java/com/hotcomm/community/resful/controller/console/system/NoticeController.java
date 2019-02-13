package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.db.system.NoticeDM;
import com.hotcomm.community.common.bean.pa.system.NoticePagePA;
import com.hotcomm.community.common.service.system.NoticeService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	//新增消息通知
	@RequestMapping(value = { RestfulUrlRecord.SYS_NOTICE_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.SYS_NOTICE_INSERT_FUN)
	@LogEvent(code = "NT001")
	public ApiResult NotInsert(NoticeDM notice) {
		try {
			return  ApiResult.success(noticeService.insert(notice));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//删除消息通知
	@RequestMapping(value = { RestfulUrlRecord.SYS_NOTICE_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.SYS_NOTICE_DELETE_FUN)
	@LogEvent(code = "NT002")
	public ApiResult NotDelete(Integer id) {
		try {
			return  ApiResult.success(noticeService.delete(id));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//修改消息通知
	@RequestMapping(value = { RestfulUrlRecord.SYS_NOTICE_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.SYS_NOTICE_UPDATE_FUN)
	@LogEvent(code = "NT003")
	public ApiResult NotUpdate(NoticeDM notice) {
		try {
			return  ApiResult.success(noticeService.update(notice));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查看消息通知
	@RequestMapping(value = { RestfulUrlRecord.SYS_NOTICE_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.SYS_NOTICE_DETAIL_FUN)
	@LogEvent(code = "NT004")
	public ApiResult NotDetail(Integer id) {
		try {
			return  ApiResult.success(noticeService.load(id));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//分页查询消息通知
	@RequestMapping(value = { RestfulUrlRecord.SYS_NOTICE_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.SYS_NOTICE_PAGELIST_FUN)
	@LogEvent(code = "NT005")
	public ApiResult NotPageList(NoticePagePA noticePagePA) {
		PageInfo<NoticeDM> pageInfo = noticeService.pageList(noticePagePA);
		return ApiResult.success(pageInfo);
	}
}
