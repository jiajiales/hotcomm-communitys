package com.hotcomm.community.car.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.car.mapper.CarAlarmRuleMapper;
import com.hotcomm.community.common.bean.db.car.CarAlarmRuleDM;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.system.UserUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarAlarmRuleService;
import com.hotcomm.community.common.service.system.UserService;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

/**
 * @Description: 车辆报警规则
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarAlarmRuleServiceImpl extends BaseService implements CarAlarmRuleService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CarAlarmRuleMapper carAlarmRuleMapper;
	
	/**分页查询车辆报警规则*/
	@Override
	public PageInfo<CarAlarmRuleUM> page(CarAlarmRulePagePA carAlarmRulePagePA) throws HKException {
		Map<String, Object>  map = super.getTableParams(carAlarmRulePagePA.getCommunityId());
		PageHelper.startPage(carAlarmRulePagePA.getPageIndex(),carAlarmRulePagePA.getPageSize());
		Page<CarAlarmRuleUM> page = carAlarmRuleMapper.pagelist(map);
		List<CarAlarmRuleUM> alarmRuleList = page;
		for (int i = 0; i < alarmRuleList.size(); i++) {
			CarAlarmRuleUM carAlarmRuleUM =alarmRuleList.get(i);
			List<String> notifyUserNameList = new ArrayList<>();
			String person = JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person").toString();
			String[] arr = person.substring(1,person.length()-1).split(",");
			for (int j = 0; j < arr.length; j++) {
				Integer personId = Integer.parseInt(arr[j]);
				String userName = "";
				Optional<UserUM> optional = Optional.ofNullable(userService.getUser(personId));
				if (optional.isPresent()) {
					userName = optional.get().getRealName();
				}
				notifyUserNameList.add(userName);
			}
			carAlarmRuleUM.setNotifyUserNameList(notifyUserNameList);
		}
		return new PageInfo<>(page.getTotal(), alarmRuleList,carAlarmRulePagePA.getPageSize(),carAlarmRulePagePA.getPageIndex());
	}

	/**新增车辆报警规则*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(CarAlarmRulePA carAlarmRulePA) throws HKException {
		String  dynamicDbname  = super.getTableParams(carAlarmRulePA.getCommunityId()).get("dynamic_dbname").toString();
		carAlarmRulePA.setDynamicDbname(dynamicDbname);
		CarAlarmRulePA alarmRule = new CarAlarmRulePA();
		alarmRule.setDynamicDbname(dynamicDbname);
		alarmRule.setName(carAlarmRulePA.getName());
		CarAlarmRuleUM carAlarmRule  = carAlarmRuleMapper.detail(alarmRule);
		if (carAlarmRule == null) {
			CarAlarmRuleDM carAlarmRuleDM  = new CarAlarmRuleDM();
			BeanUtils.copyProperties(carAlarmRulePA, carAlarmRuleDM);
			carAlarmRuleDM.setDynamicDbname(super.getTableParams(carAlarmRulePA.getCommunityId()).get("dynamic_dbname").toString());
			carAlarmRuleDM.setCreateTime(DateUtils.getNowTime());
			carAlarmRuleDM.setState(0);
			return carAlarmRuleMapper.insert(carAlarmRuleDM);
		}throw this.exceptionManager.configLog(error).errorRecord("CAR0006", new Exception());
	}

	/**删除车辆报警规则*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer communityId,Integer ruleId) throws HKException {
		return carAlarmRuleMapper.delete(super.getTableParams(communityId).get("dynamic_dbname").toString(),ruleId);
	}

	/**修改车辆报警规则*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(CarAlarmRulePA carAlarmRulePA) throws HKException {
		String  dynamicDbname  = super.getTableParams(carAlarmRulePA.getCommunityId()).get("dynamic_dbname").toString();
		carAlarmRulePA.setDynamicDbname(dynamicDbname);
		CarAlarmRulePA alarmRule = new CarAlarmRulePA();
		alarmRule.setDynamicDbname(dynamicDbname);
		if (carAlarmRulePA.getName()!=null){
			alarmRule.setName(carAlarmRulePA.getName());
			CarAlarmRuleUM carAlarmRule  = carAlarmRuleMapper.detail(alarmRule);
			if (!carAlarmRule.getRuleId().equals(carAlarmRulePA.getRuleId())) {
				throw this.exceptionManager.configLog(error).errorRecord("CAR0006", new Exception());
			}
		}
		CarAlarmRuleDM carAlarmRuleDM = new CarAlarmRuleDM();
		BeanUtils.copyProperties(carAlarmRulePA, carAlarmRuleDM);
		carAlarmRuleDM.setUpdateTime(DateUtils.getNowTime());
		carAlarmRuleDM.setDynamicDbname(dynamicDbname);
		return carAlarmRuleMapper.update(carAlarmRuleDM);
	}

	/**查询车辆报警规则详情*/
	@Override
	public CarAlarmRuleUM detail(CarAlarmRulePA carAlarmRulePA) throws HKException {
		carAlarmRulePA.setDynamicDbname(super.getTableParams(carAlarmRulePA.getCommunityId()).get("dynamic_dbname").toString());
		CarAlarmRuleUM carAlarmRuleUM = carAlarmRuleMapper.detail(carAlarmRulePA);
		List<String> notifyUserNameList = new ArrayList<>();
		List<String> dealUserNameList = new ArrayList<>();
		String person = JSONObject.fromObject(carAlarmRuleUM.getNotifyUsers()).get("person").toString();
		String[] arr = person.substring(1,person.length()-1).split(","); 
		for (int j = 0; j < arr.length; j++) {
			Integer personId = Integer.parseInt(arr[j]);
			String userName ="";
			Optional<UserUM> optional = Optional.ofNullable(userService.getUser(personId));
			if (optional.isPresent()) {
				userName = optional.get().getRealName();
			}
			notifyUserNameList.add(userName);
		}
		carAlarmRuleUM.setNotifyUserNameList(notifyUserNameList);
		String dealPerson = JSONObject.fromObject(carAlarmRuleUM.getDealUsers()).get("person").toString();
		String[] dealArr = person.substring(1,dealPerson.length()-1).split(","); 
		for (int i = 0; i < dealArr.length; i++) {
			Integer dealPersonId = Integer.parseInt(dealArr[i]);
			String userName ="";
			Optional<UserUM> optional = Optional.ofNullable(userService.getUser(dealPersonId));
			if (optional.isPresent()) {
				userName = optional.get().getRealName();
			}
			dealUserNameList.add(userName);	
		}
		carAlarmRuleUM.setDealUserNameList(dealUserNameList);
		return carAlarmRuleUM;
	}

}
