package com.hotcomm.community.common.service.process;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.pa.process.DevAlarmLogPA;
import com.hotcomm.community.common.bean.pa.process.DevFixLogPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmClosePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmDetailPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmLogPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmPagePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmWorkPA;
import com.hotcomm.community.common.bean.pa.process.alarm.WorkPA;
import com.hotcomm.community.common.bean.pa.process.event.EventClosePA;
import com.hotcomm.community.common.bean.pa.process.event.EventDetailPA;
import com.hotcomm.community.common.bean.pa.process.event.EventNewPA;
import com.hotcomm.community.common.bean.pa.process.event.EventPagePA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateAlarmInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateDevInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateEventInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreatePA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderDetailPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderListPA;
import com.hotcomm.community.common.bean.ui.home.PendingSituationUM;
import com.hotcomm.community.common.bean.ui.process.DevAlarmLogUM;
import com.hotcomm.community.common.bean.ui.process.DevFixLogUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListCotentUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmPageUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmUserLngLatUM;
import com.hotcomm.community.common.bean.ui.process.event.EventPageUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderListUM;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;


public interface ProscessService {
	
	//报警列表分页
	PageInfo<AlarmPageUM> alarmPage(AlarmPagePA pa) throws HKException;
	
	//设备维修日志
	PageInfo<DevFixLogUM> getDevFixLog(DevFixLogPA pa) throws HKException;
	
	//设备报警日志
	PageInfo<DevAlarmLogUM> getDevAlarmLog(DevAlarmLogPA pa) throws HKException;
	
	//关闭报警
	String closeAlarm(AlarmClosePA pa) throws HKException;
	
	//报警详情
	Map<String, Object> alarmDetail(AlarmDetailPA pa) throws HKException;
	
	//新增报警
	String insertAlarmToMain(AlarmInsertPA alarmInsertPA) throws HKException;
	
	//报警日志
	List<Map<String, Object>> AlarmLog(AlarmLogPA alarmLogPA);
	
	//报警总况
	Map<String, Object> alarmCountInfo(CommunityParams pa) throws HKException;
	
	//态势分析---今日报警总数
	Map<String, Object> alarmSTCountInfo(CommunityParams pa) throws HKException;
	
	//态势分析---报警趋势分析
	Map<String, Object> alarmSTTrendUMs(CommunityParams pa,Integer queryType) throws HKException;
	
	//态势分析---报警时段分析
	Map<String, Object> alarmSTAlarmCountByHour(CommunityParams pa,Integer queryType) throws HKException;
	
	//态势分析---报警类型分布
	Map<String, Object> alarmSTAlarmCountBySourceType(CommunityParams pa,Integer queryType) throws HKException;
	
	//态势分析---未处理报警列表
	Map<String, Object> alarmSTAlarmCountNotDealCount(CommunityParams pa) throws HKException;
	
	//态势分析---累计处理报警总数
	Map<String, Object> alarmSTAlarmCountProcessDealList(CommunityParams pa,Integer queryType) throws HKException;
	
	//创建工单
 	String worderCreate(WorderCreatePA pa) throws HKException; 
 	
 	//获取事件信息***创建工单用
 	List<WorderCreateEventInfoPA> getEventInfoForCreateWorder(WorderCreatePA pa);
 	
 	//获取报警信息***创建工单用
 	List<WorderCreateAlarmInfoPA> getAlarmInfoForCreateWorder(WorderCreatePA pa);
 	
 	//获取设备信息***创建工单用
 	List<WorderCreateDevInfoPA> getDevInfoForCreate(WorderCreatePA pa);
 	
 	//前端获取处理时间列表
 	List<Map<String, Object>> getWorderTime(WorderCreatePA pa);
 	
 	//新增报警***设备专用
 	Integer insertAlarmToMainDevice(AlarmInsertPA alarmInsertPA) throws HKException;
 	
 	//工单详情
 	Map<String, Object> worderDetail(WorderDetailPA pa) throws HKException;
 	
 	//事件详情
 	Map<String, Object> eventDetail(EventDetailPA pa) throws HKException;
 	
 	//前端获取设备模块列表
 	List<Map<String, Object>> getDevModule();
 	
 	//报警后台首页
	Map<String, Object> alarmBackCountInfo(CommunityParams pa) throws HKException;
	
	//事件列表
	PageInfo<EventPageUM> eventPage(EventPagePA eventPagePA) throws HKException;
	
	//关闭事件
	String closeEvent(EventClosePA pa) throws HKException;
	
	//添加事件
	Integer EventNewPA(EventNewPA eventNewPA) throws HKException;
	
	//工单列表
	public PageInfo<WorderListUM> worderPage(WorderListPA pa) throws HKException;
	
	//后台首页,报警总数
	public Integer alarmHTCount(CommunityParams pa);
	
	//工单概况
	public Map<String , Object> worderGk(CommunityParams coummunityParams) throws HKException;
	
	//工单后台首页
	 public PendingSituationUM backWorder(CommunityParams coummunityParams) throws HKException;
	 	
	//工单后台首页,工单总数
	public Integer WorderHTCount(CommunityParams coummunityParams) throws HKException;
		
	//工单后台首页,工单情况
	public Map<String, Object> WorderHTStatus(CommunityParams coummunityParams) throws HKException;
	
	//综合作业,工单列表,及时处理率
	Map<String, Object> WorderZYList(CommunityParams pa)throws HKException;
	
	//综合作业,设备感知,按时间段统计
	List<Integer[]> AlarmZYList(CommunityParams pa)throws HKException;
	
	//综合作业,累计报警,今日报警,未处理报警,未处理工单,超时工单
	Map<String, Object> AlarmZYAllCount(CommunityParams pa)throws HKException;
	
	//态势分析,实时报警
	Map<String, Object> AlarmSTRealTimeAlerts(CommunityParams pa)throws HKException;
	
	//态势分析,总报警,一级报警,处理及时率 
	Map<String, Object> AlarmSTAlarmAllCount(CommunityParams pa)throws HKException;
	
	Map<String, Object> AlarmWorkMainInfo(AlarmWorkPA pa)throws HKException;
	
	List<Map<String, Object>> workMainInfo(WorkPA pa)throws HKException;
	
	List<Map<String, Object>> alarmMainInfo(WorkPA pa)throws HKException;
	
	List<AlarmUserLngLatUM> UserLngLat(CommunityParams pa) throws HKException;
	
	Map<String, Object> alarmMainWorkDetail(AlarmDetailPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	Map<String, Object> workMainWorkDetail(AlarmDetailPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	List<Map<String, String>> getHeatPointInfo(WorkPA pa)throws HKException;
}
