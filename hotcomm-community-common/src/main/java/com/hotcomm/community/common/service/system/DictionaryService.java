package com.hotcomm.community.common.service.system;

import java.util.List;

import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.pa.system.DictionaryPA;
import com.hotcomm.community.common.bean.ui.system.DictionaryUM;
import com.hotcomm.framework.web.exception.HKException;

public interface DictionaryService {

	/**
	 * 获取字典键值对
	 * @param dictionaryPA
	 * @return List
	 * @throws HKException
	 */
	public List<DictionaryUM> getDictionary(DictionaryPA dictionaryPA) throws HKException;

	/**
	 * 根据type获取字典键值对集合
	 * @param type
	 * @return List
	 * @throws HKException
	 */
	public List<DictionaryDM> getDictionaryListByType(String type) throws HKException;

}
