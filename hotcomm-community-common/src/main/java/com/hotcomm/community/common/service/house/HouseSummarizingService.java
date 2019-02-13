package com.hotcomm.community.common.service.house;

import java.util.List;

import com.hotcomm.community.common.bean.ui.house.BuildingStatistics;
import com.hotcomm.community.common.bean.ui.house.HouseStatistics;
import com.hotcomm.community.common.bean.ui.house.PlaceNumData;
import com.hotcomm.community.common.bean.ui.house.PlaceStatistics;
import com.hotcomm.community.common.bean.ui.house.RentRoomVacancyRate;
import com.hotcomm.community.common.bean.ui.house.RentSaleRoom;
import com.hotcomm.framework.web.exception.HKException;

public interface HouseSummarizingService {

	/**
	 * 房屋数据统计，楼栋数、总建筑面积、住宅型房间数、出租屋数、隐患场所、服务场所
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	HouseStatistics getHouseStatistics(Integer communityId) throws HKException;

	/**
	 * 房屋楼栋数统计
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<BuildingStatistics> getBuildingStatistics(Integer communityId) throws HKException;

	/**
	 * 隐患场所统计、服务场所统计
	 * 
	 * @param ways
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<PlaceStatistics> getPlaceStatistics(Integer ways, Integer communityId) throws HKException;

	/**
	 * 隐患、服务场所情况
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<PlaceNumData> getPlaceNumData(Integer communityId) throws HKException;

	/**
	 * 出租屋空置率
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<RentRoomVacancyRate> getData(Integer communityId) throws HKException;

	/**
	 * 住宅型房屋租赁和购买数情况
	 * 
	 * @return
	 * @throws HKException
	 */
	List<RentSaleRoom> getRentSaleRoom(Integer communityId) throws HKException;

}
