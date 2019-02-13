package com.hotcomm.framework.web.exception;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;

/**
 * @Description: 
 * @author  wanpeng http://www.hotcomm.net/
 * @date 2018年3月16日 下午2:18:35
 */
public class ExceptionManager {

	Environment environment;
	
	private Logger log;

	public ExceptionManager() {}
	
	public ExceptionManager(Environment environment) {
		this.environment = environment;
	}
	
	public HKException create(String code) {
		return new HKException(code, environment.getProperty(code));
	}

	public ExceptionManager configLog(Logger log) {
		this.log = log;
		return this;
	}
	
	public HKException errorRecord(String code,Exception e) {
		String codeVal = environment.getProperty(code);
		this.log.error(codeVal, e);
		return new HKException(code, codeVal);
	}
	
	public HKException serviceRecord(String code) {
		String codeVal = environment.getProperty(code);
		this.log.info("业务异常:异常码:{},异常内容={}",code,codeVal);
		return new HKException(code, codeVal);
	}
}
