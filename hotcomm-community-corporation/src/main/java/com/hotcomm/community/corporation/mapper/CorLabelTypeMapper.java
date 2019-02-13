package com.hotcomm.community.corporation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.corporation.CorLabelTypeDM;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelTypeUM;

public interface CorLabelTypeMapper {

	public Integer insert(CorLabelTypeDM corLabelTypeDM);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean update(CorLabelTypeDM corLabelTypeDM);
	
	public CorLabelTypeDM detail(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id
			,@Param("typeName")String typeName,@Param("state")Integer  state);
	
	public List<CorLabelTypeUM> list(@Param("dynamicDbname")String dynamicDbname);
}
