package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.UserDM;
import com.hotcomm.community.common.bean.en.system.UserEN.IsDelete;
import com.hotcomm.community.common.bean.en.system.UserEN.UserStatus;
import com.hotcomm.community.common.bean.en.system.UserEN.UserType;
import com.hotcomm.community.common.bean.pa.system.UserCommunityPA;
import com.hotcomm.community.common.bean.pa.system.UserLoginPA;
import com.hotcomm.community.common.bean.pa.system.UserPA;
import com.hotcomm.community.common.bean.pa.system.UserPagePA;
import com.hotcomm.community.common.bean.pa.system.UserPwdPA;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.common.bean.ui.system.UserUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.common.service.system.UserService;
import com.hotcomm.community.system.mapper.UserMapper;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.CodeUtils;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	RoleService roleService;

	@Autowired
	CommunityService communityService;

	@Override
	public Integer addUser(UserPA userPa) throws HKException {
		if (userMapper.isUserNameUsed(userPa.getUserName()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("USER0001", HKException.instance());

		if (userMapper.isEmailUsed(userPa.getEmail()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("USER0002", HKException.instance());

		if (userMapper.isTelephoneUsed(userPa.getTelephone()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("USER0003", HKException.instance());

		Integer userId = userPa.getUserId();
		UserDM userDM = new UserDM();
		BeanUtils.copyProperties(userPa, userDM);
		userDM.setPassword(CodeUtils.md5(userDM.getPassword()));
		userDM.setUserType(UserType.SYSTEM.getValue());
		userDM.setCreateTime(new Date());
		userDM.setUpdateTime(new Date());
		userMapper.addUser(userDM);
		userId = userDM.getUserId();
		roleService.addUserRole(userId, userPa.getRoleId());

		// 添加社区用户关联
		UserCommunityPA params = new UserCommunityPA();
		params.setUserId(userId);
		params.setCids(userPa.getCids());
		communityService.addUserCommunity(params);

		return userId;
	}

	@Override
	public void delUser(Integer userId) throws HKException {
		UserDM userDM = new UserDM();
		userDM.setUserId(userId);
		userDM.setStatus(UserStatus.INVALID.getValue());
		userDM.setIsDelete(IsDelete.YES.getValue());
		userDM.setUpdateTime(new Date());
		userMapper.delUser(userDM);
	}

	@Override
	public void updateUser(UserPA userPa) throws HKException {
		UserUM userUM = userMapper.getUser(userPa.getUserId());

		if (!userUM.getUserName().equals(userPa.getUserName())) {
			if (userMapper.isUserNameUsed(userPa.getUserName()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("USER0004", HKException.instance());
		}

		if (!userUM.getEmail().equals(userPa.getEmail())) {
			if (userMapper.isEmailUsed(userPa.getEmail()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("USER0005", HKException.instance());
		}

		if (!userUM.getTelephone().equals(userPa.getTelephone())) {
			if (userMapper.isTelephoneUsed(userPa.getTelephone()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("USER0006", HKException.instance());
		}

		UserDM userDM = new UserDM();
		BeanUtils.copyProperties(userPa, userDM);
		userDM.setUpdateTime(new Date());
		userMapper.updateUser(userDM);
		roleService.updateUserRole(userDM.getUserId(), userPa.getRoleId());

		// 更新社区用户关联
		UserCommunityPA params = new UserCommunityPA();
		params.setUserId(userPa.getUserId());
		params.setCids(userPa.getCids());
		communityService.addUserCommunity(params);
	}

	@Override
	public UserUM getUser(Integer userId) throws HKException {
		return userMapper.getUser(userId);
	}

	@Override
	public LoginUser getUser(String userName, String password) throws HKException {
		LoginUser loginUser = new LoginUser();
		Map<String, Object> userMap = userMapper.getUserByNamePwd(userName, CodeUtils.md5(password));
		Integer userId = Integer.parseInt(userMap.get("user_id").toString());
		loginUser.setUserId(userId);
		loginUser.setUserName(userMap.get("user_name").toString());
		loginUser.setRealName(userMap.get("real_name").toString());
		loginUser.setRoleCode(userMap.get("role_code").toString());
		List<Map<String, Object>> resources = new ArrayList<>();

		if (roleService.isUserRoleStatusEnable(userId))
			resources = this.userMapper.getUserResoruceByUserId(userId);

		loginUser.setResources(resources);
		return loginUser;
	}

	@Override
	public List<UserListUM> queryListUser(Integer cid) throws HKException {
		return userMapper.queryListUser(cid);
	}

	@Override
	public PageInfo<UserUM> queryPageUser(UserPagePA userPagePA) throws HKException {
		int pageIndex = userPagePA.getPageIndex();
		int pageSize = userPagePA.getPageSize();
		userPagePA.setStartIndex(((pageIndex - 1) * pageSize));
		userPagePA.setEndIndex(pageSize);
		Long count  = userMapper.queryPageUserCount(userPagePA);
		return new PageInfo<>(count, count == 0 ? new ArrayList<>() : userMapper.queryPageUser(userPagePA));
	}

	@Override
	public boolean exists(UserLoginPA userLoginPA) throws HKException {
		String encodePwd = CodeUtils.md5(userLoginPA.getPassword());
		UserDM userDM = new UserDM();
		userDM.setUserName(userLoginPA.getUserName());
		userDM.setPassword(encodePwd);
		Boolean exists = userMapper.isUserExists(userDM);
		return exists;
	}

	@Override
	public void setPwd(UserPwdPA userPwdPa) throws HKException {
		// 校验旧密码是否正确
		String encodeOldPwd = CodeUtils.md5(userPwdPa.getOldPassword());

		if (userMapper.isPwdCorrect(userPwdPa.getUserId(), encodeOldPwd) == null)
			throw this.exceptionManager.configLog(error).errorRecord("USER0007", HKException.instance());

		// 校验两次新密码输入是否一致
		String newPwd = userPwdPa.getNewPassword();

		if (!newPwd.equals(userPwdPa.getNewPassword2()))
			throw this.exceptionManager.configLog(error).errorRecord("USER0008", HKException.instance());

		String encodeNewPwd = CodeUtils.md5(newPwd);
		UserDM userDM = new UserDM();
		userDM.setUserId(userPwdPa.getUserId());
		userDM.setPassword(encodeNewPwd);
		userDM.setUpdateTime(new Date());
		userMapper.setPwd(userDM);
	}

	@Override
	public boolean isUserValid(String userName) throws HKException {
		if (userMapper.isUserValid(userName) == null)
			return false;

		return true;
	}

}
