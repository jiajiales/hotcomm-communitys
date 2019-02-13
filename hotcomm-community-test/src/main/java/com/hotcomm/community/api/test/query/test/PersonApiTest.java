package com.hotcomm.community.api.test.query.test;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonApiTest extends CommunityTest {

    public static final Logger ROOT = LoggerFactory.getLogger(CommunityTest.class);


	/**
	 * 请求后台接口
	 */
	private void postAfterResult(List<HotHttpEntity> requestMap,String url){
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		url = host +url;
		HttpClientUtils.post(requestMap, url);
	}
	/**
	 * 请求大屏幕接口
	 */
	private void postBeforeResult(List<HotHttpEntity> requestMap,String url){
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		url = host +url;
		HttpClientUtils.post(requestMap,url);
	}

	//**********************大数据人口态式**************************
    /**
     * 人口总况概况
     */
    @Test
    public void personSituation(){
        String  url = host +"/population/situation/data";
	    ROOT.info("**********************大数据人口态式**************************");
        ROOT.info("查询 人口总况概况");
        List<HotHttpEntity> requestMap = new ArrayList<>();
        requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
        requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
        requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
        HttpClientUtils.post(requestMap, url);
    }

	/**
	 * 本年通行情况
	 */
	@Test
	public void getPassInfoOfYear(){
		String url =host+ "/population/situation/PassInfoOfYear";
		ROOT.info("本年通行情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		HttpClientUtils.post(requestMap,url);
	}

	/**
	 * 人口趋势
	 */
	@Test
	public void getPopulationTrend(){
		String url =host+ "/population/situation/Trend";
		ROOT.info("人口趋势");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		HttpClientUtils.post(requestMap,url);
	}

	/**
	 * 小区人口概况
	 */
	@Test
	public void getLalePopulationRatio(){
		String url =host+ "/population/situation/Ratio";
		ROOT.info("小区人口概况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		HttpClientUtils.post(requestMap,url);
	}

	/**
	 * 常住人群概况
	 */
	@Test
	public void getPersonNumByfloors(){
		String url =host+ "/population/situation/Floors";
		ROOT.info("常住人群概况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		HttpClientUtils.post(requestMap,url);
	}

	/**
	 * 警报情况
	 */
	@Test
	public void personAlarmCondition(){
		ROOT.info("警报情况");
		this.postBeforeResult(new ArrayList<>(),"/population/situation/AlarmCondition");
	}


	/**
	 * 实时通行情况
	 */
	@Test
	public void getPassRealTime(){
		ROOT.info("实时通行情况");
		this.postBeforeResult(new ArrayList<>(),"/population/pass/realTime");
	}

	//**********************大数据通行感知--人脸感知**************************
	/**
	 * 人脸感知相关信息统计
	 */
	@Test
	public void getPersonFaceRec(){
		ROOT.info("**********************大数据通行感知--人脸感知**************************");
		ROOT.info("人脸感知相关信息统计");
		this.postBeforeResult(new ArrayList<>(),"/person/face/reaction");
	}

	/**
	 * 特殊人群人脸感知情况
	 */
	@Test
	public void getSpecialPersonRec(){
		ROOT.info("特殊人群人脸感知情况");
		this.postBeforeResult(new ArrayList<>(),"/person/special/reaction");
	}

	/**
	 * 今日人脸通行情况
	 */
	@Test
	public void getFaceFellInfoOfDay(){
		ROOT.info("今日人脸通行情况");
		this.postBeforeResult(new ArrayList<>(),"/person/face/feel");
	}

	/**
	 * 本周人脸警报情况
	 */
	@Test
	public void getFaceAlarmLastWeek(){
		ROOT.info("本周人脸警报情况");
		this.postBeforeResult(new ArrayList<>(),"/person/face/alarm/lastWeek");
	}

	/**
	 * 最后50条人脸感知记录
	 */
	@Test
	public void getFaceRecordLast50(){
		ROOT.info("最后50条人脸感知记录");
		this.postBeforeResult(new ArrayList<>(),"/person/face/record/last50");
	}
	//**********************大数据通行感知--通行感知**************************
	/**
	 * 通行基础数据统计
	 */
	@Test
	public void RecordDataStatistics(){
		ROOT.info("**********************大数据通行感知--通行感知**************************");
		ROOT.info("通行基础数据统计");
		this.postBeforeResult(new ArrayList<>(),"/person/record/Reaction");
	}

	/**
	 * 今日通行数据统计
	 */
	@Test
	public void RecordNumberByDay(){
		ROOT.info("今日通行数据统计");
		this.postBeforeResult(new ArrayList<>(),"/person/record/day");
	}

	/**
	 * 今日开门方式统计
	 */
	@Test
	public void RecordTotalByType(){
		ROOT.info("今日开门方式统计");
		this.postBeforeResult(new ArrayList<>(),"/person/record/type");
	}

	/**
	 * 本周人口警报情况
	 */
	@Test
	public void getRecordAlarmLastWeek(){
		ROOT.info("本周人口警报情况");
		this.postBeforeResult(new ArrayList<>(),"/person/record/alarm/lastWeek");
	}

	/**
	 * 最后50条通行记录
	 */
	@Test
	public void getRecordLast50(){
		ROOT.info("最后50条通行记录");
		this.postBeforeResult(new ArrayList<>(),"/person/record/face/last50");
	}

	//**********************后台人口標簽管理**************************
	/**
	 * 获取人口标签列表
	 */
	@Test
	public void populationLablePage(){
		ROOT.info("**********************后台人口標簽管理**************************");
		ROOT.info("获取人口标签列表");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("typeCode", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-01-01"));
		requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, "2019-12-12"));
		requestMap.add(new HotHttpEntity("userName", EntityEnum.TEXT, "关"));

		this.postAfterResult(requestMap,"/population/lable/page");
	}

	/**
	 * 标签详情
	 */
	@Test
	public void populationLableInfo(){
		ROOT.info("标签详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/lable/Info");
	}

	/**
	 * 查询所有标签(下拉框)
	 */
	@Test
	public void selectPopulationLable(){
		ROOT.info("查询所有标签(下拉框)");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		this.postAfterResult(requestMap,"/population/lable/select");
	}

	//**********************居民信息管理**************************
	/**
	 * 居民信息管理
	 */
	@Test
	public void PersonPageList(){
		ROOT.info("**********************居民信息管理**************************");
		ROOT.info("居民信息管理");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("cardType", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-01-01"));
		requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, "2019-12-12"));
		requestMap.add(new HotHttpEntity("dataSource", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("sex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("nation", EntityEnum.TEXT, "汉族"));
		requestMap.add(new HotHttpEntity("lableId", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, "张"));
		requestMap.add(new HotHttpEntity("lableType", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/message/page");
	}

	/**
	 * 居民信息详情
	 */
	@Test
	public void PersonInfo(){
		ROOT.info("居民信息详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/message/Info");
	}

	/**
	 * 陌生人信息(分页)
	 */
	@Test
	public void PersonStrangerPage(){
		ROOT.info("陌生人信息(分页)");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("sex", EntityEnum.TEXT, ""));
		requestMap.add(new HotHttpEntity("beginNum", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("endNum", EntityEnum.TEXT, 100));
		requestMap.add(new HotHttpEntity("dataSource", EntityEnum.TEXT, 2));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, ""));
		requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-01-01"));
		requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, "2019-12-12"));

		this.postAfterResult(requestMap,"/population/stranger/page");
	}

	/**
	 * 陌生人详情
	 */
	@Test
	public void StrangerInfo(){
		ROOT.info("陌生人详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("faceNo", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/stranger/info");
	}

	/**
	 * 房间详情信息
	 */
	@Test
	public void roomDetailsData(){
		ROOT.info("房间详情信息");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/house/room/detailsData");
	}

	/**
	 * 关联人口分页列表
	 */
	@Test
	public void personRoomPageData(){
		ROOT.info("关联人口分页列表");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, ""));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/house/personRoom/pageData");
	}

	//**********************人口警报管理**************************
	/**
	 * 警报列表
	 */
	@Test
	public void populationAlarmPage(){
		ROOT.info("**********************人口警报管理**************************");
		ROOT.info("警报列表");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, ""));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/alarm/page");
	}

	/**
	 * 警报详情
	 */
	@Test
	public void populationAlarmInfo(){
		ROOT.info("警报详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/alarm/Info");
	}

	//**********************人口通行管理**************************
	/**
	 * 通行列表
	 */
	@Test
	public void populationRecordPage(){
		ROOT.info("**********************人口通行管理**************************");
		ROOT.info("通行列表");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, ""));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("lableId", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-01-01"));
		requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, "2018-12-31"));
		requestMap.add(new HotHttpEntity("recordType", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/record/page");
	}

	/**
	 * 通行记录详情
	 */
	@Test
	public void populationRecordInfo(){
		ROOT.info("通行记录详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/record/Info");
	}

	//**********************人口通行管理**************************
	/**
	 * 通行列表
	 */
	@Test
	public void populationHolePage(){
		ROOT.info("**********************人口通行管理**************************");
		ROOT.info("通行列表");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		requestMap.add(new HotHttpEntity("startAge", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("endAge", EntityEnum.TEXT, 100));
		requestMap.add(new HotHttpEntity("type", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("sex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("lableId", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-01-01"));
		requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, "2019-12-31"));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, ""));

		this.postAfterResult(requestMap,"/population/hole/page");
	}

	/**
	 * 布控详情
	 */
	@Test
	public void populationHoleInfo(){
		ROOT.info("布控详情");
		List<HotHttpEntity> requestMap= Lists.newArrayList();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("type", EntityEnum.TEXT, 2));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));

		this.postAfterResult(requestMap,"/population/hole/Info");
	}

	//**********************人口概况管理**************************
	/**
	 * 人口总况概况
	 */
	@Test
	public void populationSituationData(){
		ROOT.info("**********************人口概况管理**************************");
		ROOT.info("人口总况概况");
		this.postAfterResult(new ArrayList<>(),"/population/situation/data");
	}

	/**
	 * 人口结构|人口户籍情况
	 */
	@Test
	public void PersonClassification(){
		ROOT.info("人口结构|人口户籍情况");
		this.postAfterResult(new ArrayList<>(),"/population/situation/Classification");
	}

	/**
	 * 本月通行情况
	 */
	@Test
	public void PersonPassInfoOfMon(){
		ROOT.info("本月通行情况");
		this.postAfterResult(new ArrayList<>(),"/population/situation/PassInfoOfMon");
	}
	/**
	 * 今日通行情况
	 */
	@Test
	public void PersonPassInfoOfDay(){
		ROOT.info("今日通行情况");
		this.postAfterResult(new ArrayList<>(),"/population/situation/PassInfoOfDay");
	}

	/**
	 * 关注人群数
	 */
	@Test
	public void lablePopulationSituation(){
		ROOT.info("关注人群数");
		this.postAfterResult(new ArrayList<>(),"/population/situation/lable");
	}

	/**
	 * 本月报警类别情况
	 */
	@Test
	public void AlarmOfTypeToMonth(){
		ROOT.info("本月报警类别情况");
		this.postAfterResult(new ArrayList<>(),"/population/situation/AlarmType");
	}

}
