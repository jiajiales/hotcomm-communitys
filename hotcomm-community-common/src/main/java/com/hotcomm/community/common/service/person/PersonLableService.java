package com.hotcomm.community.common.service.person;

import java.util.List;

import com.hotcomm.community.common.bean.db.person.LableTagDM;
import com.hotcomm.community.common.bean.pa.person.PersonLablePagePA;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface PersonLableService  {
	
	/**
	 * 标签列表
	 * @param params
	 * @return
	 * @throws HKException
	 */
	public PageInfo<PersonLableUM> page(PersonLablePagePA params)throws HKException;
	
	/**
	 * 标签详情
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public PersonLableUM getPersonLableInfo(Integer communityId,Integer id)throws HKException;
	
	/**
	 * 删除标签
	 * @param communityId	社区id
	 * @param id	标签id
	 * @return
	 * @throws HKException
	 */
	public Integer delPersonLable(Integer communityId,Integer id)throws HKException;
	
	/**
	 * 修改人口标签
	 * @param communityId 社区id
	 * @param id	标签id
	 * @param typeCode	标签类型
	 * @param name	标签名称
	 * @param description	标签说名
	 * @return
	 * @throws HKException
	 */
	public Integer updatePersonLable(Integer communityId,Integer id,Integer typeCode,String name,String description)throws HKException;

	/**
	 * 修改人口标签
	 * @param communityId 社区id
	 * @param id	标签id
	 * @param uid	修改的id
	 * @return
	 * @throws HKException
	 */
	public Integer updatePersonLableId(Integer communityId,Integer id,Integer uid)throws HKException;
	
	/**
	 * 添加人口标签
	 * @param communityId 社区id
	 * @param createUser	创建人
	 * @param typeCode	标签类型
	 * @param name	标签名称	
	 * @param description	标签说明
	 * @return
	 * @throws HKException
	 */
	public Integer insertPersonLable(Integer communityId,Integer createUser,Integer typeCode,String name,String description)throws HKException;
	
	/**
	 * 所有标签
	 * @param communityId
	 * @return
	 */
	public List<LableTagDM> selectLable(Integer communityId)throws HKException;
}
