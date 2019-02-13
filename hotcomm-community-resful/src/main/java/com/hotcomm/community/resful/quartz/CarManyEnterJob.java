package com.hotcomm.community.resful.quartz;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hotcomm.community.common.service.quartz.QuartzCarJobService;
import com.hotcomm.framework.utils.SpringUtil;

public class CarManyEnterJob implements Job ,Serializable{
	
	private static final long serialVersionUID = 1044905039837512326L;
	
	//车辆多次出入定时预警
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringUtil.getBean(QuartzCarJobService.class).CarManyEnter();
	}

}
