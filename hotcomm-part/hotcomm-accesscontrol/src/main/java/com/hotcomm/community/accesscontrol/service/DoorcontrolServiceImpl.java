package com.hotcomm.community.accesscontrol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.accesscontrol.mapper.DoorcontrolMapper;
import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DoorcontrolDevicedata;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.doorcontrol.DoorcontrolAttributeService;
import com.hotcomm.community.common.service.device.doorcontrol.DoorcontrolService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

@Service
public class DoorcontrolServiceImpl extends BaseService implements DoorcontrolService {

	@Autowired
	CommunityService communityService;
	@Autowired
	DoorcontrolAttributeService DoorcontrolAttributeService;
	@Autowired
	DoorcontrolMapper doorcontrolMapper;

	// 同步第三方数据（多度),查询数据库所有门禁设备的guid,比较同步拿到的数据，存在的修改，不存的添加，多余的删除
	@Transactional
	@Override
	public void synchrodata() throws HKException {
		/*CommunityListAllUM communityListAllUM=new CommunityListAllUM();//测试代码
		communityListAllUM.setCid(31);
		communityListAllUM.setDoorduDepId(6062);*/
		
		
		List<CommunityListAllUM> queryListCommunityAll = communityService.queryListCommunityAll();
		for (CommunityListAllUM communityListAllUM : queryListCommunityAll) {
			List<HotHttpEntity> params = new ArrayList<>();
			params.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
			params.add(new HotHttpEntity("depId", EntityEnum.TEXT, communityListAllUM.getDoorduDepId()));
			params.add(new HotHttpEntity("limit", EntityEnum.TEXT, 300));
			params.add(new HotHttpEntity("page", EntityEnum.TEXT, 1));
			HotHttpResponse resp = HttpClientUtils.doPost(params,
					"http://ddflow.doordu.com/open_api/d_d/deviceList/v1");
			JSONObject object = JSONObject.fromObject(resp.getReturnJson());
			if (object.get("code").equals("200")) {
				@SuppressWarnings("unchecked")
				List<Object> list = (List<Object>) object.get("data");
				// 同步属性表
				List<DoorcontrolAttributeDM> synchrodata = DoorcontrolAttributeService.synchrodata(list,
						communityListAllUM);
				// 同步设备主表
				updateDevTable(list, synchrodata, communityListAllUM.getCid());
			}
		}
	}

	@Override
	public void updateDevTable(List<Object> list, List<DoorcontrolAttributeDM> synchrodata, Integer cid)
			throws HKException {
		Map<String, Integer> map = new HashMap<>();
		if (synchrodata!=null) {
			for (DoorcontrolAttributeDM doorcontrolAttributeDM : synchrodata) {
				map.put(doorcontrolAttributeDM.getGuid(), doorcontrolAttributeDM.getId());
			}
		}
		if (!list.isEmpty()) {
			Map<String, Object> tableParams = super.getTableParams(cid);
			List<String> devMac = doorcontrolMapper.selectDevMac(tableParams);
			List<DoorcontrolDevicedata> update = new ArrayList<>();
			List<Object> remove = new ArrayList<>();
			List<String> del = new ArrayList<>();
			for (Object obj : list) {
				JSONObject fromObject = JSONObject.fromObject(obj);
				for (String mac : devMac) {
					if (fromObject.get("guid").equals(mac)) {
						DoorcontrolDevicedata devicedata = new DoorcontrolDevicedata();
						Integer state = (Integer) fromObject.get("heartBeatStatus") == 0 ? 1 : 0;
						devicedata.setState(state);
						update.add(devicedata);
						remove.add(obj);
						del.add(mac);
					}
				}
			}
			if (update.size()!=0) {
				doorcontrolMapper.updateBatch(tableParams, update);
			}
			devMac.removeAll(del);
			if (devMac.size()!=0) {
				doorcontrolMapper.delBatch(tableParams, devMac);
			}
			list.removeAll(remove);
			List<DoorcontrolDevicedata> array = new ArrayList<>();
			for (Object obj : list) {
				JSONObject fromObject = JSONObject.fromObject(obj);
				DoorcontrolDevicedata devicedata = new DoorcontrolDevicedata();
				//devicedata.setDevAddress(fromObject.getString("position"));
				devicedata.setDevNum("MJ-" + fromObject.get("guid"));
				devicedata.setDevTrademark("多度");
				devicedata.setMac(fromObject.getString("guid"));
				devicedata.setModuleId(6);
				devicedata.setUseType(0);
				devicedata.setDoorcontrolAttrId(map.get(fromObject.getString("guid")));
				Integer state = (Integer) fromObject.get("heartBeatStatus") == 0 ? 1 : 0;
				devicedata.setState(state);
				array.add(devicedata);
			}
			if (array.size()!=0) {
				doorcontrolMapper.insertBatch(tableParams, array);	
			}
		}
	}
}
