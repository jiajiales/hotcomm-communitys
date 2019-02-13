package com.hotcomm.community.resful.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotcomm.community.common.service.system.LogService;

@Component
public class LogTask {

	@Autowired
	LogService logService;

	// Cron表达式范例：
	// 每隔5秒执行一次：*/5 * * * * ?
	// 每隔1分钟执行一次：0 */1 * * * ?
	// 每天23点执行一次：0 0 23 * * ?
	// 每天凌晨1点执行一次：0 0 1 * * ?
	// 每月1号凌晨1点执行一次：0 0 1 1 * ?
	// 每月最后一天23点执行一次：0 0 23 L * ?
	// 每周星期天凌晨1点实行一次：0 0 1 ? * L
	@Scheduled(cron = "0 0 1 * * ?")
	private void clearHistoryOfOperateLog() {
		logService.clearHistoryOfOperateLog();
	}

	@Scheduled(cron = "0 0 1 * * ?")
	private void clearHistoryOfPerformanceLog() {
		logService.clearHistoryOfPerformanceLog();
	}

}
