package com.hotcomm.community.house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hotcomm.community.common.bean.en.house.HouseEN;
import com.hotcomm.community.common.bean.pa.house.RoomPA;
import com.hotcomm.community.common.bean.pa.house.RoomPagePA;
import com.hotcomm.community.common.bean.ui.house.RoomUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.house.mapper.RoomMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;

@Service
public class RoomServiceImpl extends BaseService implements RoomService {
	@Autowired
	RoomMapper roomMapper;

	@Override
	public PageInfo<RoomUM> pageData(RoomPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<RoomUM> page = roomMapper.page(tableParams, params);
		List<RoomUM> rooms = page;
		if (rooms.size()!=0) {
			for (RoomUM roomUM : rooms) {
				if (roomUM.getAttribute()!=null) {
					int attribute = Integer.parseInt(roomUM.getAttribute());
					roomUM.setAttribute(HouseEN.RoomAttribute.getByValue(attribute).getName());	
				}
				if (roomUM.getRentOrSale()!=null) {
					int rentOrSale = Integer.parseInt(roomUM.getRentOrSale());
					roomUM.setRentOrSale(HouseEN.RoomRentOrSale.getByValue(rentOrSale).getName());	
				}
			}
		}
		return new PageInfo<>(page.getTotal(), rooms, params.getPageSize(), params.getPageIndex());
	}

	@Override
	public RoomUM detailsData(Integer id, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("id", id);
		RoomUM roomUM = roomMapper.detailsData(tableParams);
		if (roomUM==null) {
			throw exceptionManager.configLog(error).errorRecord("HR0007",HKException.instance());
		}
		if (roomUM.getAttribute()!=null) {
			int attribute = Integer.parseInt(roomUM.getAttribute());
			roomUM.setAttribute(HouseEN.RoomAttribute.getByValue(attribute).getName());	
		}
		if (roomUM.getRentOrSale()!=null) {
			int rentOrSale = Integer.parseInt(roomUM.getRentOrSale());
			roomUM.setRentOrSale(HouseEN.RoomRentOrSale.getByValue(rentOrSale).getName());	
		}
		return roomUM;
	}

	@Transactional
	@Override
	public void addData(RoomPA params) throws Exception {
		Map<String, Object> tableParams = null;
		Boolean exist = isRoomExist(null, params.getUnitId(), params.getBuildingId(), params.getFloorId(),
				params.getRoomName(), params.getCommunityId());
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HR0001", HKException.instance());
		try {
			// 本地添加
			tableParams = super.getTableParams(params.getCommunityId());
			roomMapper.addData(tableParams, params);
			tableParams.put("id", params.getId());
			tableParams.put("unitId", params.getUnitId());
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HR0002", e);
		}
		String model = SpringUtil.getBean(Environment.class).getProperty("doordu_model", String.class);
		if (model.equals("use")) {
			// 第三添加
			Integer queryDoorduUnitId = roomMapper.queryDoorduUnitId(tableParams);
			List<HotHttpEntity> param = new ArrayList<>();
			param.add(new HotHttpEntity("token", EntityEnum.TEXT, getToken()));
			param.add(new HotHttpEntity("roomNumber", EntityEnum.TEXT, params.getRoomName()));
			param.add(new HotHttpEntity("unitId", EntityEnum.TEXT, queryDoorduUnitId));
			param.add(new HotHttpEntity("roomNickname", EntityEnum.TEXT, params.getRoomName()));
			HotHttpResponse response = HttpClientUtils.doPost(param,
					"http://ddflow.doordu.com/open_api/h_d/createRoom/v1");
			JSONObject fromObject = JSONObject.fromObject(response.getReturnJson());
			if (!fromObject.get("code").equals("200")) {
				throw exceptionManager.configLog(error).errorRecord("HR0006", HKException.instance());
			}
			// 本地修改
			JSONObject object = JSONObject.fromObject(fromObject.get("data"));
			tableParams.put("roomNumberId", object.get("roomNumberId"));
			roomMapper.updateDoorduRoomId(tableParams);
		}
	}

	@Transactional
	@Override
	public void updateData(RoomPA params) throws HKException {
		Boolean exist = isRoomExist(params.getId(),params.getUnitId(),params.getBuildingId(), params.getFloorId(), params.getRoomName(),
				params.getCommunityId());
		if (exist)
			throw exceptionManager.configLog(error).errorRecord("HR0001",HKException.instance());
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			roomMapper.updateData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HR0003", e);
		}

	}

	@Transactional
	@Override
	public void delData(Integer id, Integer communityId) throws HKException {
		Boolean exist = isExistRelationRoom(id, communityId);
		if (exist) {
			throw exceptionManager.configLog(error).errorRecord("HR0005",HKException.instance());
		}
		try {
			Map<String, Object> tableParams = super.getTableParams(communityId);
			tableParams.put("id", id);
			roomMapper.delData(tableParams);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("HR0004", e);
		}
	}

	@Override
	public Boolean isRoomExist(Integer id, Integer unitId,Integer buildingId, Integer floorId, String roomName, Integer communityId)
			throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		Integer exist = roomMapper.isRoomExist(tableParams, id, buildingId, unitId,floorId, roomName);
		if (exist != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> getRoomList(Integer floorId, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("floorId", floorId);
		return roomMapper.getRoomList(tableParams);
	}

	@Override
	public Boolean isExistRelationRoom(Integer roomId, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("roomId", roomId);
		Integer existRelationRoom = roomMapper.isExistRelationRoom(tableParams);
		if (existRelationRoom != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> getRoomListByPid(Integer pId, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("pId", pId);
		return roomMapper.getRoomListByPid(tableParams);
	}

	@Override
	public List<Map<String, Object>> getAllList(Integer communityId) {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return roomMapper.getAllList(tableParams);
	}
	
	/**
	 * （综合态势）总数、出租屋、购置、在住房、空置房
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	@Override
	public Map<String, Integer> roomStatistics(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return roomMapper.roomStatistics(tableParams);
	}

}
