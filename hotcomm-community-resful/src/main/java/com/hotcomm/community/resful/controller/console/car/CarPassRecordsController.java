package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPA;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPagePA;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarPassRecordsController {
	
	@Autowired
	private  CarPassRecordsService carPassRecordsService;
	
	//分页查询车辆通行记录列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_PASS_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_PASS_PAGELIST_FUN)
	@LogEvent(code = "CAR00115")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult passRecordsPageList(CarPassRecordsPagePA carPassRecordsPagePA) {
		PageInfo<CarPassRecordsUM> pageInfo = carPassRecordsService.page(carPassRecordsPagePA);
		return ApiResult.success(pageInfo);
	}
	
//	@Deprecated
	//新增车辆通行记录
	@RequestMapping(value = { RestfulUrlRecord.CAR_PASS_INSERT },method= {RequestMethod.POST})
	@LogEvent(code = "CAR00116")
	@LogSkip
	@ParamsValidate(validateParams = { /*@Param(key = "communityId", code = "UA00102")*/ })
	public ApiResult passRecordsInsert(CarPassRecordsPA carPassRecordsPA) {
		try {
			return ApiResult.success(carPassRecordsService.insert(carPassRecordsPA));
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查询车辆通行记录详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_PASS_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_PASS_DETAIL_FUN)
	@LogEvent(code = "CAR00144")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult passRecordsDetail(CarPassRecordsPA carPassRecordsPA) {
		return ApiResult.success(carPassRecordsService.detail(carPassRecordsPA.getCommunityId(),carPassRecordsPA.getId()));
	}
}
