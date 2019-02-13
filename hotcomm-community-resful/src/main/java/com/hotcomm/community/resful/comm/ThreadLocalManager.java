package com.hotcomm.community.resful.comm;

import java.util.Map;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import com.hotcomm.framework.comm.LoginUser;

@Component
public class ThreadLocalManager {
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("WatchExecuteTime");
	
	private NamedThreadLocal<LoginUser> usersThreadLocal = new NamedThreadLocal<>("CurrenLoginUser");
	
	private NamedThreadLocal<Map<String, Object>> paramsThreadLocal = new NamedThreadLocal<>("CurrenParams");
	
	public void setLoginTime(Long currenTime) {
		this.startTimeThreadLocal.set(currenTime);
	}
	
	public Long getLastTime() {
		return this.startTimeThreadLocal.get();
	}
	
	public void setLoingUser(LoginUser user) {
		this.usersThreadLocal.set(user);
	}
	
	public LoginUser getLoginUser() {
		return this.usersThreadLocal.get();
	}
	
	public void setRequestParams(Map<String, Object> params) {
		this.paramsThreadLocal.set(params);
	}
	
	public Map<String, Object> getRequestParams(){
		return this.paramsThreadLocal.get();
	}
}
