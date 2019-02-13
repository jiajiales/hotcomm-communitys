package com.hotcomm.community.resful.controller.console.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.service.house.HouseSummarizingService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class HouseSummarizingController {
	@Autowired
	HouseSummarizingService houseSummarizingService;

	/**
	 * 房屋数据统计，楼栋数、总建筑面积、住宅型房间数、出租屋数、隐患场所、服务场所
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_STATISTICS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_STATISTICS_FUN)
	@LogEvent(code = "HS00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getHouseStatistics(Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getHouseStatistics(communityId));
	}

	/**
	 * 房屋楼栋数统计
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_BUILDING_STATISTICS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_BUILDING_STATISTICS_FUN)
	@LogEvent(code = "HS00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getBuildingStatistics(Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getBuildingStatistics(communityId));
	}

	/**
	 * 隐患场所统计、服务场所统计
	 * 
	 * @param ways
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PLACE_STATISTICS }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PLACE_STATISTICS_FUN)
	@LogEvent(code = "HS00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102"),
			@Param(key = "ways", code = "HS_P010") })
	public ApiResult getPlaceStatistics(Integer ways, Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getPlaceStatistics(ways, communityId));
	}

	/**
	 * 隐患、服务场所情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_PLACE_NUM }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_PLACE_NUM_FUN)
	@LogEvent(code = "HS00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getPlaceNumData(Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getPlaceNumData(communityId));
	}

	/**
	 * 出租屋空置率
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_RENT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_RENT_FUN)
	@LogEvent(code = "HS00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getData(Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getData(communityId));
	}

	/**
	 * 住宅型房屋租赁和购买数情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@RequestMapping(value = { RestfulUrlRecord.HOUSE_ROOM_RENT_SALE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.HOUSE_ROOM_RENT_SALE_FUN)
	@LogEvent(code = "HS00106")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102") })
	public ApiResult getRentSaleRoom(Integer communityId) throws HKException {
		return ApiResult.success(houseSummarizingService.getRentSaleRoom(communityId));
	}
}
