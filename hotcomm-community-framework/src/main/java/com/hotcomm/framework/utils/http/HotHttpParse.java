package com.hotcomm.framework.utils.http;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HotHttpParse {
	
	public static <T> T parseJsonToObject(String json,TypeReference<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		try {
			Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
			String dataStr = JSONObject.toJSON(map.get("data")).toString();
			t = mapper.readValue(dataStr,type);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return t;
	}
	
	public static void main(String[] args) {
		String str = "{\"code\":\"0\",\"data\":[{\"cid\":1,\"cname\":\"东华花园,恒大花园\"},{\"cid\":5,\"cname\":\"东华花园,恒大花园1\"},{\"cid\":6,\"cname\":\"东华花园,恒大花园3\"},{\"cid\":12,\"cname\":\"东华花园,恒大花园5\"}]}";
		TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>() {};
		Map<String, Object> map = parseJsonToObject(str, type);
		System.out.println(map);
		
//		List<Map<String, Object>> list = parseJsonToObject(str, new TypeReference<List<Map<String, Object>>>() {});
	}
}
