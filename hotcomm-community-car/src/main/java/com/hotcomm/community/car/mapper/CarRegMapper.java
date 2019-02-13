package com.hotcomm.community.car.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarRegDM;
import com.hotcomm.community.common.bean.db.car.CarTypeCountDM;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarRegUM;

public interface CarRegMapper {
	
	public Page<CarRegUM> pagelist(@Param("pa")Map<String, Object> tableParams);
	
	public boolean insert(CarRegDM carRegDM);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id) ;
	
	public boolean update(CarRegDM carRegDM);
	
	public boolean  deleteCarPerson(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public boolean updateAlarmCountById(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id,
			@Param("alarmCount")Integer alarmCount);
	
	public CarRegDetailUM detail(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id,
			@Param("num")String num);
	
	public Integer selectCount(Map<String, Object> map);
	
	public Integer selectCorCarCount(@Param("dynamicDbname")String dynamicDbname);
	
	public List<CarTypeCountDM> selectTypeCount(Map<String, Object> map);
}
