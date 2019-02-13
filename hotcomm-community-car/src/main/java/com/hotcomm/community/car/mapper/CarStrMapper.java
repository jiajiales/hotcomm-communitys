package com.hotcomm.community.car.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;

public interface CarStrMapper {
	
	public Page<CarStrUM> pagelist(@Param("pa")Map<String, Object> params);
	
	public Integer insert(CarStrDM carStrDM);
	
	public CarStrUM detail(CarStrPA carStrPA);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean update(CarStrDM carStrDM);
	
	public List<CarStrUM>  selectLongParkingCarList(Map<String , Object> map);
	
	public List<CarStrUM> selectNightCarList(Map<String , Object> map);
	
	public List<CarStrUM> selectNightCarList2(Map<String , Object> map);
	
	public Integer selectYearCount(@Param("dynamicDbname")String dynamicDbname);
}
