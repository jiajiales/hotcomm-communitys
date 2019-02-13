package com.hotcomm.community.resful.controller.console.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.UserPA;
import com.hotcomm.community.common.bean.pa.system.UserPagePA;
import com.hotcomm.community.common.bean.pa.system.UserPwdPA;
import com.hotcomm.community.common.service.system.UserService;
import com.hotcomm.community.resful.comm.AbstractController;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class UserController extends AbstractController {

	@Autowired
	UserService userService;

	@Function(key = RestfulUrlRecord.USER_ADD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_ADD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "USER_F12"), 
									   @Param(key = "userName", code = "USER_F03"), 
									   @Param(key = "password", code = "USER_F04"), 
									   @Param(key = "realName", code = "USER_F06"), 
									   @Param(key = "email", code = "USER_F07"), 
									   @Param(key = "telephone", code = "USER_F08") })
	@LogEvent(code = "USER00101")
	public ApiResult addUser(UserPA params, HttpServletRequest request) throws HKException {
		params.setCreateUser(getLogin(request).getUserName());
		userService.addUser(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.USER_DEL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_DEL }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "USER_F01") })
	@LogEvent(code = "USER00102")
	public ApiResult delUser(Integer userId) throws HKException {
		userService.delUser(userId);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.USER_UPDATE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_UPDATE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "USER_F01"), 
									   @Param(key = "roleId", code = "USER_F12"), 
									   @Param(key = "userName", code = "USER_F03"), 
									   @Param(key = "status", code = "USER_F05"), 
									   @Param(key = "realName", code = "USER_F06"), 
									   @Param(key = "email", code = "USER_F07"), 
									   @Param(key = "telephone", code = "USER_F08") })
	@LogEvent(code = "USER00103")
	public ApiResult updateUser(UserPA params, HttpServletRequest request) throws HKException {
		params.setUpdateUser(getLogin(request).getUserName());
		userService.updateUser(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.USER_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "USER_F01") })
	@LogEvent(code = "USER00104")
	public ApiResult getUser(Integer userId) throws HKException {
		return ApiResult.success(userService.getUser(userId));
	}

	@Function(key = RestfulUrlRecord.USER_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_LIST }, method = { RequestMethod.POST })
	@LogEvent(code = "USER00105")
	public ApiResult queryListUser(Integer cid) throws HKException {
		return ApiResult.success(userService.queryListUser(cid));
	}

	@Function(key = RestfulUrlRecord.USER_PAGE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_PAGE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "pageIndex", code = "PAGE_F01"), 
			                           @Param(key = "pageSize", code = "PAGE_F02") })
	@LogEvent(code = "USER00106")
	@LogSkip
	public ApiResult queryPageUser(UserPagePA params) throws HKException {
		return ApiResult.success(userService.queryPageUser(params));
	}

	@Function(key = RestfulUrlRecord.USER_SET_PWD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.USER_SET_PWD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "USER_F01"), 
									   @Param(key = "oldPassword", code = "USER_F09"), 
									   @Param(key = "newPassword", code = "USER_F10"), 
									   @Param(key = "newPassword2", code = "USER_F11") })
	@LogEvent(code = "USER00107")
	public ApiResult setPwd(UserPwdPA params) throws HKException {
		userService.setPwd(params);
		return ApiResult.success();
	}

}
