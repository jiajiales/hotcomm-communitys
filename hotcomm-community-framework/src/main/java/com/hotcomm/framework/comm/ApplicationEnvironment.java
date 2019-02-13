package com.hotcomm.framework.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEnvironment {
	
	@Value(value="${spring.datasource.dbname}")
	public String baseDbName;
	
}
