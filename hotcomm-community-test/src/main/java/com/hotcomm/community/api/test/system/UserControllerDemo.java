package com.hotcomm.community.api.test.system;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpParse;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

public class UserControllerDemo {
	
	final String hostPort = "http://192.168.120.120:8281";
	@Autowired
	MsgServiceImpl msgServiceImpl;
	
	//获取用户列表
	public List<UserListUM> getUserList()throws HKException{
		String url = hostPort +"/user/list";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		if(!response.isSuccess()) {
			throw new HKException("", "执行失败");
		}
		String json = response.getReturnJson();
		return HotHttpParse.parseJsonToObject(json, new TypeReference<List<UserListUM>>(){});
	}  
	
	
	//测试获取用户列表
	@Test 
	public void testGetUserList() {
		String appId="100010";
		String secretKey="3dedc2fe6a6e13eeaaeaf25e5aeefee5";
		String url="http://ddflow.doordu.com/open_api/auth/getToken/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("appId",  EntityEnum.TEXT,appId));
		params.add(new HotHttpEntity("secretKey",  EntityEnum.TEXT,secretKey));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		System.out.println(response.getReturnJson());
	}
	
	//多度获取token
	@Test
	public void getToken() {
		String appId = "100010";
		String secretKey = "3dedc2fe6a6e13eeaaeaf25e5aeefee5";
		String url = "http://ddflow.doordu.com/open_api/auth/getToken/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("appId", EntityEnum.TEXT, appId));
		params.add(new HotHttpEntity("secretKey", EntityEnum.TEXT, secretKey));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		System.out.println(response.getReturnJson());
	}
	
	//添加小区
	@Test
	public void addCommunity() {
		String url="http://ddflow.doordu.com/open_api/h_d/createDep/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("token",  EntityEnum.TEXT,"8ca7304d084e407a80754ed07097f41d"));
		params.add(new HotHttpEntity("agentId",  EntityEnum.TEXT,7342));
		params.add(new HotHttpEntity("depName",  EntityEnum.TEXT,"东莞市东华花园5"));
		params.add(new HotHttpEntity("address",  EntityEnum.TEXT,"东莞市东华花园5"));
		params.add(new HotHttpEntity("remark",  EntityEnum.TEXT,"DH001"));
		params.add(new HotHttpEntity("manageNumber",  EntityEnum.TEXT,"1878290123"));
		params.add(new HotHttpEntity("serverNumber",  EntityEnum.TEXT,"1878290123"));
		params.add(new HotHttpEntity("setupNumber",  EntityEnum.TEXT,"1878290123"));
		params.add(new HotHttpEntity("curKey",  EntityEnum.TEXT,"441900"));
		params.add(new HotHttpEntity("buildingNum",  EntityEnum.TEXT,50));
		params.add(new HotHttpEntity("userNum",  EntityEnum.TEXT,2000));
		params.add(new HotHttpEntity("type",  EntityEnum.TEXT,1));
		params.add(new HotHttpEntity("longitude",  EntityEnum.TEXT,113.391744));
		params.add(new HotHttpEntity("latitude",  EntityEnum.TEXT,22.940563));
		params.add(new HotHttpEntity("managePeople",  EntityEnum.TEXT,"张三"));
		HotHttpResponse response = HttpClientUtils.doPost(params, url);
		System.out.println(response.getReturnJson());
	}
	
	//获取小区
	@Test
	public void getCommunityList() throws NoSuchFieldException, SecurityException {
		String url="http://ddflow.doordu.com/open_api/h_d/getDepList/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("token",  EntityEnum.TEXT,"2131b0296b2848b18617151fa087f96e"));
		params.add(new HotHttpEntity("agentId",  EntityEnum.TEXT,7342));
		HotHttpResponse response = HttpClientUtils.doPost(params, url);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> object = mapper.readValue(response.getReturnJson(), new TypeReference<Map<String, Object>>() {});
			Object object2 = object.get("data");
			@SuppressWarnings("unchecked")
			List<Object> str= (List<Object>) object2;
			for (Object object3 : str) {
				Field field = object3.getClass().getDeclaredField("userName");
				System.out.println(field);
			}
			System.out.println(str.get(0));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	//新增楼栋
	@Test
	public void addBuilding(){
		List<HotHttpEntity> addParam = new ArrayList<>();
		addParam.add(new HotHttpEntity("token",EntityEnum.TEXT,"8ca7304d084e407a80754ed07097f41d"));
		addParam.add(new HotHttpEntity("buildingVo.buildingNo",EntityEnum.TEXT,"05"));
		addParam.add(new HotHttpEntity("buildingVo.buildingName ",EntityEnum.TEXT,"05"));
		addParam.add(new HotHttpEntity("buildingVo.depId",EntityEnum.TEXT,5959));
		HotHttpResponse addPost = HttpClientUtils.doPost(addParam, "http://ddflow.doordu.com/open_api/h_d/createBuild/v1");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> object = mapper.readValue(addPost.getReturnJson(), new TypeReference<Map<String, Object>>() {});
			//Object obj = object.get("data");
			System.out.println(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取楼栋
	@Test
	public void getBuildingList() {
		String url="http://ddflow.doordu.com/open_api/h_d/getBuildingList/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("token",  EntityEnum.TEXT,"8ca7304d084e407a80754ed07097f41d"));
		params.add(new HotHttpEntity("depId",  EntityEnum.TEXT,5947));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> object = mapper.readValue(response.getReturnJson(), new TypeReference<Map<String, Object>>() {});
			Object object2 = object.get("data");
			List<Object> str= (List<Object>) object2;
			System.out.println(str);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//单元新增
	@Test
	public void addUnit() {
		List<HotHttpEntity> param = new ArrayList<>();
		param.add(new HotHttpEntity("token",  EntityEnum.TEXT,"62995e3f112343e8a1c5c0ab5b50d12c"));
		param.add(new HotHttpEntity("unitName",  EntityEnum.TEXT,"1单元"));
		param.add(new HotHttpEntity("unitNo",  EntityEnum.TEXT,"01"));
		param.add(new HotHttpEntity("buildingId",  EntityEnum.TEXT,75866));
		HotHttpResponse response = HttpClientUtils.doPost(param, "http://ddflow.doordu.com/open_api/h_d/createUnit/v1");
		JSONObject object = JSONObject.fromObject(response.getReturnJson());
		System.out.println(object.get("data"));
	}
	
	//单元新增
	@Test
	public void addRoom() {
		List<HotHttpEntity> param = new ArrayList<>();
		param.add(new HotHttpEntity("token",  EntityEnum.TEXT,"62995e3f112343e8a1c5c0ab5b50d12c"));
		param.add(new HotHttpEntity("roomNumber",  EntityEnum.TEXT,101));
		param.add(new HotHttpEntity("unitId",  EntityEnum.TEXT,478924));
		param.add(new HotHttpEntity("roomNickname",  EntityEnum.TEXT,101));
		HotHttpResponse response = HttpClientUtils.doPost(param, "http://ddflow.doordu.com/open_api/h_d/createRoom/v1");
		JSONObject fromObject = JSONObject.fromObject(response.getReturnJson());
		System.out.println(fromObject.get("data"));
	}
}
