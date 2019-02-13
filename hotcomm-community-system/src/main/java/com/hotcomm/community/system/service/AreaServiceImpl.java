package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.AreaDM;
import com.hotcomm.community.common.bean.pa.system.AreaPA;
import com.hotcomm.community.common.service.system.AreaService;
import com.hotcomm.community.system.mapper.AreaMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaMapper areaMapper;

	@Override
	public List<AreaDM> getAreaListByLevel(AreaPA areaPa) throws HKException {
		if (areaPa.getLevel() != null || areaPa.getParentId() != null)
			return areaMapper.getAreaListByLevel(areaPa);

		return new ArrayList<AreaDM>();
	}

}
