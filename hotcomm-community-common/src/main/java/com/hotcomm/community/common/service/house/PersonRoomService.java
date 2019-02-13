package com.hotcomm.community.common.service.house;

import java.util.List;

import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPagePA;
import com.hotcomm.community.common.bean.ui.house.PersonRoomUM;
import com.hotcomm.community.common.bean.ui.house.RelationRoomUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface PersonRoomService {

	/**
	 * 关联人口分页列表
	 * 
	 * @param params
	 * @return
	 * @throws HKException
	 */
	PageInfo<PersonRoomUM> pageData(PersonRoomPagePA params) throws HKException;

	/**
	 * 新增人房关联
	 * 
	 * @param pId
	 * @param roomId
	 * @param communityId
	 * @throws HKException
	 */
	void addData(PersonRoomPA params) throws HKException;

	/**
	 * 删除关联关系
	 * 
	 * @param id
	 * @param communityId
	 * @throws HKException
	 */
	void delData(Integer id, Integer communityId) throws HKException;

	/**
	 * 车辆关联，根据人名查询房间
	 * 
	 * @param communityId
	 * @param name
	 * @return
	 * @throws HKException
	 */
	List<RelationRoomUM> getRelationRoomUMs(Integer communityId, String name) throws HKException;
	
	/**
	 * 是否存在关联数据
	 * @param communityId
	 * @param pId
	 * @param roomId
	 * @return
	 */
	Boolean isEixst(Integer communityId,Integer pId,Integer roomId);

}
