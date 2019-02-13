package com.hotcomm.community.house.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.ui.house.BuildingStatistics;
import com.hotcomm.community.common.bean.ui.house.HouseStatistics;
import com.hotcomm.community.common.bean.ui.house.PlaceNumData;
import com.hotcomm.community.common.bean.ui.house.PlaceStatistics;
import com.hotcomm.community.common.bean.ui.house.RentSaleRoom;

public interface HouseSummarizingMapper {
	HouseStatistics getHouseStatistics(@Param("tableParams") Map<String, Object> tableParams);

	List<BuildingStatistics> getBuildingStatistics(@Param("tableParams") Map<String, Object> tableParams);

	List<PlaceStatistics> getPlaceStatistics(@Param("tableParams") Map<String, Object> tableParams);

	List<PlaceNumData> getPlaceNumData(@Param("tableParams") Map<String, Object> tableParams);

	Map<String, Integer> getData(@Param("tableParams") Map<String, Object> tableParams);

	List<RentSaleRoom> getRentSaleRoom(@Param("tableParams") Map<String, Object> tableParams);
}
