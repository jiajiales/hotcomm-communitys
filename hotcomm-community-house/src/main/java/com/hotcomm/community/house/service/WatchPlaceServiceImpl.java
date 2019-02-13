package com.hotcomm.community.house.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.en.house.WatchPlaceEN;
import com.hotcomm.community.common.bean.pa.house.WatchPlacPA;
import com.hotcomm.community.common.bean.pa.house.WatchPlacePagePA;
import com.hotcomm.community.common.bean.ui.house.WatchPlaceUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.WatchPlaceService;
import com.hotcomm.community.house.mapper.WatchPlaceMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class WatchPlaceServiceImpl extends BaseService implements WatchPlaceService {
	@Autowired
	WatchPlaceMapper watchPlaceMapper;

	@Override
	public PageInfo<WatchPlaceUM> pageData(WatchPlacePagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<WatchPlaceUM> page = watchPlaceMapper.page(tableParams, params);
		List<WatchPlaceUM> places = page;
		if (places.size()!=0) {
			List<DictionaryDM> list = super.getDictionaryList(WatchPlaceEN.WaysEnum.getByValue(params.getWays()).getName());
			for (WatchPlaceUM watchPlaceUM : places) {
				if (watchPlaceUM.getPlaceType()!=null) {
					int type = Integer.parseInt(watchPlaceUM.getPlaceType());
					for (DictionaryDM dictionaryDM : list) {
						if (dictionaryDM.getId() == type) {
							watchPlaceUM.setPlaceType(dictionaryDM.getKeyValue());
						}
					}
				}
			}
		}
		return new PageInfo<>(page.getTotal(), places, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public WatchPlaceUM detailsData(Integer id, Integer communityId,Integer ways) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		WatchPlaceUM detailsData = watchPlaceMapper.detailsData(tableParams);
		List<DictionaryDM> list = super.getDictionaryList(WatchPlaceEN.WaysEnum.getByValue(ways).getName());
		if (detailsData.getPlaceType()!=null) {
			int type = Integer.parseInt(detailsData.getPlaceType());
			for (DictionaryDM dictionaryDM : list) {
				if (dictionaryDM.getId() == type) {
					detailsData.setPlaceType(dictionaryDM.getKeyValue());
				}
			}
		}
		return detailsData;
	}

	@Transactional
	@Override
	public void addData(WatchPlacPA params) throws HKException {
		Boolean exist = isExistPlace(params);
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HW0001",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			watchPlaceMapper.addData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HW0002", e);
		}
	}

	@Transactional
	@Override
	public void updateData(WatchPlacPA params) throws HKException {
		Boolean exist = isExistPlace(params);
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HW0001",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			watchPlaceMapper.updateData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HW0003", e);
		}
	}

	@Transactional
	@Override
	public void delData(Integer id, Integer communityId) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			tableParams.put("id", id);
			watchPlaceMapper.delData(tableParams);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HW0004", e);
		}
	}

	@Override
	public Boolean isExistPlace(WatchPlacPA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		Integer exist = watchPlaceMapper.isExistPlace(tableParams, params);
		if (exist != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<WatchPlaceUM> getPlaceList(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return watchPlaceMapper.getPlaceList(tableParams);
	}

}
