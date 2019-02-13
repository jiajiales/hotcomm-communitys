package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.pa.system.UserLoginPA;
import com.hotcomm.community.common.bean.pa.system.UserPA;
import com.hotcomm.community.common.bean.pa.system.UserPagePA;
import com.hotcomm.community.common.bean.pa.system.UserPwdPA;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.common.bean.ui.system.UserUM;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface UserService {

	/**
	 * 新增用户
	 * @param userPa
	 * @return Integer
	 * @throws HKException
	 */
	public Integer addUser(UserPA userPa) throws HKException;

	/**
	 * 删除用户
	 * @param userPa
	 * @throws HKException
	 */
	public void delUser(Integer userId) throws HKException;

	/**
	 * 更新用户
	 * @param userPa
	 * @return
	 * @throws HKException
	 */
	public void updateUser(UserPA userPa) throws HKException;

	/**
	 * 查询用户
	 * @param userId
	 * @return UserUM
	 * @throws HKException
	 */
	public UserUM getUser(Integer userId) throws HKException;

	/**
	 * 用户列表
	 * @param cid
	 * @return List
	 * @throws HKException
	 */
	public List<UserListUM> queryListUser(Integer cid) throws HKException;

	/**
	 * 用户分页查询
	 * @param userPagePA
	 * @return PageInfo
	 * @throws HKException
	 */
	public PageInfo<UserUM> queryPageUser(UserPagePA userPagePA) throws HKException;

	/**
	 * 效验账号密码
	 * @param userLoginPA
	 * @return boolean
	 * @throws HKException
	 */
	public boolean exists(UserLoginPA userLoginPA) throws HKException;

	/**
	 * 获取登入用户信息
	 * @param userName
	 * @param password
	 * @return LoginUser
	 * @throws HKException
	 */
	public LoginUser getUser(String userName, String password) throws HKException;

	/**
	 * 用户设置密码
	 * @param userPwdPa
	 * @throws HKException
	 */
	public void setPwd(UserPwdPA userPwdPa) throws HKException;

	/**
	 * 效验用户是否有效
	 * @param userName
	 * @return boolean
	 * @throws HKException
	 */
	public boolean isUserValid(String userName) throws HKException;

}
