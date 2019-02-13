package com.hotcomm.community.common.service.system;

import com.hotcomm.community.common.bean.db.system.NoticeDM;
import com.hotcomm.community.common.bean.pa.system.NoticePagePA;
import com.hotcomm.framework.comm.PageInfo;

public interface NoticeService {
		
		/**
		 * 新增通知
		 * @param notice
		 * @return
		 */
	    public boolean insert(NoticeDM notice);

	    /**
	     * 删除通知
	     * @param id
	     * @return
	     */
	    public boolean delete(int id);

	    /**
	     * 修改通知
	     * @param notice
	     * @return
	     */
	    public boolean update(NoticeDM notice);

	    /**
	     * 查询通知（根据Id）
	     * @param id
	     * @return
	     */
	    public NoticeDM load(int id);

	   /**
	    * 分页查询通知
	    * @param noticePagePA
	    * @return
	    */
	    public PageInfo<NoticeDM> pageList(NoticePagePA noticePagePA);
	    

}
