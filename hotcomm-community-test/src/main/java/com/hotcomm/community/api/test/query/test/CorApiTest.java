package com.hotcomm.community.api.test.query.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

public class CorApiTest extends CommunityTest {

	public static final Logger ROOT = LoggerFactory.getLogger(CommunityTest.class);
	
	/*************************单位总况****************************/	
	@Test
	public void getCorTotalCase() throws HKException{
		ROOT.info("单位总况统计");
		this.postAfterResult(new ArrayList<>(),"corporation/totalCase");
	}
	
	@Test
	public void getCorTotalType() throws HKException{
		ROOT.info("单位类型统计");
		this.postAfterResult(new ArrayList<>(),"corporation/totalType");
	}
	
	@Test
	public void getCorTotalAttention() throws HKException{
		ROOT.info("关注单位统计");
		this.postAfterResult(new ArrayList<>(),"corporation/totalAttention");
	}
	
	@Test
	public void getCorTotalMon() throws HKException{
		ROOT.info("每月登记单位数量");
		this.postAfterResult(new ArrayList<>(),"corporation/totalMon");
	}
	
	/*************************单位列表详情****************************/	
	@Test
	public void getCorPage() throws HKException{
		ROOT.info("分页查询单位列表");
		this.postAfterResult(new ArrayList<>(),"corporation/page");
	}
	
	@Test
	public void getCorDetail() throws HKException{
		ROOT.info("查看单位详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		this.postAfterResult(requestMap,"corporation/detail");
	}
	
	@Test
	public void getCorPersonPage() throws HKException{
		ROOT.info("分页查询单位关联人口列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("corporationId", EntityEnum.TEXT, 1));
		this.postAfterResult(new ArrayList<>(),"corporation/personPage");
	}
	
	@Test
	public void getCorList() throws HKException{
		ROOT.info("查询单位列表");
		this.postAfterResult(new ArrayList<>(),"corporation/list");
	}
	
	@Test
	public void getCorPersonList() throws HKException{
		ROOT.info("查询单位关联人口列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("corId", EntityEnum.TEXT, 1));
		this.postAfterResult(new ArrayList<>(),"corporation/personList");
	}
	
	/*************************单位标签****************************/	
	public void getCorLabelPage() throws HKException{
		ROOT.info("分页查询单位标签列表");
		this.postAfterResult(new ArrayList<>(),"corporation/labelPage");
	}
	
	@Test
	public void getCorLabelDetail() throws HKException{
		ROOT.info("查看单位标签详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("corId", EntityEnum.TEXT, 1));
		this.postAfterResult(new ArrayList<>(),"corporation/labelDetail");
	}
	
	public void getCorLabelTypeList() throws HKException{
		ROOT.info("查询单位标签类型列表");
		this.postAfterResult(new ArrayList<>(),"corporation/labelTypeList");
	}
	
	//请求后台接口
	private void postAfterResult(List<HotHttpEntity> requestMap,String url){
        requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
        requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
        requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
        url = host +url;
        HttpClientUtils.post(requestMap,url);            
	}
	
}
