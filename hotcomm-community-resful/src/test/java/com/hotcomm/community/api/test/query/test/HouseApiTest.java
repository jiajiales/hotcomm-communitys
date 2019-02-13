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

public class HouseApiTest extends CommunityTest {
	
	public static final Logger ROOT = LoggerFactory.getLogger(HouseApiTest.class);

	@Test
	public void buildingPage() {
		String url = host + "/house/building/pageData";
		ROOT.info("楼栋分页查询");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
		requestMap.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
		//requestMap.add(new HotHttpEntity("minFloors", EntityEnum.TEXT, communityId));
		//requestMap.add(new HotHttpEntity("maxFloors", EntityEnum.TEXT, communityId));
		//requestMap.add(new HotHttpEntity("buildingName", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void buildingDetails() {
		String url = host + "/house/building/detailsData";
		ROOT.info("楼栋详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void buildingList() {
		String url = host + "/house/building/getBuildingList";
		ROOT.info("楼栋列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void unitPage() {
		String url = host + "/house/unit/pageData";
		ROOT.info("单元分页列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, 1));
		//requestMap.add(new HotHttpEntity("startTime", EntityEnum.TEXT, "2018-12-20"));
		//requestMap.add(new HotHttpEntity("endTime", EntityEnum.TEXT, new Date()));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, "1单元"));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void unitList() {
		String url = host + "/house/unit/getDataList";
		ROOT.info("单元列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void unitDetails() {
		String url = host + "/house/unit/detailsData";
		ROOT.info("单元详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void floorPage() {
		String url = host + "/house/floors/pageData";
		ROOT.info("楼层分页");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 8));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, "1栋"));
		requestMap.add(new HotHttpEntity("unitId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void floorDetails() {
		String url = host + "/house/floors/detailsData";
		ROOT.info("楼层详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void floorList() {
		String url = host + "/house/floors/getFloorList";
		ROOT.info("楼层列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("unitId", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void roomPage() {
		String url = host + "/house/room/pageData";
		ROOT.info("房间分页");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("unitId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT, "101"));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void roomList() {
		String url = host + "/house/room/getRoomList";
		ROOT.info("房间列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("floorId", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void roomDetails() {
		String url = host + "/house/room/detailsData";
		ROOT.info("房间详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void roomGetRoomListByPid() {
		String url = host + "/house/room/getRoomListByPid";
		ROOT.info("通过人口ID查询房间列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void personRoomPage() {
		String url = host + "/house/personRoom/pageData";
		ROOT.info("关联人口分页列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("roomId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("reason", EntityEnum.TEXT, 31));
		// requestMap.add(new HotHttpEntity("content", EntityEnum.TEXT,1));
		requestMap.add(new HotHttpEntity("pId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("floorId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void personRoomGetRelations() {
		String url = host + "house/personRoom/getRelations";
		ROOT.info("根据人名查询关联房间信息");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("name", EntityEnum.TEXT, "张三"));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void watchPlacePageData() {
		String url = host + "/house/watchPlace/pageData";
		ROOT.info("关注场所分页");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("ways", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("placeType", EntityEnum.TEXT, 18));
		requestMap.add(new HotHttpEntity("dangerLevel", EntityEnum.TEXT, 3));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void watchPlaceDetails() {
		String url = host + "/house/watchPlace/detailsData";
		ROOT.info("关注场所详情信息");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("ways", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("id", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getHouseStatistics() {
		String url = host + "/house/getHouseStatistics";
		ROOT.info("房屋数据统计，楼栋数、总建筑面积、住宅型房间数、出租屋数、隐患场所、服务场所");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getBuildingStatistics() {
		String url = host + "/house/getBuildingStatistics";
		ROOT.info("房屋类型统计");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getPlaceStatistics() {
		String url = host + "/house/getPlaceStatistics";
		ROOT.info("隐患场所统计、服务场所统计");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("ways", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getPlaceNumData() {
		String url = host + "/house/getPlaceNumData";
		ROOT.info("隐患、服务场所情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getData() {
		String url = host + "/house/getData";
		ROOT.info("出租屋空置率");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}

	@Test
	public void getRentSaleRoom() {
		String url = host + "/house/getRentSaleRoom";
		ROOT.info("住宅型房屋租赁和购买数情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}
	
	@Test
	public void getPlaceList() {
		String url = host + "/house/housePosture/getPlaceList";
		ROOT.info("（房屋态势）获取关注场所列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 0));
		checkHttpResponse(HttpClientUtils.post(requestMap, url));
	}
}
