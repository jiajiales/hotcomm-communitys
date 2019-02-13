package com.hotcomm.community.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hotcomm.community.common.bean.db.system.UserDM;
import com.hotcomm.community.common.bean.pa.system.UserPagePA;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.common.bean.ui.system.UserUM;

public interface UserMapper {

	public Integer addUser(UserDM userDM);

	public void delUser(UserDM userDM);

	public void updateUser(UserDM userDM);

	public UserUM getUser(@Param("userId") Integer userId);

	public List<UserListUM> queryListUser(@Param("cid") Integer cid);

	public List<UserUM> queryPageUser(@Param("params") UserPagePA userPagePA);

	public Long queryPageUserCount(@Param("params") UserPagePA userPagePA);

	public Integer isUserNameUsed(@Param("userName") String userName);

	public Integer isEmailUsed(@Param("email") String email);

	public Integer isTelephoneUsed(@Param("telephone") String telephone);

	@Select("SELECT count(0) FROM hk_sys_user WHERE password = #{password} AND user_name = #{userName} AND is_delete = 1")
	public boolean isUserExists(UserDM userDM);

	@Select("SELECT v1.user_id, v1.password, v1.user_name, v1.real_name, v3.role_code FROM hk_sys_user v1 INNER JOIN hk_sys_user_role v2 ON v1.user_id = v2.user_id INNER JOIN hk_sys_role v3 ON v2.role_id = v3.id WHERE v1.user_name = #{userName} AND v1.password = #{password} AND v1.is_delete = 1 AND v1.status = 1")
	public Map<String, Object> getUserByNamePwd(@Param("userName") String userName, @Param("password") String password);

	@Select("SELECT f5.* FROM hk_sys_resource f5 WHERE id IN (SELECT DISTINCT f2.resource_id FROM hk_sys_user_role f1 INNER JOIN hk_sys_role_resource f2 ON f1.role_id = f2.role_id WHERE f1.user_id = #{userId}) AND f5.res_status = 1")
	public List<Map<String, Object>> getUserResoruceByUserId(@Param("userId") Integer userId);

	public void setPwd(UserDM userDM);

	public Integer isPwdCorrect(@Param("userId") Integer userId, @Param("password") String password);

	public Integer isUserValid(@Param("userName") String userName);

}
