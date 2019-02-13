package com.hotcomm.community.resful.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 将job交给spring来管理
 */

@Component
public class SpringJobFactory extends AdaptableJobFactory {
	
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法    
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入    
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
    
}  