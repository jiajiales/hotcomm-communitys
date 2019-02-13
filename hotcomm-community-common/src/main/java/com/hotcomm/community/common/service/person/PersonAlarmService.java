package com.hotcomm.community.common.service.person;

import java.util.List;


import com.hotcomm.community.common.bean.db.person.PersonAlarmInfoDM;
import com.hotcomm.community.common.bean.db.person.PopulationAlarmDM;
import com.hotcomm.community.common.bean.pa.person.PersonAlarmPA;
import com.hotcomm.framework.comm.PageInfo;

public interface PersonAlarmService {

	/**
	 * 警报列表(分页)
	 * @param params
	 * @return
	 */
	public PageInfo<PopulationAlarmDM> PersonAlarmPage(PersonAlarmPA params);
	
	/**
	 * 警报详情
	 * @param id
	 * @param communityId
	 * @return
	 */
	public PersonAlarmInfoDM PersonAlarmInfo(Integer id,Integer communityId);
	
	/**
	 * 删除警报记录
	 * @param id
	 * @param communityId
	 * @return
	 */
	public Integer delPersonAlarm(Integer id,Integer communityId);
	
	/**
	 * 添加警报数据
	 * @param communityId
	 * @return
	 */
	public void addPersonAlarm();
	
	/**
	 * 获取最后50条警报数据
	 * @param communityId
	 * @param type  1人脸报警/2通行
	 * @return
	 */
	public List<Object> getAlarmLast50(Integer communityId,Integer type);
}
