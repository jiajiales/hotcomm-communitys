package com.hotcomm.community.resful.controller.console.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hotcomm.community.common.bean.pa.car.CarRegPA;
import com.hotcomm.community.common.bean.pa.car.CarRegPagePA;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarRegUM;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CarRegController {

	@Autowired
	private CarRegService carRegService;
	
	//分页查询登记车辆列表(重点关注车辆、黑名单车辆、服务车辆、其他登记车辆)
	@RequestMapping(value = { RestfulUrlRecord.CAR_REG_PAGELIST },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_REG_PAGELIST_FUN)
	@LogEvent(code = "CAR00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult regPageList(CarRegPagePA carRegPagePA) {
		PageInfo<CarRegUM> pageInfo = carRegService.page(carRegPagePA);
		return ApiResult.success(pageInfo);
	}
	
	//新增登记车辆
	@RequestMapping(value = { RestfulUrlRecord.CAR_REG_INSERT },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_REG_INSERT_FUN)
	@LogEvent(code = "CAR00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult regInsert(CarRegPA carRegPA) {
		try {
			boolean bl = carRegService.insert(carRegPA);
			if (bl) {
				return ApiResult.success(bl);
			} else {
				return ApiResult.error("-1", "该车牌号已登记过！");
			}
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//删除登记车辆
	@RequestMapping(value = { RestfulUrlRecord.CAR_REG_DELETE },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_REG_DELETE_FUN)
	@LogEvent(code = "CAR00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult regDelete(CarRegPA carRegPA) {
		try {
			return ApiResult.success(carRegService.delete(carRegPA.getCommunityId(),carRegPA.getId()));
		}catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//修改登记车辆详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_REG_UPDATE},method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_REG_UPDATE_FUN)
	@LogEvent(code = "CAR00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102")})
	public ApiResult regUpdate(CarRegPA carRegPA) {
		try {
			return ApiResult.success(carRegService.update(carRegPA));
		}catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
	}
	
	//查询登记车辆详情
	@RequestMapping(value = { RestfulUrlRecord.CAR_REG_DETAIL },method= {RequestMethod.POST})
	@Function(key = RestfulUrlRecord.CAR_REG_DETAIL_FUN)
	@LogEvent(code = "CAR00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult regDetail(CarRegPA carRegPA) {
		CarRegDetailUM carRegDetailUM = carRegService.detail(carRegPA.getCommunityId(),carRegPA.getId(),carRegPA.getNum());
		return ApiResult.success(carRegDetailUM);
	}
}
