package com.hotcomm.community.common.service.corporation;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPA;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelPagePA;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelUM;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface CorLabelService {

	/**
	 * 分页查询单位标签列表
	 * @param corLabelPA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<CorLabelUM> page(CorLabelPagePA corLabelPagePA) throws HKException;
	
	/**
	 * 新增单位标签
	 * @param corLabelPA
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CorLabelPA corLabelPA) throws HKException;
	
	/**
	 * 删除单位标签
	 * @param corLabelDM
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer id) throws HKException;
	
	/**
	 * 修改单位标签
	 * @param corLabelPA
	 * @return
	 * @throws HKException
	 */
	public boolean update(CorLabelPA corLabelPA) throws HKException;
	
	/**
	 * 查询单位标签详情
	 * @param communityId,id,labelName
	 * @return
	 * @throws HKException
	 */
	public CorLabelUM  detail(Integer communityId,Integer id ,String labelName) throws HKException;
}
