package com.hotcomm.community.resful.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.bean.db.system.LogDM;
import com.hotcomm.community.common.service.system.LogService;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.SuccessApiResult;

@Deprecated
public class LogEventAop {
	
	@Autowired
	Environment environment;
	
	@Autowired
	LogService logservice;
	
	Logger log = LoggerFactory.getLogger("infoLogger");
	
	@Pointcut(value = "execution(public * com.hotcomm.community1.resful.controller.*.*.*(..)) ")
	public void logPointCut() {}
	
	public LogEventAop() {}

	
	@AfterReturning(value = "logPointCut()", argNames = "retVal", returning = "retVal")
	public void logInfo(JoinPoint joinPoint, Object retVal) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		LogEvent event = method.getAnnotation(LogEvent.class);
		
		if (event == null || event.code() == null)return;
		String code = event.code();
		String logDesc = environment.getProperty(code);
		RequestMapping request = method.getAnnotation(RequestMapping.class);
		
		if(request.value().length<1) return;
		String requestPath = request.value()[0];
		
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String paramsJson = mapper.writeValueAsString(httpServletRequest.getParameterMap());
		
		boolean userIng = event.userIng();
		
		String executeUser = "";
		if(userIng) {
	/*		LoginUser loginUser = SpringUtil.getBean(CurrenUserLoder.class).getUser(httpServletRequest);
			executeUser = loginUser.getUserName();*/
		}
		String ip = getIP(httpServletRequest);
		
		String responseResult = mapper.writeValueAsString(retVal);
		
		LogDM logDM = new LogDM(executeUser,logDesc,code,new Date(),ip,paramsJson,responseResult);
		logDM.setRecordAction(requestPath);
		if(retVal instanceof SuccessApiResult) {
			logDM.setRecordStatus(1);
		}else {
			logDM.setRecordStatus(2);
		}
		
		//logservice.saveLog(logDM);
		log.info("HTTP:拦截-处理正常!--[日志编号:{},日志内容:{},拦截路径:{},拦截用户:{},拦截IP:{},拦截参数:{},返回参数:{}]",code,logDesc,requestPath,executeUser,ip,paramsJson,responseResult);
	}
	
	@AfterThrowing(value = "logPointCut()", throwing = "e")
	public void handleError(JoinPoint point, Throwable e) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		LogEvent event = method.getAnnotation(LogEvent.class);
		
		if (event == null || event.code() == null)return;
		String code = event.code();
		String logDesc = environment.getProperty(code);
		RequestMapping request = method.getAnnotation(RequestMapping.class);
		
		if(request.value().length<1) return;
		String requestPath = request.value()[0];
		
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String paramsJson = mapper.writeValueAsString(httpServletRequest.getParameterMap());
		
		boolean userIng = event.userIng();
		
		String executeUser = "";
		if(userIng) {
		/*	LoginUser loginUser = SpringUtil.getBean(CurrenUserLoder.class).getUser(httpServletRequest);
			executeUser = loginUser.getUserName();*/
		}
		String ip = getIP(httpServletRequest);
		LogDM logDM = new LogDM(executeUser,logDesc,code,new Date(),ip,paramsJson,null);
		logDM.setRecordAction(requestPath);
		logDM.setRecordStatus(3);
		if(e instanceof HKException) {
			HKException exception = (HKException)e;
			logDM.setRecordErrorMessage(exception.getMsg());
		}
		//logservice.saveLog(logDM);
		log.info("HTTP:拦截-处理异常!--[日志编号:{},日志内容:{},拦截路径:{},拦截用户:{},拦截IP:{},拦截参数:{},]",code,logDesc,requestPath,executeUser,ip,paramsJson);
	}

	// 获取请求IP
	public String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
