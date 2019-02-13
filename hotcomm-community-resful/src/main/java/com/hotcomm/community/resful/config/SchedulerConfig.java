package com.hotcomm.community.resful.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;



@Configuration
public class SchedulerConfig {

	@Value("${org.quartz.dataSource.qzDS.URL}")
	private String dbUrl;
	@Value("${org.quartz.dataSource.qzDS.user}")
	private String dbUser;
	@Value("${org.quartz.dataSource.qzDS.password}")
	private String dbPwd;
	
    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
    	PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        Properties result = propertiesFactoryBean.getObject();
        result.setProperty("org.quartz.dataSource.qzDS.URL", dbUrl);
        result.setProperty("org.quartz.dataSource.qzDS.user", dbUser);
        result.setProperty("org.quartz.dataSource.qzDS.password", dbPwd);
        return result;
    }
  
    
   //quartz初始化监听器  
    @Bean
    public QuartzInitializerListener executorListener() {
    	try {
			Properties prop = new Properties();
			String filepath = Thread.currentThread().getContextClassLoader().getResource("quartz.properties").getPath();
			File file = new File(filepath);
			InputStream in = new FileInputStream(file);
			prop.load(in); 
			in.close();
			System.out.println("filepath---------->" + filepath);
			OutputStream fos = new FileOutputStream(file);
			prop.setProperty("org.quartz.dataSource.qzDS.URL",dbUrl); 
			prop.setProperty("org.quartz.dataSource.qzDS.user",dbUser); 
			prop.setProperty("org.quartz.dataSource.qzDS.password",dbPwd); 
			prop.store(fos, "Update '" + "" + "' value");
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
       return new QuartzInitializerListener();
    }
    
    
	//通过SchedulerFactoryBean获取Scheduler的实例   
//    @Bean(name="Scheduler")
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
//    }
    
}
