package com.hotcomm.community.common.service;

import com.hotcomm.community.common.bean.ui.home.KPIUM;
import com.hotcomm.community.common.bean.ui.home.PendingSituationUM;
import com.hotcomm.framework.web.exception.HKException;

public interface HomeService {

	/**
	 * 获取关键性能指标KPI(实有人口数、房屋楼栋数、实有车辆数、设备总数、单位总数、报警数、工单总数)
	 * @param cid
	 * @return KPIUM
	 * @throws HKException
	 */
	public KPIUM getKPI(Integer cid) throws HKException;

	/**
	 * 获取待处理情况(待处理工单、待处理上报警情、待处理人口报警、待处理设备报警、待处理车辆报警)
	 * @param cid
	 * @return PendingSituationUM
	 * @throws HKException
	 */
	public PendingSituationUM getPendingSituation(Integer cid) throws HKException;

}
