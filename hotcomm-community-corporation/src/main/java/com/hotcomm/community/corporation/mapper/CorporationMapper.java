package com.hotcomm.community.corporation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.corporation.CorPersonRelationDM;
import com.hotcomm.community.common.bean.db.corporation.CorporationDM;
import com.hotcomm.community.common.bean.ui.corporation.CorMonCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorPerRelationUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorporationUM;

public interface CorporationMapper {

	public Page<CorporationUM> pagelist(@Param("pa")Map<String, Object> map);
	
	public List<CorporationUM> corlist(@Param("dynamicDbname")String  dynamicDbname,@Param("corName")String  corName);
	
	public Integer insert(CorporationDM corporationDM);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean deleteCorPersonById(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean update(CorporationDM corporationDM);
	
	public CorporationUM detail(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id,@Param("corName")String corName);
	
	public Integer selectCorTotal(@Param("map")Map<String, Object> map);
	
	public boolean insertCorPerRelationBatch(@Param("dynamicDbname")String dynamicDbname,@Param("list")List<CorPersonRelationDM> list);
	
	public Page<CorPerRelationUM> selectCorPersonList(@Param("pa")Map<String, Object> map);
	
	public List<CorPerRelationUM> selectPersonList(@Param("dynamicDbname")String dynamicDbname,@Param("corporationId")Integer corporationId,
			@Param("personName")String personName);
	
	public Integer selectCorPerTotal(@Param("dynamicDbname")String dynamicDbname,@Param("corporationId")Integer corporationId);
	
	public List<CorTypeCountUM> selectCorTypeGroup(@Param("dynamicDbname")String dynamicDbname);
	
	public List<CorTypeCountUM> selectCorTypeCount(@Param("dynamicDbname")String dynamicDbname,@Param("typeId")Integer typeId);
	
	public List<CorMonCountUM> selectCorMonCount(@Param("dynamicDbname")String dynamicDbname,@Param("year")Integer year);
	
	public boolean  updateCorPerRelation(CorPersonRelationDM corPersonRelationDM);
	
	public CorPersonRelationDM selectCorPerRelation(CorPersonRelationDM corPersonRelationDM);
	
	
	
}
