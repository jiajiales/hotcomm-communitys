package com.hotcomm.community.corporation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.ui.corporation.CorMonCountUM;
import com.hotcomm.community.common.bean.ui.corporation.CorToalCaseUM;
import com.hotcomm.community.common.bean.ui.corporation.CorTypeCountUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.corporation.CorToalCaseService;
import com.hotcomm.community.common.service.corporation.CorporationService;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 单位总况
 * @author: lhx
 * @date: 2019-01-30 16:02
 **/
@Service
public class CorToalCaseServiceImpl extends BaseService implements CorToalCaseService {

	@Autowired
	private CorporationService corporationService;
	
	@Autowired
	private CarRegService carRegService;

	@Override
	public CorToalCaseUM selectToalCount(CorporationPA corporationPA) throws HKException {
		CorToalCaseUM corToalCaseUM = new CorToalCaseUM();
		//查询单位总数
		Integer corporationCount =  corporationService.selectCorTotal(corporationPA);
		corToalCaseUM.setCorporationCount(corporationCount);
		//查询单位相关人口数
		Integer corPerCount = corporationService.selectCorPerTotal(corporationPA.getCommunityId(),corporationPA.getId());
		corToalCaseUM.setCorPerCount(corPerCount);
		//重点关注单位总数
		corporationPA.setLabelTypeId(1);
		Integer attenCorCount = corporationService.selectCorTotal(corporationPA);
		corToalCaseUM.setAttenCorCount(attenCorCount);
		//单位车辆数
		Integer corCarCount =  carRegService.selectCorCarCount(super.getTableParams(corporationPA.getCommunityId()).get("dynamic_dbname").toString());
		corToalCaseUM.setCorCarCount(corCarCount);
		return corToalCaseUM;
	}

	@Override
	public List<CorTypeCountUM> selectCorTypeCount(Integer communityId) throws HKException {
		return corporationService.selectCorTypeGroup(super.getTableParams(communityId).get("dynamic_dbname").toString());
	}

	@Override
	public  List<CorTypeCountUM> selectAttentionCorCount(CorporationPA corporationPA) throws HKException {
		return corporationService.selectCorTypeCount(corporationPA);
	}

	@Override
	public List<CorMonCountUM> selectCorMonCount(CommunityParams communityParams) throws HKException {
		return corporationService.selectCorMonCount(communityParams);
	}

}
