package com.hotcomm.community.common.service.device.doorcontrol;

import java.util.List;

import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.framework.web.exception.HKException;

public interface DoorcontrolAttributeService {
	List<DoorcontrolAttributeDM> synchrodata(List<Object> list, CommunityListAllUM communityListAllUM) throws HKException;
}
