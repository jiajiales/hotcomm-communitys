package com.hotcomm.community.api.test.query.test;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CarApiTest extends CommunityTest {
	
	public static final Logger ROOT = LoggerFactory.getLogger(CommunityTest.class);
	
	/*************************车辆总况****************************/	
	@Test
	public void getCarStsCount() throws HKException{
		ROOT.info("查询车辆总况统计数");
		this.postAfterResult(new ArrayList<>(),"car/statistical/count");
	}
	
	@Test
	public void getCarStsTypeCount() throws HKException{
		ROOT.info("查询登记车辆结构总数");
		this.postAfterResult(new ArrayList<>(),"car/statistical/typeCount");
	}
	
	@Test
	public void getCarStsMonTimeCount() throws HKException{
		ROOT.info("查询上个月份各时段进出车辆总数");
		this.postAfterResult(new ArrayList<>(),"car/statistical/monTimeCount");
	}
	
	@Test
	public void getCarStsParkingCount() throws HKException{
		ROOT.info("查询本月停车情况");
		this.postAfterResult(new ArrayList<>(),"car/statistical/parkingCount");
	}
	
	@Test
	public void getCarStsAttentionCount() throws HKException{
		ROOT.info("本月关注车辆通行记录统计");
		this.postAfterResult(new ArrayList<>(),"car/statistical/attentionCount");
	}
	
	@Test
	public void getCarStsAlarmLevelCount() throws HKException{
		ROOT.info("本月关注车辆预警级别统计");
		this.postAfterResult(new ArrayList<>(),"car/statistical/alarmLevelCount");
	}
	
	@Test
	public void getCarStsAlarmDayCount() throws HKException{
		ROOT.info("月报警统计（报警趋势）");
		this.postAfterResult(new ArrayList<>(),"car/statistical/AlarmDayCount");
	}
	
	/*************************陌生车辆****************************/
	@Test
	public void getCarStrPageList() throws HKException{
		ROOT.info("陌生车辆分页列表");
		this.postAfterResult(new ArrayList<>(),"car/carStr/pageList");
	}
	
	@Test
	public void getCarStrDetail() throws HKException{
		ROOT.info("陌生车辆详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"car/carStr/detail");
	}
	
	/*************************登记车辆****************************/
	@Test
	public void getCarRegDetail() throws HKException{
		ROOT.info("登记车辆详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"car/carReg/detail");
	}
	
	@Test
	public void getCarRegList() throws HKException{
		ROOT.info("登记车辆分页查询");
		this.postAfterResult(new ArrayList<>(),"car/carReg/pageList");
	}
	
	/*************************车辆标签****************************/	
	@Test
	public void getCarLabelPageList() throws HKException{
		ROOT.info("分页查询车辆标签列表");
		this.postAfterResult(new ArrayList<>(),"car/label/pageList");
	}
	
	@Test
	public void getCarLabelDetail() throws HKException{
		ROOT.info("查看车辆标签详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"car/label/detail");
	}
	
	@Test
	public void getCarLabelList() throws HKException{
		ROOT.info("查询车辆标签列表");
		this.postAfterResult(new ArrayList<>(),"car/label/list");
	}
	
	/*************************车辆通行记录****************************/	
	@Test
	public void getCarPassPageList() throws HKException{
		ROOT.info("分页查询车辆通行记录列表");
		this.postAfterResult(new ArrayList<>(),"car/pass/pageList");
	}
	
	@Test
	public void getCarPassDetail() throws HKException{
		ROOT.info("查询车辆通行记录详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"car/pass/detail");
	}
	
	/*************************预警规则****************************/	
	@Test
	public void getCarRulePageList() throws HKException{
		ROOT.info("分页查询车辆预警规则列表");
		this.postAfterResult(new ArrayList<>(),"car/rule/pageList");
	}
	
	@Test
	public void getCarRuleDetail() throws HKException{
		ROOT.info("查询车辆预警规则详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("ruleId", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"car/rule/detail");
	}
	
	/*************************神经感知****************************/	
	@Test
	public void getCarFeelInfoCount() throws HKException{
		ROOT.info("车辆神经感知基础信息统计");
		this.postBeforeResult(new ArrayList<>(),"car/feel/infoCount");
	}
	
	@Test
	public void getCarFeelCarPassCount() throws HKException{
		ROOT.info("按小时统计今日车辆通行次数、车辆数");
		this.postBeforeResult(new ArrayList<>(),"car/feel/carPassCount");
	}
	
	@Test
	public void getCarFeelAlarmTypeCount() throws HKException{
		ROOT.info("查询车辆当天不同报警类型数");
		this.postBeforeResult(new ArrayList<>(),"car/feel/alarmTypeCount");
	}
	
	@Test
	public void getCarFeelPassDayCount() throws HKException{
		ROOT.info("查询当天通行车辆数、通行次数");
		this.postBeforeResult(new ArrayList<>(),"car/feel/passDayCount");
	}
	
	@Test
	public void getCarFeelAlarmList() throws HKException{
		ROOT.info("查询未处理的报警记录、本周报警次数");
		this.postBeforeResult(new ArrayList<>(),"car/feel/alarmList");
	}
	
	/*************************态势分析****************************/	
	@Test
	public void getCarPosTodayCount() throws HKException{
		ROOT.info("查询当天车辆进、出次数、外来车辆总数、登记车辆总数、今日黑名单总数");
		this.postBeforeResult(new ArrayList<>(),"car/pos/todayCount");
	}
	
	@Test
	public void getCarPosEnterOutCount() throws HKException{
		ROOT.info("按小时查询最月、年车辆进、出次数");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("timeType", EntityEnum.TEXT, 0));
		this.postBeforeResult(requestMap,"car/pos/enterOutCount");
	}
	
	@Test
	public void getCarPosParkingCount() throws HKException{
		ROOT.info("按天数查询最一个月、一年停车车辆数");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("timeType", EntityEnum.TEXT, 0));
		this.postBeforeResult(requestMap,"car/pos/parkingCount");
	}
	
	@Test
	public void getCarPosAttentionCount() throws HKException{
		ROOT.info("统计关注车辆次数");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("timeType", EntityEnum.TEXT, 0));
		this.postBeforeResult(requestMap,"car/pos/attentionCount");
	}
	
	@Test
	public void getCarPosAlarmCount() throws HKException{
		ROOT.info("报警级别统计");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("timeType", EntityEnum.TEXT, 0));
		this.postBeforeResult(requestMap,"car/pos/alarmCount");
	}
	
	//请求后台接口
	private void postAfterResult(List<HotHttpEntity> requestMap,String url){
        requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
        requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
        requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
        url = host +url;
		checkHttpResponse(HttpClientUtils.post(requestMap,url));
	}
	
	//请求大屏幕接口
	private void postBeforeResult(List<HotHttpEntity> requestMap,String url){
        requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
        requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
        requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		url = host +url;
		checkHttpResponse(HttpClientUtils.post(requestMap,url));
	}
}
