package com.hotcomm.community.corporation.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.corporation.CorLabelDM;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;

public interface CorLabelMapper {

	public Page<CorLabelUM> pagelist(@Param("pa")Map<String, Object> map);
	
	public  Integer insert(CorLabelDM corLabelDM);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean update(CorLabelDM corLabelDM);
	
	public CorLabelUM detail(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id,@Param("labelName")String labelName);
	
	public CorTypeCountUM selectCorTypeGroup(@Param("dynamicDbname")String dynamicDbname);
}
