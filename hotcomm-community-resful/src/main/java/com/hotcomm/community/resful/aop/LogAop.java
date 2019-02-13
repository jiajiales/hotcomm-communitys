package com.hotcomm.community.resful.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hotcomm.community.common.bean.db.system.OperateLogDM;
import com.hotcomm.community.common.bean.db.system.PerformanceDM;
import com.hotcomm.community.common.service.system.LogService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.community.resful.comm.ThreadLocalManager;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.comm.ContentType;
import com.hotcomm.framework.utils.NeTools;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.web.result.SuccessApiResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Aspect
@Component
public class LogAop {

	@Autowired
	LogService logservice;

	Logger log = LoggerFactory.getLogger("infoLogger");

	@Pointcut(value = "execution(public * com.hotcomm.community.resful.controller.*.*.*.*(..)) ")
	public void logPointCut() {
	}

	private NamedThreadLocal<LogParams> logThreadLocal = new NamedThreadLocal<LogParams>("log");

	@Before(value = "logPointCut()")
	public void beforeMethed(JoinPoint joinPoint) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String requestUrl = request.getRequestURI();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		log.info("[HTTP拦截]--------------------------begin--------------------------");
		log.info("[HTTP拦截]请求路径 :{}", requestUrl);
		log.info("[HTTP拦截]请求参数列表 ----------------");

		// 判断日志记录是否需要忽略
		if (RestfulUrlRecord.skip(requestUrl)) return;

		Map<String, Object> requestParams = new HashMap<>();

		if(request.getContentType().equals(ContentType.JSON.getName())||request.getContentType().startsWith(ContentType.JSON.getName())) {
			log.info("{}", SpringUtil.getBean(ThreadLocalManager.class).getRequestParams());
		} else {
			for (Enumeration<String> names = request.getParameterNames(); names.hasMoreElements();) {
				String name = names.nextElement();
				log.info("{}:{}", name, request.getParameter(name));
				requestParams.put(name, request.getParameter(name));
			}
		}

		String requestIP = NeTools.getIP(request);

		// 记录操作记录---- 当前用户,记录事件,记录事件编号,记录时间,记录客户端IP,记录社区编号ID
		// 执行存储
		LogEvent event = method.getAnnotation(LogEvent.class);
		if (event != null && event.code() != null) {
			String logCode = event.code();
			// 判断当前操作路径是否为执行登入
			// 判断当前项目开发模块 test:用户默认名称为admin,prod:用户从usersThreadLocal 中获取
			String loginUserName = "";
			if (requestUrl.equals(RestfulUrlRecord.PREFIX + RestfulUrlRecord.LOGIN)) {
				loginUserName = requestParams.get("userName") == null ? "" : requestParams.get("userName").toString();
			} else {
				loginUserName = SpringUtil.getBean(ThreadLocalManager.class).getLoginUser().getUserName();
			}
			String logDesc = SpringUtil.getBean(Environment.class).getProperty(logCode);
			Integer communityId = request.getParameter("communityId") == null ? 0 : Integer.parseInt(request.getParameter("communityId"));
			OperateLogDM log = new OperateLogDM(loginUserName, logDesc, logCode, new Date(), requestIP, communityId);
			SpringUtil.getBean(LogService.class).recordOperateLog(log);
		}

		logThreadLocal.set(new LogParams(requestIP, requestParams == null ? "" : JSONObject.toJSON(requestParams).toString(), requestUrl, System.currentTimeMillis()));
	}

	@AfterReturning(value = "logPointCut()", argNames = "retVal", returning = "retVal")
	public void logInfo(JoinPoint joinPoint, Object retVal) throws IOException {
		LogParams logparams = logThreadLocal.get();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String requestUrl = request.getRequestURI();
		// 判断日志记录是否需要忽略
		if (RestfulUrlRecord.skip(requestUrl))return;

		// 打印返回数据
		Long beginTimes = logparams.getBeginTimes();
		Long endTimes = System.currentTimeMillis();
		Long executeTimes = endTimes - beginTimes;
		log.info("[HTTP拦截]请求执行时间:{}毫秒", executeTimes);
		String responseStr = JSONObject.toJSON(retVal).toString();
		log.info("[HTTP拦截]返回数据:{}", responseStr);
		log.info("[HTTP拦截]--------------------------end----------------------------");

		// 判断返回结果状态
		Integer executeStatus = retVal instanceof SuccessApiResult ? 1 : 2;

		// 判断哪些日志忽略记录 通过@LogSkip注解
		// 记录性能日志 -- 客户端IP,客户端请求参数列表, 客户端请求路径,执行时间,客户端返回数据,执行状态,记录时间
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		LogSkip skip = method.getAnnotation(LogSkip.class);
		if (skip != null) {
			PerformanceDM operation = new PerformanceDM(logparams.getRequestIp(), logparams.getRequstData(), requestUrl, executeTimes, "{}", executeStatus, new Date()) ;
			logservice.recordPerformanceLog(operation);
		} else {
			PerformanceDM operation = new PerformanceDM(logparams.getRequestIp(), logparams.getRequstData(), requestUrl, executeTimes, responseStr, executeStatus, new Date());
			logservice.recordPerformanceLog(operation);
		}

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@NoArgsConstructor
	class LogParams {

		private String requestIp;
		private String requstData;
		private String responseData;
		private String requestUrl;
		private Long beginTimes;

		public LogParams(String requestIp, String requstData, String requestUrl, Long beginTimes) {
			super();
			this.requestIp = requestIp;
			this.requstData = requstData;
			this.requestUrl = requestUrl;
			this.beginTimes = beginTimes;
		}

	}

}
