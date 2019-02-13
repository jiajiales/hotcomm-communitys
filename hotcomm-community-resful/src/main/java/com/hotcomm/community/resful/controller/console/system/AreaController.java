package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.AreaPA;
import com.hotcomm.community.common.service.system.AreaService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class AreaController {

	@Autowired
	AreaService areaService;

	@RequestMapping(value = { RestfulUrlRecord.AREA_LIST }, method = { RequestMethod.POST })
	public ApiResult getAreaList(AreaPA areaPa) throws HKException {
		return ApiResult.success(areaService.getAreaListByLevel(areaPa));
	}

}
