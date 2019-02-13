package com.hotcomm.community.parse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import com.hotcomm.framework.web.exception.ExceptionManager;

@SpringBootApplication(scanBasePackages = { "com.hotcomm.community" })
@MapperScan(basePackages = { "com.hotcomm.community.parse.*.mapper", "com.hotcomm.community.*.mapper",
		"com.hotcomm.community.process.*.mapper" })
@EnableAsync
public class ParseApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ParseApplication.class).run(args);
	}

	@Bean(name = "exceptionManager")
	public ExceptionManager createExcMananger(Environment environment) {
		return new ExceptionManager(environment);
	}
}