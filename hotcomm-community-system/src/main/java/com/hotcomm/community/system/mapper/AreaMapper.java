package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.system.AreaDM;
import com.hotcomm.community.common.bean.pa.system.AreaPA;

public interface AreaMapper{
	
	public List<AreaDM> getAreaListByLevel(@Param("areaPa")AreaPA areaPa);
	
}
