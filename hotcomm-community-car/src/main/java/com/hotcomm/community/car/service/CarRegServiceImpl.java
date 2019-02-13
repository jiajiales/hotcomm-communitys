package com.hotcomm.community.car.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.car.mapper.CarRegMapper;
import com.hotcomm.community.common.bean.db.car.CarLabelRelationDM;
import com.hotcomm.community.common.bean.db.car.CarRegDM;
import com.hotcomm.community.common.bean.db.car.CarTypeCountDM;
import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.pa.car.CarRegPA;
import com.hotcomm.community.common.bean.pa.car.CarRegPagePA;
import com.hotcomm.community.common.bean.pa.car.CarStrPA;
import com.hotcomm.community.common.bean.ui.car.CarRegDetailUM;
import com.hotcomm.community.common.bean.ui.car.CarRegUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.bean.ui.corporation.CorporationUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarLabelRelationService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.common.service.corporation.CorporationService;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.utils.UUIDUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 登记车辆
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarRegServiceImpl extends BaseService  implements CarRegService {

	@Autowired
	private CarRegMapper carRegMapper;
	
	@Autowired
	private CarLabelRelationService  carLabelRelationService;
	
	@Autowired
	private CarStrService carStrService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CorporationService corporationService;
	
	/**分页查询登记车辆列表*/
	@Override
	public PageInfo<CarRegUM> page(CarRegPagePA carRegPagePA) throws HKException {
		Map<String, Object>  tableParams = super.getTableParams(carRegPagePA.getCommunityId());
		PageHelper.startPage(carRegPagePA.getPageIndex(),carRegPagePA.getPageSize());
		tableParams.put("type", carRegPagePA.getType());
		tableParams.put("companyId", carRegPagePA.getCompanyId());
		tableParams.put("personId", carRegPagePA.getPersonId());
		tableParams.put("labelId", carRegPagePA.getLabelId());
		tableParams.put("startTime", carRegPagePA.getStartTime());
		tableParams.put("endTime", carRegPagePA.getEndTime());
		tableParams.put("num", carRegPagePA.getNum());
		//查询黑名单
		tableParams.put("labelTypeId", carRegPagePA.getLabelTypeId());
		tableParams.put("color", carRegPagePA.getColor());
		tableParams.put("modelType",carRegPagePA.getModelType());
		tableParams.put("brand", carRegPagePA.getBrand());
		//登记车辆与标签一对一
		Page<CarRegUM> page =carRegMapper.pagelist(tableParams);
		List<CarRegUM> carRegList   = page;
		return new PageInfo<>(page.getTotal(), carRegList,carRegPagePA.getPageSize(),carRegPagePA.getPageIndex());
	}

	/**新增登记车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(CarRegPA carRegPA) throws HKException {
			CarRegDM carRegDM = new CarRegDM();
			String dynamicDbname = super.getTableParams(carRegPA.getCommunityId()).get("dynamic_dbname").toString();
			CarRegDetailUM carRegDetailUM = carRegMapper.detail(dynamicDbname,null,carRegPA.getNum());
			if (carRegDetailUM!=null) {
				throw this.exceptionManager.configLog(error).errorRecord("CAR0001", new Exception());
			} else {
				CarLabelRelationDM carLabelRelationDM = new CarLabelRelationDM();
				//查询陌生车辆
				CarStrPA carStrPA = new CarStrPA();
				carStrPA.setNum(carRegPA.getNum());
				carStrPA.setCommunityId(carRegPA.getCommunityId());
				CarStrUM carStrUM = carStrService.detail(carStrPA);
				if (carStrUM!=null) {
					//删除陌生车辆记录
					carStrService.delete(carStrUM.getId() ,carRegPA.getCommunityId());
					//删除车辆标签关系
					carLabelRelationDM.setCommunityId(carRegPA.getCommunityId());
					carLabelRelationDM.setCarNum(carStrUM.getNum());
					carLabelRelationService.deleteByCarNum(carLabelRelationDM);
				}

				carRegPA.setCreateTime(DateUtils.getNowTime());
				//报警次数暂时写死
				carRegPA.setAlarmCount(0);
				if (carRegPA.getLabelId()!=null) {
					carLabelRelationDM.setCommunityId(carRegPA.getCommunityId());
					carLabelRelationDM.setLabelId(carRegPA.getLabelId());
					carLabelRelationDM.setCarNum(carRegPA.getNum());
					//新增车辆标签关系
					carLabelRelationService.insert(carLabelRelationDM);
				}
				BeanUtils.copyProperties(carRegPA, carRegDM);
				carRegDM.setDynamicDbname(dynamicDbname);
				return carRegMapper.insert(carRegDM);
			}
	}

	/**删除登记车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer communityId,Integer id) throws HKException {
		String dynamicDbname = super.getTableParams(communityId).get("dynamic_dbname").toString();
		CarRegDetailUM carRegDetailUM = carRegMapper.detail(dynamicDbname,id,null);
		if (carRegDetailUM!=null) {
			//先删除对应的标签
			CarLabelRelationDM carLabelRelationDM = new CarLabelRelationDM();
			carLabelRelationDM.setCommunityId(communityId);
			carLabelRelationDM.setId(carRegDetailUM.getLabelRelationId());
			carLabelRelationService.delete(carLabelRelationDM);
			return carRegMapper.delete(dynamicDbname,id);
		}throw this.exceptionManager.configLog(error).errorRecord("CAR0002", new Exception());
	}
	
	/**修改登记车辆*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(CarRegPA carRegPA) throws HKException {
		try {
			String dynamicDbname  = super.getTableParams(carRegPA.getCommunityId()).get("dynamic_dbname").toString();
			CarRegDM carRegDM = new CarRegDM();
			carRegPA.setUpdateTime(DateUtils.getNowTime());
			carRegPA.setDynamicDbname(dynamicDbname);
			CarRegDM carReg  = new CarRegDM();
			carReg.setId(carRegPA.getId());
			carReg.setDynamicDbname(carRegPA.getDynamicDbname());
			CarRegDetailUM carRegDetailUM = carRegMapper.detail(carRegPA.getDynamicDbname(),carRegPA.getId(),null);
			CarLabelRelationDM carLabelRelationDM = new CarLabelRelationDM();
			carLabelRelationDM.setCommunityId(carRegPA.getCommunityId());
			carLabelRelationDM.setLabelId(carRegPA.getLabelId());
			carLabelRelationDM.setCarNum(carRegDetailUM.getNum());
			
			if (carRegPA.getLabelId()!=null && carRegDetailUM.getLabelId()==null) {
				//新增标签
				carLabelRelationService.insert(carLabelRelationDM);
			}else if (carRegPA.getLabelId()!=null && carRegDetailUM.getLabelId()!=null ) {
				if (!carRegPA.getLabelId().equals(carRegDetailUM.getLabelId())) {
					carLabelRelationDM.setId(carRegDetailUM.getLabelRelationId());
					//更新标签
					carLabelRelationService.update(carLabelRelationDM);
				}
			}else if (carRegPA.getLabelId()==null && carRegDetailUM.getLabelId()!=null) {
				carLabelRelationDM.setId(carRegDetailUM.getLabelRelationId());
				//删除标签
				carLabelRelationService.delete(carLabelRelationDM);
			}	
			BeanUtils.copyProperties(carRegPA, carRegDM);
			Boolean bl = carRegMapper.update(carRegDM);
			//解除人车关联
			if (carRegPA.getPersonId() != null && carRegPA.getPersonId()==0) {
				carRegMapper.deleteCarPerson(dynamicDbname, carRegPA.getId());
			}
			return bl;
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("CAR0008", e);
		}
	}

	@Override
	public boolean updateAlarmCountById(CarRegDM carRegDM) throws HKException {
		String dynamicDbname  = super.getTableParams(carRegDM.getCommunityId()).get("dynamic_dbname").toString();
		return carRegMapper.updateAlarmCountById(dynamicDbname,carRegDM.getId(),carRegDM.getAlarmCount());	
	}
	
	/**查询登记车辆详情*/
	@Override
	public CarRegDetailUM detail(Integer communityId,Integer id,String num) throws HKException {
		CarRegDetailUM carRegDetailUM = carRegMapper.detail(super.getTableParams(communityId).get("dynamic_dbname").toString(),id,num);
		if (carRegDetailUM!=null) {
			num = carRegDetailUM.getNum();
			carRegDetailUM.setArea(num.substring(0,1));
			carRegDetailUM.setShortNum(num.substring(1, num.length()));
			//小区住户车辆
			if (carRegDetailUM.getPersonId()!=null) {
				if (carRegDetailUM.getPersonName()==null ) {
					//小区人口详情
					PersonDM personDM  = personService.getPersonInfo(communityId, carRegDetailUM.getPersonId());
					carRegDetailUM.setPersonName(personDM.getName());
					carRegDetailUM.setPersonPhone(personDM.getPhone());
				} else {
					carRegDetailUM.setPersonName(carRegDetailUM.getPersonName());
					carRegDetailUM.setPersonPhone(carRegDetailUM.getPersonPhone());
				}
			}		
			if (carRegDetailUM.getPersonId()!=null) {
				//人口ID查询房屋ID
				List<Map<String, Object>> rommList = roomService.getRoomListByPid(carRegDetailUM.getPersonId(),communityId);
				if (rommList.size()>0) {
					Map<String, Object> map = rommList.stream().findFirst().get();
					carRegDetailUM.setRoomId(Integer.parseInt(map.get("roomId").toString()));
					carRegDetailUM.setRoomAdress(map.get("roomName").toString());
				}
			}		
			if (carRegDetailUM.getCompanyId()!=null) {
				if (carRegDetailUM.getCompanyName()!=null ) {
					carRegDetailUM.setCompanyName(carRegDetailUM.getCompanyName());
					carRegDetailUM.setCompanyAddress(carRegDetailUM.getCompanyAddress());
				}else {
					CorporationUM corporationUM = corporationService.detail(communityId,carRegDetailUM.getCompanyId(),null);
					carRegDetailUM.setCompanyName(corporationUM.getCorName());
					carRegDetailUM.setCompanyAddress(corporationUM.getAddress());
				}
			}
		}
		return carRegDetailUM;
	}

	/**查询小区登记车辆总数*/
	@Override
	public Integer selectCount(Map<String, Object> map) throws HKException {
		return carRegMapper.selectCount(map);
	}
	
	/**分类查询小区登记车辆数（单位、小区、其他）*/
	@Override
	public List<CarTypeCountDM> selectTypeCount(Map<String, Object> map) throws HKException {
		return carRegMapper.selectTypeCount(map);
	}

	/**查询单位登记车辆数*/
	@Override
	public Integer selectCorCarCount(String dynamicDbname) throws HKException {
		return carRegMapper.selectCorCarCount(dynamicDbname);
	}
	
}
