package com.hotcomm.community.system.mapper;


import com.hotcomm.community.common.bean.db.quartz.QuartzDM;
import com.hotcomm.community.common.bean.pa.quartz.QuartzPA;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;


public interface QuartzMapper {

	public Page<QuartzDM> pagelist(@Param("params")QuartzPA params);
	
	public Long pageCount(@Param("params")QuartzPA params);
}
