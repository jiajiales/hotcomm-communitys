package com.hotcomm.community.resful.controller.console.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.en.system.RoleEN.RoleType;
import com.hotcomm.community.common.bean.pa.system.CommunityPA;
import com.hotcomm.community.common.bean.pa.system.CommunityPagePA;
import com.hotcomm.community.common.bean.pa.system.UserCommunityPA;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.UserUM;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.UserService;
import com.hotcomm.community.resful.comm.AbstractController;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.log.LogManager;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class CommunityController extends AbstractController {

	@Autowired
	CommunityService communityService;

	@Autowired
	UserService userService;

	@Autowired
	LogManager logManager;

	@Function(key = RestfulUrlRecord.COMMUNITY_ADD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_ADD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cname", code = "CY_F02"), 
									   @Param(key = "code", code = "CY_F13"), 
									   @Param(key = "caddress", code = "CY_F08"), 
									   @Param(key = "lon", code = "CY_F09"),
									   @Param(key = "lat", code = "CY_F10"),
									   @Param(key = "linkUser", code = "CY_F11"),
									   @Param(key = "linkPhone", code = "CY_F12") })
	@LogEvent(code = "UA00101")
	public ApiResult addCommunity(CommunityPA params, HttpServletRequest request) throws HKException {
		try {
			params.setCreateUser(getLogin(request).getUserName());
			communityService.addCommunity(params);
		} catch (Exception e) {
			HKException exception = (HKException) e;
			return ApiResult.error("-1", exception.getMsg());
		}
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_DEL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_DEL }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "UA00102")
	public ApiResult delCommunity(Integer cid, HttpServletRequest request) throws HKException {
		CommunityPA params = new CommunityPA();
		params.setCid(cid);
		params.setUpdateUser(getLogin(request).getUserName());
		communityService.delCommunity(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_UPDATE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_UPDATE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01"),
									   @Param(key = "cname", code = "CY_F02"), 
									   @Param(key = "code", code = "CY_F13"), 
									   @Param(key = "caddress", code = "CY_F08"), 
									   @Param(key = "lon", code = "CY_F09"), 
									   @Param(key = "lat", code = "CY_F10"), 
									   @Param(key = "linkUser", code = "CY_F11"), 
									   @Param(key = "linkPhone", code = "CY_F12") })
	@LogEvent(code = "UA00103")
	public ApiResult updateCommunity(CommunityPA params, HttpServletRequest request) throws HKException {
		params.setUpdateUser(getLogin(request).getUserName());
		communityService.updateCommunity(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "UA00104")
	public ApiResult getCommunity(Integer cid) throws HKException {
		return ApiResult.success(communityService.getCommunity(cid));
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_LIST }, method = { RequestMethod.POST })
	@LogEvent(code = "UA00105")
	public ApiResult queryListCommunity(HttpServletRequest request) throws HKException {
		Integer userId = getLogin(request).getUserId();
		UserUM loginUser = userService.getUser(userId);

		if (loginUser.getRoleId() == RoleType.SUPER_ADMIN.getValue())
			return ApiResult.success(communityService.queryListCommunityAll());
		else
			return ApiResult.success(communityService.queryListCommunity(userId));
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_LIST_ALL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_LIST_ALL }, method = { RequestMethod.POST })
	@LogEvent(code = "UA00110")
	public ApiResult queryListCommunityAll() throws HKException {
		return ApiResult.success(communityService.queryListCommunityAll());
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_LIST_DETAIL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_LIST_DETAIL }, method = { RequestMethod.POST })
	@LogEvent(code = "UA00111")
	public ApiResult queryListCommunityDetail(Integer userId) throws HKException {
		List<CommunityDetailListUM> communityList = null;
		UserUM loginUser = userService.getUser(userId);

		if (loginUser.getRoleId() == RoleType.SUPER_ADMIN.getValue())
			communityList = communityService.queryListCommunityDetailAll();
		else
			communityList = communityService.queryListCommunityDetail(userId);

		return ApiResult.success(communityList);
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_PAGE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_PAGE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "pageIndex", code = "PAGE_F01"), 
									   @Param(key = "pageSize", code = "PAGE_F02") })
	@LogEvent(code = "UA00106")
	@LogSkip
	public ApiResult queryPageCommunity(CommunityPagePA params) throws HKException {
		return ApiResult.success(communityService.queryPageCommunity(params));
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_ROLE_USER_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_ROLE_USER_LIST }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "UA00107")
	public ApiResult queryRoleUserListByCid(Integer cid, String roleId) throws HKException {
		return ApiResult.success(communityService.queryRoleUserListByCid(cid, roleId));
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_ADD_USER_COMMUNITY_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_ADD_USER_COMMUNITY }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "CY_F14") })
	@LogEvent(code = "UA00108")
	public ApiResult addUserCommunity(UserCommunityPA params) throws HKException {
		communityService.addUserCommunity(params);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_GET_USER_COMMUNITY_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_GET_USER_COMMUNITY }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userId", code = "CY_F14") })
	@LogEvent(code = "UA00109")
	public ApiResult getUserCommunity(Integer userId) throws HKException {
		return ApiResult.success(communityService.getUserCommunity(userId));
	}

	@Function(key = RestfulUrlRecord.COMMUNITY_USER_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.COMMUNITY_USER_LIST }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "cid", code = "CY_F01") })
	@LogEvent(code = "UA00111")
	public ApiResult queryUserListByCid(Integer cid, String roleId) throws HKException {
		return ApiResult.success(communityService.queryUserListByCid(cid, roleId));
	}

}
