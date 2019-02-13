package com.hotcomm.community.common.service.person;

import java.util.List;

import com.hotcomm.community.common.bean.db.person.HoleAndDetailInfoDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDM;
import com.hotcomm.community.common.bean.pa.person.PersonHolePA;
import com.hotcomm.community.common.bean.pa.person.AddPersonHolePA;
import com.hotcomm.community.common.bean.ui.person.PersonHoleInfoToAllUM;
import com.hotcomm.community.common.bean.ui.person.PersonHoleInfoToOneUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface PersonHoleService {
	
	/**
	 * 布控列表分页
	 * @param params
	 * @return
	 */
	PageInfo<PersonHoleDM> PersonHolePage(PersonHolePA params )throws HKException;
	
	/**
	 * 启动/关闭布控
	 * @param id
	 * @param communityId
	 * @param onOff 0关闭 1开启
	 * @return
	 */
	Integer PersonHoleOnOff(Integer id,Integer communityId,Integer onOff)throws HKException;
	
	/**
	 * 删除布控
	 * @param id
	 * @param communityId
	 * @return
	 */
	Integer delPersonHole(Integer id,Integer communityId)throws HKException;
	
	
	/**
	 * (个人)布控详情
	 * @param id
	 * @Param pId
	 * @param communityId
	 * @return
	 */
	PersonHoleInfoToOneUM PersonHoleInfoByOne(Integer id,Integer communityId,Integer pId)throws HKException;
	
	/**
	 * (群体)布控详情
	 * @param id
	 * @param communityId
	 * @return
	 */
	PersonHoleInfoToAllUM PersonHoleInfoByAll(Integer id,Integer communityId)throws HKException;
	
	/**
	 * 添加预警布控
	 * @param pa
	 * @return
	 * @throws HKException
	 */
	Integer addPersonHole(AddPersonHolePA pa)throws HKException;
	
	/**
	 * 根据时间筛选出已启动的预警布控 与 规则信息
	 * @param communityId 
	 * @param time
	 * @return
	 */
	List<HoleAndDetailInfoDM> getHoleAndDetail(Integer communityId,String time)throws HKException;
	
	/**
	 * 修改预警布控
	 * @param pa
	 * @return
	 * @throws HKException
	 */
	Integer updatePersonHole(AddPersonHolePA pa)throws HKException;
}
