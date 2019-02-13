package com.hotcomm.community.common.service.device.doorcontrol;

import java.util.List;

import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;
import com.hotcomm.framework.web.exception.HKException;

public interface DoorcontrolService {
	
	void synchrodata() throws HKException;

	void updateDevTable(List<Object> list,List<DoorcontrolAttributeDM> synchrodata,Integer cid) throws HKException;
}
