package com.hotcomm.community.car.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.car.CarAlarmRuleDM;
import com.hotcomm.community.common.bean.pa.car.CarAlarmRulePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;

public interface CarAlarmRuleMapper {

	public Page<CarAlarmRuleUM> pagelist(@Param("pa")Map<String, Object> params);
	
	public Integer insert(CarAlarmRuleDM carAlarmRuleDM);
	
	public CarAlarmRuleUM detail(CarAlarmRulePA carAlarmRulePA);
	
	public boolean delete(@Param("dynamicDbname")String dynamicDbname,@Param("ruleId")Integer ruleId);
	
	public boolean update(CarAlarmRuleDM carAlarmRuleDM);
	
}
