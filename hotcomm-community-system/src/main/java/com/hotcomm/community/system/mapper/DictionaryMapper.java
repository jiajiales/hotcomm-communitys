package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.pa.system.DictionaryPA;
import com.hotcomm.community.common.bean.ui.system.DictionaryUM;

public interface DictionaryMapper {

	public List<DictionaryUM> getDictionary(@Param("dictionaryPA") DictionaryPA dictionaryPA);

	public List<DictionaryDM> getDictionaryListByType(@Param("type") String type);

}
