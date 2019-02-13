package com.hotcomm.community.common.service.person;


import java.util.List;

import com.hotcomm.community.common.bean.db.person.PersonRecordDM;
import com.hotcomm.community.common.bean.db.person.PersonRecordInfoDM;
import com.hotcomm.community.common.bean.db.person.RecordNumDM;
import com.hotcomm.community.common.bean.pa.person.PersonRecordPA;
import com.hotcomm.community.common.bean.ui.person.DeviceMacByRecord;
import com.hotcomm.community.common.bean.ui.person.PersonFaceMessageSendUM;
import com.hotcomm.community.common.bean.ui.person.PersonLast50RecordUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface PersonRecordService {

	/**
	 * 通行列表(分页)
	 * @param params
	 * @return
	 */
	public PageInfo<PersonRecordDM> PersonRecordPage(PersonRecordPA params);
	
	/**
	 * 通行详情
	 * @param id 通行记录id
	 * @param communityId
	 * @return
	 */
	public PersonRecordInfoDM PersonRecordInfo(Integer id,Integer communityId);
	
	/**
	 * 删除通行记录
	 * @param id 通行记录id
	 * @param communityId
	 * @return
	 */
	public Integer delPersonRecord(Integer id,Integer communityId); 
	
	/**
	 * 根据用户id与时间筛选通行记录
	 * @param communityId
	 * @param pId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	
	public PersonRecordDM getPersonRecordByPId(Integer communityId,Integer pId,String beginTime,String endTime);
	
	/**
	 * 根据id 和预选时间段 查询通行次数
	 * @param communityId
	 * @param pId
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws HKException
	 */
	public List<RecordNumDM> selectRecordNum(Integer communityId,Integer pId,String beginTime,String endTime)throws HKException;
	
	/**
	 * 添加通行记录
	 * @param type [必填] 1 : 摄像头  2:门禁
	 * @param pNo	(第三方平台的)人员编号
	 * @param record_time [必填]发生时间
	 * @param img	图片
	 * @param video	视频
	 * @param record_type [必填] 记录方式,2 门禁人脸开门 3 门卡开门  4 手机APP开门 5 密码开门 	
	 * @param deviceMac		[必填]设备mac地址
	 * @return
	 */
	Integer addPersonRecord(Integer type,
							String pNo,
							String record_time,
							String img,
							String video,
							Integer record_type,
							String deviceMac);
	
	/**
	 * 查询最后50条通行记录
	 * @param communityId
	 * @param type  1人脸/2通行
	 * @return
	 */
	List<PersonLast50RecordUM> getRecordLast50(Integer communityId,Integer type);
	
	/**
	 * 人脸感知消息推送
	 * @param mes
	 */
	void RecordMessageSend(PersonFaceMessageSendUM mes);

	/**
	 * 根据pid/faceNo 获取最近1个月通行被记录的设备mac 与最新时间
	 * @param communityId
	 * @param faceNo
	 * @param pId
	 * @return
	 */
	List<DeviceMacByRecord> getDeviceMacByRecord(Integer communityId, String faceNo, Integer pId);
}
