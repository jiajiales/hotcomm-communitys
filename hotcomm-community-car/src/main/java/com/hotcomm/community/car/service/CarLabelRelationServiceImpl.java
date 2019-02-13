package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotcomm.community.car.mapper.CarLabelRelationMapper;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.ui.car.CarLabelRelationUM;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarLabelService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.framework.utils.StringUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 车辆与标签关系
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarLabelRelationServiceImpl extends BaseService implements CarLabelRelationService {

	@Autowired
	private CarLabelRelationMapper carLabelRelationMapper;

	@Autowired
	private CarLabelService carLabelService;

	@Autowired
	private CarStrService carStrService;

	@Autowired
	private CarRegService carRegService;

	/**新增车辆标签关系*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(CarLabelRelationDM carLabelRelationDM) throws HKException {
		carLabelRelationDM.setDynamicDbname(
				super.getTableParams(carLabelRelationDM.getCommunityId()).get("dynamic_dbname").toString());
		CarLabelRelationUM carLabelRelation = carLabelRelationMapper.detail(carLabelRelationDM);
		if (carLabelRelation == null) {
			return carLabelRelationMapper.insert(carLabelRelationDM);
		}
		throw this.exceptionManager.configLog(error).errorRecord("CAR0005", new Exception());
	}

	/**删除车辆标签关系*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(CarLabelRelationDM carLabelRelationDM) throws HKException {
		carLabelRelationDM.setDynamicDbname(
				super.getTableParams(carLabelRelationDM.getCommunityId()).get("dynamic_dbname").toString());
		return carLabelRelationMapper.delete(carLabelRelationDM);
	}

	/**修改车辆标签关系*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(CarLabelRelationDM carLabelRelationDM) throws HKException {
		String dynamicDbname = super.getTableParams(carLabelRelationDM.getCommunityId()).get("dynamic_dbname").toString();
		carLabelRelationDM.setDynamicDbname(dynamicDbname);
		CarStrPA carStrPA = new CarStrPA();
		carStrPA.setNum(carLabelRelationDM.getCarNum());
		carStrPA.setCommunityId(carLabelRelationDM.getCommunityId());
		CarStrUM carStrUM = carStrService.detail(carStrPA);
		if (carStrUM != null) {
			ArrayList<Integer> oldLabelList = carStrUM.getLabelIdList();
			List<Integer> newLabelList = new ArrayList<>();
			if (!StringUtils.isEmpty(carLabelRelationDM.getLabelIdList())) {
				newLabelList = Arrays.stream(carLabelRelationDM.getLabelIdList().split(","))
						.map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
			}
			ArrayList<CarLabelRelationDM> addList = new ArrayList<>();
			// 原标签ID被干掉了
			for (int i = 0; i < oldLabelList.size(); i++) {
				Integer oldId = oldLabelList.get(i);
				if (!newLabelList.contains(oldId)) {
					CarLabelRelationDM carLabelRelation = new CarLabelRelationDM();
					carLabelRelation.setCarNum(carLabelRelationDM.getCarNum());
					carLabelRelation.setLabelId(oldId);
					carLabelRelation.setDynamicDbname(dynamicDbname);
					carLabelRelationMapper.deleteByCarNum(carLabelRelation);
				}
			}
			// 有新的标签加进来了
			for (int i = 0; i < newLabelList.size(); i++) {
				Integer newId = newLabelList.get(i);
				if (!oldLabelList.contains(newId)) {
					CarLabelRelationDM carLabelRelation = new CarLabelRelationDM();
					carLabelRelation.setCarNum(carLabelRelationDM.getCarNum());
					carLabelRelation.setLabelId(newId);
					addList.add(carLabelRelation);
				}
			}
			if (addList.size() > 0) {
				carLabelRelationMapper.insertBatch(dynamicDbname, addList);
			}
			return true;
		} else {
			CarRegDetailUM carRegDetailUM = carRegService.detail(carLabelRelationDM.getCommunityId(), null,
					carLabelRelationDM.getCarNum());
			if (carRegDetailUM.getLabelId() != null) {
				if (carLabelRelationDM.getLabelId() != null) {
					if (!carLabelRelationDM.getLabelId().equals(carRegDetailUM.getLabelId())) {
						return carLabelRelationMapper.update(carLabelRelationDM);
					}
				} else {
					return carLabelRelationMapper.delete(carLabelRelationDM);
				}
			} else {
				if (carLabelRelationDM.getLabelId() != null) {
					return carLabelRelationMapper.insert(carLabelRelationDM);
				}
			}
			return true;
		}
	}


	/**通过车牌号删除车辆标签关系*/
	@Override
	public boolean deleteByCarNum(CarLabelRelationDM carLabelRelationDM) throws HKException {
		carLabelRelationDM.setDynamicDbname(
				super.getTableParams(carLabelRelationDM.getCommunityId()).get("dynamic_dbname").toString());
		return carLabelRelationMapper.deleteByCarNum(carLabelRelationDM);
	}

	/**通过车牌号查询车辆标签*/
	@Override
	public ArrayList<String> selectLabelByCarNum(Map<String, Object> map) throws HKException {
		return carLabelRelationMapper.selectLabelByCarNum(map);
	}

	@Override
	public Integer selectBlackCarByLabelId(String dynamicDbname, String day) throws HKException {
		// 查询黑名单对应的标签Id
		List<Integer> list = carLabelService.selectLabelListByType(dynamicDbname, 13);
		if (day != null) {
			return carLabelRelationMapper.selectTodayBlackCarByLabelId(dynamicDbname, list, day);
		} else {
			return carLabelRelationMapper.selectBlackCarByLabelId(dynamicDbname, list);
		}
	}

	@Override
	public ArrayList<Integer> selectLabelIdByCarNum(Map<String, Object> map) throws HKException {
		return carLabelRelationMapper.selectLabelIdByCarNum(map);
	}

}
