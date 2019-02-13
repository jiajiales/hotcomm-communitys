package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.system.OperateLogDM;
import com.hotcomm.community.common.bean.db.system.PerformanceDM;
import com.hotcomm.community.common.bean.pa.system.OperateLogPagePA;
import com.hotcomm.community.common.bean.pa.system.PerformanceLogPagePA;
import com.hotcomm.community.common.bean.ui.system.OperateLogUM;
import com.hotcomm.community.common.bean.ui.system.PerformanceLogUM;

public interface LogMapper {

	@Insert("INSERT INTO hotcomm_community.hk_operate_log (record_user,record_event,record_code,record_time,record_ip,community_id) VALUES (#{recordUser},#{recordEvent},#{recordCode},#{recordTime},#{recordIp},#{communityId})")
	public void insertOperateLog(OperateLogDM log);

	@Insert("INSERT INTO hotcomm_community.hk_performance_log(request_ip, request_params, request_url, execute_times, response_obj, response_status, record_time) VALUES (#{requestIp},#{requestParams},#{requestUrl},#{executeTimes},#{responseObj},#{responseStatus},#{recordTime})")
	public void insertPerformanceLog(PerformanceDM performanceDM);

	public List<OperateLogUM> queryPageOperateLog(@Param("params") OperateLogPagePA params);

	Long queryPageOperateLogCount(@Param("params") OperateLogPagePA params);

	public List<PerformanceLogUM> queryPagePerformanceLog(@Param("params") PerformanceLogPagePA params);

	Long queryPagePerformanceLogCount(@Param("params") PerformanceLogPagePA params);

	void clearOperateLogBeforeTime(@Param("time") String time);

	void clearPerformanceLogBeforeTime(@Param("time") String time);

}
