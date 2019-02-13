package com.hotcomm.community.resful.comm;

import javax.servlet.http.HttpServletRequest;

import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.web.exception.HKException;

public abstract class AbstractController {
	
	public LoginUser getLogin(HttpServletRequest request)throws HKException{
		return SpringUtil.getBean(ThreadLocalManager.class).getLoginUser();
	}
	
}
