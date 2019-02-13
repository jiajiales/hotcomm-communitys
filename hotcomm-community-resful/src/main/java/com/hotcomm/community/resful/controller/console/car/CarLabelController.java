package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.db.car.CarLabelDM;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.pa.car.CarLabelPA;
import com.hotcomm.community.common.bean.pa.car.CarLabelPagePA;
import com.hotcomm.community.common.bean.ui.car.CarLabelUM;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarLabelService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarLabelController {

	@Autowired
	private CarLabelService carLabelService;
	
	@Autowired
	private CarLabelRelationService carLabelRelationService;
	
	//分页查询车辆标签列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_PAGELIST_FUN)
	@LogEvent(code = "CAR00110")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult pageList(CarLabelPagePA carLabelPagePA) {
		PageInfo<CarLabelUM> pageInfo = carLabelService.page(carLabelPagePA);
		return ApiResult.success(pageInfo);
	}
	
	//查询车辆标签列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_LIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_LIST_FUN)
	@LogEvent(code = "CAR00144")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult labelList(CarLabelDM carLabelDM) {
		try {
			return ApiResult.success(carLabelService.labelList(carLabelDM));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查询车辆标签、标签类型列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_LAYERED_LIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_LAYERED_LIST_FUN)
	@LogEvent(code = "CAR00145")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult labellayeredList(CarLabelDM carLabelDM) {
		try {
			return ApiResult.success(carLabelService.labellayeredList(carLabelDM.getCommunityId()));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//新增车辆标签
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_INSERT_FUN)
	@LogEvent(code = "CAR00111")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult insert(CarLabelPA carLabelPA) {
		try {
			return ApiResult.success(carLabelService.insert(carLabelPA));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//删除车辆标签（硬删除）
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_DELETE_FUN)
	@LogEvent(code = "CAR00112")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult delete(CarLabelPA carLabelPA) {
		try {
			return ApiResult.success(carLabelService.delete(carLabelPA.getCommunityId(),carLabelPA.getId()));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//修改车辆标签
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_UPDATE_FUN)
	@LogEvent(code = "CAR00113")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult update(CarLabelPA carLabelPA) {
		try {
			return ApiResult.success(carLabelService.update(carLabelPA));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查看车辆标签详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_DETAIL_FUN)
	@LogEvent(code = "CAR00114")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult detail(CarLabelPA carLabelPA) {
		return ApiResult.success(carLabelService.detail(carLabelPA.getCommunityId(),carLabelPA.getId(),carLabelPA.getName()));
	}
	
	//修改车辆标签关系
	@RequestMapping(value = { RestfulUrlRecord.CAR_LABEL_RELATION_UPDATE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_LABEL_RELATION_UPDATE_FUN)
	@LogEvent(code = "CAR00115")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult labelRlationUpdate(CarLabelRelationDM carLabelRelationDM) {
		try {
			return ApiResult.success(carLabelRelationService.update(carLabelRelationDM));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
}
