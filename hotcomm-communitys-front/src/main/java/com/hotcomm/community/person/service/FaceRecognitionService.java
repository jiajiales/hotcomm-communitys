package com.hotcomm.community.person.service;

import java.util.List;

import com.hotcomm.community.common.bean.ui.person.PeopleFaceStatisticsUM;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfDayUM;
import com.hotcomm.community.common.bean.ui.person.SpecialCrowdFaceRecUM;
import com.hotcomm.framework.web.exception.HKException;

public interface FaceRecognitionService {

	/**
	 * 人脸感知相关信息统计
	 * @param communityId 社区id
	 * @return
	 * @throws HKException
	 */
	PeopleFaceStatisticsUM getPersonFaceRec(Integer communityId)throws HKException;
	
	/**
	 * 特殊人群人脸感知情况
	 * @param communityId 社区id
	 * @return
	 * @throws HKException
	 */
	SpecialCrowdFaceRecUM getSpecialPersonRec(Integer communityId)throws HKException;
	
	/**
	 * 今日人脸通行情况
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	List<PersonPassInfoOfDayUM> getFaceFellInfoOfDay(Integer communityId)throws HKException;
	
	/**
	 * 人脸感知消息推送
	 * @param mes
	 */
	void FaceFeelMessageSend(PersonFaceMessageSendUM mes);
}
