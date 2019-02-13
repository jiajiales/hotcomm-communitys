package com.hotcomm.community.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.accesscontrol.mapper.DoorcontrolAttributeMapper;
import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.doorcontrol.DoorcontrolAttributeService;
import com.hotcomm.framework.web.exception.HKException;
import net.sf.json.JSONObject;

@Service
public class DoorcontrolAttributeServiceImpl extends BaseService implements DoorcontrolAttributeService {

	@Autowired
	DoorcontrolAttributeMapper attributeMapper;
	
	@Transactional
	@Override
	public List<DoorcontrolAttributeDM> synchrodata(List<Object> list, CommunityListAllUM communityListAllUM) throws HKException {
		List<DoorcontrolAttributeDM> in = null;
		if (list.size() != 0) {
			List<DoorcontrolAttributeDM> update = new ArrayList<>();
			List<String> remove = new ArrayList<>();
			List<Object> exist = new ArrayList<>();
			Map<String, Object> tableParams = super.getTableParams(communityListAllUM.getCid());
			List<String> devMac = attributeMapper.selectDevMac(tableParams);
			for (Object obj : list) {
				JSONObject fromObject = JSONObject.fromObject(obj);
				DoorcontrolAttributeDM dm = new DoorcontrolAttributeDM();
				for (String mac : devMac) {
					// 修改
					if (fromObject.getString("guid").equals(mac)) {
						dm.setFaceCaptureOnOff((Boolean) fromObject.get("faceCaptureOnOff"));
						dm.setFaceDetectSupport((Boolean) fromObject.get("faceDetectSupport"));
						dm.setFaceOpenDoorOnOff((Boolean) fromObject.get("faceOpenDoorOnOff"));
						dm.setIsDoorLock((Integer) fromObject.get("isDoorLock"));
						dm.setGuid(fromObject.getString("guid"));
						dm.setLocalType((Integer)fromObject.get("localType"));
						update.add(dm);
						remove.add(mac);
						exist.add(obj);
					}
				}
			}
			// 需要新增的数据
			list.removeAll(exist);
			in = new ArrayList<>();
			if (list.size() != 0) {
				for (Object obj : list) {
					DoorcontrolAttributeDM dm = new DoorcontrolAttributeDM();
					JSONObject object = JSONObject.fromObject(obj);
					dm.setFaceCaptureOnOff((Boolean) object.get("faceCaptureOnOff"));
					dm.setFaceDetectSupport((Boolean) object.get("faceDetectSupport"));
					dm.setFaceOpenDoorOnOff((Boolean) object.get("faceOpenDoorOnOff"));
					dm.setIsDoorLock((Integer) object.get("isDoorLock"));
					dm.setGuid(object.getString("guid"));
					dm.setLocalType((Integer)object.get("localType"));
					in.add(dm);
				}
			}
			// 需要删除的数据
			devMac.removeAll(remove);
			if (in.size()!=0) {
				attributeMapper.insertBatch(tableParams, in);	
			}
			if (update.size()!=0) {
				attributeMapper.updateBatch(tableParams, update);	
			}
			if (devMac.size()!=0) {
				attributeMapper.delBatch(tableParams, devMac);
			}
		}
		return in;
	}

}
