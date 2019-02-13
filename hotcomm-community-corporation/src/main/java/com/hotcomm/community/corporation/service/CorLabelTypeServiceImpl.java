package com.hotcomm.community.corporation.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotcomm.community.common.bean.db.corporation.CorLabelTypeDM;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelTypePA;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelTypeUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.corporation.CorLabelTypeService;
import com.hotcomm.community.corporation.mapper.CorLabelTypeMapper;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 单位标签类型
 * @author: lhx
 * @date: 2019-01-30 16:02
 **/
@Service
public class CorLabelTypeServiceImpl  extends BaseService implements CorLabelTypeService {

	@Autowired
	private  CorLabelTypeMapper corLabelTypeMapper;
	
	@Override
	public Integer insert(CorLabelTypePA corLabelTypePA) throws HKException {
		String dynamicDbname =  super.getTableParams(corLabelTypePA.getCommunityId()).get("dynamic_dbname").toString();
		CorLabelTypeDM corLabelTypeDM = corLabelTypeMapper.detail(dynamicDbname,null,corLabelTypePA.getTypeName(),null);
		if (corLabelTypeDM==null) {
			CorLabelTypeDM corLabelType = new CorLabelTypeDM();
			corLabelType.setDynamicDbname(dynamicDbname);
			corLabelType.setId(corLabelTypePA.getId());
			BeanUtils.copyProperties(corLabelTypePA, corLabelType);
			corLabelType.setDynamicDbname(dynamicDbname);
			return corLabelTypeMapper.insert(corLabelType);
		}throw this.exceptionManager.configLog(error).errorRecord("COR0002", new Exception());
	}

	@Override
	public boolean delete(Integer communityId,Integer id) throws HKException {
		return corLabelTypeMapper.delete(super.getTableParams(communityId).get("dynamic_dbname").toString(),id);
	}

	@Override
	public CorLabelTypeDM detail(CorLabelTypePA corLabelTypePA) throws HKException {
		String dynamicDbname =  super.getTableParams(corLabelTypePA.getCommunityId()).get("dynamic_dbname").toString();
		CorLabelTypeDM corLabelTypeDM = new CorLabelTypeDM();
		BeanUtils.copyProperties(corLabelTypePA, corLabelTypeDM);
		corLabelTypeDM.setDynamicDbname(dynamicDbname);
		return corLabelTypeMapper.detail(dynamicDbname,corLabelTypePA.getId(),corLabelTypePA.getTypeName(),corLabelTypePA.getState());
	}

	@Override
	public List<CorLabelTypeUM> list(CorLabelTypePA corLabelTypePA) throws HKException {
		return corLabelTypeMapper.list(super.getTableParams(corLabelTypePA.getCommunityId()).get("dynamic_dbname").toString());
	}

}
