package com.hotcomm.community.car.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.ui.car.CarLabelRelationUM;

public interface CarLabelRelationMapper {

	public boolean insert(CarLabelRelationDM carLabelRelationDM);
	
	public CarLabelRelationUM detail(CarLabelRelationDM carLabelRelationDM);
	
	public boolean insertBatch(@Param("dynamicDbname")String dynamicDbname, @Param("list")List<CarLabelRelationDM> list);
	
	public boolean delete(CarLabelRelationDM carLabelRelationDM);
	
	public boolean update(CarLabelRelationDM carLabelRelationDM);
	
	public boolean deleteByCarNum(CarLabelRelationDM carLabelRelationDM);	
	
	public ArrayList<String> selectLabelByCarNum(Map<String, Object> map);
	
	public ArrayList<Integer> selectLabelIdByCarNum(Map<String, Object> map);
	
	public Integer  selectBlackCarByLabelId(@Param("dynamicDbname")String dynamicDbname,@Param("list")List<Integer> list);
	
	public Integer selectTodayBlackCarByLabelId(@Param("dynamicDbname")String dynamicDbname,@Param("list")List<Integer> list,@Param("day")String day);
}
