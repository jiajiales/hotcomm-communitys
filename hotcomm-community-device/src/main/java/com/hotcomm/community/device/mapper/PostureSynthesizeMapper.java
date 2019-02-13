package com.hotcomm.community.device.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectDeviceNumUM;
import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectFullStatisticsUM;
import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectRecentlySevenDaysUM;

public interface PostureSynthesizeMapper {

	public SelectDeviceNumUM selectDeviceNum(@Param("tableParams") Map<String, Object> tableParams);

	public List<SelectRecentlySevenDaysUM> selectRecentlySevenDays(
			@Param("tableParams") Map<String, Object> tableParams);

	public SelectFullStatisticsUM selectFullStatistics(@Param("tableParams") Map<String, Object> tableParams);

}
