package com.hotcomm.community.process.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.process.AlarmTypeStateDM;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmClosePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmDetailPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmPagePA;
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
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmNotDealListUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmPageUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmTrendUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmUserLngLatUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmZYGetTimeUM;
import com.hotcomm.community.common.bean.ui.process.event.EventPageUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkOrderCountUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkProRateUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkSourceUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkStatusUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderListUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderZYOrderListUM;

public interface ProcessMapper {
	
	//报警列表分页
	Page<AlarmPageUM> alarmPage(@Param("library") Map<String, Object> library, @Param("alarmPagePA") AlarmPagePA pa);

	//设备维修日志
	Page<DevFixLogUM> getDevFixLog(@Param("library") Map<String, Object> library,@Param("devId") Integer devId,@Param("moduleId") Integer moduleId);
	
	//设备报警日志
	Page<DevAlarmLogUM> getDevAlarmLog(@Param("library") Map<String, Object> library,@Param("devId") Integer devId,@Param("moduleId") Integer moduleId);
	
	//检查该报警关闭状态
	@Select("SELECT COUNT(0) FROM ${lib.dynamic_dbname}.hk_alarm alm WHERE alm.`id` =#{alarmID} AND alm.`handel_state` IN (0,1)")
	Integer checkAlarm(@Param("lib") Map<String, Object> lib, @Param("alarmID") Integer alarmID);
	
	//获取设备视图
	@Select("SELECT alm.`alarm_view` FROM ${lib.dynamic_dbname}.hk_alarm alm WHERE alm.`id`=#{alarmID}")
	String getAlarmView(@Param("lib") Map<String, Object> lib, @Param("alarmID") Integer alarmID);

	//关闭报警
	Integer closeAlarm(@Param("lib") Map<String, Object> lib,@Param("pa") AlarmClosePA pa);

	//设置报警默认视图
	@Update("UPDATE ${lib.dynamic_dbname}.hk_alarm alm SET alm.`alarm_view`='{\"view\": {\"closedView\": [{}], \"finishView\": [{}], \"pendingView\": [{}], \"processedView\": [{}]}}' WHERE alm.`id`=#{alarmID}")
	Integer setDefaultViewJson(@Param("lib") Map<String, Object> lib, @Param("alarmID") Integer alarmID);

	//获取报警视图
	Map<String, Object> alarmDetail(@Param("lib") Map<String, Object> lib,@Param("pa") AlarmDetailPA pa);

	//获取报警类型和状态
	AlarmTypeStateDM getAlarmCover(@Param("lib") Map<String, Object> lib, @Param("alarmID") Integer alarmID);

	//新增报警***入库到报警主表
	Integer insertAlarmToMain(AlarmInsertPA alarmInsertPA);

	//新增报警***入库到报警附表
	Integer insertAlarmToVice(@Param("lib") Map<String, Object> lib, @Param("pa") AlarmInsertPA alarmInsertPA);

    //判断该报警入库去向***主表或附表
	List<AlarmInsertPA> checkAlarmLog(@Param("lib") Map<String, Object> lib, @Param("pa") AlarmInsertPA alarmInsertPA);

	//更新报警视图
	@Update("UPDATE ${test.dynamic_dbname}.hk_alarm a SET a.`alarm_view`=#{alarmView} WHERE a.`id` =#{alarmID}")
	Integer upDateViewJson(@Param("test") Map<String, Object> test,@Param("alarmID") Integer alarmID,@Param("alarmView") String alarmView);
	
	//准备报警主表的缩略内容，用于插入报警日志表
	AlarmInsertPA getAlarmMainInfo(@Param("lib") Map<String, Object> lib,@Param("alarmID") Integer alarmID);
	
	//查询报警日志
	List<Map<String, Object>> selectAlarmLogByAlarmId(@Param("lib") Map<String, Object> lib, @Param("alarmId") Integer alarmId);

	//新建工单
	Integer creatWorder(WorderCreatePA pa);

	//查询设备关联的未处理的报警
	List<WorderCreateAlarmInfoPA> getDevRelationAlarm(@Param("queryType") Integer queryType,
			@Param("alarmId") String alarmId, @Param("deviceModule") Integer deviceModule,
			@Param("devId") Integer devId, @Param("library") Map<String, Object> library,@Param("selectStartTime") String selectStartTime,
			@Param("selectEndTime") String selectEndTime,@Param("selectWord") String selectWord);
	//查询需要插入到工单的设备信息（不关联报警的情况下）
	List<WorderCreateDevInfoPA> getDevInfoForCreate(@Param("queryType") Integer queryType,@Param("devId") String devId,
		@Param("deviceModule") Integer deviceModule,@Param("mac") String mac, @Param("library") Map<String, Object> library);
	
	//查询需要插入到工单的事件信息
	List<WorderCreateEventInfoPA> getEventInfo(@Param("queryType") Integer queryType, @Param("devId") Integer devId,
			@Param("deviceModule") Integer deviceModule,@Param("eventStr") String eventStr,
			@Param("library") Map<String, Object> library,@Param("selectStartTime") String selectStartTime,
			@Param("selectEndTime") String selectEndTime,@Param("selectWord") String selectWord
			);
	
	//获取工单时间
	@Select("SELECT t.`id`,t.`time_confine` FROM (SELECT 60 AS id,'一小时内' AS time_confine UNION SELECT 480 AS id,'八小时内' AS time_confine UNION SELECT 1440 AS id,'一天内' AS time_confine UNION SELECT 4320 AS id,'三天内' AS time_confine UNION SELECT 7200 AS id,'其他' AS time_confine) t \r\n" + 
			"")
	List<Map<String, Object>> getWorderTime(@Param("library") Map<String, Object> library);

	//新增报警
	AlarmInsertPA getAlarmDevInfo(@Param("library") Map<String, Object> library);
	
	//获取工单数量
	@Select("SELECT COUNT(0) FROM ${library.dynamic_dbname}.hk_worder w WHERE w.`source_id` = #{sourceId} AND w.`source_type` = #{sourceType} ")
	Integer checkWorderCount(@Param("library") Map<String, Object> library,@Param("sourceId") Integer sourceId,@Param("sourceType") Integer sourceType);
	
	//获取工单详情
	Map<String, Object> worderDetail(@Param("library") Map<String, Object> library,@Param("type") Integer type,@Param("state") Integer state);
	
	//获取设备类型***前端用
	@Select("SELECT m.`id`,m.`module_name` FROM hotcomm_community.hk_device_module m")
	List<Map<String, Object>> getDevModule();

	//获取工单类型
	@Select("SELECT w.`order_status` AS state,w.`source_type` AS type FROM ${library.dynamic_dbname}.hk_worder w WHERE w.`id`=#{wid}")
	WorderDetailPA getWorderStateType(@Param("library") Map<String, Object> library,@Param("wid") Integer wid);
	
	//获取工单详情
	Map<String, Object> getWorderDetail(@Param("library") Map<String, Object> library,@Param("wid") Integer wid,@Param("state") Integer state,@Param("type") Integer type);
	
	//获取事件类型
	@Select("SELECT e.`event_status` AS state,e.`event_type` as type FROM ${library.dynamic_dbname}.hk_event e WHERE e.`id`=#{eid}")
	EventDetailPA getEventStateType(@Param("library") Map<String, Object> library,@Param("eid") Integer eid);
	
	//获取事件详情
	Map<String, Object> getEventDetail(@Param("library") Map<String, Object> library,@Param("eid") Integer eid,@Param("state") Integer state);
	
	//报警总况》统计本月各报警源报警数
	Map<String, Object> getAlarmCountByType(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警来源分布(事件和报警合并后的)
	List<Map<String, Object>> getAlarmCountBySource(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警类型分布(事件和报警合并后的)
	List<Map<String, Object>> getAlarmType(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警类型分布(单独查出本月报警车辆)
	Map<String, Object> getAlarmTypeCar(@Param("lib") Map<String, Object> lib);
	
	//本月报警来源分布
	List<Map<String, Object>> getAlarmCountAndPercentByCase(@Param("lib") Map<String, Object> lib);
	
	//报警总况》报警趋势（环比 于文杰要求改的）
	List<Map<String, Object>> getAlarmTrend(@Param("lib") Map<String, Object> lib); 
	
	//报警总况》报警时段分析（月平均值）
	List<Map<String, Object>> getAlarmCountByHour(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警类型分布
	List<Map<String, Object>> getAlarmCountByDevAlarmType(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月人口报警类型分布(20181203)
	List<Map<String, Object>> getAlarmCountByPerType(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警设备分布
	List<Map<String, Object>> getAlarmCountByDevModule(@Param("lib") Map<String, Object> lib);
	
	//报警总况》本月报警级别分布
	List<Map<String, Object>> getAlarmCountPercentByLevel(@Param("lib") Map<String, Object> lib);
	
	//报警总况》报警趋势（环比）
	List<Map<String, Object>> getCurrentAlarmCountAndLastMonthAlarmCountByDay(@Param("lib") Map<String, Object> lib);
	
	//后台首页》报警情况（设备感知,人工上报,民意舆情）
	List<Map<String, Object>> getAlarmHTCountBySource(@Param("lib") Map<String, Object> lib);
	
	//后台首页》报警情况（统计本周的设备感知和人工上报和民意舆情的次数）
	List<Map<String, Object>> getAlarmHTCountByAllSource(@Param("lib") Map<String, Object> lib);
	
	//后台首页》报警情况（统计本周发生事件次数）
	List<Map<String, Object>> getAlarmHTCountByWeek(@Param("lib") Map<String, Object> lib);
	
	//后台首页》报警情况（报警,单独查出报警车辆的总数,及所占百分比）
	Map<String, Object> getHTAlarmCarCount(@Param("lib") Map<String, Object> lib);
	
	//态势分析》报警趋势分析
	List<AlarmTrendUM> getSTAlarmCountByTrend(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);
	
	//态势分析》报警时段分析
	List<Map<String, Object>> getSTAlarmCountByHour(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);
	
    //态势分析》报警时段分析(饼图)
	List<Map<String, Object>> getSTAlarmCountByPie(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);

	//态势分析》报警类型分布
	List<Map<String, Object>> getSTAlarmCountBySourceType(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);
	
	//态势分析》报警类型分布(单独查出报警车辆统计)
	Map<String, Object> getSTAlarmCountBySourceTypeCar(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);

	//态势分析》未处理报警统计
	List<Map<String, Object>> getSTAlarmCountNotDealCount(@Param("lib") Map<String, Object> lib);

	//态势分析》未处理报警统计列表
	List<AlarmNotDealListUM> getSTAlarmCountNotDealCountList(@Param("lib") Map<String, Object> lib);

	//态势分析》累计处理报警总数
	List<Map<String, Object>> getSTAlarmCountProcess(@Param("lib") Map<String, Object> lib,@Param("queryType")Integer queryType);
	
	//态势分析》今日报警总数
	List<Map<String, Object>> getSTAlarmTodayCount(@Param("lib") Map<String, Object> lib);
	
	//更新报警主表报警时间
	@Select("UPDATE ${lib.dynamic_dbname}.hk_alarm a SET a.`create_time`=NOW() WHERE a.`id`=#{id}")
	void updateAlarmMainTime(@Param("lib") Map<String, Object> lib,@Param("id")Integer id);
	
	//事件列表
	Page<EventPageUM> eventPage(@Param("library") Map<String, Object> library,@Param("eventPagePA") EventPagePA pa);
	
	//根据事件ID查询事件是否被关闭
	@Select("SELECT COUNT(0) FROM ${library.dynamic_dbname}.hk_event e WHERE e.`id` = #{eventId} and e.`event_status` NOT IN(1,2,3,4)")
	Integer checkEvent(@Param("library") Map<String, Object> library,@Param("eventId")Integer eventId);
	
	//根据事件ID获取事件视图
	@Select("SELECT e.`event_view` FROM ${library.dynamic_dbname}.hk_event e WHERE e.`id` = #{eventId}")
	String getEventView(@Param("library") Map<String, Object> library,@Param("eventId")Integer eventId);
	
	//根据事件ID关闭事件
	Integer closeEvent(@Param("library") Map<String, Object> library,@Param("pa")EventClosePA pa);
	
	//生成事件编号
	@Update("UPDATE ${library.dynamic_dbname}.hk_event e SET e.`event_no`=(CASE WHEN e.`id`>=100 THEN CONCAT('event_',e.`id`) WHEN e.`id`<100 AND e.`id`>=10 THEN CONCAT('event_0',e.`id`) WHEN e.`id`<10 THEN CONCAT('event_00',e.`id`) END) WHERE e.`id`=#{id}")
	void updateEventNo(@Param("library") Map<String, Object> library,@Param("id")Integer id);
	
	//生成工单编号
	@Update("UPDATE ${library.dynamic_dbname}.hk_worder w SET w.order_no=(CASE WHEN w.id>=100 THEN CONCAT('worder_',w.id) WHEN w.id<100 AND w.id>=10 THEN CONCAT('worder_0',w.id) WHEN w.id<10 THEN CONCAT('worder_00',w.id) END),w.order_status=1 WHERE w.id=#{id}")
	void updateWorderNo(@Param("library") Map<String, Object> library,@Param("id")Integer id);
	
	//事件创建工单后赋值工单编号到事件表
	@Update("update ${library.dynamic_dbname}.hk_event e set e.worder_id=#{wid},e.event_type=#{eType},e.time_confine=#{eTimeConfine},e.handle_user_id=${worderUser},e.event_status=1 where e.id=#{eid}")
	void updateEventWorderId(@Param("library") Map<String, Object> library,@Param("wid")Integer wid,@Param("eid")Integer eid,@Param("eType")Integer eType,@Param("eTimeConfine")Integer eTimeConfine,@Param("worderUser") Integer handleUserId);

	//事件创建工单后赋值工单编号到事件表
	@Update("update ${library.dynamic_dbname}.hk_event e set e.worder_id=#{wid},e.event_status=1 where e.id=#{eid}")
	void updateEventWorderIdDemo(@Param("library") Map<String, Object> library,@Param("wid")Integer wid,@Param("eid")Integer eid);
	
	//报警创建工单后赋值工单编号到报警主表
	@Update("UPDATE ${library.dynamic_dbname}.hk_alarm a SET a.`worder_no`=(SELECT w.`order_no` FROM ${library.dynamic_dbname}.hk_worder w WHERE w.`id` = #{wid}),a.`wor_id`=#{wid},a.`update_time`=NOW(),a.`update_user`=#{uid},a.`handel_state`=3,a.`is_dispatch`=2 WHERE a.`id`=#{aid}")
	void updateAlarmWorderId(@Param("library") Map<String, Object> library,@Param("wid")Integer wid,@Param("aid")Integer aid,@Param("uid")Integer uid);
	
	//添加事件
	Integer EventNewPA(EventNewPA eventNewPA);
	
	//报警入库后更改设备状态
	@Update("UPDATE ${library.dynamic_dbname}.hk_device d SET d.`state`=(SELECT CASE WHEN s.type IN (1,2) THEN 3 WHEN s.type=3 THEN 2 END FROM hotcomm_community.`hk_alarm_state` s WHERE s.id = #{sid}) WHERE d.`id`=#{did}")
	void updateDevState(@Param("library") Map<String, Object> library,@Param("sid")Integer sid,@Param("did")Integer did);
	
	//获取摄像头经纬度
	@Select("select v.lat,v.lon as lng from ${library.dynamic_dbname}.hk_device_video v where v.mac=#{mac}")
	Map<String, Object> getVideoLngLat(@Param("library") Map<String, Object> library,@Param("mac")String mac);
	
	//获取报警推送人信息
	@Select("SELECT CONCAT(u.`real_name`,u.`telephone`) FROM hotcomm_community.`hk_sys_user` u WHERE u.`user_id`=#{id}")
	String getAlarmPushHandleUserInfo(@Param("library") Map<String, Object> library,@Param("id")Integer id);
	
	//工单列表
	Page<WorderListUM> worderPage(@Param("library") Map<String, Object> library, @Param("worderListPA") WorderListPA pa);
	
	//后台首页,报警总数
	@Select("SELECT a.alarmCount+e.eventCount AS alarmCount FROM (SELECT COUNT(1) AS alarmCount FROM ${library.dynamic_dbname}.hk_alarm) a LEFT JOIN (SELECT COUNT(1) AS eventCount FROM ${library.dynamic_dbname}.hk_event) e ON 1=1")
	Integer getAlarmHTCount(@Param("library") Map<String, Object> library);
	
	//获取本月工单总数,本月待处理工单,本月工单及时处理率
	List<WorderGkUM> getWorderCount(@Param("library") Map<String, Object> library);
		
	//获取本月工单来源分布
	List<WorderGkSourceUM> getWorderSource(@Param("library") Map<String, Object> library);
		
	//获取本月工单处理状态
	List<WorderGkStatusUM> getWorderState(@Param("library") Map<String, Object> library);
		
	//获取本月工单数量
	List<WorderGkOrderCountUM> getWorderdata(@Param("library") Map<String, Object> library);
		
	//获取工单处理及时率趋势
	List<WorderGkProRateUM> getWorderProTrend(@Param("library") Map<String, Object> library);
		
	//获取后台首页待处理工单(总数|百分比)
	PendingSituationUM getWorder(@Param("library") Map<String, Object> library);
		
	//后台首页工单总数
	@Select("SELECT COUNT(1) AS worderCount FROM ${library.dynamic_dbname}.hk_worder w")
	Integer getHTWorderCount(@Param("library") Map<String, Object> library);
		
	//后台首页,工单情况(报警,设备,事件,其他)
	List<Map<String, Object>> getHTWorderSourceCount(@Param("library") Map<String, Object> library);
		
	//后台首页,工单情况(工单总数,待处理,已处理,挂起)
	List<Map<String, Object>> getHTWorderBystatus(@Param("library") Map<String, Object> library);
		
	//后台首页,工单情况(统计每天的处理及时率)
	List<Map<String, Object>> getHTWorderByWeek(@Param("library") Map<String, Object> library);
	
	Map<String, Object> getEventRelationDev(@Param("library") Map<String, Object> library,@Param("id") Integer id);
	
	//综合作业,工单,工单列表,及时处理率
	List<WorderZYOrderListUM> getZYWorderList(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周一)
	List<AlarmZYGetTimeUM> getZYAlarmCountByMonday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周二)
	List<AlarmZYGetTimeUM> getZYAlarmCountByTuesday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周三)
	List<AlarmZYGetTimeUM> getZYAlarmCountByWednesday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周四)
	List<AlarmZYGetTimeUM> getZYAlarmCountByThursday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周五)
	List<AlarmZYGetTimeUM> getZYAlarmCountByFriday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周六)
	List<AlarmZYGetTimeUM> getZYAlarmCountBySaturday(@Param("library") Map<String, Object> library);
	
	//综合作业,设备感知,按时间段统计(周日)
	List<AlarmZYGetTimeUM> getZYAlarmCountBySunday(@Param("library") Map<String, Object> library);
	
	//综合作业,累计报警,今日报警,未处理报警,未处理工单,超时工单
	List<Map<String, Object>> getZYAllCount(@Param("library") Map<String, Object> library);
	
	//态势分析,实时报警
	List<Map<String, Object>> getSTRealTimeAlerts(@Param("library") Map<String, Object> library);
	
	//态势分析,总报警,一级报警,处理及时率
	List<Map<String, Object>> getSTAlarmAllCount(@Param("library") Map<String, Object> library);
	
	List<Map<String, Object>> getDevAlarmInfoForFont(@Param("library") Map<String, Object> library,@Param("moduleId") String moduleId);
	
	List<Map<String, Object>> getPersonAlarmInfoForFontWork(@Param("library") Map<String, Object> library,@Param("alarmSource") String alarmSource);
	
	List<Map<String, Object>> getCarAlarmInfoForFontWork(@Param("library") Map<String, Object> library);
	
	List<Map<String, Object>> getOtherAlarmInfoForFontWork(@Param("library") Map<String, Object> library);
	
	List<Map<String, String>> getHeatPointInfo(@Param("library") Map<String, Object> library);
	
	List<Map<String, Object>> getWorderInfoForFontWork(@Param("library") Map<String, Object> library,@Param("queryType") String queryType);
	
	List<AlarmUserLngLatUM> UserLngLat(@Param("library") Map<String, Object> library);
	
	List<Map<String, Object>> getAlarmWorkMainInfo(@Param("library") Map<String, Object> library,@Param("queryType") String queryType);
	
	Integer getAlarmMainWork(@Param("library") Map<String, Object> library,@Param("aid") Integer aid,@Param("lid") Integer lid);
	
	Map<String, Object> getMainWorkAlarmDevDetail(@Param("library") Map<String, Object> library,@Param("aid") Integer aid);
	
	Map<String, Object> getMainWorkAlarmPersonDetail(@Param("library") Map<String, Object> library,@Param("aid") Integer aid);
	
	Map<String, Object> getMainWorkAlarmCarDetail(@Param("library") Map<String, Object> library,@Param("aid") Integer aid);
	
	Map<String, Object> getMainWorkAlarmOtherDetail(@Param("library") Map<String, Object> library,@Param("aid") Integer aid,@Param("lid") Integer lid);
	
	Map<String, Object> getAlarmWorkMainWorkDetail(@Param("library") Map<String, Object> library,@Param("wid") Integer wid);
	
	String fixDevAlarmMessage(@Param("library") Map<String, Object> library,@Param("aid") Integer aid);
	
}
