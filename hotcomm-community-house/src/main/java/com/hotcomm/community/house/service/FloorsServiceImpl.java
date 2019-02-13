package com.hotcomm.community.house.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.en.house.HouseEN;
import com.hotcomm.community.common.bean.pa.house.FloorsPA;
import com.hotcomm.community.common.bean.pa.house.FloorsPagePA;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.FloorsService;
import com.hotcomm.community.house.mapper.FloorsMapper;
import com.hotcomm.framework.comm.DictionaryDesc;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class FloorsServiceImpl extends BaseService implements FloorsService {
	@Autowired
	FloorsMapper floorsMapper;

	@Override
	public PageInfo<FloorsUM> pageData(FloorsPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<FloorsUM> page = floorsMapper.page(tableParams, params);
		List<FloorsUM> floors = page;
		List<DictionaryDM> list = super.getDictionaryList(DictionaryDesc.BUILDING_FLOOR_ATTRIBUTE);
		if (floors.size() != 0) {
			for (FloorsUM floorsUM : floors) {
				if (floorsUM.getAttribute() != null) {
					int attribute = Integer.parseInt(floorsUM.getAttribute());
					for (DictionaryDM dictionaryDM : list) {
						if (dictionaryDM.getId() == attribute) {
							floorsUM.setAttribute(dictionaryDM.getKeyValue());
						}
					}
				}
				if (floorsUM.getRentOrSale() != null) {
					int RentOrSale = Integer.parseInt(floorsUM.getRentOrSale());
					floorsUM.setRentOrSale(HouseEN.BuildingRentOrSale.getByValue(RentOrSale).getName());
				}
			}
		}
		return new PageInfo<>(page.getTotal(), floors, params.getPageSize(), params.getPageIndex());
	}

	@Transactional
	@Override
	public void addData(FloorsPA params) throws HKException {
		Boolean exist = isFloorExist(null, params.getUnitId(), params.getFloorNum(), params.getBuildingId(),
				params.getCommunityId());
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HF0004",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			floorsMapper.addData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HF0001", e);
		}
	}

	@Transactional
	@Override
	public void updateData(FloorsPA params) throws HKException {
		Boolean exist = isFloorExist(params.getId(), params.getUnitId(), params.getFloorNum(), params.getBuildingId(),
				params.getCommunityId());
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HF0004",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			floorsMapper.updateData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HF0002", e);
		}
	}

	@Transactional
	@Override
	public void deleteData(Integer id, Integer communityId) throws HKException {
		Boolean exist = isRommexist(id, communityId);
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HF0005",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			tableParams.put("id", id);
			floorsMapper.deleteData(tableParams);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HF0003", e);
		}
	}

	@Override
	public List<FloorsListUM> getFloorsList(Integer unitId, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("unitId", unitId);
		return floorsMapper.getFloorsList(tableParams);
	}

	@Override
	public FloorsUM detailsData(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		FloorsUM detailsData = floorsMapper.detailsData(tableParams);
		if (detailsData.getId() != null) {
			List<DictionaryDM> list = super.getDictionaryList(DictionaryDesc.BUILDING_FLOOR_ATTRIBUTE);
			int parseInt = Integer.parseInt(detailsData.getAttribute());
			for (DictionaryDM dm : list) {
				if (dm.getId() == parseInt) {
					detailsData.setAttribute(dm.getKeyValue());
				}
			}
			if (detailsData.getRentOrSale() != null) {
				detailsData.setRentOrSale(
						HouseEN.BuildingRentOrSale.getByValue(Integer.parseInt(detailsData.getRentOrSale())).getName());
			}
			return detailsData;
		}
		return null;
	}

	@Override
	public Boolean isRommexist(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		Integer exist = floorsMapper.isRoomExist(tableParams);
		if (exist != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isFloorExist(Integer id, Integer unitId, Integer floorNum, Integer buildingId, Integer communityId)
			throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("unitId", unitId);
		tableParams.put("floorNum", floorNum);
		tableParams.put("buildingId", buildingId);
		tableParams.put("id", id);
		Integer exist = floorsMapper.isFloorExist(tableParams);
		if (exist != 0) {
			return true;
		}
		return false;
	}

}
