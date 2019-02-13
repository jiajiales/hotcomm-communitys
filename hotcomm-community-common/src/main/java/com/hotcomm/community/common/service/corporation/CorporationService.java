package com.hotcomm.community.common.service.corporation;

import java.util.List;
import com.hotcomm.community.common.bean.pa.corporation.CorPerRelationListPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPagePA;
import com.hotcomm.community.common.bean.ui.corporation.CorMonCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorPerRelationUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorporationUM;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CorporationService {

	/**
	 * 分页查询单位列表
	 * @param corporationPagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CorporationUM> page(CorporationPagePA corporationPagePA) throws HKException;
	
	/**
	 * 查询单位列表
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public List<CorporationUM> list(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 新增单位
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 删除单位
	 * @param communityId,id
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer id) throws HKException;

	/**
	 * 删除单位人口关联关系
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public boolean deleteCorPersonById(Integer communityId,Integer id) throws HKException;
	
	/**
	 * 修改单位
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public boolean update(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 查询单位详情
	 * @param communityId,id,corName
	 * @return
	 * @throws HKException
	 */
	public CorporationUM detail(Integer communityId,Integer id,String corName) throws HKException;
	
	
	/**
	 * 单位统计
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public Integer selectCorTotal(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 批量新增单位人口关联
	 * @param corPerRelationListPA
	 * @return
	 * @throws HKException
	 */
	public boolean insertCorPerRelationBatch(CorPerRelationListPA corPerRelationListPA) throws HKException;
	
	/**
	 * 分页查询单位关联人员列表
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CorPerRelationUM> selectCorPersonList(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 查询单位关联人员列表
	 * @param corPerRelationListPA
	 * @return
	 * @throws HKException
	 */
	public List<CorPerRelationUM> selectPersonList(CorPerRelationListPA corPerRelationListPA) throws HKException;
	
	/**
	 * 单位人员统计
	 * @param communityId,id
	 * @return
	 * @throws HKException
	 */
	public Integer selectCorPerTotal(Integer communityId,Integer id) throws HKException;
	
	/**
	 * 单位类型统计
	 * @param dynamicDbname
	 * @return
	 * @throws HKException
	 */
	public List<CorTypeCountUM> selectCorTypeGroup(String dynamicDbname) throws HKException;
	
	/**
	 * 查询重点单位分布
	 * @param corporationPA
	 * @return
	 * @throws HKException
	 */
	public List<CorTypeCountUM> selectCorTypeCount(CorporationPA corporationPA) throws HKException;
	
	/**
	 * 每月登记单位数量
	 * @return
	 * @throws HKException
	 */
	public List<CorMonCountUM> selectCorMonCount(CommunityParams communityParams) throws HKException;
}
