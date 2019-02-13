package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.db.system.AreaDM;
import com.hotcomm.community.common.bean.pa.system.AreaPA;
import com.hotcomm.framework.web.exception.HKException;

public interface AreaService {

	/**
	 * 获取区域列表
	 * @param areaPa
	 * @return List
	 * @throws HKException
	 */
	public List<AreaDM> getAreaListByLevel(AreaPA areaPa) throws HKException;

}
