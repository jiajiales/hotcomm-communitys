package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.car.mapper.CarStrMapper;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.pa.car.CarStrPagePA;
import com.hotcomm.community.common.bean.ui.car.CarAlarmRuleUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.utils.UUIDUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

/**
 * @Description: 陌生车辆
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarStrServiceImpl extends BaseService implements CarStrService {

	@Autowired
	private CarStrMapper carStrMapper;
	
	@Autowired
	private CarLabelRelationService carLabelRelationService;


	/**分页查询陌生车俩列表*/
	@Override
	public PageInfo<CarStrUM> page(CarStrPagePA carStrPagePA) throws HKException {
		Map<String, Object>  params = super.getTableParams(carStrPagePA.getCommunityId());
		CarLabelRelationDM carLabelRelationDM = new CarLabelRelationDM();
		carLabelRelationDM.setDynamicDbname(params.get("dynamic_dbname").toString());
		PageHelper.startPage(carStrPagePA.getPageIndex(),carStrPagePA.getPageSize());
		params.put("num", carStrPagePA.getNum());
		params.put("labelId",carStrPagePA.getLabelId());
		params.put("color", carStrPagePA.getColor());
		params.put("brand", carStrPagePA.getBrand());
		params.put("state", carStrPagePA.getState());
		params.put("modelType", carStrPagePA.getModelType());
		Page<CarStrUM> page = carStrMapper.pagelist(params);
		List<CarStrUM> carStrList   = page;
		Optional<List<CarStrUM>> optional = Optional.ofNullable(carStrList);
		optional.ifPresent(new Consumer<List<CarStrUM>>() {
			@Override
			public void accept(List<CarStrUM> carStrUMs) {
				carStrUMs.stream().forEach(carStrUM ->{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("carNum", carStrUM.getNum());
					map.put("dynamicDbname", params.get("dynamic_dbname").toString());
					ArrayList<Integer> labelIdList = carLabelRelationService.selectLabelIdByCarNum(map);
					ArrayList<String> array= carLabelRelationService.selectLabelByCarNum(map);
					carStrUM.setLabelIdList(labelIdList);
					carStrUM.setLabelNameList(array);
				});
			}
		});
		return new PageInfo<>(page.getTotal(), carStrList,carStrPagePA.getPageSize(),carStrPagePA.getPageIndex());
	}

	/**新增陌生车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(CarStrDM carStrDM) throws HKException {
		//报警次数
		carStrDM.setAlarmCount(0);
		//出入次数
		carStrDM.setEnterCount(1);
		carStrDM.setCreateTime(DateUtils.getNowTime());
		if (carStrDM.getUuid()==null) {
			carStrDM.setUuid(UUIDUtils.get32BitUUID());
		}
		carStrDM.setEnterOutTime(DateUtils.getNowTime());
		carStrDM.setDynamicDbname(super.getTableParams(carStrDM.getCommunityId()).get("dynamic_dbname").toString());
		return carStrMapper.insert(carStrDM);
	}

	/**查看陌生车辆详情*/
	@Override
	public CarStrUM detail(CarStrPA carStrPA) throws HKException {
		carStrPA.setDynamicDbname(super.getTableParams(carStrPA.getCommunityId()).get("dynamic_dbname").toString());
		CarStrUM carStrUM = carStrMapper.detail(carStrPA);
		if (carStrUM!=null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("carNum", carStrUM.getNum());
			map.put("dynamicDbname", carStrPA.getDynamicDbname());
			//陌生车辆与标签一对多关系
			ArrayList<String>  arrayList = carLabelRelationService.selectLabelByCarNum(map);
			ArrayList<Integer> arrayIdList = carLabelRelationService.selectLabelIdByCarNum(map);
			carStrUM.setLabelIdList(arrayIdList);
			carStrUM.setLabelNameList(arrayList);
		}
		return carStrUM;
	}

	/**删除陌生车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer id ,Integer communityId) throws HKException {
		return carStrMapper.delete(super.getTableParams(communityId).get("dynamic_dbname").toString(),id);
	}

	/**修改陌生车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(CarStrDM carStrDM) throws HKException {
		carStrDM.setDynamicDbname(super.getTableParams(carStrDM.getCommunityId()).get("dynamic_dbname").toString());
		if (carStrDM.getState()!=null ) {
			//出入时间
			carStrDM.setEnterOutTime(DateUtils.getNowTime());
		}
		return carStrMapper.update(carStrDM);
	}

	/**修改陌生车辆*/
	@Override
	public List<CarStrUM> selectLongParkingCarList(CarStrDM carStrDM,CarAlarmRuleUM carAlarmRuleUM) throws HKException {
		//该社区的该任务暂停时carAlarmRuleUM为Null,直接跳过
		if (carAlarmRuleUM!=null) {
			Map<String,Object> map  = new HashMap<>();
			map.put("dynamicDbname", super.getTableParams(carStrDM.getCommunityId()).get("dynamic_dbname").toString());
			map.put("state", carStrDM.getState());
			map.put("month", DateUtils.getYearMon(new Date()));
			//N小时之前的时间
			map.put("beforeTime", DateUtils.getBeforeDate(Integer.parseInt(JSONObject.fromObject(carAlarmRuleUM.getRule()).getString("maxHours"))));
			return carStrMapper.selectLongParkingCarList(map);
		}else {
			return null;
		}
	}

	/**查询过夜车辆列表*/
	@Override
	public List<CarStrUM> selectNightCarList(CarStrDM carStrDM,CarAlarmRuleUM carAlarmRuleUM) throws HKException {
		//该社区的该任务暂停时carAlarmRuleUM为Null,直接跳过
		if (carAlarmRuleUM!=null) {
			JSONObject jsonobject = JSONObject.fromObject(carAlarmRuleUM.getRule().toString());
			String startTime = jsonobject.optString("startTime");
			String endTime = jsonobject.optString("endTime");
			String subEndTime = endTime.substring(0, endTime.indexOf(":"));
			
			String subStartTime = startTime.substring(0, startTime.indexOf(":"));
			//停车超过指定小时才报警
			int startHours = Integer.parseInt(jsonobject.getString("stayTime"))+Integer.parseInt(subStartTime);
			subStartTime = startHours < 24 ? "" + startHours : ""+(startHours-24);
			
			Map<String,Object> map  = new HashMap<>();
			map.put("dynamicDbname", super.getTableParams(carStrDM.getCommunityId()).get("dynamic_dbname").toString());
			map.put("state", carStrDM.getState());
			map.put("startTime", Integer.parseInt(subStartTime));
			map.put("endTime", Integer.parseInt(subEndTime));
			// 获取当前月份
			map.put("month", DateUtils.getYearMon(new Date()));
			// 敏感时段跨天情况
			if (Integer.parseInt(subStartTime) <= Integer.parseInt(subEndTime)) {
				return carStrMapper.selectNightCarList(map);
			}else {
				return carStrMapper.selectNightCarList2(map);
			}	
		}else {
			return null;
		}
	}

	/**查询陌生车辆数*/
	@Override
	public Integer selectYearCount(String dynamicDbname) throws HKException {
		return carStrMapper.selectYearCount(dynamicDbname);
	}

}