package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.pa.system.CommunityPA;
import com.hotcomm.community.common.bean.pa.system.CommunityPagePA;
import com.hotcomm.community.common.bean.pa.system.UserCommunityPA;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityUM;
import com.hotcomm.community.common.bean.ui.system.RoleUserListUM;
import com.hotcomm.community.common.bean.ui.system.UserCommunityUM;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CommunityService {

	/**
	 * 社区新增
	 * @param params
	 * @return Integer
	 * @throws HKException
	 */
	public Integer addCommunity(CommunityPA params) throws HKException;

	/**
	 * 社区删除
	 * @param params
	 * @throws HKException
	 */
	public void delCommunity(CommunityPA params) throws HKException;

	/**
	 * 社区更新
	 * @param params
	 * @throws HKException
	 */
	public void updateCommunity(CommunityPA params) throws HKException;

	/**
	 * 获取社区信息 
	 * @param cid
	 * @return CommunityUM
	 * @throws HKException
	 */
	public CommunityUM getCommunity(Integer cid) throws HKException;

	/**
	 * 根据userId获取社区列表
	 * @param userId
	 * @return List
	 * @throws HKException
	 */
	public List<CommunityListUM> queryListCommunity(Integer userId) throws HKException;

	/**
	 * 根据userId获取社区详情列表
	 * @param userId
	 * @return List
	 * @throws HKException
	 */
	public List<CommunityDetailListUM> queryListCommunityDetail(Integer userId) throws HKException;
	
	/**
	 * 获取所有社区详情列表
	 * @return List
	 * @throws HKException
	 */
	public List<CommunityDetailListUM> queryListCommunityDetailAll() throws HKException;

	/**
	 * 获取所有的社区列表
	 * @return List
	 * @throws HKException
	 */
	public List<CommunityListAllUM> queryListCommunityAll() throws HKException;

	/**
	 * 分页查询社区信息
	 * @param userId
	 * @return List
	 * @throws HKException
	 */
	public PageInfo<CommunityUM> queryPageCommunity(CommunityPagePA params) throws HKException;

	/**
	 * 根据社区id查询角色用户列表
	 * @param cid
	 * @param roleId
	 * @return List
	 * @throws HKException
	 */
	public List<RoleUserListUM> queryRoleUserListByCid(Integer cid, String roleId) throws HKException;

	/**
	 * 根据社区id查询用户列表
	 * @param cid
	 * @param roleId
	 * @return List
	 * @throws HKException
	 */
	public List<UserListUM> queryUserListByCid(Integer cid, String roleId) throws HKException;

	/**
	 * 添加用户社区关联
	 * @param params
	 * @param cids
	 * @throws HKException
	 */
	public void addUserCommunity(UserCommunityPA params) throws HKException;

	/**
	 * 获取用户社区关联
	 * @param userId
	 * @return UserCommunityUM
	 * @throws HKException
	 */
	public UserCommunityUM getUserCommunity(Integer userId) throws HKException;

}
