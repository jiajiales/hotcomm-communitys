package com.hotcomm.community.resful.controller.console.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.system.RolePA;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@Function(key = RestfulUrlRecord.ROLE_ADD_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_ADD }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleName", code = "ROLE_F02"),
									   @Param(key = "roleCode", code = "ROLE_F03") })
	@LogEvent(code = "RO00101")
	public ApiResult addRole(RolePA rolePa) throws HKException {
		roleService.addRole(rolePa);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.ROLE_DEL_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_DEL }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "ROLE_F01") })
	@LogEvent(code = "RO00102")
	public ApiResult delRole(Integer roleId) throws HKException {
		roleService.delRole(roleId);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.ROLE_UPDATE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_UPDATE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "ROLE_F01"),
								       @Param(key = "roleName", code = "ROLE_F02"), 
								       @Param(key = "roleCode", code = "ROLE_F03") })
	@LogEvent(code = "RO00103")
	public ApiResult updateRole(RolePA rolePa) throws HKException {
		roleService.updateRole(rolePa);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.ROLE_GET_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_GET }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "ROLE_F01") })
	@LogEvent(code = "RO00104")
	public ApiResult getRole(Integer roleId) throws HKException {
		return ApiResult.success(roleService.getRole(roleId));
	}

	@Function(key = RestfulUrlRecord.ROLE_LIST_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_LIST }, method = { RequestMethod.POST })
	@LogEvent(code = "RO00105")
	@LogSkip
	public ApiResult queryList(Integer level) throws HKException {
		return ApiResult.success(roleService.queryList(level));
	}

	@Function(key = RestfulUrlRecord.ROLE_ADD_ROLERESOURCE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_ADD_ROLERESOURCE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "ROLE_F01") })
	@LogEvent(code = "RO00106")
	public ApiResult addRoleResources(RolePA rolePa) throws HKException {
		roleService.addRoleResources(rolePa);
		return ApiResult.success();
	}

	@Function(key = RestfulUrlRecord.ROLE_GET_ROLERESOURCE_FUN)
	@RequestMapping(value = { RestfulUrlRecord.ROLE_GET_ROLERESOURCE }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "roleId", code = "ROLE_F01") })
	@LogEvent(code = "RO00107")
	public ApiResult getRoleResources(Integer roleId) throws HKException {
		return ApiResult.success(roleService.getRoleResources(roleId));
	}

}
