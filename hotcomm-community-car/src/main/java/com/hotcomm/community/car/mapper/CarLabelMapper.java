package com.hotcomm.community.car.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarLabelDM;
import com.hotcomm.community.common.bean.ui.car.CarLabelUM;

public interface CarLabelMapper {
	
	public Page<CarLabelUM> pagelist(@Param("pa")Map<String, Object> map);
	
	public List<CarLabelUM> labelList(@Param("dynamicDbname")String dynamicDbname,@Param("labelTypeId")Integer labelTypeId);
	
	public boolean insert(CarLabelDM carLabelDM);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean update(CarLabelDM carLabelDM);
	
	public CarLabelUM detail(@Param("dynamicDbname")String dynamicDbname ,@Param("id")Integer id,@Param("name")String name);
	
	public List<Integer> selectLabelListByType(@Param("dynamicDbname")String dynamicDbname ,@Param("labelTypeId")Integer labelTypeId);
}
