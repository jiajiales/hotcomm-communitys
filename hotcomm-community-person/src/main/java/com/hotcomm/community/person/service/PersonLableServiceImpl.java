package com.hotcomm.community.person.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.person.LableTagDM;
import com.hotcomm.community.common.bean.en.person.PersonLableEN;
import com.hotcomm.community.common.bean.pa.person.PersonLablePagePA;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.person.PersonLableService;
import com.hotcomm.community.person.mapper.PersonLableMapper;
import com.hotcomm.framework.comm.DictionaryDesc;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class PersonLableServiceImpl extends BaseService implements PersonLableService {

	@Autowired
	PersonLableMapper popualtionLableMapper;

	@Override
	public PageInfo<PersonLableUM> page(PersonLablePagePA Params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(Params.getCommunityId());
		tableParams.put("dic_type", DictionaryDesc.POPUALTION_LABLE_TYPE);
		if (Params.getTypeCode() != null) {
			tableParams.put("typeCode", PersonLableEN.getValueByIndex(Params.getTypeCode()).getKey());
		}
		tableParams.put("sourceType", Params.getSourceType());
		tableParams.put("startTime", Params.getStartTime());
		tableParams.put("endTime", Params.getEndTime());
		tableParams.put("userName", Params.getUserName());

		PageHelper.startPage(Params.getPageIndex(), Params.getPageSize());
		Page<PersonLableUM> page = popualtionLableMapper.pagelist(tableParams);
		List<PersonLableUM> personLableList = page;
		return new PageInfo<>(page.getTotal(), personLableList, Params.getPageSize(), Params.getPageIndex());
	}

	@Override
	public PersonLableUM getPersonLableInfo(Integer communityId, Integer id) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("dic_type", DictionaryDesc.POPUALTION_LABLE_TYPE);

		PersonLableUM result = popualtionLableMapper.PersonLableInfo(tableParams, id);

		if (result!=null) {
			result.setTypeId(PersonLableEN.getIndexByKey(result.getTypeCode()).getIndex());
		}
		return result;
	}

	@Override
	public Integer delPersonLable(Integer communityId, Integer id) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer result;
		if(popualtionLableMapper.checkLablePersonRe(tableParams,id)!=0)throw this.exceptionManager.configLog(error).errorRecord("PLE0004", new Exception());
		try {
			result = popualtionLableMapper.deletePersonLable(tableParams, id);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PLE0003", e);
		}
		return result;
	}

	@Override
	public Integer updatePersonLable(Integer communityId, Integer id,Integer typeCode,String name,String description) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer result;
		if(popualtionLableMapper.checkLableName(tableParams, name)!=0)throw this.exceptionManager.configLog(error).errorRecord("PLE0005", new Exception());
		try {
			result= popualtionLableMapper.updatePersonLable(tableParams, id, PersonLableEN.getValueByIndex(typeCode).getKey(), name, description);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PLE0002", e);
		}
		return result;
	}

	@Override
	public Integer updatePersonLableId(Integer communityId, Integer id, Integer uid) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer result;
		try {
			result= popualtionLableMapper.updatePersonLableId(tableParams, id,uid);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PLE0002", e);
		}
		return result;
	}

	@Override
	public Integer insertPersonLable(Integer communityId, Integer createUser, Integer typeCode, String name,
			String description) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		if(popualtionLableMapper.checkLableName(tableParams, name)!=0)throw this.exceptionManager.configLog(error).errorRecord("PLE0005", new Exception());
		Integer result;
		try {
			result = popualtionLableMapper.insertPersonLable(tableParams, PersonLableEN.getValueByIndex(typeCode).getKey(), name, description, createUser);	
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("PLE0001", e);
		}
		
		return result;
	}

	@Override
	public List<LableTagDM> selectLable(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		
		LableTagDM lable=new LableTagDM();
		lable.setLableId(-1);
		lable.setLableName("陌生人");
		lable.setTypeCode("F05");
		lable.setTypeName("陌生人");
		List<LableTagDM> selectLable = popualtionLableMapper.selectLable(tableParams);
		selectLable.add(lable);
		
		return selectLable;
	}

	
}
