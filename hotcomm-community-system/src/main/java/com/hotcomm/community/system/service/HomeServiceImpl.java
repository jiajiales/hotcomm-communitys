package com.hotcomm.community.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.bean.ui.device.fell.DeviceMsgCountUM;
import com.hotcomm.community.common.bean.ui.home.KPIUM;
import com.hotcomm.community.common.bean.ui.home.PendingSituationUM;
import com.hotcomm.community.common.service.HomeService;
import com.hotcomm.community.common.service.car.CarToalCaseService;
import com.hotcomm.community.common.service.corporation.CorToalCaseService;
import com.hotcomm.community.common.service.device.DeviceFellService;
import com.hotcomm.community.common.service.house.HouseSummarizingService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	PersonService personService;

	@Autowired
	HouseSummarizingService houseSummarizingService;

	@Autowired
	CarToalCaseService carToalCaseService;

	@Autowired
	DeviceFellService deviceFellService;

	@Autowired
	CorToalCaseService corToalCaseService;

	@Autowired
	ProscessService worderService;

	@Autowired
	ProscessService proscessService;

	@Override
	public KPIUM getKPI(Integer cid) throws HKException {
		int totalPopulation = personService.getPopulationSituation(cid).getTotalPopulation();
		int buildingNum = houseSummarizingService.getHouseStatistics(cid).getBuildingNum();
		
		int regCarCount = carToalCaseService.selectToalCount(cid).getRegCarCount();

		List<DeviceMsgCountUM> dmcList = deviceFellService.DeviceMsgCount(cid);
		int totalDevNum = 0;
		for( int i = 0 ; i < dmcList.size() ; i++)
			totalDevNum += dmcList.get(i).getDevNum();

		CorporationPA corporationPA = new CorporationPA();
		corporationPA.setCommunityId(cid);
		int corporationCount = corToalCaseService.selectToalCount(corporationPA).getCorporationCount();

		KPIUM result = new KPIUM();
		result.setTotalPopulation(totalPopulation);
		result.setBuildingNum(buildingNum);
		result.setRegCarCount(regCarCount);
		result.setTotalDevNum(totalDevNum);
		result.setCorporationCount(corporationCount);
		CommunityParams params = new CommunityParams();
		params.setCommunityId(cid);
		result.setAlarmNum(proscessService.alarmHTCount(params));
		result.setOrderNum(proscessService.WorderHTCount(params));
		return result;
	}

	@Override
	public PendingSituationUM getPendingSituation(Integer cid) throws HKException {
		CommunityParams params = new CommunityParams();
		params.setCommunityId(cid);
		PendingSituationUM result = worderService.backWorder(params);
		return result;
	}

}
