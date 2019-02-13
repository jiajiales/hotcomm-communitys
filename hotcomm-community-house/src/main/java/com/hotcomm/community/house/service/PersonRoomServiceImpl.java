package com.hotcomm.community.house.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.house.RelationRoomUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.PersonRoomService;
import com.hotcomm.community.house.mapper.PersonRoomMapper;
import com.hotcomm.framework.comm.DictionaryDesc;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class PersonRoomServiceImpl extends BaseService implements PersonRoomService {
	@Autowired
	PersonRoomMapper personRoomMapper;

	@Override
	public PageInfo<PersonRoomUM> pageData(PersonRoomPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<PersonRoomUM> page = personRoomMapper.page(tableParams, params);
		List<PersonRoomUM> personRoomUMs = page;
		if (personRoomUMs.size()!=0) {
			List<DictionaryDM> list = super.getDictionaryList(DictionaryDesc.PERSON_ROOM_RELATION);
			for (PersonRoomUM personRoomUM : personRoomUMs) {
				if (personRoomUM.getReason()!=null) {
					int reason = Integer.parseInt(personRoomUM.getReason());
					for (DictionaryDM dictionaryDM : list) {
						if (dictionaryDM.getId() == reason) {
							personRoomUM.setReason(dictionaryDM.getKeyValue());
						}
					}
				}
			}
		}
		return new PageInfo<>(page.getTotal(), personRoomUMs, params.getPageSize(), params.getPageIndex());
	}

	@Transactional
	@Override
	public void addData(PersonRoomPA params) throws HKException {
		Boolean eixst = isEixst(params.getCommunityId(), params.getPId(), params.getRoomId());
		if (eixst) {
			throw exceptionManager.configLog(error).errorRecord("PR0003",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			personRoomMapper.addData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("PR0001", e);
		}
	}

	@Transactional
	@Override
	public void delData(Integer id, Integer communityId) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			personRoomMapper.delData(tableParams, id);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("PR0002", e);
		}
	}

	@Override
	public List<RelationRoomUM> getRelationRoomUMs(Integer communityId, String name) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("name", name);
		return personRoomMapper.getRelationRoomUMs(tableParams);
	}

	@Override
	public Boolean isEixst(Integer communityId, Integer pId, Integer roomId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("pId", pId);
		tableParams.put("roomId", roomId);
		Integer exist = personRoomMapper.isExist(tableParams);
		if (exist!=0) {
			return true;
		}
		return false;
	}
}
