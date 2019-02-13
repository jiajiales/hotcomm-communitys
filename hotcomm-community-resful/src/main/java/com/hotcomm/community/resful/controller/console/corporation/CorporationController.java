package com.hotcomm.community.resful.controller.console.corporation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.pa.corporation.CorPerRelationListPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPagePA;
import com.hotcomm.community.common.bean.ui.corporation.CorporationUM;
import com.hotcomm.community.common.service.corporation.CorporationService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CorporationController {

	@Autowired
	private CorporationService corporationService;
	
	//分页查询单位列表
	@RequestMapping(value = { RestfulUrlRecord.COR_PAGE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_PAGE_FUN)
	@LogEvent(code = "COR00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corPageList(CorporationPagePA corporationPagePA) {
		PageInfo<CorporationUM> pageInfo = corporationService.page(corporationPagePA);
		return ApiResult.success(pageInfo);
	}
	
	//查询单位列表
	@RequestMapping(value = { RestfulUrlRecord.COR_LIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_LIST_FUN)
	@LogEvent(code = "COR00119")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corList(CorporationPA corporationPA) {
		List<CorporationUM> corlist = corporationService.list(corporationPA);
		return ApiResult.success(corlist);
	}
	
	//新增单位
	@RequestMapping(value = { RestfulUrlRecord.COR_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_INSERT_FUN)
	@LogEvent(code = "COR00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corInsert(CorporationPA corporationPA) {
		return ApiResult.success(corporationService.insert(corporationPA));
	}
	
	//删除单位
	@RequestMapping(value = { RestfulUrlRecord.COR_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_DELETE_FUN)
	@LogEvent(code = "COR00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corDelete(CorporationPA corporationPA) {
		return ApiResult.success(corporationService.delete(corporationPA.getCommunityId(),corporationPA.getId()));
	}
	
	//修改单位
	@RequestMapping(value = { RestfulUrlRecord.COR_UPDATE},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_UPDATE_FUN)
	@LogEvent(code = "COR00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corUpdate(CorporationPA corporationPA) {
		return ApiResult.success(corporationService.update(corporationPA));
	}
	
	//单位详情
	@RequestMapping(value = { RestfulUrlRecord.COR_DETAIL},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_DETAIL_FUN)
	@LogEvent(code = "COR00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corDetail(CorporationPA corporationPA) {
		return ApiResult.success(corporationService.detail(corporationPA.getCommunityId(),corporationPA.getId(),corporationPA.getCorName()));
	}
	
	//批量添加单位人口关系
	@RequestMapping(value = { RestfulUrlRecord.COR_PER_BATCH_INSERT},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_PER_BATCH_INSERT_FUN)
	@LogEvent(code = "COR00112")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corPerRelationBatchInsert(CorPerRelationListPA corPerRelationListPA) {
		return ApiResult.success(corporationService.insertCorPerRelationBatch(corPerRelationListPA));
	}
	
	//分页查询单位关联人口列表
	@RequestMapping(value = { RestfulUrlRecord.COR_PERSON_PAGE},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_PERSON_FUN)
	@LogEvent(code = "COR00113")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corPersonListPage(CorporationPA corporationPA) {
		return ApiResult.success(corporationService.selectCorPersonList(corporationPA));
	}
	
	//查询单位关联人口列表
	@RequestMapping(value = { RestfulUrlRecord.COR_PERSON_LIST},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_PERSON_LIST_FUN)
	@LogEvent(code = "COR00119")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corPersonList(CorPerRelationListPA corPerRelationListPA) {
		return ApiResult.success(corporationService.selectPersonList(corPerRelationListPA));
	}
	
	//删除单位人口关联关系
	@RequestMapping(value = { RestfulUrlRecord.COR_PERSON_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.COR_DELETE_FUN)
	@LogEvent(code = "COR00118")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult corDeletePerson(CorPerRelationListPA corPerRelationListPA) {
		return ApiResult.success(corporationService.deleteCorPersonById(corPerRelationListPA.getCommunityId(),corPerRelationListPA.getId()));
	}
}
