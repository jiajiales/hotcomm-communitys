package com.hotcomm.community.accesscontrol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotcomm.community.accesscontrol.mapper.DevDoorMapper;
import com.hotcomm.community.common.bean.db.device.DeviceAlarmGzDM;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPA;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DevDoorPagePA;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevAttrUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorDetailsUM;
import com.hotcomm.community.common.bean.ui.device.doorcontrol.DevDoorsUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.device.doorcontrol.DevDoorService;
import com.hotcomm.community.device.mapper.DeviceMapper;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class DevDoorServiceImpl extends BaseService implements DevDoorService {
	@Autowired
	DevDoorMapper devDoorMapper;
	@Autowired
	DeviceService deviceService;
	@Autowired
	DeviceMapper deviceMapper;

	@Override
	public PageInfo<DevDoorsUM> pageData(DevDoorPagePA params) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
		PageHelper.startPage(params.getPageIndex(), params.getPageSize());
		Page<DevDoorsUM> page = devDoorMapper.pageData(tableParams, params);
		List<DevDoorsUM> list = page;
		return new PageInfo<>(page.getTotal(), list, params.getPageSize(), params.getPageIndex());
	}

	@Transactional
	@Override
	public void install(DevDoorPA params) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			params.setInstallState(1);
			String pictureList = params.getPictureList();
			String videoList = params.getVideoList();
			Map<String, List<String>> pics = new HashMap<>();
			Map<String, List<String>> vis = new HashMap<>();
			if (!pictureList.isEmpty()) {
				String[] split = pictureList.split(",");
				List<String> list = new ArrayList<>();
				for (String str : split) {
					list.add(str);
				}
				pics.put("picture", list);
				params.setPictureList(new Gson().toJson(pics));
			}
			if (!videoList.isEmpty()) {
				String[] videos = videoList.split(",");
				List<String> vs = new ArrayList<>();
				for (String str : videos) {
					vs.add(str);
				}
				vis.put("video", vs);
				params.setVideoList(new Gson().toJson(vis));
			}
			devDoorMapper.updateData(tableParams, params);
			// 添加报警规则
			// 查询出该设备类型下的所有规则,第三位参数传0代表查询系统规则
			List<DeviceAlarmGzDM> deviceAlarmGzDM = deviceMapper.deviceAlarmGz(tableParams, 6, 0, null);
			System.out.println(deviceAlarmGzDM);
			// 给新设备关联规则
			deviceMapper.deviceAlarmGzInsert(tableParams, deviceAlarmGzDM, params.getMac());
			// 新增基础库总表数据
			deviceMapper.deviceInstallCommunity(tableParams, params.getMac(), params.getCommunityId());
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("DEVE0001", e);
		}

	}

	@Override
	public void update(DevDoorPA params) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(params.getCommunityId());
			String pictureList = params.getPictureList();
			String videoList = params.getVideoList();
			Map<String, List<String>> pics = new HashMap<>();
			Map<String, List<String>> vis = new HashMap<>();
			if (!pictureList.isEmpty()) {
				String[] split = pictureList.split(",");
				List<String> list = new ArrayList<>();
				for (String str : split) {
					list.add(str);
				}
				pics.put("picture", list);
			}
			if (!videoList.isEmpty()) {
				String[] videos = videoList.split(",");
				List<String> vs = new ArrayList<>();
				for (String str : videos) {
					vs.add(str);
				}
				vis.put("video", vs);
			}
			params.setPictureList(new Gson().toJson(pics));
			params.setVideoList(new Gson().toJson(vis));
			devDoorMapper.updateData(tableParams, params);
		} catch (Exception e) {
			throw exceptionManager.configLog(error).errorRecord("DEVE0002", e);
		}
	}

	@Override
	public DevAttrUM selectAttr(Integer communityId, String mac) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("mac", mac);
		return devDoorMapper.selectAttr(tableParams);
	}

	@Override
	public DevDoorDetailsUM detailsData(Integer communityId, String mac) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		tableParams.put("mac", mac);
		DevDoorDetailsUM detailsData = devDoorMapper.detailsData(tableParams);
		if (detailsData != null) {
			String videoList = detailsData.getVideoList();
			if (videoList != null) {
				List<String> videos = new Gson().fromJson(videoList, new TypeToken<List<String>>() {
				}.getType());
				if (videos.size() != 0) {
					List<Object> list = new ArrayList<>();
					for (String str : videos) {
						SelectVideoDeviceOnMacUM video = deviceService.selectVideoDeviceOnMac(str, communityId);
						list.add(video);
					}
					detailsData.setVideos(list);
				}
			}
		}
		return detailsData;
	}

}
