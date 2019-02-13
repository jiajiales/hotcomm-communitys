package com.hotcomm.community.resful.controller.console.process;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreatePA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderDetailPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderListPA;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class ProcessController {

	@Autowired
	ProscessService alarmService;
	
	//报警列表分页
	@RequestMapping(value = { RestfulUrlRecord.ALARM_PAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_PAGE_FUN)
	@LogEvent(code = "ALM00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01")})
	public ApiResult page(AlarmPagePA pa) {
		return ApiResult.success(alarmService.alarmPage(pa));
	}
	
	//设备维修日志
	@RequestMapping(value = {RestfulUrlRecord.DEV_FIXLOG}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEV_FIXLOG_FUN)
	public ApiResult devFixLog(DevFixLogPA pa){
		return ApiResult.success(alarmService.getDevFixLog(pa)); 
	}
	
	//设备报警日志
	@RequestMapping(value = {RestfulUrlRecord.DEV_ALMLOG}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEV_ALMLOG_FUN)
	public ApiResult devAlmLog(DevAlarmLogPA pa){
		return ApiResult.success(alarmService.getDevAlarmLog(pa)); 
	}
	
	//关闭报警
	@RequestMapping(value = { RestfulUrlRecord.ALARM_CLOSE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_CLOSE_FUN)
	@LogEvent(code = "ALM00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),@Param(key = "alarmID", code = "ALM_F01")})
	public Map<String, Object> closeAlarm(AlarmClosePA pa) {
		Map<String, Object> info=new HashMap<>();
		info.put("code", 0);
		info.put("data", alarmService.closeAlarm(pa));
		info.put("msg", "关闭成功");
		return info;
	}
	
	//报警详情
	@RequestMapping(value = { RestfulUrlRecord.ALARM_DETAIL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_DETAIL_FUN)
	public ApiResult AlarmDetail(AlarmDetailPA pa) {
		return ApiResult.success(alarmService.alarmDetail(pa));
	}
	
	//新增报警
	@RequestMapping(value = { RestfulUrlRecord.ALARM_INSERT }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_INSERT_FUN)
	public ApiResult AlarmInsert(AlarmInsertPA pa) {
		if (pa.getAlarmType()==1) {
		return ApiResult.success(alarmService.insertAlarmToMainDevice(pa));
		}else {
		return ApiResult.success(alarmService.insertAlarmToMain(pa));
		}
	}
	
	//报警日志
	@RequestMapping(value = { RestfulUrlRecord.ALARM_LOG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_LOG_FUN)
	public ApiResult AlarmLog(AlarmLogPA pa) {
		return ApiResult.success(alarmService.AlarmLog(pa));
	}
	
	//报警总况
	@RequestMapping(value = { RestfulUrlRecord.ALARM_COUNTINFO }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_COUNTINFO_FUN)
	public ApiResult AlarmCountInfo(CommunityParams pa) {
		return ApiResult.success(alarmService.alarmCountInfo(pa));
	}
	
	//创建工单
	@RequestMapping(value = {RestfulUrlRecord.WOD_CREATE}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_CREATE_FUN)
	public Map<String, Object> worderCreate(WorderCreatePA pa){
		Map<String, Object> back=new HashMap<>();
		back.put("code", "0");
		back.put("msg","创建工单成功");
		back.put("data", alarmService.worderCreate(pa));
		return back;
	}
	
	//获取事件信息***创建工单用
	@RequestMapping(value = {RestfulUrlRecord.WOD_GETEVENT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_GETEVENT_FUN)
	public ApiResult worderGetEvent(WorderCreatePA pa){
		return ApiResult.success(alarmService.getEventInfoForCreateWorder(pa)); 
	}
	
	//获取报警信息***创建工单用
	@RequestMapping(value = {RestfulUrlRecord.WOD_GETALARM}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_GETALARM_FUN)
	public ApiResult worderGetAlarm(WorderCreatePA pa){
		return ApiResult.success(alarmService.getAlarmInfoForCreateWorder(pa)); 
	}
	
	//获取设备信息***创建工单用
	@RequestMapping(value = {RestfulUrlRecord.WOD_GETDEV}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_GETDEV_FUN)
	public ApiResult worderGetDev(WorderCreatePA pa){
		return ApiResult.success(alarmService.getDevInfoForCreate(pa)); 
	}
	
	//前端获取处理时间列表
	@RequestMapping(value = {RestfulUrlRecord.WOD_GETTIME}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_GETTIME_FUN)
	public ApiResult worderGetTime(WorderCreatePA pa){
		return ApiResult.success(alarmService.getWorderTime(pa)); 
	}
	
	//前端获取设备模块列表
	@RequestMapping(value = {RestfulUrlRecord.ALARM_DEV_MODULE}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_DEV_MODULE_FUN)
	public ApiResult alarmGetDevModule(){
		return ApiResult.success(alarmService.getDevModule()); 
	}
	
	//工单详情
	@RequestMapping(value = {RestfulUrlRecord.WORDER_DETAIL}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WORDER_DETAIL_FUN)
	public ApiResult worderDetail(WorderDetailPA pa){
		return ApiResult.success(alarmService.worderDetail(pa)); 
	}
	
	//事件详情
	@RequestMapping(value = {RestfulUrlRecord.EVENT_DETAIL}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.EVENT_DETAIL_FUN)
	public ApiResult eventDetail(EventDetailPA pa){
		return ApiResult.success(alarmService.eventDetail(pa)); 
	}
	
	//报警后台首页
	@RequestMapping(value = { RestfulUrlRecord.ALARM_BACK }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_BACK_FUN)
	public ApiResult AlarmBack(CommunityParams pa){
		return ApiResult.success(alarmService.alarmBackCountInfo(pa));	
	}
	
	//态势分析(今日报警总数)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_ATTITUDE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_ATTITUDE_FUN)
	public ApiResult AlarmAttitude(CommunityParams pa) {
		return ApiResult.success(alarmService.alarmSTCountInfo(pa));	
	}
	
	//态势分析(报警趋势分析)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_TREND }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_TREND_FUN)
	public ApiResult AlarmAttitudeTrend(CommunityParams pa,Integer queryType) {
		return ApiResult.success(alarmService.alarmSTTrendUMs(pa, queryType));	
	}
	
	//态势分析(报警时段分析)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_HOUR }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_HOUR_FUN)
	public ApiResult AlarmAttitudeHour(CommunityParams pa,Integer queryType) {
		return ApiResult.success(alarmService.alarmSTAlarmCountByHour(pa, queryType));	
	}
	
	//态势分析(报警类型分析)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_TYPE}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_TYPE_FUN)
	public ApiResult AlarmAttitudeType(CommunityParams pa,Integer queryType) {
		return ApiResult.success(alarmService.alarmSTAlarmCountBySourceType(pa, queryType));	
	}
	
	//态势分析(未处理报警)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_NOTDEAL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_NOTDEAL_FUN)
	public ApiResult AlarmAttitudeNotDeal(CommunityParams pa) {
		return ApiResult.success(alarmService.alarmSTAlarmCountNotDealCount(pa));	
	}
	
	//态势分析(累计处理报警总数)
	@RequestMapping(value = { RestfulUrlRecord.ALARM_DEAL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_DEAL_FUN)
	public ApiResult AlarmAttitudeDeal(CommunityParams pa,Integer queryType) {
		return ApiResult.success(alarmService.alarmSTAlarmCountProcessDealList(pa, queryType));	
	}
	
	//事件列表
	@RequestMapping(value = {RestfulUrlRecord.EVE_PAGE}, method ={ RequestMethod.POST })
	@Function(key = RestfulUrlRecord.EVE_PAGE_FUN)
	public ApiResult page(EventPagePA pa){
		return ApiResult.success(alarmService.eventPage(pa));
	}
	
	//关闭事件
	@RequestMapping(value = {RestfulUrlRecord.EVE_CLOSE}, method ={RequestMethod.POST })
	@Function(key = RestfulUrlRecord.EVE_CLOSE_FUN)
	public Map<String, Object> closeEvent(EventClosePA pa){
		Map<String, Object> info=new HashMap<>();
		info.put("code", "0");
		info.put("msg", alarmService.closeEvent(pa));
		return info;
	}
	
	//添加事件
	@RequestMapping(value = {RestfulUrlRecord.EVE_ADD}, method ={RequestMethod.POST })
	@Function(key = RestfulUrlRecord.EVE_ADD_FUN)
	public Map<String, Object> eventAdd(EventNewPA eventNewPA){
		Map<String, Object> info=new HashMap<>();
		info.put("code", "0");
		info.put("msg", "创建成功");
		info.put("data", alarmService.EventNewPA(eventNewPA));
		return info;
	}
	
	//工单列表
	@RequestMapping(value = { RestfulUrlRecord.WOD_PAGE}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_PAGE_FUN)
	@LogEvent(code = "WOD00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "UA00102")})
	public ApiResult page(WorderListPA pa) {
		return ApiResult.success(alarmService.worderPage(pa));	
	}
	
	//工单概况
	@RequestMapping(value = {RestfulUrlRecord.WOD_GK}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_GK_FUN)
	public ApiResult wordreGk(CommunityParams coummunityParams){
		return ApiResult.success(alarmService.worderGk(coummunityParams));
	}
	
	//后台首页,工单情况
	@RequestMapping(value = {RestfulUrlRecord.WOD_HT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_HT_FUN)
	public ApiResult wordreHT(CommunityParams coummunityParams){
		return ApiResult.success(alarmService.WorderHTStatus(coummunityParams));
	}
	
	//综合作业,工单列表,及时处理率
	@RequestMapping(value = {RestfulUrlRecord.WOD_ZY}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.WOD_ZY_FUN)
	public ApiResult worderZY(CommunityParams coummunityParams) {
		return ApiResult.success(alarmService.WorderZYList(coummunityParams));	
	}
	
	//综合作业,设备感知,按时间段统计
	@RequestMapping(value = {RestfulUrlRecord.ALARM_ZY}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_ZY_FUN)
	public ApiResult alarmZY(CommunityParams coummunityParams) {
		return ApiResult.success(alarmService.AlarmZYList(coummunityParams));
	}
	
	//综合作业,累计报警,今日报警,未处理报警,未处理工单,超时工单
	@RequestMapping(value = {RestfulUrlRecord.ALARM_ZYCOUNT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_ZYCOUNT_FUN)
	public ApiResult alarmZYAllCount(CommunityParams coummunityParams) {
		return ApiResult.success(alarmService.AlarmZYAllCount(coummunityParams));
	}
	
	//态势分析,实时报警
	@RequestMapping(value = {RestfulUrlRecord.ALARM_STREALTIME}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_STREALTIME_FUN)
	public ApiResult alarmSTRealTimeAlerts(CommunityParams pa) {
		return ApiResult.success(alarmService.AlarmSTRealTimeAlerts(pa));
		
	}
	
	//综合作业主屏标点,报警内容
	@RequestMapping(value = {RestfulUrlRecord.ALARM_WFONT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_WFONT_FUN)
	public ApiResult wfont(AlarmWorkPA pa) {
		return ApiResult.success(alarmService.AlarmWorkMainInfo(pa));
	}
	
	//获取人员标点
	@RequestMapping(value = {RestfulUrlRecord.ALARM_USERPOINT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_USERPOINT_FUN)
	public ApiResult UserLngLat(CommunityParams coummunityParams) {
		return ApiResult.success(alarmService.UserLngLat(coummunityParams));
	}
	
	//获取综合作业主屏幕工单信息
	@RequestMapping(value = {RestfulUrlRecord.ALARM_ALARM_MAIN}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_ALARM_MAIN_FUN)
	public ApiResult alarmMainInfo(WorkPA pa) {
		return ApiResult.success(alarmService.alarmMainInfo(pa));
	}
	
	@RequestMapping(value = {RestfulUrlRecord.ALARM_WORK_MAIN}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_WORK_MAIN_FUN)
	public ApiResult workMainInfo(WorkPA pa) {
		return ApiResult.success(alarmService.workMainInfo(pa));
	}
	
	@RequestMapping(value = {RestfulUrlRecord.ALARM_WORK_MAIN_DETAIL}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_WORK_MAIN_DETAIL_FUN)
	public ApiResult alarmMainWorkDetail(AlarmDetailPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return ApiResult.success(alarmService.alarmMainWorkDetail(pa));
	}
	
	@RequestMapping(value = {RestfulUrlRecord.ALARM_WORK_MAIN_DETAIL_WORK}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_WORK_MAIN_DETAIL_WORK_FUN)
	public ApiResult alarmMainWorkDetailWork(AlarmDetailPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return ApiResult.success(alarmService.workMainWorkDetail(pa));
	}
	
	@RequestMapping(value = {RestfulUrlRecord.ALARM_WORK_MAIN_POINT}, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.ALARM_WORK_MAIN_POINT_FUN)
	public ApiResult getHeatPointInfo(WorkPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return ApiResult.success(alarmService.getHeatPointInfo(pa));
	}
	
}
