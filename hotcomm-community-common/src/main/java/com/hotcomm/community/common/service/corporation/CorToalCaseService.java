package com.hotcomm.community.common.service.corporation;

import java.util.List;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.ui.corporation.CorMonCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorToalCaseUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.exception.HKException;

public interface CorToalCaseService {

	/**
	 * 单位总况统计
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public  CorToalCaseUM selectToalCount(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 单位类型分布统计
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<CorTypeCountUM> selectCorTypeCount(Integer communityId) throws HKException;
	
	/**
	 * 查询重点单位分布
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public  List<CorTypeCountUM> selectAttentionCorCount(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 每月登记单位数量
	 * @param communityParams
	 * @return
	 * @throws HKException
	 */
	public List<CorMonCountUM> selectCorMonCount(CommunityParams communityParams)throws HKException;
}
