package com.hotcomm.community.common.service.device.doorcontrol;

import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPA;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPagePA;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevAttrUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorDetailsUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorsUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface DevDoorService {

	PageInfo<DevDoorsUM> pageData(DevDoorPagePA params) throws HKException;

	void update(DevDoorPA params) throws HKException;

	void install(DevDoorPA params) throws HKException;

	DevAttrUM selectAttr(Integer communityId, String mac) throws HKException;

	DevDoorDetailsUM detailsData(Integer communityId, String mac) throws HKException;

}
