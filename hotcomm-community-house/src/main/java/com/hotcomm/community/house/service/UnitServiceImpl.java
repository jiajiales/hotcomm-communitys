package com.hotcomm.community.house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.pa.house.UnitPA;
import com.hotcomm.community.common.bean.pa.house.UnitPagePA;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.UnitService;
import com.hotcomm.community.house.mapper.UnitMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

@Service
public class UnitServiceImpl extends BaseService implements UnitService {
	@Autowired
	UnitMapper unitMapper;

	@Override
	public PageInfo<UnitUM> pageData(UnitPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<UnitUM> page = unitMapper.page(tableParams, params);
		List<UnitUM> list=page;	
		return new PageInfo<>(page.getTotal(), list, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public List<UnitUM> getDataList(Integer buildingId, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("buildingId", buildingId);
		return unitMapper.dataList(tableParams);
	}

	@Override
	public UnitUM detailsData(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		return unitMapper.detailsData(tableParams);
	}

	@Transactional
	@Override
	public void addData(UnitPA params) throws Exception {
		Map<String, Object> tableParams = null;
		Boolean exist = isExist(null, params.getBuildingId(), params.getUnitNo(), params.getCommunityId());
		if (exist) {
			throw exceptionManager.configLog(error).errorRecord("UN0003", HKException.instance());
		}
		try {
			// 添加本地
			tableParams = super.getTableParams(params.getCommunityId());
			unitMapper.addData(tableParams, params);
			tableParams.put("id", params.getId());
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("UN0005", e);
		}
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			// 第三方添加
			tableParams.put("buildingId", params.getBuildingId());
			Integer queryDoorduBuildingId = unitMapper.queryDoorduBuildingId(tableParams);
			List<HotHttpEntity> param = new ArrayList<>();
			param.add(new HotHttpEntity("token", EntityEnum.TEXT, super.getToken()));
			param.add(new HotHttpEntity("unitName", EntityEnum.TEXT, params.getUnitName()));
			param.add(new HotHttpEntity("unitNo", EntityEnum.TEXT, params.getUnitNo()));
			param.add(new HotHttpEntity("buildingId", EntityEnum.TEXT, queryDoorduBuildingId));
			HotHttpResponse response = HttpClientUtils.doPost(param,
					"http://ddflow.doordu.com/open_api/h_d/createUnit/v1");
			JSONObject object = JSONObject.fromObject(response.getReturnJson());
			if (!object.get("code").equals("200")) {
				throw exceptionManager.configLog(error).errorRecord("UN0006", HKException.instance());
			}
			// 本地修改
			JSONObject fromObject = JSONObject.fromObject(object.get("data"));
			tableParams.put("doorduUnitId", fromObject.get("unitId"));
			unitMapper.updateDoorduUnitId(tableParams);
		}

	}

	@Transactional
	@Override
	public void updateData(UnitPA params) throws HKException {
		Boolean exist = isExist(params.getId(),params.getBuildingId(), params.getUnitNo(), params.getCommunityId());
		if (exist) {
			throw exceptionManager.configLog(error).errorRecord("UN0003",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			unitMapper.updateData(tableParams, params);	
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("UN0004", e);
		}
	}

	@Transactional
	@Override
	public void delData(Integer id, Integer communityId) throws HKException {
		Boolean existRelation = isExistRelation(id, communityId);
		if (existRelation) {
			throw exceptionManager.configLog(error).errorRecord("UN0001",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			tableParams.put("id", id);
			unitMapper.delData(tableParams);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("UN0002", e);
		}
	}

	@Override
	public Boolean isExist(Integer id,Integer buildingId, String unitNo, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("buildingId", buildingId);
		tableParams.put("unitNo", unitNo);
		tableParams.put("id", id);
		Integer exist = unitMapper.isExist(tableParams);
		if (exist != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isExistRelation(Integer id, Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		Integer existRelation = unitMapper.isExistRelation(tableParams);
		if (existRelation != 0) {
			return true;
		}
		return false;
	}

}
