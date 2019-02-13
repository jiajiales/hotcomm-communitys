package com.hotcomm.community.common.service.corporation;

import java.util.List;

import com.hotcomm.community.common.bean.db.corporation.CorLabelTypeDM;
import com.hotcomm.community.common.bean.pa.corporation.CorLabelTypePA;
import com.hotcomm.community.common.bean.ui.corporation.CorLabelTypeUM;
import com.hotcomm.framework.web.exception.HKException;

public interface CorLabelTypeService {

	/**
	 * 新增单位标签类型
	 * @param corLabelTypePA
	 * @return
	 * @throws HKException
	 */
	public Integer insert(CorLabelTypePA corLabelTypePA) throws HKException;

	/**
	 * 删除单位标签类型
	 * @param communityId
	 * @param id
	 * @return
	 * @throws HKException
	 */
	public boolean delete(Integer communityId,Integer id) throws HKException;

	/**
	 * 查看单位标签类型
	 * @param corLabelTypePA
	 * @return
	 * @throws HKException
	 */
	public  CorLabelTypeDM detail(CorLabelTypePA corLabelTypePA) throws HKException;
	
	/**
	 * 查看单位标签类型列表
	 * @param corLabelTypePA
	 * @return
	 * @throws HKException
	 */
	public List<CorLabelTypeUM> list(CorLabelTypePA corLabelTypePA) throws HKException;
	
}
