package com.hotcomm.community.api.test.add;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListUM;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpParse;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class HouseControllerData extends CommunityTest {
	// 社区新增
	@Test
	public void addData() throws HKException {
		//新增社区
		List<HotHttpEntity> params1 = new ArrayList<>();
		params1.add(new HotHttpEntity("cname", EntityEnum.TEXT, "松湖悦府002"));
		params1.add(new HotHttpEntity("code", EntityEnum.TEXT, "SH001002"));
		params1.add(new HotHttpEntity("provinceNo", EntityEnum.TEXT, "483250"));
		params1.add(new HotHttpEntity("cityNo", EntityEnum.TEXT, "506616"));
		params1.add(new HotHttpEntity("regionNo", EntityEnum.TEXT, "507249"));
		params1.add(new HotHttpEntity("caddress", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁"));
		params1.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.92175"));
		params1.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.969219"));
		params1.add(new HotHttpEntity("linkUser", EntityEnum.TEXT, "李加陈"));
		params1.add(new HotHttpEntity("linkPhone", EntityEnum.TEXT, " 18085851101"));
		params1.add(new HotHttpEntity("outlineCoords", EntityEnum.TEXT,
				"113.92175,22.969219;113.923524,22.967305;113.924709,22.969238;113.922356,22.970852"));
		params1.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params1.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.doPost(params1, host + "/community/add");
		//查询社区
		List<HotHttpEntity> params2 = new ArrayList<>();
		params2.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params2.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HotHttpResponse response = HttpClientUtils.doPost(params2, host + "/community/listAll");
		if(!response.isSuccess()) {
			throw new HKException("", "执行失败");
		}
		String json = response.getReturnJson();
		List<CommunityListAllUM> list = HotHttpParse.parseJsonToObject(json, new TypeReference<List<CommunityListAllUM>>(){});
//		List<CommunityListUM> list2 = list.stream().filter((CommunityListUM um)->um.getCname().equals("松湖悦府")).collect(Collectors.toList());
		if (list.size()==0) {
			throw new HKException("", "执行失败");
		}
		//楼栋新增
		for (int i = 1; i < 11; i++) {
			List<HotHttpEntity> params3 = new ArrayList<>();
			params3.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params3.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params3.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
			params3.add(new HotHttpEntity("buildingName", EntityEnum.TEXT, i + "栋"));
			params3.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府" + i + "栋"));
			params3.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
			params3.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
			params3.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
			params3.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 2));
			params3.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
			HotHttpResponse response2 = HttpClientUtils.doPost(params3, host + "/house/building/addData");
			if(!response2.isSuccess()) {
				throw new HKException("", "执行失败");
			}
		}
		//查询新增楼栋列表
		List<HotHttpEntity> params4 = new ArrayList<>();
		params4.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params4.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		params4.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		HotHttpResponse response2 = HttpClientUtils.doPost(params4, host + "/house/building/getBuildingList");
		String json2 = response2.getReturnJson();
		List<BuildingListUM> buildings = HotHttpParse.parseJsonToObject(json2, new TypeReference<List<BuildingListUM>>(){});
		if (buildings.size()==0) {
			throw new HKException("", "执行失败");
		}
		//单元新增
		for (BuildingListUM buildingListUM : buildings) {
			for (int i = 1; i < 4; i++) {
				List<HotHttpEntity> params5 = new ArrayList<>();
				params5.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
				params5.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
				params5.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
				params5.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
				params5.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "0"+i));
				params5.add(new HotHttpEntity("unitName", EntityEnum.TEXT, i + "单元"));
				params5.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
				HttpClientUtils.doPost(params5, host+"/house/unit/addData");
			}
			//查询单元列表
			List<HotHttpEntity> params6 = new ArrayList<>();
			params6.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params6.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params6.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
			params6.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
			HotHttpResponse response3 = HttpClientUtils.doPost(params6, host+"/house/unit/getDataList");
			String json3 = response3.getReturnJson();
			List<UnitUM> list3 = HotHttpParse.parseJsonToObject(json3, new TypeReference<List<UnitUM>>(){});
			if (list3.size()==0) {
				throw new HKException("", "执行失败");
			}
			for (UnitUM unitUM : list3) {
				//楼层新增
				for (int j = 1; j < 10; j++) {
					List<HotHttpEntity> params7 = new ArrayList<>();
					params7.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
					params7.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
					params7.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
					params7.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
					params7.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
					params7.add(new HotHttpEntity("floorName", EntityEnum.TEXT, j + "层"));
					params7.add(new HotHttpEntity("floorNum", EntityEnum.TEXT, j));
					params7.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
					params7.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
					params7.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
					params7.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
					params7.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 95));
					params7.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
					HttpClientUtils.doPost(params7, host+"/house/floors/addData");
				}
				//查询楼层列表
				List<HotHttpEntity> params8 = new ArrayList<>();
				params8.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
				params8.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
				params8.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
				params8.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
				HotHttpResponse response4 = HttpClientUtils.doPost(params8, host+"/house/floors/getFloorList");
				List<FloorsListUM> list4 = HotHttpParse.parseJsonToObject(response4.getReturnJson(), new TypeReference<List<FloorsListUM>>(){});
				if (list4.size()==0) {
					throw new HKException("", "执行失败");
				}
				for (FloorsListUM floorsListUM : list4) {
					//房间新增
					for (int i = 3; i < 4; i++) {
						List<HotHttpEntity> params9 = new ArrayList<>();
						params9.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
						params9.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
						params9.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
						params9.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
						params9.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
						params9.add(new HotHttpEntity("floorId", EntityEnum.TEXT, floorsListUM.getId()));
						params9.add(new HotHttpEntity("roomName", EntityEnum.TEXT, floorsListUM.getFloorName().substring(0, 1)+"0"+ i));
						params9.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
						params9.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
						params9.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 96));
						params9.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 1));
						params9.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
						params9.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
						HttpClientUtils.doPost(params9, host+"/house/room/addData");
					}
				}
				
			}
		}
		
		
		
	}

	// 楼栋新增
	@Test
	public void addBuilding() throws HKException {
		String url = host + "/house/building/addData";
		for (int i = 1; i < 11; i++) {
			List<HotHttpEntity> params = new ArrayList<>();
			params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("buildingName", EntityEnum.TEXT, i + "栋"));
			params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府" + i + "栋"));
			params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
			params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
			params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
			params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 2));
			params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
			HttpClientUtils.post(params, url);
		}
	}

	// 单元新增
	@Test
	public void addUnit() throws HKException {
		String url = host + "/house/unit/addData";
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 4; j++) {
				List<HotHttpEntity> params = new ArrayList<>();
				params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
				params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
				params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
				params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, i));
				params.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "0"+j));
				params.add(new HotHttpEntity("unitName", EntityEnum.TEXT, j + "单元"));
				params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
				HttpClientUtils.post(params, url);
			}
		}
	}

	// 楼层新增
	@Test
	public void addfloors() throws HKException {
		String url = host + "/house/floors/addData";
		for (int a = 1; a < 11; a++) {
			for (int i = 1; i < 4; i++) {
				for (int j = 1; j < 10; j++) {
					List<HotHttpEntity> params = new ArrayList<>();
					params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
					params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
					params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
					params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, a));
					params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (a-1)*3+i));
					params.add(new HotHttpEntity("floorName", EntityEnum.TEXT, j + "层"));
					params.add(new HotHttpEntity("floorNum", EntityEnum.TEXT, j));
					params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
					params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
					params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
					params.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
					params.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 95));
					params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
					HttpClientUtils.post(params, url);
				}
			}
		}
	}

	// 房间新增
	@Test
	public void addRooms() throws HKException {
		String url = host + "/house/room/addData";
		for (int a =1; a < 11; a++) {
			for (int b = 0; b < 4; b++) {//4
				for (int c = 1; c < 10; c++) {//28
					for (int i = 1; i < 4; i++) {
						List<HotHttpEntity> params = new ArrayList<>();
						params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
						params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
						params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
						params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, a));
						params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (a-1)*3+b+1));
						params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, ((a-1)*3+b)*9+c));
						params.add(new HotHttpEntity("roomName", EntityEnum.TEXT, c+"0" + i));
						params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
						params.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
						params.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 96));
						params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 1));
						params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
						params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
						HttpClientUtils.post(params, url);
					}
				}
			}
		}
	}
	
	// 关注场所新增
	@Test
	public void addPlace() throws HKException {
		String url = host + "/house/watchPlace/addData";
		for (int i = 0; i < 4; i++) {
			List<HotHttpEntity> params = new ArrayList<>();
			params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("ways", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("placeName", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("placeType", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("dangerLevel", EntityEnum.TEXT, 3));
			params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("roomId", EntityEnum.TEXT, ""));
			params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
			params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
			params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
			params.add(new HotHttpEntity("placeAddress", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
			HttpClientUtils.post(params, url);
		}
	}

}
