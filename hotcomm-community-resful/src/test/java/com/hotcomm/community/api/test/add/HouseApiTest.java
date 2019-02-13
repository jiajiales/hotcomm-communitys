package com.hotcomm.community.api.test.add;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.framework.utils.RandomNum;
import com.hotcomm.framework.utils.http.*;
import com.hotcomm.framework.web.exception.HKException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HouseApiTest extends CommunityTest {
	//房屋基础数据新增，楼栋、单元、楼层、房间
	@Test
	public void addData() throws HKException {
                                
		/*// 新增社区                                   
		List<HotHttpEntity> params1 = new ArrayList<>();
		params1.add(new HotHttpEntity("cname", EntityEnum.TEXT, "新中银.金色华庭"));
		params1.add(new HotHttpEntity("code", EntityEnum.TEXT, "JSHT001001"));
		params1.add(new HotHttpEntity("provinceNo", EntityEnum.TEXT, "483250"));
		params1.add(new HotHttpEntity("cityNo", EntityEnum.TEXT, "506616"));
		params1.add(new HotHttpEntity("streetNo", EntityEnum.TEXT, "506643"));
		params1.add(new HotHttpEntity("villageNo", EntityEnum.TEXT, "506656"));
		params1.add(new HotHttpEntity("caddress", EntityEnum.TEXT, "东莞市新中路1号"));
		params1.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.750092"));
		params1.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994802"));
		params1.add(new HotHttpEntity("linkUser", EntityEnum.TEXT, "李加陈"));
		params1.add(new HotHttpEntity("linkPhone", EntityEnum.TEXT, "18782906523"));
		params1.add(new HotHttpEntity("outlineCoords", EntityEnum.TEXT,
				"113.74972,22.992276;113.747622,22.993501;113.750278,22.996957;113.752413,22.995506"));
		params1.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params1.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.doPost(params1, host + "/community/add");
		// 查询社区
		List<HotHttpEntity> params2 = new ArrayList<>();
		params2.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params2.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HotHttpResponse response = HttpClientUtils.doPost(params2, host + "/community/listAll");
		if (!response.isSuccess()) {
			throw new HKException("", "执行失败");
		}
		String json = response.getReturnJson();
		List<CommunityListAllUM> list = HotHttpParse.parseJsonToObject(json,
				new TypeReference<List<CommunityListAllUM>>() {
				});
		// List<CommunityListUM> list2 = list.stream().filter((CommunityListUM
		// um)->um.getCname().equals("松湖悦府")).collect(Collectors.toList());
		if (list.size() == 0) {
			throw new HKException("", "执行失败");
		}
		
		//一栋
		List<HotHttpEntity> building1 = new ArrayList<>();
		building1.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building1.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building1.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building1.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"1栋"));
		building1.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号1栋"));
		building1.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building1.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.750457"));
		building1.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.99645"));
		building1.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building1.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		HttpClientUtils.doPost(building1, host + "/house/building/addData");
		// 查询新增楼栋列表
		List<HotHttpEntity> params4 = new ArrayList<>();
		params4.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params4.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		params4.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		HotHttpResponse response2 = HttpClientUtils.doPost(params4, host + "/house/building/getBuildingList");
		String json2 = response2.getReturnJson();
		List<BuildingListUM> buildings = HotHttpParse.parseJsonToObject(json2,
				new TypeReference<List<BuildingListUM>>() {
				});
		if (buildings.size() == 0) {
			throw new HKException("", "执行失败");
		}
		List<BuildingListUM> um1 = buildings.stream().filter((BuildingListUM um)->um.getBuildingName().equals("1栋")).collect(Collectors.toList());
		//添加单元
		//一单元
		List<HotHttpEntity> unit1 = new ArrayList<>();
		unit1.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		unit1.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		unit1.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		unit1.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, um1.get(0).getId()));
		unit1.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "01"));
		unit1.add(new HotHttpEntity("unitName", EntityEnum.TEXT,"1单元"));
		unit1.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		HttpClientUtils.doPost(unit1, host + "/house/unit/addData");
		//2单元
		List<HotHttpEntity> unit2 = new ArrayList<>();
		unit2.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		unit2.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		unit2.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		unit2.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, um1.get(0).getId()));
		unit2.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "02"));
		unit2.add(new HotHttpEntity("unitName", EntityEnum.TEXT,"2单元"));
		unit2.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		HttpClientUtils.doPost(unit1, host + "/house/unit/addData");
		
		
		
		
		
		
		//2栋
		List<HotHttpEntity> building2 = new ArrayList<>();
		building2.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building2.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building2.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building2.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"2栋"));
		building2.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号2栋"));
		building2.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building2.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.751583"));
		building2.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.99526"));
		building2.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building2.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//3栋
		List<HotHttpEntity> building3 = new ArrayList<>();
		building3.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building3.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building3.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building3.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"3栋"));
		building3.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号3栋"));
		building3.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building3.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.751374"));
		building3.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.99481"));
		building3.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building3.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//4栋
		List<HotHttpEntity> building4 = new ArrayList<>();
		building4.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building4.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building4.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building4.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"4栋"));
		building4.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号4栋"));
		building4.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building4.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.750355"));
		building4.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.993813"));
		building4.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building4.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//5栋
		List<HotHttpEntity> building5 = new ArrayList<>();
		building5.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building5.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building5.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building5.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"5栋"));
		building5.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号5栋"));
		building5.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building5.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749893"));
		building5.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.993364"));
		building5.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building5.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//6栋
		List<HotHttpEntity> building6 = new ArrayList<>();
		building6.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building6.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building6.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building6.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"6栋"));
		building6.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号6栋"));
		building6.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building6.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749555"));
		building6.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.992978"));
		building6.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building6.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//7栋
		List<HotHttpEntity> building7 = new ArrayList<>();
		building7.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building7.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building7.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building7.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"7栋"));
		building7.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号7栋"));
		building7.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building7.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.748917"));
		building7.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.993285"));
		building7.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building7.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//8栋
		List<HotHttpEntity> building8 = new ArrayList<>();
		building8.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building8.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building8.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building8.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"8栋"));
		building8.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号8栋"));
		building8.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building8.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.748252"));
		building8.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.993611"));
		building8.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building8.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//9栋
		List<HotHttpEntity> building9 = new ArrayList<>();
		building9.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building9.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building9.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building9.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"9栋"));
		building9.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号9栋"));
		building9.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building9.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.748729"));
		building9.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994099"));
		building9.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building9.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//10栋
		List<HotHttpEntity> building10 = new ArrayList<>();
		building8.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building8.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building8.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building8.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"10栋"));
		building8.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号10栋"));
		building8.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building8.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749529"));
		building8.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.993996"));
		building8.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building8.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//11栋
		List<HotHttpEntity> building11 = new ArrayList<>();
		building11.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building11.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building11.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building11.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"11栋"));
		building11.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号11栋"));
		building11.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building11.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749035"));
		building11.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994598"));
		building11.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building11.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//12栋
		List<HotHttpEntity> building12 = new ArrayList<>();
		building12.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building12.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building12.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building12.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"12栋"));
		building12.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号12栋"));
		building12.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building12.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749942"));
		building12.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.99405"));
		building12.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building12.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//13栋
		List<HotHttpEntity> building13 = new ArrayList<>();
		building13.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building13.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building13.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building13.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"13栋"));
		building13.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号13栋"));
		building13.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building13.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749357"));
		building13.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994959"));
		building13.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building13.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//14栋
		List<HotHttpEntity> building14 = new ArrayList<>();
		building14.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building14.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building14.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building14.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"14栋"));
		building14.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号14栋"));
		building14.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building14.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.75021"));
		building14.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994391"));
		building14.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building14.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//15栋
		List<HotHttpEntity> building15 = new ArrayList<>();
		building15.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building15.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building15.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building15.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"15栋"));
		building15.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号15栋"));
		building15.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building15.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.750146"));
		building15.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.994786"));
		building15.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building15.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//16栋
		List<HotHttpEntity> building16 = new ArrayList<>();
		building16.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building16.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building16.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building16.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"16栋"));
		building16.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号16栋"));
		building16.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building16.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.74969"));
		building16.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.995408"));
		building16.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building16.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//17栋
		List<HotHttpEntity> building17 = new ArrayList<>();
		building17.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building17.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building17.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building17.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"11栋"));
		building17.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号11栋"));
		building17.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building17.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.749909"));
		building17.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.995729"));
		building17.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building17.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		//18栋
		List<HotHttpEntity> building18 = new ArrayList<>();
		building18.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		building18.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		building18.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		building18.add(new HotHttpEntity("buildingName", EntityEnum.TEXT,"18栋"));
		building18.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞市新中路1号18栋"));
		building18.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
		building18.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.750253"));
		building18.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.996233"));
		building18.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
		building18.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		List<List<HotHttpEntity>> arry = new ArrayList<>();
		arry.add(building1);
		arry.add(building2);
		arry.add(building3);
		arry.add(building4);
		arry.add(building5);
		arry.add(building6);
		arry.add(building7);
		arry.add(building8);
		arry.add(building9);
		arry.add(building10);
		arry.add(building11);
		arry.add(building12);
		arry.add(building13);
		arry.add(building14);
		arry.add(building15);
		arry.add(building16);
		arry.add(building17);
		arry.add(building18);
		// 楼栋新增
		for (List<HotHttpEntity> list2 : arry) {
			HttpClientUtils.doPost(list2, host + "/house/building/addData");
		}
		// 查询新增楼栋列表
		List<HotHttpEntity> params4 = new ArrayList<>();
		params4.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		params4.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		params4.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		HotHttpResponse response2 = HttpClientUtils.doPost(params4, host + "/house/building/getBuildingList");
		String json2 = response2.getReturnJson();
		List<BuildingListUM> buildings = HotHttpParse.parseJsonToObject(json2,
				new TypeReference<List<BuildingListUM>>() {
				});
		if (buildings.size() == 0) {
			throw new HKException("", "执行失败");
		}
		List<BuildingListUM> um1 = buildings.stream().filter((BuildingListUM um)->um.getBuildingName().equals("1栋")).collect(Collectors.toList());
		List<HotHttpEntity> unit1 = new ArrayList<>();
		unit1.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		unit1.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		unit1.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
		unit1.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, um1.get(0).getId()));
		unit1.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "01"));
		unit1.add(new HotHttpEntity("unitName", EntityEnum.TEXT,"1单元"));
		unit1.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
		HttpClientUtils.doPost(unit1, host + "/house/unit/addData");
		
		
		
		
		
		// 单元新增
		for (BuildingListUM buildingListUM : buildings) {
			for (int i = 1; i < 4; i++) {
				List<HotHttpEntity> params5 = new ArrayList<>();
				params5.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
				params5.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
				params5.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
				params5.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
				params5.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "0" + i));
				params5.add(new HotHttpEntity("unitName", EntityEnum.TEXT, i + "单元"));
				params5.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
				HttpClientUtils.doPost(params5, host + "/house/unit/addData");
			}
			// 查询单元列表
			List<HotHttpEntity> params6 = new ArrayList<>();
			params6.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params6.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params6.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
			params6.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
			HotHttpResponse response3 = HttpClientUtils.doPost(params6, host + "/house/unit/getDataList");
			String json3 = response3.getReturnJson();
			List<UnitUM> list3 = HotHttpParse.parseJsonToObject(json3, new TypeReference<List<UnitUM>>() {
			});
			if (list3.size() == 0) {
				throw new HKException("", "执行失败");
			}
			for (UnitUM unitUM : list3) {
				// 楼层新增
				for (int j = 1; j < 10; j++) {
					List<HotHttpEntity> params7 = new ArrayList<>();
					params7.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
					params7.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
					params7.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
					params7.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
					params7.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
					params7.add(new HotHttpEntity("floorName", EntityEnum.TEXT, j + "层"));
					params7.add(new HotHttpEntity("floorNum", EntityEnum.TEXT, j));
					params7.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(12, 6)));
					params7.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
					params7.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
					params7.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, RandomNum.getNum(120, 85)));
					params7.add(new HotHttpEntity("useArea", EntityEnum.TEXT, RandomNum.getNum(120, 85)));
					params7.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
					HttpClientUtils.doPost(params7, host + "/house/floors/addData");
				}
				// 查询楼层列表
				List<HotHttpEntity> params8 = new ArrayList<>();
				params8.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
				params8.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
				params8.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
				params8.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
				HotHttpResponse response4 = HttpClientUtils.doPost(params8, host + "/house/floors/getFloorList");
				List<FloorsListUM> list4 = HotHttpParse.parseJsonToObject(response4.getReturnJson(),
						new TypeReference<List<FloorsListUM>>() {
						});
				if (list4.size() == 0) {
					throw new HKException("", "执行失败");
				}
				for (FloorsListUM floorsListUM : list4) {
					// 房间新增
					for (int i = 1; i < 4; i++) {
						List<HotHttpEntity> params9 = new ArrayList<>();
						params9.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
						params9.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
						params9.add(new HotHttpEntity("communityId", EntityEnum.TEXT, list.get(0).getCid()));
						params9.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, buildingListUM.getId()));
						params9.add(new HotHttpEntity("unitId", EntityEnum.TEXT, unitUM.getId()));
						params9.add(new HotHttpEntity("floorId", EntityEnum.TEXT, floorsListUM.getId()));
						params9.add(new HotHttpEntity("roomName", EntityEnum.TEXT,
								floorsListUM.getFloorName().substring(0, 1) + "0" + i));
						params9.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
						params9.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, RandomNum.getNum(120, 85)));
						params9.add(new HotHttpEntity("useArea", EntityEnum.TEXT, RandomNum.getNum(120, 85)));
						params9.add(new HotHttpEntity("attribute", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
						params9.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, RandomNum.getNum(2, 1)));
						params9.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
						HttpClientUtils.doPost(params9, host + "/house/room/addData");
					}
				}

			}
		}
	}
	
	// 关注场所新增----危险场所
	@Test
	public void addPlace() throws HKException {
		String[] str={"煤气站","满天星爆竹厂","星星网吧","梦之蓝酒吧","OO作坊"};
		String url = host + "/house/watchPlace/addData";
		for (int i = 1; i < 11; i++) {
			List<HotHttpEntity> params = new ArrayList<>();
			params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("ways", EntityEnum.TEXT, 1));
			int num = RandomNum.getNum(4, 0);
			params.add(new HotHttpEntity("placeName", EntityEnum.TEXT, str[num]));
			params.add(new HotHttpEntity("placeType", EntityEnum.TEXT, num+17));
			params.add(new HotHttpEntity("dangerLevel", EntityEnum.TEXT, RandomNum.getNum(3, 1)));
			params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, i));
			params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (i-1)*3+1));
			//params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, "李加陈"));
			//params.add(new HotHttpEntity("roomId", EntityEnum.TEXT, ));
			params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
			params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
			params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
			params.add(new HotHttpEntity("placeAddress", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府" + i + "栋"));
			HttpClientUtils.post(params, url);
		}
	}

	// 关注场所新增----服务场所
	@Test
	public void addServicePlace() throws HKException {
		String[] str={"松湖避难所","儿童中心医院","中老年调养院","灾害救助中心","孤寡老人养老中心","松湖孤儿院","松湖消防指挥中心","松湖残疾人救助中心","松湖居委会"};
		String url = host + "/house/watchPlace/addData";
		for (int i = 1; i < 11; i++) {
			List<HotHttpEntity> params = new ArrayList<>();
			params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
			params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
			params.add(new HotHttpEntity("ways", EntityEnum.TEXT, 2));
			int num = RandomNum.getNum(8, 0);
			params.add(new HotHttpEntity("placeName", EntityEnum.TEXT, str[num]));
			params.add(new HotHttpEntity("placeType", EntityEnum.TEXT, 22+num));
			//params.add(new HotHttpEntity("dangerLevel", EntityEnum.TEXT, 3));
			params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, i));
			params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (i-1)*3+1));
			//params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, "李加陈"));
			//params.add(new HotHttpEntity("roomId", EntityEnum.TEXT, ));
			params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
			params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
			params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
			params.add(new HotHttpEntity("placeAddress", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府" + i + "栋"));
			HttpClientUtils.post(params, url);
		}
	}
	
*/
	//// 楼栋新增
	//@Test
	//public void addBuilding() throws HKException {
	//	String url = host + "/house/building/addData";
	//	for (int i = 1; i < 11; i++) {
	//		List<HotHttpEntity> params = new ArrayList<>();
	//		params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
	//		params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
	//		params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
	//		params.add(new HotHttpEntity("buildingName", EntityEnum.TEXT, i + "栋"));
	//		params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府" + i + "栋"));
	//		params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
	//		params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
	//		params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
	//		params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 2));
	//		params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
	//		HttpClientUtils.post(params, url);
	//	}
	//}
    //
	//// 单元新增
	//@Test
	//public void addUnit() throws HKException {
	//	String url = host + "/house/unit/addData";
	//	for (int i = 1; i < 11; i++) {
	//		for (int j = 1; j < 4; j++) {
	//			List<HotHttpEntity> params = new ArrayList<>();
	//			params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
	//			params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
	//			params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
	//			params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, i));
	//			params.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, "0"+j));
	//			params.add(new HotHttpEntity("unitName", EntityEnum.TEXT, j + "单元"));
	//			params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
	//			HttpClientUtils.post(params, url);
	//		}
	//	}
	//}
    //
	//// 楼层新增
	//@Test
	//public void addfloors() throws HKException {
	//	String url = host + "/house/floors/addData";
	//	for (int a = 1; a < 11; a++) {
	//		for (int i = 1; i < 4; i++) {
	//			for (int j = 1; j < 10; j++) {
	//				List<HotHttpEntity> params = new ArrayList<>();
	//				params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
	//				params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
	//				params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
	//				params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, a));
	//				params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (a-1)*3+i));
	//				params.add(new HotHttpEntity("floorName", EntityEnum.TEXT, j + "层"));
	//				params.add(new HotHttpEntity("floorNum", EntityEnum.TEXT, j));
	//				params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 6));
	//				params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
	//				params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
	//				params.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
	//				params.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 95));
	//				params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
	//				HttpClientUtils.post(params, url);
	//			}
	//		}
	//	}
	//}
    //
	//// 房间新增
	//@Test
	//public void addRooms() throws HKException {
	//	String url = host + "/house/room/addData";
	//	for (int a =1; a < 11; a++) {
	//		for (int b = 0; b < 4; b++) {//4
	//			for (int c = 1; c < 10; c++) {//28
	//				for (int i = 1; i < 4; i++) {
	//					List<HotHttpEntity> params = new ArrayList<>();
	//					params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
	//					params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
	//					params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
	//					params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, a));
	//					params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, (a-1)*3+b+1));
	//					params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, ((a-1)*3+b)*9+c));
	//					params.add(new HotHttpEntity("roomName", EntityEnum.TEXT, c+"0" + i));
	//					params.add(new HotHttpEntity("detailedAddr", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
	//					params.add(new HotHttpEntity("constructArea", EntityEnum.TEXT, 100));
	//					params.add(new HotHttpEntity("useArea", EntityEnum.TEXT, 96));
	//					params.add(new HotHttpEntity("attribute", EntityEnum.TEXT, 1));
	//					params.add(new HotHttpEntity("rentOrSale", EntityEnum.TEXT, 1));
	//					params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
	//					HttpClientUtils.post(params, url);
	//				}
	//			}
	//		}
	//	}
	//}
	//
	//// 关注场所新增
	//@Test
	//public void addPlace() throws HKException {
	//	String url = host + "/house/watchPlace/addData";
	//	for (int i = 0; i < 4; i++) {
	//		List<HotHttpEntity> params = new ArrayList<>();
	//		params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
	//		params.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
	//		params.add(new HotHttpEntity("communityId", EntityEnum.TEXT, 1));
	//		params.add(new HotHttpEntity("ways", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("placeName", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("placeType", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("dangerLevel", EntityEnum.TEXT, 3));
	//		params.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("unitId", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("floorId", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("roomId", EntityEnum.TEXT, ""));
	//		params.add(new HotHttpEntity("lon", EntityEnum.TEXT, "113.924521"));
	//		params.add(new HotHttpEntity("lat", EntityEnum.TEXT, "22.96975"));
	//		params.add(new HotHttpEntity("createUser", EntityEnum.TEXT, "李加陈"));
	//		params.add(new HotHttpEntity("placeAddress", EntityEnum.TEXT, "东莞·松山湖CBD·华为南方基地旁,松湖悦府"));
	//		HttpClientUtils.post(params, url);
	//	}
	//}
	}
}
