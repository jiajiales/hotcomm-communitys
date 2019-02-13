package com.hotcomm.community.common.service.quartz;


import com.hotcomm.community.common.bean.db.quartz.QuartzDM;
import com.hotcomm.community.common.bean.pa.quartz.QuartzPA;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface QuartzService {
	
	/**
	 * 分页查询任务列表
	 * @return
	 * @throws HKException
	 */
	public PageInfo<QuartzDM> page(QuartzPA quartzPA) throws HKException;
	
	/**
	 * 新增或修改任务
	 * @param quartz
	 * @return
	 * @throws HKException
	 */
	public boolean insertOrUpdate(QuartzDM quartz) throws HKException;
	
	/**
	 * 删除任务
	 * @param quartz
	 * @return
	 * @throws HKException
	 */
	public boolean delete(QuartzDM quartz)  throws HKException;
	
    /**
     * 暂停任务
     * @param quartz
     * @return
     * @throws HKException
     */
	public boolean pause(QuartzDM quartz) throws HKException;
	
	/**
	 * 恢复任务
	 * @param quartz
	 * @return
	 * @throws HKException
	 */
	public boolean resume(QuartzDM quartz) throws HKException;
}
