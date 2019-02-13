package com.hotcomm.community.resful;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hotcomm.community.message.service.MsgServer;
import com.hotcomm.framework.web.exception.ExceptionManager;
/**
 * @Description: springboot 项目启动器
 * @author http://www.hotcomm.net/
 * @date 2018年9月12日
 */
@MapperScan(basePackages = { "com.hotcomm.community.*.mapper"})
@SpringBootApplication(scanBasePackages = { "com.hotcomm"})
@EnableAsync
@EnableScheduling
public class CommunityRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CommunityRunner.class).run(args);
		LoggerFactory.getLogger("infoLogger").info("--初始化--日志组件-Logback");
		LoggerFactory.getLogger("infoLogger").info("--初始化--缓存组件-Redis");
		LoggerFactory.getLogger("infoLogger").info("--加载智慧社区项目接口完成");
		new MsgServer().start();
	}

	@Bean(name = "exceptionManager")
	public ExceptionManager createExcMananger(Environment environment) {
		return new ExceptionManager(environment);
	}
}
