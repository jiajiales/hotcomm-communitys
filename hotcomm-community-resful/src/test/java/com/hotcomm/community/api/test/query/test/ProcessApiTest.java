package com.hotcomm.community.api.test.query.test;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProcessApiTest extends CommunityTest {

	public static final Logger ROOT = LoggerFactory.getLogger(CommunityTest.class);
	
	//后台首页,工单情况
    @Test
    public void getHTWorder(){
    	String url = host + "/worder/ht";
    	ROOT.info("后台首页,工单情况");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));
    }
    
    //后台首页,报警情况
    @Test
    public void getHTAlarm(){
    	String url = host + "/alarm/back";
    	ROOT.info("后台首页,报警情况");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));
    }
    
    //态势分析(今日报警总数)
    @Test
    public void getSTAlarmAttitude(){
    	String url = host + "/alarm/attitude";
    	ROOT.info("态势分析,今日报警总数");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //态势分析(报警趋势分析)
    @Test
    public void getSTAlarmAttitudeTrend(){
    	String url = host + "/alarm/trend";
    	ROOT.info("态势分析,报警趋势分析");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //态势分析(报警时段分析)
    @Test
    public void getSTAlarmAttitudeHour(){
    	String url = host + "/alarm/hour";
    	ROOT.info("态势分析,报警时段分析");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //态势分析(报警类型分析)
    @Test
    public void getSTAlarmAttitudeType(){
    	String url = host + "/alarm/type";
    	ROOT.info("态势分析,报警类型分析");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //态势分析(未处理报警)
    @Test
    public void getSTAlarmAttitudeNotDeal(){
    	String url = host + "/alarm/notdeal";
    	ROOT.info("态势分析,未处理报警");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //态势分析(累计处理报警总数)
    @Test
    public void getSTAlarmAttitudeDeal(){
    	String url = host + "/alarm/deal";
    	ROOT.info("态势分析,累计处理报警总数");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //报警总况
    @Test
    public void getAlarmCountInfo(){
    	String url = host + "/alarm/countInfo";
    	ROOT.info("报警总况");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //工单概况
    @Test
    public void getwordreGk(){
    	String url = host + "/worder/gk";
    	ROOT.info("工单概况");
       	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
        checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //报警列表分页
    @Test
    public void page(){
    	String url = host + "/alarm/page";
    	ROOT.info("报警列表分页");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("pageIndex",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageSize",EntityEnum.TEXT,10));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageType",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("getCarInfo",EntityEnum.TEXT,2));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //设备维修日志
    @Test
    public void devFixLog(){
    	String url = host + "/dev/fixLog";
    	ROOT.info("设备维修日志");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("devId",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("moduleId",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //设备报警日志
    @Test
    public void devAlmLog(){
    	String url = host + "/dev/almLog";
    	ROOT.info("设备报警日志");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("devId",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("moduleId",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //报警详情
    @Test
    public void AlarmDetail(){
    	String url = host + "/alarm/detail";
    	ROOT.info("报警详情");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("alarmID",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
   
    //报警日志
    @Test
    public void AlarmLog(){
    	String url = host + "/alarm/log";
    	ROOT.info("报警日志");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("alarmID",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //获取事件信息***创建工单用
    @Test
    public void worderGetEvent(){
    	String url = host + "/worder/getEvent";
    	ROOT.info("获取事件信息***创建工单用");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,3));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //获取报警信息***创建工单用
    @Test
    public void worderGetAlarm(){
    	String url = host + "/worder/getAlarm";
    	ROOT.info("获取报警信息***创建工单用");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,4));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //获取设备信息***创建工单用
    @Test
    public void worderGetDev(){
    	String url = host + "/worder/getDev";
    	ROOT.info("获取设备信息***创建工单用");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("queryType",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //前端获取处理时间列表
    @Test
    public void worderGetTime(){
    	String url = host + "/worder/time";
    	ROOT.info("前端获取处理时间列表");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userId",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //前端获取设备模块列表
    @Test
    public void alarmGetDevModule(){
    	String url = host + "/alarm/devModule";
    	ROOT.info("前端获取设备模块列表");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //工单详情
    @Test
    public void worderDetail(){
    	String url = host + "/worder/detail";
    	ROOT.info("工单详情");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("worderId",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //事件详情
    @Test
    public void eventDetail(){
    	String url = host + "/event/detail";
    	ROOT.info("事件详情");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("eventId",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userid",EntityEnum.TEXT,1));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //事件列表
    @Test
    public void eventPage(){
    	String url = host + "/event/page";
    	ROOT.info("事件列表");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageIndex",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageSize",EntityEnum.TEXT,10));
    	requestMap.add(new HotHttpEntity("pageType",EntityEnum.TEXT,0));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //工单列表
    @Test
    public void worderPage(){
    	String url = host + "/worder/page";
    	ROOT.info("工单列表");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageIndex",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("pageSize",EntityEnum.TEXT,10));
    	requestMap.add(new HotHttpEntity("pageType",EntityEnum.TEXT,0));
    	checkHttpResponse(HttpClientUtils.post(requestMap, url));	
    }
    
    //工单概况
    @Test
    public void wordreGk(){
    	String url = host + "/worder/gk";
    	ROOT.info("工单概况");
    	List<HotHttpEntity> requestMap = new ArrayList<>();
    	requestMap.add(new HotHttpEntity("communityId",EntityEnum.TEXT,communityId));
    	requestMap.add(new HotHttpEntity("token",EntityEnum.TEXT,token));
    	requestMap.add(new HotHttpEntity("source",EntityEnum.TEXT,1));
    	requestMap.add(new HotHttpEntity("userId",EntityEnum.TEXT,1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}
    
}
