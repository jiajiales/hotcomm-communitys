package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.common.bean.db.system.CommunityDM;
import com.hotcomm.community.common.bean.en.system.CommunityEN.IsDelete;
import com.hotcomm.community.common.bean.en.system.RoleEN.RoleType;
import com.hotcomm.community.common.bean.pa.system.CommunityPA;
import com.hotcomm.community.common.bean.pa.system.CommunityPagePA;
import com.hotcomm.community.common.bean.pa.system.UserCommunityPA;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityUM;
import com.hotcomm.community.common.bean.ui.system.RoleUM;
import com.hotcomm.community.common.bean.ui.system.RoleUserListUM;
import com.hotcomm.community.common.bean.ui.system.UserCommunityUM;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.CommunityAsyncService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.system.mapper.CommunityMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class CommunityServiceImpl extends BaseService implements CommunityService {

	@Autowired
	CommunityMapper communityMapper;

	@Autowired
	RoleService roleService;

	@Autowired
	CommunityAsyncService communityAsyncService;

	private final String DATABASE_NO_PREFIX = "HOT_COMMUNITY_";

	public static ConcurrentHashMap<Integer,Object> COMMUNITYCACHE = new ConcurrentHashMap<>();

	@Override
	public Integer addCommunity(CommunityPA params) throws HKException {
		Integer cid = params.getCid();

		if (communityMapper.isCnameUsed(params.getCname()) != null)
			throw this.exceptionManager.configLog(service).serviceRecord("CY0002");

		if (communityMapper.isCodeUsed(params.getCode()) != null)
			throw this.exceptionManager.configLog(service).serviceRecord("CY0003");

		try {
			CommunityDM communityDM = new CommunityDM();
			BeanUtils.copyProperties(params, communityDM);
			communityDM.setCreateTime(new Date());
			String dbNo = generationDatabaseNo();
			communityDM.setDatabaseNo(dbNo);

			// 新增社区表记录
			this.communityMapper.add(communityDM);
			cid = communityDM.getCid();

			// 动态创建社区业务数据库
			communityAsyncService.dynamicGeneralDatabase(dbNo);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("CY0001", e);
		}

		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);

		if (model.equals("use")) {
			addDep(params); // 调用多度接口添加小区
			Integer doorduDepId = getDoorduDepIdLatestInsert(); // 调用多度接口获取新增小区ID
			communityMapper.updateCommunityDoorduDepId(cid, doorduDepId);
		}

		return cid;
	}

	private void addDep(CommunityPA params) {
		String url = "http://ddflow.doordu.com/open_api/h_d/createDep/v1";
		List<HotHttpEntity> pa = new ArrayList<>();
		pa.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
		pa.add(new HotHttpEntity("agentId", EntityEnum.TEXT, 7342));
		pa.add(new HotHttpEntity("depName", EntityEnum.TEXT, params.getCname()));
		pa.add(new HotHttpEntity("address", EntityEnum.TEXT, params.getCaddress()));
		pa.add(new HotHttpEntity("remark", EntityEnum.TEXT, params.getCode()));
		pa.add(new HotHttpEntity("manageNumber", EntityEnum.TEXT, params.getLinkPhone()));
		pa.add(new HotHttpEntity("serverNumber", EntityEnum.TEXT, params.getLinkPhone()));
		pa.add(new HotHttpEntity("setupNumber", EntityEnum.TEXT, params.getLinkPhone()));
		pa.add(new HotHttpEntity("curKey", EntityEnum.TEXT, "441900")); // 省市区代码
		pa.add(new HotHttpEntity("buildingNum", EntityEnum.TEXT, 50));
		pa.add(new HotHttpEntity("userNum", EntityEnum.TEXT, 2000));
		pa.add(new HotHttpEntity("type", EntityEnum.TEXT, 1)); // 住宅类型 1住宅小区
		pa.add(new HotHttpEntity("longitude", EntityEnum.TEXT, params.getLon()));
		pa.add(new HotHttpEntity("latitude", EntityEnum.TEXT, params.getLat()));
		pa.add(new HotHttpEntity("managePeople", EntityEnum.TEXT, params.getLinkUser()));
		HttpClientUtils.doPost(pa, url);
	}

	class DoorDuDepIdComparator implements Comparator<JSONObject>{
	    public int compare(JSONObject o1, JSONObject o2) {
	    	Integer depId1 = o1.getInt("depId");
	    	Integer depId2 = o2.getInt("depId");
	    	return depId2.compareTo(depId1);
	    }
	}

	private Integer getDoorduDepIdLatestInsert() {
		String url = "http://ddflow.doordu.com/open_api/h_d/getDepList/v1";
		List<HotHttpEntity> params = new ArrayList<>();
		params.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
		params.add(new HotHttpEntity("agentId", EntityEnum.TEXT, 7342));
		HotHttpResponse response = HttpClientUtils.post(params, url);
		JSONObject responseJsonObj = JSONObject.fromObject(response.getReturnJson());
		JSONArray dataJsonArray = JSONArray.fromObject(responseJsonObj.get("data"));

		List<JSONObject> jsonObjList = new ArrayList<JSONObject>();

        for (int i = 0; i < dataJsonArray.size(); i++)
            jsonObjList.add(dataJsonArray.getJSONObject(i));

		Collections.sort(jsonObjList, new Comparator<JSONObject>() {
			@Override
		    public int compare(JSONObject o1, JSONObject o2) {
		    	Integer depId1 = o1.getInt("depId");
		    	Integer depId2 = o2.getInt("depId");
		    	return depId2.compareTo(depId1); // 多度ID降序排序
		    }
		});

		//JSONObject resultJsonObj = JSONObject.fromObject(dataJsonArray.get(0));
		JSONObject resultJsonObj = jsonObjList.get(0);
		return resultJsonObj.getInt("depId");
	}

	private String generationDatabaseNo() {
		String result = DATABASE_NO_PREFIX;
		String lastDbNo = this.communityMapper.getLastDatabaseNO();
		if (lastDbNo == null) {
			result = result + "0001";
		} else {
			String no = lastDbNo.substring(result.length(), lastDbNo.length());
			no = Integer.toString(Integer.parseInt(no) + 1);
			int len = no.length();
			for (int i = 0; i < 4 - len; i++) {
				no = "0" + no;
			}
			result = DATABASE_NO_PREFIX + no;
		}
		return result;
	}

	@Override
	public void delCommunity(CommunityPA params) throws HKException {
		Integer cid = params.getCid();
		CommunityDM communityDM = new CommunityDM();
		communityDM.setCid(cid);
		communityDM.setUpdateUser(params.getUpdateUser());
		communityDM.setUpdateTime(new Date());
		communityDM.setIsDelete(IsDelete.YES.getValue());
		communityMapper.delCommunity(communityDM);
		COMMUNITYCACHE.remove(cid);
	}

	@Override
	public void updateCommunity(CommunityPA params) throws HKException {
		Integer cid = params.getCid();
		CommunityUM communityUM = getCommunity(cid);

		if (!communityUM.getCname().equals(params.getCname())) {
			if (communityMapper.isCnameUsed(params.getCname()) != null)
				throw this.exceptionManager.configLog(service).serviceRecord("CY0004");
		}

		if (!communityUM.getCode().equals(params.getCode())) {
			if (communityMapper.isCodeUsed(params.getCode()) != null)
				throw this.exceptionManager.configLog(service).serviceRecord("CY0005");
		}

		CommunityDM communityDM = new CommunityDM();
		BeanUtils.copyProperties(params, communityDM);
		communityDM.setUpdateTime(new Date());
		communityMapper.updateCommunity(communityDM);
		COMMUNITYCACHE.put(cid, communityMapper.getCommunity(cid));
	}

	@Override
	public CommunityUM getCommunity(Integer cid) throws HKException {
		Object cm = COMMUNITYCACHE.get(cid);
		if (cm == null) {
			try {
				CommunityUM um = this.communityMapper.getCommunity(cid);
				if (um == null) {
					throw this.exceptionManager.configLog(error).errorRecord("CY0006", HKException.instance());
				} else {
					COMMUNITYCACHE.put(cid, um);
				}
				return um;
			} catch (Exception e) {
				throw this.exceptionManager.configLog(error).errorRecord("CY0006", HKException.instance());
			}
		} else
			return (CommunityUM) cm;
	}

	@Override
	public List<CommunityListUM> queryListCommunity(Integer userId) throws HKException {
		return communityMapper.queryListCommunityByUserId(userId);
	}

	@Override
	public List<CommunityDetailListUM> queryListCommunityDetail(Integer userId) throws HKException {
		return communityMapper.queryListCommunityDetail(userId);
	}

	@Override
	public List<CommunityDetailListUM> queryListCommunityDetailAll() throws HKException {
		List<CommunityDetailListUM> result = communityMapper.queryListCommunityDetailAll();

		if (result.size() > 0)
			result.get(0).setIsDefault(2);

		return communityMapper.queryListCommunityDetailAll();
	}

	@Override
	public List<CommunityListAllUM> queryListCommunityAll() throws HKException {
		return communityMapper.queryListCommunityAll(); // 超级管理员获取所有社区列表
	}

	@Override
	public PageInfo<CommunityUM> queryPageCommunity(CommunityPagePA params) throws HKException {
		int pageIndex = params.getPageIndex();
		int pageSize = params.getPageSize();
		params.setStartIndex(((pageIndex - 1) * pageSize));
		params.setEndIndex(pageSize);
		Long count  = communityMapper.queryPageCommunityCount(params);
		return new PageInfo<>(count, count == 0 ? new ArrayList<>() : communityMapper.queryPageCommunity(params));
	}

	@Override
	public List<RoleUserListUM> queryRoleUserListByCid(Integer cid, String roleId) throws HKException {
		List<RoleUserListUM> roleUserList = new ArrayList<>();
		List<RoleUM> roleList = roleService.queryList(null);

		if (roleId == null || roleId.isEmpty()) {
			for (RoleUM roleUM : roleList) {
				if (roleUM.getRoleId() == RoleType.SUPER_ADMIN.getValue())
					continue;

				RoleUserListUM roleUserListUM = new RoleUserListUM();
				roleUserListUM.setRoleName(roleUM.getRoleName());
				List<UserListUM> userList = communityMapper.queryUserList(cid, roleUM.getRoleId().toString());

				if (userList.size() > 0) {
					roleUserListUM.setUserList(userList);
					roleUserList.add(roleUserListUM);
				}
			}
		} else {
			String[] ridArr = roleId.split(",");

			for (RoleUM roleUM : roleList) {
				if (roleUM.getRoleId() == RoleType.SUPER_ADMIN.getValue())
					continue;

				for (String rid : ridArr) {
					if (roleUM.getRoleId() == Integer.valueOf(rid)) {
						RoleUserListUM roleUserListUM = new RoleUserListUM();
						roleUserListUM.setRoleName(roleUM.getRoleName());
						List<UserListUM> userList = communityMapper.queryUserList(cid, rid);

						if (userList.size() > 0) {
							roleUserListUM.setUserList(userList);
							roleUserList.add(roleUserListUM);
						}

						break;
					}
				}
			}
		}

		return roleUserList;
	}

	@Override
	public List<UserListUM> queryUserListByCid(Integer cid, String roleId) throws HKException {
		return communityMapper.queryUserList(cid, roleId);
	}

	@Override
	@Transactional
	public void addUserCommunity(UserCommunityPA params) throws HKException {
		Integer userId = params.getUserId();
		String cids = params.getCids();

		if (cids == null || cids.split(",").length == 0 || cids.length() == 0) {
			communityMapper.delUserCommunity(userId);
		} else {
			Integer defaultCid = params.getDefaultCid();
			String[] cidsArr = cids.split(",");
			communityMapper.delUserCommunity(userId);
			communityMapper.addUserCommunity(userId, cidsArr);

			if (defaultCid == null)
				defaultCid = Integer.valueOf(cidsArr[0]);

			communityMapper.setUserCommunityDefaultCid(userId, defaultCid);
		}
	}

	@Override
	public UserCommunityUM getUserCommunity(Integer userId) throws HKException {
		UserCommunityUM result = new UserCommunityUM();
		result.setUserId(userId);
		result.setCommunityIds(communityMapper.getUserCommunity(userId));
		return result;
	}

}
