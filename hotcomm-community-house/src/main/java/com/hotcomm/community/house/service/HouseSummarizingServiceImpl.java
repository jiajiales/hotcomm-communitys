package com.hotcomm.community.house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotcomm.community.common.bean.en.house.HouseEN;
import com.hotcomm.community.common.bean.en.house.WatchPlaceEN.WaysEnum;
import com.hotcomm.community.common.bean.ui.house.BuildingStatistics;
import com.hotcomm.community.common.bean.ui.house.HouseStatistics;
import com.hotcomm.community.common.bean.ui.house.PlaceNumData;
import com.hotcomm.community.common.bean.ui.house.PlaceStatistics;
import com.hotcomm.community.common.bean.ui.house.RentRoomVacancyRate;
import com.hotcomm.community.common.bean.ui.house.RentSaleRoom;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.HouseSummarizingService;
import com.hotcomm.community.house.mapper.HouseSummarizingMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class HouseSummarizingServiceImpl extends BaseService implements HouseSummarizingService {
	@Autowired
	HouseSummarizingMapper houseSummarizingMapper;

	/**
	 * 房屋数据统计
	 */
	@Override
	public HouseStatistics getHouseStatistics(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return houseSummarizingMapper.getHouseStatistics(tableParams);
	}

	@Override
	public List<BuildingStatistics> getBuildingStatistics(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return houseSummarizingMapper.getBuildingStatistics(tableParams);
	}

	@Override
	public List<PlaceStatistics> getPlaceStatistics(Integer ways, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("ways", ways);
		tableParams.put("type", WaysEnum.getByValue(ways).getName());
		return houseSummarizingMapper.getPlaceStatistics(tableParams);
	}

	@Override
	public List<PlaceNumData> getPlaceNumData(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<PlaceNumData> placeNumData = houseSummarizingMapper.getPlaceNumData(tableParams);
		if (placeNumData.size()!=0) {
			return placeNumData;
		}
		return null;
	}

	@Override
	public List<RentRoomVacancyRate> getData(Integer communityId) throws HKException {
		List<RentRoomVacancyRate> list = new ArrayList<>();
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Map<String, Integer> data = houseSummarizingMapper.getData(tableParams);
		Number totalRoom = (Number) data.get("totalRoom");
		Number rentRoom = (Number) data.get("rentRoom");
		int unRentRoom = totalRoom.intValue() - rentRoom.intValue();
		if (totalRoom.intValue() != 0) {
			list.add(new RentRoomVacancyRate("已出租房屋", rentRoom.intValue(),
					(int) ((rentRoom.doubleValue() / totalRoom.doubleValue() * 1.0) * 100) / 100.0));
			list.add(new RentRoomVacancyRate("未出租房屋", unRentRoom,
					(int) ((unRentRoom / totalRoom.doubleValue() * 1.0) * 100) / 100.0));
		}
		return list;
	}

	@Override
	public List<RentSaleRoom> getRentSaleRoom(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<RentSaleRoom> list = houseSummarizingMapper.getRentSaleRoom(tableParams);
		for (RentSaleRoom rentSaleRoom : list) {
			int rentOrSale = Integer.parseInt(rentSaleRoom.getRentOrSale());
			rentSaleRoom.setRentOrSale(HouseEN.RoomRentOrSale.getByValue(rentOrSale).getName());
		}
		return list;
	}

}
