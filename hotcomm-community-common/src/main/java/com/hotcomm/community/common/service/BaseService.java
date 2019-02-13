package com.hotcomm.community.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.ui.system.CommunityUM;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.DictionaryService;
import com.hotcomm.framework.comm.ApplicationEnvironment;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.ExceptionManager;

public abstract class BaseService {

	protected Logger service = LoggerFactory.getLogger("infoLogger");
	protected Logger error = LoggerFactory.getLogger("errorLogger");
	protected Logger console = LoggerFactory.getLogger("root");
	private static Map<String, List<DictionaryDM>> dictionaryList = new HashMap<>();
	final static String appId="100010";
	final static String secretKey="3dedc2fe6a6e13eeaaeaf25e5aeefee5";
	final static String url="http://ddflow.doordu.com/open_api/auth/getToken/v1";
	final static Integer agentId=7342;

	@Autowired
	@Qualifier("exceptionManager")
	protected ExceptionManager exceptionManager;

	public Map<String, Object> getTableParams(Integer communityId) {
		Map<String, Object> result = new HashMap<>();
		CommunityService communityService = SpringUtil.getBean(CommunityService.class);
		CommunityUM communityUM = communityService.getCommunity(communityId);
		result.put("base_dbname", SpringUtil.getBean(ApplicationEnvironment.class).baseDbName);
		result.put("dynamic_dbname", communityUM.getDatabaseNo());
		result.put("doorduDepId", communityUM.getDoorduDepId());
		return result;
	}

	public List<DictionaryDM> getDictionaryList(String type) {
		if (!dictionaryList.containsKey(type)) {
			DictionaryService dictionaryService = SpringUtil.getBean(DictionaryService.class);
			List<DictionaryDM> kvpList = dictionaryService.getDictionaryListByType(type);
			dictionaryList.put(type, kvpList);
		}
		return dictionaryList.get(type);
	}
	
	@SuppressWarnings("unchecked")
	public String getToken() {
		String token = null;
		/*
		 * RedisHelper bean = SpringUtil.getBean(RedisHelper.class); token =
		 * bean.get("doordu_token"); if (token==null) {
		 */
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("appId", EntityEnum.TEXT, appId));
		params.add(new HotHttpEntity("secretKey", EntityEnum.TEXT, secretKey));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> object;
		try {
			object = mapper.readValue(response.getReturnJson(), new TypeReference<Map<String, Object>>() {
			});
			if (object.get("code").equals("200")) {
				Map<String, Object> map = (Map<String, Object>) object.get("data");
				token = (String) map.get("token");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
}
