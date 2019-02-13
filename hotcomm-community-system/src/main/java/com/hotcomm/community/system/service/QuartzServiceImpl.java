package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.quartz.QuartzDM;
import com.hotcomm.community.common.bean.pa.quartz.QuartzPA;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.quartz.QuartzService;
import com.hotcomm.community.system.mapper.QuartzMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;


@Service
public class QuartzServiceImpl extends BaseService implements QuartzService {

	private final static Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);
	
	@Autowired
    private Scheduler scheduler;
    
    @Autowired
    private QuartzMapper quartzMapper;
    
    
	//分页查询任务列表
	@Override
	public PageInfo<QuartzDM> page(QuartzPA quartzPA) throws HKException {
		int pageIndex = quartzPA.getPageIndex();
		int pageSize = quartzPA.getPageSize();
		if (pageIndex==0) {
			quartzPA.setStartIndex(0);
		} else {
			quartzPA.setStartIndex(((pageIndex - 1) * pageSize));
		}
		quartzPA.setEndIndex(pageSize);
		Page<QuartzDM> page = quartzMapper.pagelist(quartzPA);
		List<QuartzDM> quartzRecords = page;
		Long count =   quartzMapper.pageCount(quartzPA);
		 return new PageInfo<>(count, count == 0 ?new ArrayList<>():quartzRecords, quartzPA.getPageSize(), quartzPA.getPageIndex());
	}

	//新增或修改任务
	@Override
	@Transactional
	public boolean insertOrUpdate(QuartzDM quartz) throws HKException {
		try {
			if (quartz.getOldJobGroup() != null) {
	            JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
	            scheduler.deleteJob(key);
	        }
	        //反射获取.class
	        Class cls = Class.forName(quartz.getJobClassName());
	        cls.newInstance();
	        //创建jobdetail
	        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(), quartz.getJobGroup())
	                //描述
	                .withDescription(quartz.getDescription())
	                .build();
	        // 使用cron表达式
	        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
	        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
	                .startNow()
	                .withSchedule(cronScheduleBuilder)
	                .build();
	        //交由Scheduler安排触发
	        scheduler.scheduleJob(job, trigger);
	        return true;
		} catch (Exception e) {
			logger.info("查询任务列表失败:   "+e);
			throw this.exceptionManager.configLog(error).errorRecord("QT0001", e);
		}
	}

	//删除任务
	@Override
	@Transactional
	public boolean delete(QuartzDM quartz) throws HKException {
		TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        // 停止触发器
        try {
			scheduler.pauseTrigger(triggerKey);
	        // 移除触发器
	        scheduler.unscheduleJob(triggerKey);
	        // 删除任务
	        scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
	        return true;
		} catch (SchedulerException e) {
			logger.info("删除任务失败:   "+e);
			throw this.exceptionManager.configLog(error).errorRecord("QT0002", e);
		}
	}

	//暂停任务
	@Override
	public boolean pause(QuartzDM quartz) throws HKException {
        try {
        	 JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
			scheduler.pauseJob(key);
			return true;
		} catch (SchedulerException e) {
			logger.info("暂停任务失败:   "+e);
			throw this.exceptionManager.configLog(error).errorRecord("QT0003", e);
		}
	}

	//恢复任务
	@Override
	public boolean resume(QuartzDM quartz) throws HKException {
        try {
        	JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
			scheduler.resumeJob(key);
			return true;
		} catch (SchedulerException e) {
			logger.info("恢复任务失败:   "+e);
			throw this.exceptionManager.configLog(error).errorRecord("QT0004", e);
		}
	}

}
