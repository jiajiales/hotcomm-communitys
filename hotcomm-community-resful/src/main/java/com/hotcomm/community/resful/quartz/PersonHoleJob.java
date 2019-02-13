package com.hotcomm.community.resful.quartz;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hotcomm.community.common.service.person.PersonAlarmService;
import com.hotcomm.framework.utils.SpringUtil;

public class PersonHoleJob implements Job,Serializable {


	private static final long serialVersionUID = -2214110584926105996L;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringUtil.getBean(PersonAlarmService.class).addPersonAlarm();
	}

}
