package com.hotcomm.community.data;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.core.env.Environment;

import com.hotcomm.community.message.service.MsgServer;
import com.hotcomm.framework.web.exception.ExceptionManager;

@SpringBootApplication(scanBasePackages = { "com.hotcomm"})
@MapperScan(basePackages = { "com.hotcomm.community.*.mapper"})
@EnableAsync
@EnableScheduling
public class HotcommCommunityDataApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HotcommCommunityDataApplication.class).run(args);
		LoggerFactory.getLogger("infoLogger").info("--加载智慧社区项目定时任务加载完成");
		new MsgServer().start();
	}
	@Bean(name = "exceptionManager")
	public ExceptionManager createExcMananger(Environment environment) {
		return new ExceptionManager(environment);
	}
}

