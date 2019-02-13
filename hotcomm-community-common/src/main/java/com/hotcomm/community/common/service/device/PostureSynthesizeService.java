package com.hotcomm.community.common.service.device;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectDeviceNumUM;
import com.hotcomm.community.common.bean.ui.device.postureSynthesize.SelectRecentlySevenDaysUM;

public interface PostureSynthesizeService {
	/**
	 * 地图中间设备总数，离线，报警数统计
	 * 
	 * @param communityId
	 * @return
	 */
	public SelectDeviceNumUM selectDeviceNum(Integer communityId);

	/**
	 * 最近七天消防报警数量统计
	 * 
	 * @param communityId
	 * @return
	 */
	public List<SelectRecentlySevenDaysUM> selectRecentlySevenDays(Integer communityId);

	/**
	 * 综合态势分析中间地图数据
	 * 
	 * @param communityId
	 * @return
	 */
	public Map<String, Object> fullStatistics(Integer communityId);
}
