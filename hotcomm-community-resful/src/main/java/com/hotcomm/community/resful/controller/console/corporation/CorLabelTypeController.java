package com.hotcomm.community.resful.controller.console.corporation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelTypePA;
import com.hotcomm.community.common.service.corporation.CorLabelTypeService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CorLabelTypeController {

	@Autowired
	private CorLabelTypeService corLabelTypeService;
	
	//查询单位标签类型列表
	@RequestMapping(value = { RestfulUrlRecord.COR_LABEL_TYPE_PAGE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LABEL_TYPE_PAGE_FUN)
	@LogEvent(code = "COR00111")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corLabelPageList(CorLabelTypePA corLabelTypePA) {
		return ApiResult.success(corLabelTypeService.list(corLabelTypePA));
	}
}
