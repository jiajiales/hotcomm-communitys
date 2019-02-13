package com.hotcomm.community.resful.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotcomm.community.common.service.device.doorcontrol.DoorcontrolService;
import com.hotcomm.community.common.service.device.doorcontrol.SynchroAlarmData;
import com.hotcomm.community.common.service.device.doorcontrol.SynchroDoorRecordData;
import com.hotcomm.framework.utils.SpringUtil;

@Component
@EnableScheduling // 该注解必须要加
public class QuartzDemo {
	@Autowired
	DoorcontrolService doorcontrolService;
	@Autowired
	SynchroAlarmData synchroAlarmData;
	@Autowired
	SynchroDoorRecordData synchroDoorRecordData;

	

	/**
	 * 同步设备列表
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24) // 每24小时执行一次
	public void playDev() throws Exception {
		// System.out.println("执行设备列表同步定时任务：-------------");
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			doorcontrolService.synchrodata();// 同步设备
		}
	}

	/**
	 * 同步报警数据
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000 * 60) // 每1分钟执行一次
	public void playAlarmData() throws Exception {
		// System.out.println("执行报警数据同步定时任务：-------------");
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			synchroAlarmData.synchroAlarmData();
		}

	}

	/**
	 * 同步门禁记录数据
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRate = 1000 * 60) // 每1分钟执行一次
	public void playRecordData() throws Exception {
		// System.out.println("执行通行数据同步定时任务：-------------");
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			synchroDoorRecordData.SynchroDoorRecord();
		}
	}

}
