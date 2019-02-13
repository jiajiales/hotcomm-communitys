package com.hotcomm.community.resful.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hotcomm.community.resful.comm.ThreadLocalManager;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.ContentType;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.web.exception.HKException;

/**
* @Description: 请求参数效验
* @author 万鹏
* @date 2018年12月5日 下午2:56:48 
* @version V1.0
 */
public class HttpParamsInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean result = true;
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (!method.isAnnotationPresent(ParamsValidate.class))
			return result;

		Environment env = SpringUtil.getBean(Environment.class);

		ParamsValidate paramsValidate = method.getAnnotation(ParamsValidate.class);
		Param[] params = paramsValidate.validateParams();
		for (Param param : params) {
			String key = param.key();
			if(request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
				Map<String, Object> requestParams = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
				if (requestParams.get(key) == null) {
					String returnMsg = env.getProperty(param.code(), String.class);
					throw new HKException("-1", returnMsg);
				}
			} else {
				if (request.getParameter(key) == null || request.getParameter(key).isEmpty()) {
					String returnMsg = env.getProperty(param.code(), String.class);
					throw new HKException("-1", returnMsg);
				}
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
}
