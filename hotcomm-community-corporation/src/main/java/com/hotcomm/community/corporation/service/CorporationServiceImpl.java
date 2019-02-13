package com.hotcomm.community.corporation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.db.corporation.CorPersonRelationDM;
import com.hotcomm.community.common.bean.db.corporation.CorporationDM;
import com.hotcomm.community.common.bean.pa.corporation.CorPerRelationListPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPagePA;
import com.hotcomm.community.common.bean.ui.corporation.CorMonCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorPerRelationUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorporationUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.corporation.CorporationService;
import com.hotcomm.community.corporation.mapper.CorporationMapper;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

/**
 * @Description: 单位
 * @author: lhx
 * @date: 2019-01-30 16:02
 **/
@Service
public class CorporationServiceImpl extends BaseService implements CorporationService {

	@Autowired
	private CorporationMapper corporationMapper;
	
	@Autowired
	private CarRegService carRegService;

	@Override
	public PageInfo<CorporationUM> page(CorporationPagePA corporationPagePA) throws HKException {
		Map<String, Object> map = super.getTableParams(corporationPagePA.getCommunityId());
		PageHelper.startPage(corporationPagePA.getPageIndex(),corporationPagePA.getPageSize());
		map.put("corTypeId", corporationPagePA.getCorTypeId());
		map.put("labelId", corporationPagePA.getLabelId());
		map.put("typeId", corporationPagePA.getTypeId());
		map.put("corName", corporationPagePA.getCorName());
		map.put("startTime", corporationPagePA.getStartTime());
		map.put("endTime", corporationPagePA.getEndTime());
		Page<CorporationUM> page =  corporationMapper.pagelist(map);
		List<CorporationUM> carPassRecords = page;
		return  new PageInfo<>(page.getTotal(), carPassRecords, corporationPagePA.getPageSize(),corporationPagePA.getPageIndex());
	}

	@Override
	public List<CorporationUM> list(CorporationPA corporationPA) throws HKException {
		String dynamicDbname  = super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString();
		return corporationMapper.corlist(dynamicDbname, corporationPA.getCorName());
	}

	@Override
	public Integer insert(CorporationPA corporationPA) throws HKException {
		String dynamicDbname  = super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString();
		CorporationUM corporationUM = corporationMapper.detail(dynamicDbname, null,corporationPA.getCorName());
		if (corporationUM == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("building", corporationPA.getBuilding());
			jsonObject.put("layer", corporationPA.getLayer());
			jsonObject.put("roomNum", corporationPA.getRoomNum());
			jsonObject.put("unit", corporationPA.getUnit());
			CorporationDM corporationDM  = new CorporationDM();
			BeanUtils.copyProperties(corporationPA, corporationDM);
			corporationDM.setDynamicDbname(dynamicDbname);
			corporationDM.setFloor(jsonObject.toString());
			corporationDM.setCreateTime(DateUtils.getNowTime());
			return corporationMapper.insert(corporationDM);
		}throw this.exceptionManager.configLog(error).errorRecord("COR0003", new Exception());
	}

	@Override
	public boolean delete(Integer communityId,Integer id) throws HKException {
		return corporationMapper.delete(super.getTableParams(communityId).get("dynamic_dbname").toString(),id);
	}

	@Override
	public boolean update(CorporationPA corporationPA) throws HKException {
		String dynamicDbname  = super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString();
		CorporationUM corporationById = corporationMapper.detail(dynamicDbname, corporationPA.getId(), null);
		CorporationUM corporationByName  = corporationMapper.detail(dynamicDbname, null, corporationPA.getCorName());
		if (corporationByName!=null && corporationById.getId() != corporationByName.getId()) {
			//单位名不可重复
			throw this.exceptionManager.configLog(error).errorRecord("COR0003", new Exception());
		}else {
			JSONObject jsonObject = new JSONObject();
			if (corporationPA.getBuilding()!= null) {
				jsonObject.put("building", corporationPA.getBuilding());
			} 
			if ( corporationPA.getLayer()!=null) {
				jsonObject.put("layer", corporationPA.getLayer());
			} 
			if (corporationPA.getRoomNum()!=null) {
				jsonObject.put("roomNum", corporationPA.getRoomNum());
			} 
			if (corporationPA.getUnit()!=null) {
				jsonObject.put("unit", corporationPA.getUnit());
			}
			CorporationDM  corporationDM = new CorporationDM();
			BeanUtils.copyProperties(corporationPA, corporationDM);
			corporationDM.setDynamicDbname(dynamicDbname);
			corporationDM.setFloor(jsonObject.toString());
			corporationDM.setUpdateTime(DateUtils.getNowTime());	
			return corporationMapper.update(corporationDM);
		}
	}

	@Override
	public CorporationUM detail(Integer communityId,Integer id,String corNam) throws HKException {
		String dynamicDbname  = super.getTableParams(communityId).get("dynamic_dbname").toString();
		CorporationUM corporationUM =corporationMapper.detail(dynamicDbname,id,corNam);
		//查询单位关联的人口数
		Integer corPerCount = corporationMapper.selectCorPerTotal(dynamicDbname,id);
		corporationUM.setPersonCount(corPerCount);
		//查询单位关联的车辆数
		Map<String , Object>  map =new HashMap<>();
		map.put("companyId", 1);
		map.put("dynamicDbname", dynamicDbname);
		corporationUM.setCarCount(carRegService.selectCount(map));
		return corporationUM;
	}

	@Override
	public Integer selectCorTotal(CorporationPA corporationPA) throws HKException {
		Map<String, Object> map = new  HashMap<>();
		map.put("dynamicDbname", super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString());
		map.put("labelTypeId", corporationPA.getLabelTypeId());
		return corporationMapper.selectCorTotal(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insertCorPerRelationBatch(CorPerRelationListPA corPerRelationListPA) throws HKException {
		String dynamicDbname =  super.getTableParams(corPerRelationListPA.getCommunityId()).get("dynamic_dbname").toString();
		List<CorPersonRelationDM> corPersonRelationList = new ArrayList<>();
		String  str = corPerRelationListPA.getPersonIdStr();
		List<Integer> ids =Arrays.stream(str.split(",")).map(s->Integer.parseInt(s.trim())). collect(Collectors.toList());
		for (int i = 0; i < ids.size(); i++) {
			//查看用户之前有无绑定单位
			CorPersonRelationDM corPersonRelation = new CorPersonRelationDM();	
			corPersonRelation.setDynamicDbname(dynamicDbname);
			corPersonRelation.setPersonId(ids.get(i));
			CorPersonRelationDM corPersonRelationDM =corporationMapper.selectCorPerRelation(corPersonRelation);
			//用户之前绑定过直接修改
			corPersonRelation.setCorporationId(corPerRelationListPA.getCorId());
			if (corPersonRelationDM!=null) {
				corPersonRelation.setId(corPersonRelationDM.getId());
				corporationMapper.updateCorPerRelation(corPersonRelation);
			}else{
				corPersonRelationList.add(corPersonRelation);
			}
		}
		if (corPersonRelationList.size()>0) {
			return corporationMapper.insertCorPerRelationBatch(dynamicDbname,corPersonRelationList);
		}else {
			return true;
		}
	}

	@Override
	public PageInfo<CorPerRelationUM> selectCorPersonList(CorporationPA corporationPA) throws HKException {
		Map<String, Object> map = super.getTableParams(corporationPA.getCommunityId());
		PageHelper.startPage(corporationPA.getPageIndex(),corporationPA.getPageSize());
		map.put("corporationId", corporationPA.getId());
		map.put("peronName", corporationPA.getPeronName());
		System.out.println("map:  "+map);
		Page<CorPerRelationUM> page =  corporationMapper.selectCorPersonList(map);
		List<CorPerRelationUM> corPerRelation = page;
		return  new PageInfo<>(page.getTotal(), corPerRelation, corporationPA.getPageSize(),corporationPA.getPageIndex());
	}

	@Override
	public Integer selectCorPerTotal(Integer communityId,Integer id) throws HKException {
		return corporationMapper.selectCorPerTotal(super.getTableParams(communityId).get("dynamic_dbname").toString(),id);
	}

	@Override
	public List<CorTypeCountUM> selectCorTypeGroup(String dynamicDbname) throws HKException {	
		return corporationMapper.selectCorTypeGroup(dynamicDbname);
	}

	@Override
	public List<CorTypeCountUM> selectCorTypeCount(CorporationPA corporationPA) throws HKException {
		String dynamicDbname = super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString();
		if (corporationPA.getLabelTypeId()==null) {
			return corporationMapper.selectCorTypeCount(dynamicDbname,1);
		} else {
			return corporationMapper.selectCorTypeCount(dynamicDbname,corporationPA.getLabelTypeId());
		}
	}

	@Override
	public List<CorMonCountUM> selectCorMonCount(CommunityParams communityParams) throws HKException {
		String  dynamicDbname = super.getTableParams(communityParams.getCommunityId()).get("dynamic_dbname").toString();
		Integer year =  DateUtils.getYmd(new Date()).get("year");
		return corporationMapper.selectCorMonCount(dynamicDbname,year);
	}

	@Override
	public boolean deleteCorPersonById(Integer communityId,Integer id) throws HKException {
		return corporationMapper.deleteCorPersonById(super.getTableParams(communityId).get("dynamic_dbname").toString(), id);
	}

	@Override
	public List<CorPerRelationUM> selectPersonList(CorPerRelationListPA corPerRelationListPA) throws HKException {
		String  dynamicDbname = super.getTableParams(corPerRelationListPA.getCommunityId()).get("dynamic_dbname").toString();
		return corporationMapper.selectPersonList(dynamicDbname,corPerRelationListPA.getCorId(),corPerRelationListPA.getPersonName());
	}

}
