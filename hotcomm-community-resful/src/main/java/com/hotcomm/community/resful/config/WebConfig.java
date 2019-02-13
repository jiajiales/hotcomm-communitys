package com.hotcomm.community.resful.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.hotcomm.community.resful.comm.LogManangerHelper;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.community.resful.interceptor.HttpParamsInterceptor;
import com.hotcomm.community.resful.interceptor.SecurityValidateInterceptor;
import com.hotcomm.community.resful.interceptor.TokenAuthInterceptor;
import com.hotcomm.framework.log.LogManager;
import com.hotcomm.framework.utils.RedisHelper;

@Configuration
@PropertySource(value = { "config/log.proeprties", "config/params.properties", "config/exception.properties" }, encoding = "UTF-8")
public class WebConfig extends WebMvcConfigurationSupport  {

	Logger log = LoggerFactory.getLogger("infoLogger");

	@Bean
	public RedisHelper initRedisBean(Environment environment) {
		return new RedisHelper(environment.getProperty("spring.redis.database", Integer.class));
	}

	@Bean
	public LogManager createLogManager() {
		return new LogManangerHelper();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenAuthInterceptor()).addPathPatterns("/**").excludePathPatterns(RestfulUrlRecord.CAR_PASS_INSERT,RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICELIST,RestfulUrlRecord.AREA_LIST,RestfulUrlRecord.GET_VALIDATE_CODE,RestfulUrlRecord.LOGIN,RestfulUrlRecord.POPULATION_RECORD_ADD,"/druid/*.html","/druid/*.json","/favicon.ico");
		registry.addInterceptor(new SecurityValidateInterceptor()).addPathPatterns("/**").excludePathPatterns(RestfulUrlRecord.CAR_PASS_INSERT,RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICELIST,RestfulUrlRecord.AREA_LIST,RestfulUrlRecord.GET_VALIDATE_CODE,RestfulUrlRecord.LOGIN,RestfulUrlRecord.POPULATION_RECORD_ADD,"/druid/*.html","/druid/*.json","/favicon.ico");
		registry.addInterceptor(new HttpParamsInterceptor()).addPathPatterns("/**").excludePathPatterns("/druid/*.html","/druid/*.json","/favicon.ico");
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
	}
	
	

}
