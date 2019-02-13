package com.hotcomm.community.person.service;

import java.util.List;

import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.RecordDataStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.RecordNumByTypeUM;
import com.hotcomm.community.common.bean.ui.person.RecordStatisticsUM;
import com.hotcomm.framework.web.exception.HKException;

public interface RecordReactionService {

	/**
	 * 通行基础数据统计
	 * @param communityId	社区id
	 * @return
	 */
	RecordDataStatisticsUM getRecordDataStatistics(Integer communityId)throws HKException;
	
	/**
	 * 开门方式
	 * @param communityId	社区id
	 * @return
	 * @throws HKException
	 */
	List<RecordNumByTypeUM> getRecordTotalByType(Integer communityId)throws HKException;
	
	/**
	 * 今日进出通行统计
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	RecordStatisticsUM getRecordNumber(Integer communityId)throws HKException;
	
	/**
	 * 通行感知消息推送
	 * @param mes
	 */
	void RecordFeelMessageSend(PersonFaceMessageSendUM mes);
}