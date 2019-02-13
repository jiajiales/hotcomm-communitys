package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.pa.system.RolePA;
import com.hotcomm.community.common.bean.ui.system.RoleUM;

public interface RoleMapper {

	public Integer addRole(RolePA rolePa);

	public void delRole(@Param("roleId") Integer roleId);

	public Integer updateRole(RolePA rolePa);

	public RoleUM getRole(@Param("roleId") Integer roleId);

	public List<RoleUM> queryList(@Param("level") Integer level);

	public void addRoleResources(@Param("roleId") Integer roleId, @Param("resourceIds") String[] resourceIds);

	public void delRoleResources(@Param("roleId") Integer roleId);

	public String getResourceIdsByRoleId(@Param("roleId") Integer roleId);

	public Integer isRoleUsed(@Param("roleId") Integer roleId);

	public Integer isRoleNameExist(@Param("roleName") String roleName);

	public Integer isRoleCodeExist(@Param("roleCode") String roleCode);

	public void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	public void updateUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	public Integer getRoleStatusByUserId(@Param("userId") Integer userId);

	public void setSessionGroupConcatMaxLen();

}
