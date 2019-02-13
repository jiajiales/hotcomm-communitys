package com.hotcomm.community.person.service;

import java.util.List;

import com.hotcomm.community.common.bean.ui.person.PassRealTimeUM;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonFloorsStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfMonUM;
import com.hotcomm.community.common.bean.ui.person.PopulationByLableUM;
import com.hotcomm.community.common.bean.ui.person.PopulationTrendUM;

public interface PopulationPostureService {

	/**
	 * 本年通行情况
	 * @param communityId
	 * @return
	 */
	public List<PersonPassInfoOfMonUM> getPassInfoOfYear(Integer communityId);
	
	/**
	 * 人口趋势
	 * @param communityId
	 * @return
	 */
	List<PopulationTrendUM> getPopulationTrend(Integer communityId);
	
	/**
	 * 各类人群统计
	 * @param communityId
	 * @return
	 */
	PopulationByLableUM getLalePopulationRatio(Integer communityId);
	
	/**
	 * 常住人群统计
	 * @param communityId
	 * @return
	 */
	List<PersonFloorsStatisticsUM> getPersonNumByfloors(Integer communityId);
	
	/**
	 * 通行实时情况(今日感知人次数|关爱人口本月警报次数|黑名单本月警报次数)
	 * @param communityId
	 * @return
	 */
	PassRealTimeUM getPassRealTime(Integer communityId);
	/**
	 * 人口态势消息推送
	 * @param mes
	 */
	void PersonMessageSend(PersonFaceMessageSendUM mes);
	
}
