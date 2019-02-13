package com.hotcomm.community.common.service.person;

import java.util.List;
import java.util.Map;


import com.hotcomm.community.common.bean.db.person.PersonAlarmByLevelDM;
import com.hotcomm.community.common.bean.db.person.PersonAlarmTypeDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonIDAndNoDM;
import com.hotcomm.community.common.bean.pa.person.AddPersonPA;
import com.hotcomm.community.common.bean.pa.person.PersonPagePA;
import com.hotcomm.community.common.bean.pa.person.PopulationRoomPagePA;
import com.hotcomm.community.common.bean.pa.person.PersonStrangerPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.person.LablePopulationUM;
import com.hotcomm.community.common.bean.ui.person.PersonClassificationUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfDayUM;
import com.hotcomm.community.common.bean.ui.person.PersonPassInfoOfMonUM;
import com.hotcomm.community.common.bean.ui.person.PersonStrangerListUM;
import com.hotcomm.community.common.bean.ui.person.PopulationSituationUM;
import com.hotcomm.framework.comm.PageInfo;

public interface PersonService {

	/**
	 * 后台总况----人口情况
	 * @param communityId
	 * @return
	 */
	public Map<String,Integer> getThePopulation(Integer communityId);
	/**
	 * 人口总况 概况信息（区域总人数，今日通行人数，今日通行总次数，本月警报次数，关爱人群数，黑名单人群数）
	 * 
	 * @param communityId 社区id
	 * @return
	 */
	public PopulationSituationUM getPopulationSituation(Integer communityId);
	
	/**
	 * 关注人群情况
	 * @param communityId 社区id
	 * @return
	 */
	public LablePopulationUM getLablePopulationSituation(Integer communityId);
	
	/**
	 * 人口结构|人口户籍情况
	 * @param communityId 社区id
	 * @return
	 */
	public PersonClassificationUM getPersonClassification(Integer communityId);
	
	/**
	 * 警报情况(12月)
	 * @param communityId 社区id
	 * @return
	 */
	public List<PersonAlarmByLevelDM> getAlarmCondition(Integer communityId);
	
	/**
	 * 本月警报类型情况
	 * @param communityId
	 * @return
	 */
	public PersonAlarmTypeDM getAlarmOfTypeToMonth(Integer communityId);
	
	/**
	 * 今日通行情况
	 * @param communityId
	 * @return
	 */
	public List<PersonPassInfoOfDayUM> getPassInfoOfDay(Integer communityId);
	
	/**
	 * 本月通行情况
	 * @param communityId
	 * @return
	 */
	public List<PersonPassInfoOfMonUM> getPassInfoOfMon(Integer communityId);

	/**
	 * 本周通行情况
	 * @param communityId
	 * @return
	 */
	public Map<String,Object> getPassInfoOfWeek(Integer communityId);

	/**
	 * 居民信息(分页)
	 * @param params
	 * @return
	 */
	public PageInfo<PersonDM> getPersonPageList(PersonPagePA params);
	
	/**
	 * 陌生人信息
	 * @param communityId
	 * @param faceNo
	 * @return
	 */
	public PersonStrangerListUM StrangerInfo(Integer communityId,String faceNo);
	
	/**
	 * 删除陌生人信息
	 * @param id
	 * @return
	 */
	public Integer delPersonStranger(Integer communityId,Integer id);
	
	/**
	 * 陌生人转换
	 * @param PAparam
	 * @param id
	 * @return
	 */
	public Integer SetStrangerToPerson(AddPersonPA PAparam,Integer id);
	/**
	 * 居民详情
	 * @param communityId 社区id
	 * @param pId	用户id
	 * @return
	 */
	public PersonDM getPersonInfo(Integer communityId,Integer pId);
	
	/**
	 * 根据id删除居民
	 * @param communityId 社区id
	 * @param pId 用户id
	 * @return
	 */
	public Integer deletePersonMessage(Integer communityId,Integer pId);
	
	/**
	 * 添加居民
	 * @param PAparam
	 * @return
	 */
	public Integer addPersonMessage(AddPersonPA PAparam);
	
	/**
	 * 修改居民
	 * @param PAparam
	 * @param pId	用户id
	 * @return
	 */
	public Integer updatePersonMessage(AddPersonPA PAparam,Integer pId);
	
	/**
	 * 根据条件筛选出居民id与编号
	 * @param communityId
	 * @param Sage  开始年龄
	 * @param Eage  结束年龄
	 * @param sex   性别
	 * @param lableId   标签id(-2,查的是该小区居民且不为黑名单的)
	 * @param people    民族
	 * @param nationality
	 * @param name
	 * @return
	 */
	public List<PersonIDAndNoDM> getPersonIDAndNo(Integer communityId,Integer Sage, Integer Eage, Integer sex,Integer lableId,String people,String nationality,String name);
	
	/**
	 * 添加警报次数
	 * @param communityId
	 * @param pId 居民id
	 * @return
	 */
	public Integer updatePersonAlarmTime(Integer communityId,Integer pId);
	
	/**
	 * 关联房间分页
	 * @param pa
	 * @return
	 */
	public PageInfo<PersonRoomUM> PeopleRoomRePage(PopulationRoomPagePA pa);
	
	/**
	 * 根据人员编号查询人员信息
	 * @param communityId
	 * @param pNo	人员编号
	 * @return
	 */
	public PersonDM PersonInfoByNo(Integer communityId,String pNo);
	
	/**
	 * 陌生人页面(分页)
	 * @param pa
	 * @return
	 */
	public PageInfo<PersonStrangerListUM> PersonStrangerPage(PersonStrangerPagePA pa);
	
	/**
	 * 人房关联
	 * @param pa
	 * @return
	 */
	public Integer addPersonReRoom(com.hotcomm.community.common.bean.pa.house.PersonRoomPA pa);

	/**
	 * 综合作业页面人口信息
	 * @param communityId
	 * @return
	 */
	public Map<String,Integer> personInfoTask(Integer communityId);
}
