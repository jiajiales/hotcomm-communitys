package com.hotcomm.community.person.service;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.person.*;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.hotcomm.community.common.bean.pa.person.PersonHolePA;
import com.hotcomm.community.common.bean.pa.person.AddPersonHolePA;
import com.hotcomm.community.common.bean.ui.person.PersonHoleInfoToAllUM;
import com.hotcomm.community.common.bean.ui.person.PersonHoleInfoToOneUM;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.person.PersonHoleService;
import com.hotcomm.community.common.service.person.PersonLableService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.person.mapper.PersonHoleMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.comm.SetParamNull;
import com.hotcomm.framework.web.exception.HKException;

@Service
@Transactional
public class PersonHoleServiceImpl extends BaseService implements PersonHoleService {

	@Autowired
	private PersonHoleMapper hole;
	@Autowired
	private PersonService person;
	@Autowired
	private PersonLableService lable;

	@Override
	public PageInfo<PersonHoleDM> PersonHolePage(PersonHolePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		tableParams.put("startAge", params.getStartAge());
		tableParams.put("endAge", params.getEndAge());
		tableParams.put("type", params.getType());
		tableParams.put("sex", params.getSex());
		tableParams.put("lableId", params.getLableId());
		tableParams.put("people", params.getPeople());
		tableParams.put("content", params.getContent());
		tableParams.put("startTime", params.getStartTime());
		tableParams.put("endTime", params.getEndTime());

		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<PersonHoleDM> result = hole.PersonHolePage(tableParams);

		List<PersonHoleDM> page = result;
		return new PageInfo<>(result.getTotal(), page, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public Integer PersonHoleOnOff(Integer id, Integer communityId, Integer onOff) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		Integer result;
		try {
			result = hole.PersonHoleOnOff(tableParams, onOff, id);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(service).serviceRecord("PHE0001");
		}

		return result;
	}

	@Override
	public Integer delPersonHole(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		Integer result;
		try {
			result = hole.delPersonHoleDetail(tableParams, id);
			result = hole.delPersonHole(tableParams, id);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(service).errorRecord("PHE0002", e);
		}
		return result;
	}

	@Override
	public PersonHoleInfoToOneUM PersonHoleInfoByOne(Integer id, Integer communityId, Integer pId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		PersonHoleInfoToOneUM result = new PersonHoleInfoToOneUM();
		result.setPerson(person.getPersonInfo(communityId, pId));
		PersonHoleInfoDM holeInfo = hole.selectPersonHoleInfo(tableParams, id);

		if (holeInfo != null){
			result.setPush(holeInfo.getPush());
			result.setHoleTitle(holeInfo.getHole_title());
			result.setAlarmLv(holeInfo.getAlarm_level());
			result.setDetail(hole.selectHoleDetailInfo(tableParams, id));
		}
		return result;
	}

	@Override
	public PersonHoleInfoToAllUM PersonHoleInfoByAll(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		PersonHoleInfoToAllUM result = new PersonHoleInfoToAllUM();
		PersonHoleInfoDM holeInfo = hole.selectPersonHoleInfo(tableParams, id);// 获取布控详情

		String populations = hole.selectPersonHoleInfo(tableParams, id).getHole_populations();
		HoleObjDM obj = new Gson().fromJson(populations, HoleObjDM.class);// 把数据库的hole_populations json转java 对象

		PersonLableUM lableInfo = lable.getPersonLableInfo(communityId, obj.getLable_id());// 获取标签信息
		result.setObj(obj);
		result.setAlarmLv(holeInfo.getAlarm_level());
		result.setHoleTitle(holeInfo.getHole_title());
		result.setLableName(lableInfo.getLableName());
		result.setPush(holeInfo.getPush());
		result.setDetail(hole.selectHoleDetailInfo(tableParams, id));
		return result;
	}

	@Override
	public Integer addPersonHole(AddPersonHolePA param) throws HKException {
		String objJson = new Gson().toJson(param.getObj());
		List<PushDM> pushList= Lists.newArrayList();
		for (PushDM data:param.getPushobj()) {
			System.out.println(data.toString());
			if(data.getP_id()!=null){
				pushList.add(data);
			}
		}
		String PushJson = new Gson().toJson(pushList);
		AddPersonHolePA pa = SetParamNull.setObjVal(param);
		pa.setHolePopulations(objJson);
		pa.setPush(PushJson);
		System.out.println(pa.toString());

		Map<String, Object> tableParams = super.getTableParams(pa.getCommunityId());
		Integer result1;

		try {
			result1 = hole.insertPersonHole(tableParams, pa);
			for (PersonHoleDetailDM obj : param.getHoleDetail()) {
				if (obj.getWay()!=null) {
					PersonHoleDetailDM pas = SetParamNull.setObjVal(obj);
					pas.setHoleId(pa.getId());
					result1 = hole.insertPersonHoleDetail(tableParams, pas);
				}
			}
		} catch (Exception e) {
			throw this.exceptionManager.configLog(service).errorRecord("PHE0003", e);
		}

		return result1;
	}

	@Override
	public List<HoleAndDetailInfoDM> getHoleAndDetail(Integer communityId, String time) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		return hole.getHoleAndDetail(tableParams, time);
	}

	@Override
	public Integer updatePersonHole(AddPersonHolePA param) throws HKException {
		AddPersonHolePA pa = SetParamNull.setObjVal(param);
		if (param.getObj() != null) {
			String objJson = new Gson().toJson(param.getObj());
			pa.setHolePopulations(objJson);
		}
		if (param.getPushobj() != null) {
			String PushJson = new Gson().toJson(param.getPushobj());
			pa.setPush(PushJson);
		}
		List<PushDM> pushList= Lists.newArrayList();
		for (PushDM data:pa.getPushobj()) {
			if(data.getP_id()!=null){
				pushList.add(data);
			}
		}
		String PushJson = new Gson().toJson(pushList);
		pa.setPush(PushJson);

		Map<String, Object> tableParams = super.getTableParams(param.getCommunityId());

		Integer result1;
		try {
			result1 = hole.updateHole(tableParams, pa);
			if (pa.getHoleDetail() != null) {
				for (PersonHoleDetailDM obj : pa.getHoleDetail()) {
					PersonHoleDetailDM pas = SetParamNull.setObjVal(obj);
					if (pas.getId() != null) {      //判断此预警布控是否入库
						if (obj.getWay() != null) {//有内容
							pas.setHoleId(param.getId());
							result1 = hole.updateHoleDetail(tableParams, pas);
						}else{//没有内容
							hole.delPersonHoleDetailById(tableParams, pas.getId());//删除
						}
					}else{//没有入库
						if (obj.getWay() != null) {//是否有内容
							pas.setHoleId(pa.getId());
							hole.insertPersonHoleDetail(tableParams,pas);//添加
						}
					}
				}
			}
		} catch (Exception e) {
			throw this.exceptionManager.configLog(service).errorRecord("PHE0004", e);
		}
		return result1;
	}

}
