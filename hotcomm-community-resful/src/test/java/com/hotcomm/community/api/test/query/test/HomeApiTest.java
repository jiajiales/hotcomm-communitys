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

public class HomeApiTest extends CommunityTest {

	public static final Logger LOG = LoggerFactory.getLogger(SystemApiTest.class);

	private void post(List<HotHttpEntity> requestMap, String url) {
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.post(requestMap, host + url);
	}

	@Test
	public void getKPI() {
		LOG.info("获取关键性能指标KPI");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/home/getKPI");
	}

	@Test
	public void getPendingSituation() {
		LOG.info("获取待处理情况");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/home/getPendingSituation");
	}

}
