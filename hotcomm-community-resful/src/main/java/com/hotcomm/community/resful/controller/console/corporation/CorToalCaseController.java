package com.hotcomm.community.resful.controller.console.corporation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.service.corporation.CorToalCaseService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CorToalCaseController {

	@Autowired
	private CorToalCaseService corToalCaseService;
	
	//单位总况统计
	@RequestMapping(value = { RestfulUrlRecord.COR_TOTAL_CASE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_TOTAL_CASE_FUN)
	@LogEvent(code = "COR00114")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corToalCount(CorporationPA corporationPA) {
		return  ApiResult.success(corToalCaseService.selectToalCount(corporationPA));
	}
	
	//单位类型统计
	@RequestMapping(value = { RestfulUrlRecord.COR_TYPE_TOTAL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_TYPE_TOTAL_FUN)
	@LogEvent(code = "COR00115")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corTypeCount(CorporationPA corporationPA) {
		return  ApiResult.success(corToalCaseService.selectCorTypeCount(corporationPA.getCommunityId()));
	}
	
	//查询重点单位分布
	@RequestMapping(value = { RestfulUrlRecord.COR_ATTENTION_TOTAL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_ATTENTIONL_FUN)
	@LogEvent(code = "COR00116")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corAttentionCount(CorporationPA corporationPA) {
		return  ApiResult.success(corToalCaseService.selectAttentionCorCount(corporationPA));
	}
	
	//每月登记单位数量
	@RequestMapping(value = { RestfulUrlRecord.COR_MON_TOTAL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_MON_FUN)
	@LogEvent(code = "COR00117")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corMonCount(CommunityParams communityParams) {
		return  ApiResult.success(corToalCaseService.selectCorMonCount(communityParams));
	}
}
