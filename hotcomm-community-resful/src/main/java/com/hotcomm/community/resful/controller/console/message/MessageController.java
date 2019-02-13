package com.hotcomm.community.resful.controller.console.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class MessageController {
	@Autowired
	MsgServiceImpl msgServiceImpl;

	@RequestMapping(value = { RestfulUrlRecord.MSG_SEND }, method = { RequestMethod.POST })
	@LogEvent(code = "MSG00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "MSG_F001"),
			@Param(key = "source", code = "MSG_F002") })
	public ApiResult sendMessage(Message msg) throws HKException {
		msg.setData(new Gson().fromJson(msg.getData().toString(), DeviceSendMsg.class));
		msgServiceImpl.sendMessage(msg);
		return ApiResult.success();
	}

	@RequestMapping(value = { RestfulUrlRecord.MSG_SEND_TEST }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "MSG_F001"),
			@Param(key = "source", code = "MSG_F002") })
	public ApiResult sendMessageTest(Message msg) throws HKException {
		msgServiceImpl.sendMessage(msg);
		return ApiResult.success();
	}
}
