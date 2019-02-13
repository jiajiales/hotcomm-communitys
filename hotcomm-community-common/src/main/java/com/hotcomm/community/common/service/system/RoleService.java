package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.pa.system.RolePA;
import com.hotcomm.community.common.bean.ui.system.RoleResourceUM;
import com.hotcomm.community.common.bean.ui.system.RoleUM;
import com.hotcomm.framework.web.exception.HKException;

public interface RoleService {

	/**
	 * 新增角色
	 * @param rolePa
	 * @return Integer
	 * @throws HKException
	 */
	public Integer addRole(RolePA rolePa) throws HKException;

	/**
	 * 删除角色
	 * @param roleId
	 * @throws HKException
	 */
	public void delRole(Integer roleId) throws HKException;

	/**
	 * 更新角色
	 * @param rolePa
	 * @throws HKException
	 */
	public void updateRole(RolePA rolePa) throws HKException;

	/**
	 * 查询角色
	 * @param roleId
	 * @return RoleUM
	 * @throws HKException
	 */
	public RoleUM getRole(Integer roleId) throws HKException;

	/**
	 * 查询角色列表
	 * @param level
	 * @return List
	 * @throws HKException
	 */
	public List<RoleUM> queryList(Integer level) throws HKException;

	/**
	 * 添加角色资源关联
	 * @param rolePa
	 * @return List
	 * @throws HKException
	 */
	public void addRoleResources(RolePA rolePa) throws HKException;

	/**
	 * 查询角色资源关联
	 * @param roleId
	 * @return RoleResourceUM
	 * @throws HKException
	 */
	public RoleResourceUM getRoleResources(Integer roleId) throws HKException;

	/**
	 * 添加用户角色关联
	 * @param userId
	 * @param roleId
	 * @throws HKException
	 */
	public void addUserRole(Integer userId, Integer roleId) throws HKException;

	/**
	 * 更新用户角色关联
	 * @param userId
	 * @param roleId
	 * @throws HKException
	 */
	public void updateUserRole(Integer userId, Integer roleId) throws HKException;

	/**
	 * 判断用户的角色状态是否有效
	 * @param userId
	 * @return Boolean
	 * @throws HKException
	 */
	public Boolean isUserRoleStatusEnable(Integer userId) throws HKException;

}
