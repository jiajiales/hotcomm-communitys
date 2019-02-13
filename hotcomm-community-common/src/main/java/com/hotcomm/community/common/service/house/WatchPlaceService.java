package com.hotcomm.community.common.service.house;

import java.util.List;

import com.hotcomm.community.common.bean.pa.house.WatchPlacPA;
import com.hotcomm.community.common.bean.pa.house.WatchPlacePagePA;
import com.hotcomm.community.common.bean.ui.house.WatchPlaceUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface WatchPlaceService {

	/**
	 * 关注场所分页列表（危险和服务场所）
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	PageInfo<WatchPlaceUM> pageData(WatchPlacePagePA params) throws HKException;

	/**
	 * 查看场所详情
	 * 
	 * @param id
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	WatchPlaceUM detailsData(Integer id, Integer communityId,Integer ways) throws HKException;

	/**
	 * 添加场所
	 * 
	 * @param params
	 * @throws HKException
	 */
	void addData(WatchPlacPA params) throws HKException;

	/**
	 * 修改场所信息
	 * 
	 * @param params
	 * @throws HKException
	 */
	void updateData(WatchPlacPA params) throws HKException;

	/**
	 * 修改场所信息
	 * 
	 * @param id
	 * @param communityId
	 * @throws HKException
	 */
	void delData(Integer id, Integer communityId) throws HKException;

	/**
	 * 判断场所是否存在
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	Boolean isExistPlace(WatchPlacPA params) throws HKException;
	
	/**
	 * 态势分析（关注场所列表）
	 * @param params
	 * @return
	 * @throws HKException
	 */
	List<WatchPlaceUM> getPlaceList(Integer communityId) throws HKException;

}
