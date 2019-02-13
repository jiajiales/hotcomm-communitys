package com.hotcomm.community.resful.quartz;

import java.io.Serializable;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hotcomm.community.common.service.quartz.QuartzCarJobService;
import com.hotcomm.framework.utils.SpringUtil;

public class CarOverNightJob implements Job,Serializable {


	private static final long serialVersionUID = 4914830414411228067L;
	
	//过夜车定时预警任务
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringUtil.getBean(QuartzCarJobService.class).CarOverNight();
	}

}
