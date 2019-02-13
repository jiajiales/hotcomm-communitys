package com.hotcomm.community.house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.en.house.HouseEN;
import com.hotcomm.community.common.bean.pa.house.BuildingPA;
import com.hotcomm.community.common.bean.pa.house.BuildingPagePA;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.BuildingUM;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.common.service.house.FloorsService;
import com.hotcomm.community.common.service.house.UnitService;
import com.hotcomm.community.house.mapper.BuildingMapper;
import com.hotcomm.framework.comm.DictionaryDesc;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class BuildingServiceImpl extends BaseService implements BuildingService {
	@Autowired
	BuildingMapper buildingMapper;

	@Override
	public PageInfo<BuildingUM> pageData(BuildingPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<BuildingUM> page = buildingMapper.page(tableParams, params);
		List<BuildingUM> buildingUMs = page;
		List<DictionaryDM> list = super.getDictionaryList(DictionaryDesc.BUILDING_FLOOR_ATTRIBUTE);
		if (buildingUMs.size()!=0) {
			for (BuildingUM buildingUM : buildingUMs) {
				if (buildingUM.getRentOrSale()!=null) {
					int rentOrSale = Integer.parseInt(buildingUM.getRentOrSale());
					buildingUM.setRentOrSale(HouseEN.BuildingRentOrSale.getByValue(rentOrSale).getName());	
				}
				if (buildingUM.getAttribute()!=null) {
					int attribute = Integer.parseInt(buildingUM.getAttribute());
					for (DictionaryDM dictionaryDM : list) {
						if (dictionaryDM.getId() == attribute) {
							buildingUM.setAttribute(dictionaryDM.getKeyValue());
						}
					}	
				}
			}	
		}
		return new PageInfo<>(page.getTotal(), buildingUMs, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public BuildingUM detailsData(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		BuildingUM detailsData = buildingMapper.detailsData(tableParams);
		if (detailsData!=null) {
			if (detailsData.getRentOrSale()!=null) {
				int rentOrSale = Integer.parseInt(detailsData.getRentOrSale());
				detailsData.setRentOrSale(HouseEN.BuildingRentOrSale.getByValue(rentOrSale).getName());	
			}
			List<DictionaryDM> list = super.getDictionaryList(DictionaryDesc.BUILDING_FLOOR_ATTRIBUTE);
			if (detailsData.getAttribute()!=null) {
				int attribute = Integer.parseInt(detailsData.getAttribute());
				for (DictionaryDM dictionaryDM : list) {
					if (dictionaryDM.getId() == attribute) {
						detailsData.setAttribute(dictionaryDM.getKeyValue());
					}
				}	
			}	
		}
		return buildingMapper.detailsData(tableParams);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addData(BuildingPA params) throws Exception {
		Map<String, Object> tableParams = null;	
		Boolean exist = isExist(null, params.getCommunityId(), params.getBuildingName());
		if (exist) {
			throw exceptionManager.configLog(error).errorRecord("HB0004", HKException.instance());
		}
		try {
			// 新增本地->新增第三方->修改本地
			tableParams = super.getTableParams(params.getCommunityId());
			// 本地新增
			buildingMapper.addData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HB0001", e);
		}
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			// 第三方新增
			List<HotHttpEntity> addParam = new ArrayList<>();
			addParam.add(new HotHttpEntity("token", EntityEnum.TEXT, super.getToken()));
			addParam.add(new HotHttpEntity("buildingVo.buildingNo", EntityEnum.TEXT, params.getId()));
			addParam.add(new HotHttpEntity("buildingVo.buildingName", EntityEnum.TEXT, params.getBuildingName()));
			addParam.add(new HotHttpEntity("buildingVo.depId", EntityEnum.TEXT, tableParams.get("doorduDepId")));
			HotHttpResponse addPost = HttpClientUtils.doPost(addParam,
					"http://ddflow.doordu.com/open_api/h_d/createBuild/v1");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> readValue = mapper.readValue(addPost.getReturnJson(), new TypeReference<Map<String, Object>>() {
			});
			if (!readValue.get("code").equals("200")) {
				throw exceptionManager.configLog(error).errorRecord("HB0006", HKException.instance());
			}
			Map<String, Object> object = (Map<String, Object>) readValue.get("data");
			// 本地修改
			tableParams.put("doorduBuildingId", object.get("buildingId"));
			tableParams.put("id", params.getId());
			buildingMapper.updateDoorduId(tableParams);
		}
	}

	@Override
	public void updateData(BuildingPA params) throws HKException {
		Boolean exist = isExist(params.getId(), params.getCommunityId(), params.getBuildingName());
		if (exist) {
			throw exceptionManager.configLog(error).errorRecord("HB0004",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			buildingMapper.updateData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HB0002", e);
		}
	}

	@Override
	public void deleteData(Integer id, Integer communityId) throws HKException {
		UnitService bean = SpringUtil.getBean(UnitService.class);
		List<UnitUM> list = bean.getDataList(id, communityId);
		if (list.size() != 0) {
			throw exceptionManager.configLog(error).errorRecord("HB0005",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			tableParams.put("id", id);
			tableParams.put("isdelete", 1);
			buildingMapper.deleteData(tableParams);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HB0003", e);
		}

	}

	/**
	 * 当前小区楼栋列表
	 */
	@Override
	public List<BuildingListUM> getBuildingList(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<BuildingListUM> list = buildingMapper.getBuildingList(tableParams);
		return list;
	}

	@Override
	public Boolean isExist(Integer id, Integer communityId, String buildingName) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer exist = buildingMapper.isExist(tableParams, id, buildingName);
		if (exist != 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 综合作业楼栋信息
	 */
	@Override
	public List<Map<String, Object>> getBuildings(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return buildingMapper.getBuildings(tableParams);
	}

}
