package com.hotcomm.community.car.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.car.mapper.CarLabelMapper;
import com.hotcomm.community.common.bean.db.car.CarLabelDM;
import com.hotcomm.community.common.bean.pa.car.CarLabelPA;
import com.hotcomm.community.common.bean.pa.car.CarLabelPagePA;
import com.hotcomm.community.common.bean.pa.system.DictionaryPA;
import com.hotcomm.community.common.bean.ui.car.CarLabelLayeredList;
import com.hotcomm.community.common.bean.ui.car.CarLabelUM;
import com.hotcomm.community.common.bean.ui.system.DictionaryUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarLabelService;
import com.hotcomm.community.common.service.system.DictionaryService;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;
import com.hotcomm.framework.web.exception.HKException;

/**
 * @Description: 车辆标签
 * @author lhx
 * @create 2019-01-24 11:46
 **/
@Service
public class CarLabelServiceImpl extends BaseService implements CarLabelService {

	@Autowired
	private CarLabelMapper carLabelMapper;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**分页查询车辆标签列表*/
	@Override
	public PageInfo<CarLabelUM> page(CarLabelPagePA carLabelPagePA) throws HKException {
		Map<String, Object>  map = super.getTableParams(carLabelPagePA.getCommunityId());
		PageHelper.startPage(carLabelPagePA.getPageIndex(),carLabelPagePA.getPageSize());
		map.put("labelTypeId", carLabelPagePA.getTypeId());
		map.put("labelSource", carLabelPagePA.getLabelSource());
		map.put("state", carLabelPagePA.getState());
		Page<CarLabelUM> page =carLabelMapper.pagelist(map);
		List<CarLabelUM> carRegList   = page;
		return new PageInfo<>(page.getTotal(), carRegList,carLabelPagePA.getPageSize(),carLabelPagePA.getPageIndex());
	}
	
	/**查询车辆标签列表*/
	@Override
	public List<CarLabelUM> labelList(CarLabelDM carLabelDM) throws HKException {
		return carLabelMapper.labelList(super.getTableParams(carLabelDM.getCommunityId()).get("dynamic_dbname").toString(),carLabelDM.getCarLabelId());
	}

	/**查询车辆标签、标签类型列表*/
	@Override
	public List<CarLabelLayeredList> labellayeredList(Integer communityId) throws HKException {
		List<CarLabelLayeredList> carLabelTypelayeredList = new ArrayList<>();
		DictionaryPA dictionaryPA = new DictionaryPA();
		dictionaryPA.setType("C01");
		List<DictionaryUM> carLabelTypeList = dictionaryService.getDictionary(dictionaryPA);
		carLabelTypeList.stream().forEach(carLabelType -> {
			CarLabelLayeredList labelTypeList = new CarLabelLayeredList();
			Integer labelTypeId = carLabelType.getId();
			String labelType = carLabelType.getKeyValue();
			CarLabelDM carLabelDM = new CarLabelDM();
			carLabelDM.setCommunityId(communityId);
			carLabelDM.setCarLabelId(labelTypeId);
			List<CarLabelUM> labelList = this.labelList(carLabelDM);
			
			labelTypeList.setLabelList(labelList);
			labelTypeList.setLabelTypeId(labelTypeId);
			labelTypeList.setLabelTypeName(labelType);
			carLabelTypelayeredList.add(labelTypeList);
		});
		return carLabelTypelayeredList;	
	}
	
	/**新增车辆标签*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(CarLabelPA carLabelPA) throws HKException{
		String dynamicDbname = super.getTableParams(carLabelPA.getCommunityId()).get("dynamic_dbname").toString();
		CarLabelUM carLabelUM = carLabelMapper.detail(dynamicDbname,null,carLabelPA.getName());
		if (carLabelUM==null) {
			CarLabelDM carLabelDM  = new CarLabelDM();
			BeanUtils.copyProperties(carLabelPA, carLabelDM);
			carLabelDM.setDynamicDbname(dynamicDbname);
			carLabelDM.setCreateTime(DateUtils.getNowTime());
			carLabelDM.setLabelSource(1);
			carLabelDM.setState(0);
			return carLabelMapper.insert(carLabelDM);
		}throw this.exceptionManager.configLog(error).errorRecord("CAR0020", new Exception());
	}

	/**删除车辆标签(硬删除)*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Integer communityId,Integer id) throws HKException{
		String  dynamicDbname  = super.getTableParams(communityId).get("dynamic_dbname").toString();
		return carLabelMapper.delete(dynamicDbname,id);
	}
	
	/**修改车辆标签*/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(CarLabelPA carLabelPA) throws HKException {
		String  dynamicDbname  = super.getTableParams(carLabelPA.getCommunityId()).get("dynamic_dbname").toString();
		if(carLabelPA.getName()!=null){
			CarLabelUM carLabelByName = carLabelMapper.detail(dynamicDbname,null,carLabelPA.getName());
			CarLabelUM carLabelById = carLabelMapper.detail(dynamicDbname,carLabelPA.getId(),null);
			//标签存在且不是本次修改的标签
			if (carLabelByName != null && !carLabelById.getId().equals(carLabelByName.getId())) {
				throw this.exceptionManager.configLog(error).errorRecord("CAR0003", new Exception());
			}
		}
		CarLabelDM carLabelDM = new CarLabelDM();
		BeanUtils.copyProperties(carLabelPA, carLabelDM);
		carLabelDM.setDynamicDbname(dynamicDbname);
		carLabelDM.setUpdateTime(DateUtils.getNowTime());
		return carLabelMapper.update(carLabelDM);
	}
	
	/**查看车辆标签详情*/
	@Override
	public CarLabelUM detail(Integer communityId ,Integer id,String name) throws HKException {
		String  dynamicDbname  = super.getTableParams(communityId).get("dynamic_dbname").toString();
		return carLabelMapper.detail(dynamicDbname,id,name);
	}

	/**通过标签类型查询标签id*/
	@Override
	public List<Integer> selectLabelListByType(String dynamicDbname, Integer labelTypeId) throws HKException {
		return carLabelMapper.selectLabelListByType(dynamicDbname, labelTypeId);
	}

	
}
