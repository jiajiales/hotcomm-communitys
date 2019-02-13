package com.hotcomm.community.corporation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.corporation.CorLabelDM;
import com.hotcomm.community.common.bean.db.corporation.CorLabelTypeDM;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPA;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPagePA;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelTypePA;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.corporation.CorLabelService;
import com.hotcomm.community.common.service.corporation.CorLabelTypeService;
import com.hotcomm.community.corporation.mapper.CorLabelMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 单位标签
 * @author: lhx
 * @date: 2019-01-30 16:02
 **/
@Service
public class CorLabelServiceImpl extends BaseService implements CorLabelService {

	@Autowired
	private CorLabelMapper corLabelMapper;
	
	@Autowired
	private CorLabelTypeService corLabelTypeService;
	
	@Override
	public PageInfo<CorLabelUM> page(CorLabelPagePA corLabelPagePA) throws HKException {
		Map<String, Object>  map = super.getTableParams(corLabelPagePA.getCommunityId());
		PageHelper.startPage(corLabelPagePA.getPageIndex(),corLabelPagePA.getPageSize());
		map.put("labelTypeId", corLabelPagePA.getLabelTypeId());
		map.put("labelName", corLabelPagePA.getLabelName());
		map.put("startTime", corLabelPagePA.getStartTime());
		map.put("endTime", corLabelPagePA.getEndTime());
		Page<CorLabelUM> page =corLabelMapper.pagelist(map);
		List<CorLabelUM> corLabel   = page;
		return new PageInfo<>(page.getTotal(), corLabel,corLabelPagePA.getPageSize(),corLabelPagePA.getPageIndex());
	}

	@Override
	@Transactional
	public Integer insert(CorLabelPA corLabelPA) throws HKException {
		String dynamicDbname  = super.getTableParams(corLabelPA.getCommunityId()).get("dynamic_dbname").toString();
		CorLabelUM corLabelUM = corLabelMapper.detail(dynamicDbname,null,corLabelPA.getLabelName());
		if (corLabelUM != null) {
			throw this.exceptionManager.configLog(error).errorRecord("COR0001", new Exception());
		}else {
			CorLabelDM corLabelDM = new CorLabelDM();
			BeanUtils.copyProperties(corLabelPA, corLabelDM);
			corLabelDM.setDynamicDbname(dynamicDbname);
			corLabelDM.setCreateTime(DateUtils.getNowTime());
			corLabelDM.setState(0);
			//typeName不为 null时，默认新增标签类型
			if (corLabelPA.getTypeName()!=null) {
				CorLabelTypePA corLabelTypeDM = new CorLabelTypePA();
				corLabelTypeDM.setCommunityId(corLabelDM.getCommunityId());
				corLabelTypeDM.setTypeName(corLabelDM.getTypeName());
				CorLabelTypeDM corLabelType  =corLabelTypeService.detail(corLabelTypeDM);
				if (corLabelType==null) {
					//新增标签类型
					corLabelTypeDM.setState(0);
					Integer labelTypeId  = corLabelTypeService.insert(corLabelTypeDM);
					corLabelDM.setTypeId(labelTypeId);
				}throw this.exceptionManager.configLog(error).errorRecord("COR0002", new Exception());
			}
			return corLabelMapper.insert(corLabelDM);
		}
	}

	@Override
	public boolean delete(Integer communityId,Integer id) throws HKException {
		return corLabelMapper.delete(super.getTableParams(communityId).get("dynamic_dbname").toString(),id);
	}

	@Override
	public boolean update(CorLabelPA corLabelPA) throws HKException {
		String dynamicDbname  = super.getTableParams(corLabelPA.getCommunityId()).get("dynamic_dbname").toString();
		CorLabelUM corLabelById = corLabelMapper.detail(dynamicDbname,corLabelPA.getId(),null);
		CorLabelDM corLabeldm = new CorLabelDM();
		BeanUtils.copyProperties(corLabelPA, corLabeldm);
		corLabeldm.setUpdateTime(DateUtils.getNowTime());
		corLabeldm.setDynamicDbname(dynamicDbname);
		if (corLabelPA.getLabelName() != null){
			CorLabelUM corLabelByName = corLabelMapper.detail(dynamicDbname,null,corLabelPA.getLabelName());
			//标签存在且不是本次修改的标签
			if (corLabelByName !=null && corLabelById.getId().equals(corLabelByName.getId())) {
				throw this.exceptionManager.configLog(error).errorRecord("COR0001", new Exception());
			} else {
				return corLabelMapper.update(corLabeldm);
			}
		}else{
			return corLabelMapper.update(corLabeldm);
		}
	}

	@Override
	public CorLabelUM detail(Integer communityId,Integer id ,String labelName) throws HKException {
		return corLabelMapper.detail(super.getTableParams(communityId).get("dynamic_dbname").toString(),id,labelName);
	}

}
