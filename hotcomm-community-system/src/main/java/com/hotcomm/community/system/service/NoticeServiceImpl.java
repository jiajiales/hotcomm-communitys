package com.hotcomm.community.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.system.NoticeDM;
import com.hotcomm.community.common.bean.pa.system.NoticePagePA;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.NoticeService;
import com.hotcomm.community.system.mapper.NoticeMapper;
import com.hotcomm.framework.comm.PageInfo;

@Service
public class NoticeServiceImpl extends BaseService implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public boolean insert(NoticeDM notice) {
		return noticeMapper.insert(notice);
	}

	@Override
	public boolean delete(int id) {
		return noticeMapper.delete(id);
	}

	@Override
	public boolean update(NoticeDM notice) {
		return noticeMapper.update(notice);
	}

	@Override
	public NoticeDM load(int id) {
		return noticeMapper.load(id);
	}

	@Override
	public PageInfo<NoticeDM> pageList(NoticePagePA noticePagePA) {
		Map<String, Object> map = new HashMap<>();
		map.put("communityid", noticePagePA.getCommunityId());
		map.put("startTime", noticePagePA.getStartTime());
		map.put("endTime", noticePagePA.getEndTime());
		Page<NoticeDM> page =noticeMapper.pageList(map);
		List<NoticeDM> noticeList   = page; 
		return  new PageInfo<>(page.getTotal(), noticeList,noticePagePA.getPageSize(),noticePagePA.getPageIndex());
	}

}
