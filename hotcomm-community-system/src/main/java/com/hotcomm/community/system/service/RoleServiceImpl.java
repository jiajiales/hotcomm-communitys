package com.hotcomm.community.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.common.bean.en.system.RoleEN.RoleStatus;
import com.hotcomm.community.common.bean.pa.system.RolePA;
import com.hotcomm.community.common.bean.ui.system.RoleResourceUM;
import com.hotcomm.community.common.bean.ui.system.RoleUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.system.mapper.RoleMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

	@Autowired
	RoleMapper roleMapper;

	@Override
	public Integer addRole(RolePA rolePa) throws HKException {
		if (roleMapper.isRoleNameExist(rolePa.getRoleName()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("ROLE0001", HKException.instance());

		if (roleMapper.isRoleCodeExist(rolePa.getRoleCode()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("ROLE0002", HKException.instance());

		return roleMapper.addRole(rolePa);
	}

	@Override
	public void delRole(Integer roleId) throws HKException {
		if (roleMapper.isRoleUsed(roleId) != null)
			throw this.exceptionManager.configLog(error).errorRecord("ROLE0005", HKException.instance());

		roleMapper.delRole(roleId);
	}

	@Override
	public void updateRole(RolePA rolePa) throws HKException {
		RoleUM roleUM = getRole(rolePa.getRoleId()); 

		if (!roleUM.getRoleName().equals(rolePa.getRoleName())) {
			if (roleMapper.isRoleNameExist(rolePa.getRoleName()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("ROLE0003", HKException.instance());
		}

		if (!roleUM.getRoleCode().equals(rolePa.getRoleCode())) {
			if (roleMapper.isRoleCodeExist(rolePa.getRoleCode()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("ROLE0004", HKException.instance());
		}

		if (rolePa.getDesc() == null)
			rolePa.setDesc("");

		roleMapper.updateRole(rolePa);
	}

	@Override
	public RoleUM getRole(Integer roleId) throws HKException {
		return roleMapper.getRole(roleId);
	}

	@Override
	public List<RoleUM> queryList(Integer level) throws HKException {
		return roleMapper.queryList(level);
	}

	@Override
	@Transactional
	public void addRoleResources(RolePA rolePa) throws HKException {
		Integer roleId = rolePa.getRoleId();
		String resourceIds = rolePa.getResourceIds();

		if (resourceIds == null || resourceIds.split(",").length == 0 || resourceIds.length() == 0) {
			roleMapper.delRoleResources(roleId);
		} else {
			roleMapper.delRoleResources(roleId);
			roleMapper.addRoleResources(roleId, resourceIds.split(","));
		}
	}

	@Override
	public RoleResourceUM getRoleResources(Integer roleId) throws HKException {
		RoleResourceUM result = new RoleResourceUM();
		result.setRoleId(roleId);
		roleMapper.setSessionGroupConcatMaxLen();
		result.setResourceIds(roleMapper.getResourceIdsByRoleId(roleId));
		return result;
	}

	@Override
	public void addUserRole(Integer userId, Integer roleId) throws HKException {
		roleMapper.addUserRole(userId, roleId);
	}

	@Override
	public void updateUserRole(Integer userId, Integer roleId) throws HKException {
		roleMapper.updateUserRole(userId, roleId);
	}

	@Override
	public Boolean isUserRoleStatusEnable(Integer userId) throws HKException {
		if (roleMapper.getRoleStatusByUserId(userId) == RoleStatus.ENABLE.getValue())
			return true;

		return false;
	}

}
