package com.hotcomm.community.car.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarPassRecordsDM;
import com.hotcomm.community.common.bean.ui.car.CarAttentionCountUM;
import com.hotcomm.community.common.bean.ui.car.CarFeelPassHoursCountUM;
import com.hotcomm.community.common.bean.ui.car.CarMonParkCountUM;
import com.hotcomm.community.common.bean.ui.car.CarMonTimeCountUM;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.bean.ui.car.CarPosturePakingYearUM;
import com.hotcomm.community.common.bean.ui.car.CarmMonEnterCountUM;

public interface CarPassRecordsMapper {

	public Page<CarPassRecordsUM> pagelist(@Param("pa")Map<String, Object> map);
	
	public CarPassRecordsUM detail(@Param("dynamicDbname")String dynamicDbname,@Param("id")Integer id);
	
	public Integer insert(CarPassRecordsDM carPassRecordsDM);
	
	public boolean delete(CarPassRecordsDM carPassRecordsDM) ;
	
	public boolean update(CarPassRecordsDM carPassRecordsDM);
	
	public Integer selectDayCount(Map<String, Object> map);
	
	public Integer selectDayCarCount(Map<String, Object> map);

	public Integer selectSenTimeCount(Map<String, Object> map);
	
	public Integer selectSenTimeCount1(Map<String, Object> map);
	
	public List<CarMonTimeCountUM> selectMonTimeCount(Map<String, Object> map);
	
	public List<CarMonParkCountUM> selectMonParkingCount(Map<String, Object> map);
	
	public List<CarAttentionCountUM> selectAttentionCount(Map<String, Object> map);
	
	public  List<CarmMonEnterCountUM>  selectMonEnterCount(Map<String, Object> map);
	
	public List<CarFeelPassHoursCountUM>  selectDistinctCarPassCount(@Param("dynamicDbname")String dynamicDbname,
			@Param("beginOfDate")String beginOfDate,@Param("today")String today);
	
	public List<CarFeelPassHoursCountUM>  selectCarPassCount(@Param("dynamicDbname")String dynamicDbname,
			@Param("beginOfDate")String beginOfDate,@Param("today")String today);
	
	public List<CarFeelPassHoursCountUM> selectMonYearCarPassCount(Map<String , Object> map);
	
	public List<CarMonParkCountUM> selectPostureParkingCount(Map<String , Object> map);
	
	public List<CarPosturePakingYearUM> selectPostureParkingYearCount(Map<String , Object> map);
	
	public List<CarAttentionCountUM> selectPosAttentionCount(Map<String, Object> map);
	
	public CarPassRecordsUM  selectNewCarAddress(Map<String, Object> map);
	
	public  List<CarPassRecordsUM> selectPassRecordList(Map<String, Object> map);

	public List<CarMonParkCountUM> selectPosCarWeekCount(@Param("dynamicDbname")String dynamicDbname);

	public List<CarMonParkCountUM> selectPosAllCarWeekCount(@Param("dynamicDbname")String dynamicDbname);

}
