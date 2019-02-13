package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.pa.car.CarStrPagePA;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarStrController {

	@Autowired
	private CarStrService carStrService;

	//分页查询陌生车辆列表
	@RequestMapping(value = { RestfulUrlRecord.CAR_STR_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STR_PAGELIST_FUN)
	@LogEvent(code = "CAR00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult strPageList(CarStrPagePA carStrPagePA) {
		PageInfo<CarStrUM> pageInfo = carStrService.page(carStrPagePA);
		return ApiResult.success(pageInfo);
	}
	
	//新增陌生车辆
	@Deprecated
	@RequestMapping(value = { RestfulUrlRecord.CAR_STR_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STR_INSERT_FUN)
	@LogEvent(code = "CAR00107")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult strInsert(CarStrDM carStrDM) {
		try {
			return ApiResult.success(carStrService.insert(carStrDM));
		}catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查看陌生车辆详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_STR_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STR_DETAIL_FUN)
	@LogEvent(code = "CAR00108")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult strDetai(CarStrPA carStrPA) {
		return ApiResult.success(carStrService.detail(carStrPA));
	}
	
	//删除陌生车辆
	@Deprecated
	@RequestMapping(value = { RestfulUrlRecord.CAR_STR_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_STR_DELETE_FUN)
	@LogEvent(code = "CAR00109")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult strDelete(CarStrPA carStrPA) {
		try {
			return ApiResult.success(carStrService.delete(carStrPA.getId(),carStrPA.getCommunityId()));
		}catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
}
