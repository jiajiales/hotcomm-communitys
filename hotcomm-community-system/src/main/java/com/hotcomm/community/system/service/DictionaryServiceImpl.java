package com.hotcomm.community.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.DictionaryDM;
import com.hotcomm.community.common.bean.pa.system.DictionaryPA;
import com.hotcomm.community.common.bean.ui.system.DictionaryUM;
import com.hotcomm.community.common.service.system.DictionaryService;
import com.hotcomm.community.system.mapper.DictionaryMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	DictionaryMapper dictionaryMapper;

	@Override
	public List<DictionaryUM> getDictionary(DictionaryPA dictionaryPA) throws HKException {
		return dictionaryMapper.getDictionary(dictionaryPA);
	}

	@Override
	public List<DictionaryDM> getDictionaryListByType(String type) throws HKException {
		return dictionaryMapper.getDictionaryListByType(type);
	}

}
