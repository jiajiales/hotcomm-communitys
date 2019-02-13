package com.hotcomm.community.resful.controller.console.corporation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPA;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPagePA;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelUM;
import com.hotcomm.community.common.service.corporation.CorLabelService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CorLabelController {

	@Autowired 
	private CorLabelService corLabelService;
	
	//分页查询单位标签列表
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_PAGE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_PAGE_FUN)
	@LogEvent(code = "COR00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corLabelPageList(CorLabelPagePA corLabelPagePA) {
		PageInfo<CorLabelUM> pageInfo = corLabelService.page(corLabelPagePA);
		return ApiResult.success(pageInfo);
	}
	
	//新增单位标签
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_INSERT_FUN)
	@LogEvent(code = "COR00107")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corLabelInsert(CorLabelPA corLabelPA) {
		return ApiResult.success(corLabelService.insert(corLabelPA));
	}
	
	//删除单位标签
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_INSERT_FUN)
	@LogEvent(code = "COR00108")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult CorLabelDelete(CorLabelPA corLabelPA) {
		return ApiResult.success(corLabelService.delete(corLabelPA.getCommunityId(),corLabelPA.getId()));
	}
	
	//修改单位标签
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_UPDATE_FUN)
	@LogEvent(code = "COR00109")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult CorLabelUpadate(CorLabelPA corLabelPA) {
		return ApiResult.success(corLabelService.update(corLabelPA));
	}
	
	//查看单位标签详情
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_DETAIL_FUN)
	@LogEvent(code = "COR00110")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corLabelDetail(CorLabelPA corLabelPA) {
		return ApiResult.success(corLabelService.detail(corLabelPA.getCommunityId(),corLabelPA.getId(),corLabelPA.getLabelName()));
	}
}
