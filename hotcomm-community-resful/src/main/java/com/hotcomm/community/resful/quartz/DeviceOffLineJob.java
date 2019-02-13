package com.hotcomm.community.resful.quartz;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.framework.utils.SpringUtil;

public class DeviceOffLineJob implements Job, Serializable {

	private static final long serialVersionUID = -7294516830775586711L;

	/**
	 * 设备离线报警
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SpringUtil.getBean(DeviceService.class).deviceOffLineJob();
	}

}
