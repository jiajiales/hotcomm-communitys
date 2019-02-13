package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.DictionaryPA;
import com.hotcomm.community.common.service.system.DictionaryService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class DictionaryController {

	@Autowired
	DictionaryService dictionaryService;

	@RequestMapping(value = { RestfulUrlRecord.DICTIONARY_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "type", code = "DY_F01") })
	public ApiResult getDictionaryMap(DictionaryPA dictionaryPA) throws HKException {
		return ApiResult.success(dictionaryService.getDictionary(dictionaryPA));
	}

}
