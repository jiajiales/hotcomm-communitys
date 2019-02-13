package com.hotcomm.community.system.mapper;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.system.NoticeDM;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface NoticeMapper {

	    public boolean insert(NoticeDM noticeDM);
	    
	    public boolean delete(@Param("id") int id);
	    
	    public boolean update(NoticeDM notice);
	    
	    public NoticeDM load(@Param("id") int id);
	    
		public Page<NoticeDM> pageList(Map<String, Object> map);
		
}
