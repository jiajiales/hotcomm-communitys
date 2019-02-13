package com.hotcomm.community.resful.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hotcomm.community.resful.comm.ThreadLocalManager;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.comm.Constant;
import com.hotcomm.framework.comm.ContentType;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.web.exception.HKException;

public class SecurityValidateInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 根据项目运行模式 选择是否拦截 test:直接放行 prod：需要该用户拥有此权限
		String devModel = SpringUtil.getBean(Environment.class).getProperty("dev_model", String.class);
		if (Constant.DevModel.DEV.getVal().equals(devModel))  return true;
		
		Integer source = null;
		if (request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
			//json请求
			Map<String, Object> params = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
			source = Integer.parseInt(params.get("source").toString());
		} else {//表单请求
			source = Integer.parseInt(request.getParameter("source"));
		}
		if (source!=null&&source == 0)	return true;
		
		// 根据客户请求路径,查找该路径是否有做权限位配置
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (!method.isAnnotationPresent(Function.class))  return true;

		// 通过当前登入用户线程持有者获取到用户,并根据此数据与权限位的拦截KEY,做权限匹配
		String validateUrl = request.getRequestURI();
		ThreadLocalManager manager = SpringUtil.getBean(ThreadLocalManager.class);
		LoginUser loginUser = manager.getLoginUser();
		
		boolean exists = false;
		for (Map<String, Object> resource : loginUser.getResources()) {
			String resPath = resource.get("res_path").toString();
			if (validateUrl.endsWith(resPath)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			throw new HKException("-1", "当前用户目前尚未开通过该权限");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
}
