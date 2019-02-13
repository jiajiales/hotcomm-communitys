package com.hotcomm.community.resful.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description: HTTP拦截器
 * @author 万鹏
 * @date 2018年10月5日 下午7:41:07
 * @version V1.0
 */
@Deprecated
public class HttpInterceptor implements HandlerInterceptor {

	public Logger log = LoggerFactory.getLogger("infoLogger");

/*	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LoginUser user = new LoginUser();
		user.setDevModel(1);
		String devModel = SpringUtil.getBean(Environment.class).getProperty("dev_model", String.class);
		if(true) {
			return true;
		}
		String token = "";
		if (request.getContentType().equals(ContentType.JSON.getName())) {
			Map<String, Object> params = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
			token = params.get("token").toString();
		} else {
			token = request.getParameter("token");
		}

		if (devModel.equals("test")) {
			user.setUserName("开发测试人员");
		} else {
			if (token == null)
				throw new HKException("-1", "缺失令牌验证");
			RedisHelper redisHelper = SpringUtil.getBean(RedisHelper.class);
			String redisValStr = redisHelper.get(token);
			if (redisValStr == null)
				throw new HKException("-1", "该令牌验证不合法");
			ObjectMapper jsonMapper = new ObjectMapper();
			user = jsonMapper.readValue(redisValStr, LoginUser.class);
			user.setDevModel(2);
		}

		ThreadLocalManager localManager = SpringUtil.getBean(ThreadLocalManager.class);
		localManager.setLoginTime(System.currentTimeMillis());
		localManager.setLoingUser(user);

		logRecord(request, response, handler, user);

		boolean security = securityValidate(request, response, handler, user);
		if (!security)
			return security;

		boolean httpParams = httpParamsValidate(request, response, handler);
		if (!httpParams)
			return httpParams;
		// 更新缓存时间,所有接口调用时增加请求来源，0：大数据;1:后台
		if (Constant.DevModel.DEV.equals("test")) {
			return true;
		}
		String source;
		RedisHelper redisHelper = SpringUtil.getBean(RedisHelper.class);
		String redisValStr = redisHelper.get(token);
		if (request.getContentType().equals(ContentType.JSON.getName())) {
			Map<String, Object> params = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
			source = params.get("source").toString();
			if (source.equals("0")) {
				redisHelper.set(token, redisValStr);
			} else {
				redisHelper.set(token, redisValStr, 30 * 60);
			}
		} else {
			source = request.getParameter("source");
			if (source.equals("0")) {
				redisHelper.set(token, redisValStr);
			} else {
				redisHelper.set(token, redisValStr, 30 * 60);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	*//**
	 * 日志记录
	 *//*
	private void logRecord(HttpServletRequest request, HttpServletResponse response, Object handler,
			LoginUser loginUser) {
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (!method.isAnnotationPresent(LogEvent.class))
			return;

		LogEvent logEvent = method.getAnnotation(LogEvent.class);
		// ip,事件编号,事件内容,记录时间,记录者名称,社区编号
		String ip = NeTools.getIP(request);
		String logCode = logEvent.code();
		String logMsg = SpringUtil.getBean(Environment.class).getProperty(logCode, String.class);
		Date time = new Date();
		String userName = loginUser.getUserName();
		Integer communityId = request.getParameter("communityId") == null ? 0
				: Integer.parseInt(request.getParameter("communityId"));
		OperateLogDM log = new OperateLogDM(userName, logMsg, logCode, time, ip, communityId);
		SpringUtil.getBean(LogService.class).recordOperateLog(log);
	}

	*//**
	 * 权限效验
	 *//*
	private boolean securityValidate(HttpServletRequest request, HttpServletResponse response, Object handler,
			LoginUser loginUser) throws HKException {
		boolean result = true;
		final HandlerMethod handlerMethod = (HandlerMethod) handler;
		final Method method = handlerMethod.getMethod();
		if (!method.isAnnotationPresent(Function.class))
			return result;
		if (loginUser.getDevModel() == 1)
			return result;

		Function function = method.getAnnotation(Function.class);
		String validateKey = function.key();

		boolean exists = false;
		for (Map<String, Object> resource : loginUser.getResources()) {
			String resKey = resource.get("res_key").toString();
			if (validateKey.equals(resKey)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			throw new HKException("-1", "当前用户目前尚未开通过该权限");
		}
		return result;
	}

	*//**
	 * 请求参数效验
	 *//*
	private boolean httpParamsValidate(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws HKException {
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
			if (request.getContentType().equals(ContentType.JSON.getName())) {
				Map<String, Object> requestParams = SpringUtil.getBean(ThreadLocalManager.class).getRequestParams();
				if (requestParams.get(key) == null) {
					String returnMsg = env.getProperty(param.code(), String.class);
					throw new HKException("-1", returnMsg);
				}
			} else {
				if (request.getParameter(key) == null) {
					String returnMsg = env.getProperty(param.code(), String.class);
					throw new HKException("-1", returnMsg);
				}
			}
		}
		return result;
	}

	*//**
	 * 性能监控记录
	 */

}
