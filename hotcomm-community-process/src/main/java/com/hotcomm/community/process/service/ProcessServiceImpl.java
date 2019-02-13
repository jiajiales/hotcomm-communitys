package com.hotcomm.community.process.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.hotcomm.community.common.bean.db.process.AlarmTypeStateDM;
import com.hotcomm.community.common.bean.db.process.relationDev.Device;
import com.hotcomm.community.common.bean.db.process.relationDev.DeviceRootBean;
import com.hotcomm.community.common.bean.db.process.relationLeaderTip.leaderRootBean;
import com.hotcomm.community.common.bean.db.process.relationUser.AlarmUserRootBean;
import com.hotcomm.community.common.bean.db.process.relationUser.User;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmClosedView;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmImg;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmMp3;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmPendingView;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmVideo;
import com.hotcomm.community.common.bean.db.process.relationView.alarm.AlarmViewJsonRootBean;
import com.hotcomm.community.common.bean.db.process.relationView.event.ClosedView;
import com.hotcomm.community.common.bean.db.process.relationView.event.EventViewJsonRootBean;
import com.hotcomm.community.common.bean.db.process.relationView.event.Img;
import com.hotcomm.community.common.bean.db.process.relationView.event.PendingView;
import com.hotcomm.community.common.bean.db.process.relationView.worder.WorderHandleFinishViewDM;
import com.hotcomm.community.common.bean.db.process.relationView.worder.WorderHandleHangViewDM;
import com.hotcomm.community.common.bean.db.process.relationView.worder.WorderHandleJsonRootBeanDM;
import com.hotcomm.community.common.bean.pa.process.DevAlarmLogPA;
import com.hotcomm.community.common.bean.pa.process.DevFixLogPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmClosePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmDetailPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmLogPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmPagePA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmWorkPA;
import com.hotcomm.community.common.bean.pa.process.alarm.WorkPA;
import com.hotcomm.community.common.bean.pa.process.event.EventClosePA;
import com.hotcomm.community.common.bean.pa.process.event.EventDetailPA;
import com.hotcomm.community.common.bean.pa.process.event.EventNewPA;
import com.hotcomm.community.common.bean.pa.process.event.EventPagePA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateAlarmInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateDevInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreateEventInfoPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderCreatePA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderDetailPA;
import com.hotcomm.community.common.bean.pa.process.worder.WorderListPA;
import com.hotcomm.community.common.bean.ui.home.PendingSituationUM;
import com.hotcomm.community.common.bean.ui.house.BuildingUM;
import com.hotcomm.community.common.bean.ui.house.RoomUM;
import com.hotcomm.community.common.bean.ui.process.DevAlarmLogUM;
import com.hotcomm.community.common.bean.ui.process.DevFixLogUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListCotentUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListCotentWorkUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmMainWorkListWorkUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmNotDealListUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmPageUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmTrendUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmUserLngLatUM;
import com.hotcomm.community.common.bean.ui.process.alarm.AlarmZYGetTimeUM;
import com.hotcomm.community.common.bean.ui.process.event.EventPageUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkOrderCountUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkProRateUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkSourceUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkStatusUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderGkUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderListUM;
import com.hotcomm.community.common.bean.ui.process.worder.WorderZYOrderListUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.community.process.mapper.ProcessMapper;
import com.hotcomm.framework.comm.CommunityParams;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class ProcessServiceImpl extends BaseService implements ProscessService {

	@Autowired
	ProcessMapper alarmMapper;
	
	@Autowired
	CarPassRecordsService car;
	
	@Autowired
	MsgServiceImpl msgServiceImpl;
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	BuildingService buildingService;
	
	private Map<String, Object> userMap = new HashMap<>();

	//报警列表分页
	@Override
	public PageInfo<AlarmPageUM> alarmPage(AlarmPagePA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
		Page<AlarmPageUM> page = alarmMapper.alarmPage(library, pa);
		List<AlarmPageUM> list = page;
		return new PageInfo<>(page.getTotal(), list, pa.getPageSize(), pa.getPageIndex());
	}
	
	//设备维修日志
	@Override
	public PageInfo<DevFixLogUM> getDevFixLog(DevFixLogPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
		Page<DevFixLogUM> page = alarmMapper.getDevFixLog(library, pa.getDevId(), pa.getModuleId());
		List<DevFixLogUM> list = page;
		return new PageInfo<>(page.getTotal(), list, pa.getPageSize(), pa.getPageIndex());
	}
	
	//设备报警日志
	@Override
	public PageInfo<DevAlarmLogUM> getDevAlarmLog(DevAlarmLogPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
		Page<DevAlarmLogUM> page = alarmMapper.getDevAlarmLog(library, pa.getDevId(), pa.getModuleId());
		List<DevAlarmLogUM> list = page;
		return new PageInfo<>(page.getTotal(), list, pa.getPageSize(), pa.getPageIndex());
	}
	
	//关闭报警
	@Transactional
	@Override
	public String closeAlarm(AlarmClosePA pa) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Integer alarmID = pa.getAlarmID();
		Integer check = alarmMapper.checkAlarm(lib, alarmID);
		if (check != 1) { // 判断该报警是否存在或被关闭
			throw exceptionManager.create("ALM0001");
		} else {
			String alarmViewJsonStr = alarmMapper.getAlarmView(lib, alarmID); // 获取数据库存储的json
			AlarmViewJsonRootBean alarmViewBean = new AlarmViewJsonRootBean();
			Gson gson = new Gson();
			alarmViewBean = gson.fromJson(alarmViewJsonStr, AlarmViewJsonRootBean.class); // json转为实体类
			List<String> paView = new ArrayList<>(); // 准备放param传过来资源的数组
			String[] list = pa.getAlarmView().split(",");// 传过来的资源字符串分割把内容写进资源数组
			for (int i = 0; i < list.length; i++) {
				paView.add(list[i]);
			}
			List<AlarmImg> closeImgList = new ArrayList<>();
			for (int i = 0; i < paView.size(); i++) { // 往关闭报警资源实体类中添加关闭报警资源数组的内容
				AlarmImg closedImg = new AlarmImg();
				closedImg.setId("" + i + "");
				closedImg.setUrl(paView.get(i));
				closeImgList.add(closedImg);
			}
			AlarmClosedView closedView = alarmViewBean.getClosedView();
			closedView.setImg(closeImgList);
			alarmViewBean.setClosedView(closedView);
			pa.setAlarmView(JSON.toJSONString(alarmViewBean));
			alarmMapper.closeAlarm(lib, pa);
			return "关闭成功";
		}
	}

	//报警详情
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> alarmDetail(AlarmDetailPA pa) throws HKException {
		Gson gson = new Gson();
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		AlarmTypeStateDM alarmCover = alarmMapper.getAlarmCover(lib, pa.getAlarmID());// 获取报警源类别，获取报警状态
		Map<String, Object> info = new HashMap<String, Object>(); // 定义返回内容容器
		if (alarmCover!=null) {
//			throw exceptionManager.create("ALM0002");
		pa.setWorderNo(alarmCover.getWorderNo());
		pa.setState(alarmCover.getHandelState());
		pa.setType(alarmCover.getAlarmType());
		if (alarmCover.getAlarmType() == 1) { // 判断报警源类型
			info = alarmMapper.alarmDetail(lib, pa);
			String pendingViewStr0=info.get("pendingVideoBeanJson").toString();
			String pendingViewStr1=pendingViewStr0.replace("{\"video\": [\"", "");
			String pendingViewStr2=pendingViewStr1.replace("\", \"", ",");
			String pendingViewStr3=pendingViewStr2.replace("\"]}", "");
			info.put("pendingVideoView", pendingViewStr3);
			
			Map<String, Object> devInfo=new HashMap<String, Object>();
			devInfo.put("devNum", info.get("devNum").toString());
			devInfo.put("devType", info.get("devType").toString());
			devInfo.put("devMac", info.get("devMac").toString());
			devInfo.put("devLat", info.get("devLat").toString());
			devInfo.put("devLng", info.get("devLng").toString());
			devInfo.put("ownerName", info.get("ownerName").toString());
			devInfo.put("ownerTel", info.get("ownerTel").toString());
			devInfo.put("devAddress", info.get("devAddress").toString());
			devInfo.put("devInstallTime", info.get("devInstallTime").toString());
			devInfo.put("devInstallImg", info.get("devInstallImg").toString());
			
			info.remove("devNum");
			info.remove("devType");
			info.remove("devMac");
			info.remove("devLat");
			info.remove("devLng");
			info.remove("ownerName");
			info.remove("ownerTel");
			info.remove("devAddress");
			info.remove("devInstallTime");
			info.remove("devInstallImg");
			
			info.put("devInfo", devInfo);
			
			if (alarmCover.getHandelState() != 0) { 
				info.put("processingHandleBeginTime", info.get("processingHandleBeginTime").toString());
				if (alarmCover.getHandelState() == 2) {
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("finishView", allView.getFinishView().getImg());
				}
				if (alarmCover.getHandelState() == 3) { 
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", allView.getProcessedView().getImg());
					if (pa.getWorderNo()!=null&&pa.getWorderNo()!=""&&pa.getWorderNo().length()>0) {
						info.put("worderCreateTime", info.get("worderCreateTime").toString());
					}
				}
				if (alarmCover.getHandelState() == 4) {
					info.put("alarmHandleEndTime", info.get("alarmHandleEndTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", alarmView.getProcessedView().getImg());
					info.put("worderCreateTime", info.get("worderCreateTime").toString());
					
					info.put("worderHandleEndTime", info.get("worderHandleEndTime").toString());
					WorderHandleJsonRootBeanDM worderView = gson.fromJson(info.get("worderView").toString(),
							WorderHandleJsonRootBeanDM.class);
					info.put("worderHanldeFinishImgView", worderView.getWorderHandleFinishView().getImage());
					info.remove("worderView");
				}
				if (alarmCover.getHandelState() == 5) {
					info.put("closeTime", info.get("closeTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("closeView", alarmView.getClosedView().getImg());
				}
			}
			info.put("alarmTime", info.get("alarmTime").toString());
			info.remove("serialVersionUID");
			info.remove("pendingVideoBeanJson");
			info.remove("allViewJson");
		}
		if (alarmCover.getAlarmType() == 2) {
			info = alarmMapper.alarmDetail(lib, pa);
			String pendingViewStr0=info.get("pendingVideoBeanJson").toString();
			AlarmPendingView pendingBean=gson.fromJson(pendingViewStr0, AlarmPendingView.class);
			List<AlarmVideo> videoInfo=pendingBean.getVideo();
			String videoStr="";
			for (int i = 0; i < videoInfo.size(); i++) {
				videoStr+=videoInfo.get(i).getUrl();
			}
			info.put("pendingVideoView", videoStr);
			
			info.remove("address");
			
			if (alarmCover.getHandelState() != 0) { 
				info.put("processingHandleBeginTime", info.get("processingHandleBeginTime").toString());
				if (alarmCover.getHandelState() == 2) {
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("finishView", allView.getFinishView().getImg());
				}
				if (alarmCover.getHandelState() == 3) { 
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", allView.getProcessedView().getImg());
					if (pa.getWorderNo()!=null&&pa.getWorderNo()!=""&&pa.getWorderNo().length()>0) {
						info.put("worderCreateTime", info.get("worderCreateTime").toString());
					}
				}
				if (alarmCover.getHandelState() == 4) {
					info.put("alarmHandleEndTime", info.get("alarmHandleEndTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", alarmView.getProcessedView().getImg());
					info.put("worderCreateTime", info.get("worderCreateTime").toString());
					info.put("worderHandleEndTime", info.get("worderHandleEndTime").toString());
					WorderHandleJsonRootBeanDM worderView = gson.fromJson(info.get("worderView").toString(),
							WorderHandleJsonRootBeanDM.class);
					info.put("worderHanldeFinishImgView", worderView.getWorderHandleFinishView().getImage());
					info.remove("worderView");
				}
				if (alarmCover.getHandelState() == 5) {
					info.put("closeTime", info.get("closeTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("closeView", alarmView.getClosedView().getImg());
				}
			}
			info.put("alarmTime", info.get("alarmTime").toString());
			info.remove("serialVersionUID");
			info.remove("pendingVideoBeanJson");
			info.remove("allViewJson");
		}
		if (alarmCover.getAlarmType() == 3) {
			info = alarmMapper.alarmDetail(lib, pa);
			String pendingViewStr0=info.get("pendingVideoBeanJson").toString();
			AlarmPendingView pendingBean=gson.fromJson(pendingViewStr0, AlarmPendingView.class);
			List<AlarmVideo> videoInfo=pendingBean.getVideo();
			String videoStr="";
			for (int i = 0; i < videoInfo.size(); i++) {
				videoStr+=videoInfo.get(i).getUrl();
			}
			info.put("pendingVideoView", videoStr);
			if (alarmCover.getHandelState() != 0) { 
				info.put("processingHandleBeginTime", info.get("processingHandleBeginTime").toString());
				if (alarmCover.getHandelState() == 2) {
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("finishView", allView.getFinishView().getImg());
				}
				if (alarmCover.getHandelState() == 3) { 
					info.put("handleEndTime", info.get("handleEndTime").toString());
					AlarmViewJsonRootBean allView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", allView.getProcessedView().getImg());
					if (pa.getWorderNo()!=null&&pa.getWorderNo()!=""&&pa.getWorderNo().length()>0) {
						info.put("worderCreateTime", info.get("worderCreateTime").toString());
					}
				}
				if (alarmCover.getHandelState() == 4) {
					info.put("alarmHandleEndTime", info.get("alarmHandleEndTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("processedView", alarmView.getProcessedView().getImg());
					info.put("worderCreateTime", info.get("worderCreateTime").toString());
					info.put("worderHandleEndTime", info.get("worderHandleEndTime").toString());
					WorderHandleJsonRootBeanDM worderView = gson.fromJson(info.get("worderView").toString(),
							WorderHandleJsonRootBeanDM.class);
					System.err.println(info.get("worderView").toString());
					List<Object> list =new ArrayList<Object>();
					if (worderView.getWorderHandleFinishView()!=null) {
						info.put("worderHanldeFinishImgView", worderView.getWorderHandleFinishView().getImage());
					}else {
						info.put("worderHanldeFinishImgView", list);
					}
					info.remove("worderView");
				}
				if (alarmCover.getHandelState() == 5) {
					info.put("closeTime", info.get("closeTime").toString());
					AlarmViewJsonRootBean alarmView = gson.fromJson(info.get("allViewJson").toString(),
							AlarmViewJsonRootBean.class);
					info.put("closeView", alarmView.getClosedView().getImg());
				}
			}
			info.put("alarmTime", info.get("alarmTime").toString());
			info.remove("serialVersionUID");
			info.remove("pendingVideoBeanJson");
			info.remove("allViewJson");
		}
		info.remove("address");
		return info;
		}
		else {
			return null;
		}
		
	}

	//新增报警
	@Override
	public String insertAlarmToMain(AlarmInsertPA alarmInsertPA) throws HKException {
		Gson gson=new Gson();
		Map<String, Object> lib = new HashMap<>();
		if (alarmInsertPA.getAlarmType() == 1) {
			lib = alarmInsertPA.getLib();
		} else {
			lib = super.getTableParams(alarmInsertPA.getCommunityId());
		}
		if (alarmInsertPA.getAlarmVideo()!=null) {
			String videoInfo[]=alarmInsertPA.getAlarmVideo().split(",");
			if (alarmInsertPA.getAlarmVideo()!=null&&alarmInsertPA.getAlarmVideo()!="") {
				Map<String, Object> videoLngLat=alarmMapper.getVideoLngLat(lib, videoInfo[0]);
				if (videoLngLat!=null) {
					alarmInsertPA.setLng(videoLngLat.get("lng").toString());
					alarmInsertPA.setLat(videoLngLat.get("lat").toString());
				}else {
					alarmInsertPA.setLng(communityService.getCommunity(alarmInsertPA.getCommunityId()).getLon());
					alarmInsertPA.setLat(communityService.getCommunity(alarmInsertPA.getCommunityId()).getLat());
				}
			}
			else {
				alarmInsertPA.setLng(communityService.getCommunity(alarmInsertPA.getCommunityId()).getLon());
				alarmInsertPA.setLat(communityService.getCommunity(alarmInsertPA.getCommunityId()).getLat());
			}
		}else {
			List<Map<String, Object>> roomListByPid = roomService.getRoomListByPid(alarmInsertPA.getAlarmSourceId(), alarmInsertPA.getCommunityId());
			RoomUM detailsDataRoom = roomService.detailsData(Integer.parseInt(roomListByPid.get(0).get("roomId").toString()), alarmInsertPA.getCommunityId());
			BuildingUM detailsDataBuilding = buildingService.detailsData(detailsDataRoom.getBuildingId(), alarmInsertPA.getCommunityId());
			alarmInsertPA.setLng(detailsDataBuilding.getLon());
			alarmInsertPA.setLat(detailsDataBuilding.getLat());
		}
		alarmInsertPA.setTest(lib);
		List<AlarmInsertPA> checkAlarm = alarmMapper.checkAlarmLog(lib, alarmInsertPA);
		
		Message msg = new Message();
		Map<String, Object> alarmMsg=new HashMap<>();
		alarmMsg.put("alarmMessage", alarmInsertPA.getAlarmMessage());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		alarmMsg.put("alarmTime",df.format(new Date()));
		alarmMsg.put("alarmAddress",alarmInsertPA.getAlarmAddress());
		msg.setCommunityId(alarmInsertPA.getCommunityId()+"");
		msg.setSource("0");
		msg.setCategory("C01");
		msg.setCode("alarmMsg");
		msg.setData(alarmMsg);
		msgServiceImpl.sendMessage(msg);
		
		if (checkAlarm!=null&&alarmInsertPA.getAlarmSource()!="陌生人"&&checkAlarm.size()>0) {
			for (int i = 0; i < checkAlarm.size(); i++) {
				AlarmInsertPA paForLog = alarmMapper.getAlarmMainInfo(lib, checkAlarm.get(i).getAlarmID());
				alarmMapper.insertAlarmToVice(lib, paForLog);
				
				Message strealtimeMessage = new Message();
				Map<String, Object> strealtimeMap=new HashMap<>();
				strealtimeMap.put("alarmMessage", alarmInsertPA.getAlarmMessage());
				strealtimeMap.put("alarmTime",df.format(new Date()));
				strealtimeMap.put("address",alarmInsertPA.getAlarmAddress());
				strealtimeMap.put("alarmId",checkAlarm.get(0).getAlarmID());
				strealtimeMap.put("alarmImg",alarmInsertPA.getAlarmImg());
				strealtimeMap.put("alarmLevel",alarmInsertPA.getAlarmLevel());
				strealtimeMap.put("alarmSource",alarmInsertPA.getAlarmSource());
				strealtimeMap.put("alarmType",alarmInsertPA.getAlarmType());
				strealtimeMap.put("lat",alarmInsertPA.getLat());
				strealtimeMap.put("lng",alarmInsertPA.getLng());
				strealtimeMap.put("lableId",1);
				strealtimeMap.put("alarmFlag",alarmMapper.getAlarmMainWork(lib, paForLog.getAlarmID(),1));
				Map<String, Object> getInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(lib, paForLog.getAlarmID(), 1);
				strealtimeMap.put("alarmState", getInfoMap.get("报警状态"));
				strealtimeMap.put("alarmNo", getInfoMap.get("报警编号"));
				strealtimeMap.put("alarmHandler", getInfoMap.get("处理人"));
				if (alarmInsertPA.getAlarmType()==3) {
					strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfVehicle());
				}else {
					strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfPerson());
				}
				strealtimeMessage.setCommunityId(alarmInsertPA.getCommunityId()+"");
				strealtimeMessage.setSource("0");
				strealtimeMessage.setCategory("C01");
				strealtimeMessage.setCode("strealtimeAlarmMsg");
				strealtimeMessage.setData(strealtimeMap);
				msgServiceImpl.sendMessage(strealtimeMessage);
			}
			return "插入报警日志成功";
		} else {
			if (alarmInsertPA.getAlarmType()==2) {
				String[] info=alarmInsertPA.getHandelUser().split(",");
				AlarmUserRootBean userBean=new AlarmUserRootBean();
				List<User> userList=new ArrayList<>();
				for (int i = 0; i < info.length; i++) {
			        User userInfo = new User();
					userInfo.setId(info[i]);
					userList.add(userInfo);
				}
				userBean.setUser(userList);
				alarmInsertPA.setHandelUser(gson.toJson(userBean));
				alarmMapper.insertAlarmToMain(alarmInsertPA);
			}else {
				String[] info=alarmInsertPA.getHandelUser().split(",");
				AlarmUserRootBean userBean=new AlarmUserRootBean();
				List<User> userList=new ArrayList<>();
				for (int i = 0; i < info.length; i++) {
			        User userInfo = new User();
					userInfo.setId(info[i]);
					userList.add(userInfo);
				}
				userBean.setUser(userList);
				alarmInsertPA.setHandelUser(gson.toJson(userBean));
				alarmMapper.insertAlarmToMain(alarmInsertPA);
			}
			String alarmViewJsonStr = alarmMapper.getAlarmView(lib, alarmInsertPA.getAlarmID()); // 获取数据库存储的json
			AlarmViewJsonRootBean alarmViewBean = new AlarmViewJsonRootBean();
			alarmViewBean = gson.fromJson(alarmViewJsonStr, AlarmViewJsonRootBean.class); // json转为实体类
			List<AlarmImg> pengdingImgList = new ArrayList<>();
			List<AlarmMp3> pengdingMp3List = new ArrayList<>();
			List<AlarmVideo> pengdingVideoList = new ArrayList<>();
			AlarmPendingView alarmPendingView = alarmViewBean.getPendingView();
			if (alarmInsertPA.getAlarmImg() != null) {
				List<String> paView = new ArrayList<>(); // 准备放param传过来资源的数组
				String[] listInfo = alarmInsertPA.getAlarmImg().split(",");// 传过来的资源字符串分割把内容写进资源数组
				for (int i = 0; i < listInfo.length; i++) {
					paView.add(listInfo[i]);
				}
				for (int i = 1; i < paView.size() + 1; i++) { // 往关闭报警资源实体类中添加关闭报警资源数组的内容
					AlarmImg pengdingImg = new AlarmImg();
					pengdingImg.setId("" + i + "");
					pengdingImg.setUrl(paView.get(i - 1));
					pengdingImgList.add(pengdingImg);
				}
				alarmPendingView.setImg(pengdingImgList);
			}
			if (alarmInsertPA.getAlarmMp3() != null) {
				List<String> paView = new ArrayList<>(); // 准备放param传过来资源的数组
				String[] listInfo = alarmInsertPA.getAlarmMp3().split(",");// 传过来的资源字符串分割把内容写进资源数组
				for (int i = 0; i < listInfo.length; i++) {
					paView.add(listInfo[i]);
				}
				for (int i = 1; i < paView.size() + 1; i++) { // 往关闭报警资源实体类中添加关闭报警资源数组的内容
					AlarmMp3 pengdingMp3 = new AlarmMp3();
					pengdingMp3.setId("" + i + "");
					pengdingMp3.setUrl(paView.get(i - 1));
					pengdingMp3List.add(pengdingMp3);
				}
				alarmPendingView.setMp3(pengdingMp3List);
			}
			if (alarmInsertPA.getAlarmVideo() != null) {
				List<String> paView = new ArrayList<>(); // 准备放param传过来资源的数组
				String[] listInfo = alarmInsertPA.getAlarmVideo().split(",");// 传过来的资源字符串分割把内容写进资源数组
				for (int i = 0; i < listInfo.length; i++) {
					paView.add(listInfo[i]);
				}
				for (int i = 1; i < paView.size() + 1; i++) { // 往关闭报警资源实体类中添加关闭报警资源数组的内容
					AlarmVideo pengdingVideo = new AlarmVideo();
					pengdingVideo.setId("" + i + "");
					pengdingVideo.setUrl(paView.get(i - 1));
					pengdingVideoList.add(pengdingVideo);
				}
				alarmPendingView.setVideo(pengdingVideoList);
			}
			alarmViewBean.setPendingView(alarmPendingView);
			alarmMapper.upDateViewJson(lib, alarmInsertPA.getAlarmID(), JSON.toJSONString(alarmViewBean));
			
			Message strealtimeMessage = new Message();
			Map<String, Object> strealtimeMap=new HashMap<>();
			strealtimeMap.put("alarmMessage", alarmInsertPA.getAlarmMessage());
			strealtimeMap.put("alarmTime",df.format(new Date()));
			strealtimeMap.put("address",alarmInsertPA.getAlarmAddress());
			strealtimeMap.put("alarmId",alarmInsertPA.getAlarmID());
			strealtimeMap.put("alarmImg",alarmInsertPA.getAlarmImg());
			strealtimeMap.put("alarmLevel",alarmInsertPA.getAlarmLevel());
			strealtimeMap.put("alarmSource",alarmInsertPA.getAlarmSource());
			strealtimeMap.put("alarmType",alarmInsertPA.getAlarmType());
			strealtimeMap.put("lat",alarmInsertPA.getLat());
			strealtimeMap.put("lng",alarmInsertPA.getLng());
			strealtimeMap.put("lableId",1);
			strealtimeMap.put("alarmFlag",alarmMapper.getAlarmMainWork(lib, alarmInsertPA.getAlarmID(),1));
			Map<String, Object> getInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(lib, alarmInsertPA.getAlarmID(), 1);
			strealtimeMap.put("alarmState", getInfoMap.get("报警状态"));
			strealtimeMap.put("alarmNo", getInfoMap.get("报警编号"));
			strealtimeMap.put("alarmHandler", getInfoMap.get("处理人"));
			if (alarmInsertPA.getAlarmType()==3) {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfVehicle());
			}else {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmSourceId());
			}
			strealtimeMessage.setCommunityId(alarmInsertPA.getCommunityId()+"");
			strealtimeMessage.setSource("0");
			strealtimeMessage.setCategory("C01");
			strealtimeMessage.setCode("strealtimeAlarmMsg");
			strealtimeMessage.setData(strealtimeMap);
			msgServiceImpl.sendMessage(strealtimeMessage);
			
			return "插入报警主表成功";
		}

	}

	//报警日志
	@Override
	public List<Map<String, Object>> AlarmLog(AlarmLogPA alarmLogPA) {
		Map<String, Object> lib = super.getTableParams(alarmLogPA.getCommunityId());
		List<Map<String, Object>> info = alarmMapper.selectAlarmLogByAlarmId(lib, alarmLogPA.getAlarmID());
		for (int i = 0; i < info.size(); i++) {
			Map<String, Object> map = info.get(i);
			map.put("alarmTime", map.get("alarmTime").toString());
			info.set(i, map);
		}
		return info;
	}

	//报警总况
	@Override
	public Map<String, Object> alarmCountInfo(CommunityParams pa) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmCountByType", alarmMapper.getAlarmCountByType(lib));//(本月报警总况(事件报警合并))
		infoMap.put("alarmCountBySource", alarmMapper.getAlarmCountBySource(lib));//(本月报警来源分布(事件报警合并))
		infoMap.put("alarmCountByTrend", alarmMapper.getAlarmTrend(lib));//(报警趋势（环比）)
		List<Map<String, Object>> info = alarmMapper.getAlarmType(lib);//获取到本月报警类型分布
		info.add(alarmMapper.getAlarmTypeCar(lib));//把获取到的本月报警车辆总数,添加到本月报警类型分布
		infoMap.put("alarmCountBySourceType", info);//(本月报警类型分布(事件报警合并))
		infoMap.put("alarmCountByPerTypeToMonth", alarmMapper.getAlarmCountByPerType(lib));//(本月人口报警统计)
		infoMap.put("alarmCountByCarTypeToMonth", car.selectAttentionCount(pa.getCommunityId()));//(本月车辆报警类型统计)
//		infoMap.put("alarmCountAndPercentByCase", alarmMapper.getAlarmCountAndPercentByCase(lib));
		infoMap.put("alarmCountByHour", alarmMapper.getAlarmCountByHour(lib));
		infoMap.put("alarmCountByDevAlarmType", alarmMapper.getAlarmCountByDevAlarmType(lib));
		infoMap.put("alarmCountByDevModule", alarmMapper.getAlarmCountByDevModule(lib));
//		infoMap.put("alarmCountPercentByLevel", alarmMapper.getAlarmCountPercentByLevel(lib));
//		infoMap.put("currentAlarmCountAndLastMonthAlarmCountByDay",
//				
//		alarmMapper.getCurrentAlarmCountAndLastMonthAlarmCountByDay(lib));
		
		return infoMap;
	}

	//创建工单
	@Override
	public String worderCreate(WorderCreatePA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		pa.setMap(library);
		Gson gson = new Gson();
		if (pa.getSourceType() == 4) {
			alarmMapper.creatWorder(pa);
		} else if (pa.getSourceType() == 1) {
			List<WorderCreateAlarmInfoPA> alarmInfo = alarmMapper.getDevRelationAlarm(2, pa.getAlarmId(), null, null,
					library, null, null, null);
			for (int i = 0; i < alarmInfo.size(); i++) {
				pa.setSourceType(1);
				pa.setDevId(alarmInfo.get(i).getDevId());
				pa.setModuleId(alarmInfo.get(i).getModuleId());
				pa.setSourceId(String.valueOf(alarmInfo.get(i).getSourceId()));
				pa.setAddress(alarmInfo.get(i).getSourceInfo());
				pa.setLng(alarmInfo.get(i).getLng());
				pa.setLat(alarmInfo.get(i).getLat());
				System.err.println(pa);
				System.err.println(alarmInfo.get(i));
				if (alarmMapper.checkWorderCount(library, Integer.parseInt(pa.getSourceId()), 1)==0) {
					alarmMapper.creatWorder(pa);
					alarmMapper.updateWorderNo(library, pa.getWorderId());
					alarmMapper.updateAlarmWorderId(library, pa.getWorderId(),Integer.parseInt(alarmInfo.get(i).getSourceId()), pa.getUserId());
				}
			}
		} else if (pa.getSourceType() == 2) {
			List<WorderCreateEventInfoPA> eventInfo = alarmMapper.getEventInfo(2, null, null, pa.getEventIdStr(),
					library, null, null, null);
			if (pa.getEventType()!=null) {
				pa.setAddress(eventInfo.get(0).getAddress());
				pa.setSourceType(2);
				pa.setSourceId(eventInfo.get(0).getSourceId().toString());
				pa.setLng(eventInfo.get(0).getLng());
				pa.setLat(eventInfo.get(0).getLat());
				alarmMapper.creatWorder(pa);
				alarmMapper.updateWorderNo(library, pa.getWorderId());
				alarmMapper.updateEventWorderId(library, pa.getWorderId(), Integer.parseInt(pa.getSourceId()), pa.getEventType(), pa.getEmergencyLevel(),pa.getHandleUserId());
			}else {
			for (int j = 0; j < eventInfo.size(); j++) {
				pa.setAddress(eventInfo.get(j).getAddress());
				pa.setSourceId(String.valueOf(eventInfo.get(j).getSourceId()));
				pa.setLng(eventInfo.get(j).getLng());
				pa.setLat(eventInfo.get(j).getLat());
				if (alarmMapper.checkWorderCount(library, Integer.parseInt(pa.getSourceId()), 2)==0) {
					alarmMapper.creatWorder(pa);
					alarmMapper.updateWorderNo(library, pa.getWorderId());
					alarmMapper.updateEventWorderIdDemo(library, pa.getWorderId(), Integer.parseInt(pa.getSourceId()));
				}
			}
			}
		} else if (pa.getSourceType() == 3) {
			List<Device> device = gson.fromJson(pa.getDeviceBeanJson(), DeviceRootBean.class).getDevice();
			List<WorderCreateAlarmInfoPA> devRelationAlarmInfo = new ArrayList<>();
			for (int i = 0; i < device.size(); i++) {
				List<WorderCreateAlarmInfoPA> alarmInfo = alarmMapper.getDevRelationAlarm(1, null,
						device.get(i).getModuleid(), device.get(i).getId(), library, null, null, null);
				if (alarmInfo.size() != 0) {
					for (int q = 0; q < alarmInfo.size(); q++) {
						devRelationAlarmInfo.add(alarmInfo.get(q));
					}
				}
				if (alarmInfo.size() == 0) {
					WorderCreateDevInfoPA dev = alarmMapper.getDevInfoForCreate(0,
							String.valueOf(device.get(i).getId()), device.get(i).getModuleid(), null, library).get(0);
					BeanUtils.copyProperties(dev, pa);
					if (alarmMapper.checkWorderCount(library, dev.getDevId(), 3)==0) {
						alarmMapper.creatWorder(pa);
						alarmMapper.updateWorderNo(library, pa.getWorderId());
					}
				}
			}
			if (pa.getAlarmId() != null) {
				List<WorderCreateAlarmInfoPA> test = alarmMapper.getDevRelationAlarm(2, pa.getAlarmId(), null, null,
						library, null, null, null);
				for (int i = 0; i < test.size(); i++) {
					devRelationAlarmInfo.add(test.get(i));
				}
			}
			if (devRelationAlarmInfo != null) {
				HashSet<WorderCreateAlarmInfoPA> h = new HashSet<WorderCreateAlarmInfoPA>(devRelationAlarmInfo);
				devRelationAlarmInfo.clear();
				devRelationAlarmInfo.addAll(h);
				for (int i = 0; i < devRelationAlarmInfo.size(); i++) {
					BeanUtils.copyProperties(devRelationAlarmInfo.get(i), pa);
					pa.setSourceType(1);
					if (alarmMapper.checkWorderCount(library, Integer.parseInt(devRelationAlarmInfo.get(i).getSourceId()), 1)==0) {
						alarmMapper.creatWorder(pa);
						alarmMapper.updateWorderNo(library, pa.getWorderId());
					}
				}
			}
		}
		return pa.getWorderId().toString();
	}

	//获取事件信息***创建工单用
	@SuppressWarnings("unused")
	@Override
	public List<WorderCreateEventInfoPA> getEventInfoForCreateWorder(WorderCreatePA pa) {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		pa.setMap(library);
		List<WorderCreateEventInfoPA> eventInfo = alarmMapper.getEventInfo(pa.getQueryType(), pa.getDevId(),
				pa.getModuleId(), pa.getEventIdStr(), library, pa.getSelectStartTime(), pa.getSelectEndTime(),
				pa.getSelectWord());
		for (int i = 0; i < eventInfo.size(); i++) {
			WorderCreateEventInfoPA info = eventInfo.get(i);
			info.setCommunityId(pa.getCommunityId());
			info.setSource(1);
			eventInfo.set(i, info);
		}
		if (eventInfo!=null) {
			return eventInfo;
		}else {
			return null;
		}
	}

	//获取报警信息***创建工单用
	@SuppressWarnings("unused")
	@Override
	public List<WorderCreateAlarmInfoPA> getAlarmInfoForCreateWorder(WorderCreatePA pa) {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		pa.setMap(library);
		if (pa.getQueryType()==1) {
			String[] testStrings=pa.getDevIdStr().split(",");
			List<WorderCreateAlarmInfoPA> alarmInfo=new ArrayList<>();
			for (int i = 0; i < testStrings.length; i++) {
				List<WorderCreateAlarmInfoPA> testListInfo = alarmMapper.getDevRelationAlarm(pa.getQueryType(), pa.getAlarmId(),
						pa.getDeviceModule(), Integer.parseInt(testStrings[i]), library, pa.getSelectStartTime(), pa.getSelectEndTime(),
						pa.getSelectWord());
				for (int j = 0; j < testListInfo.size(); j++) {
					alarmInfo.add(testListInfo.get(j));
				}
			}
			for (int i = 0; i < alarmInfo.size(); i++) {
				WorderCreateAlarmInfoPA info = alarmInfo.get(i);
				info.setCommunityId(pa.getCommunityId());
				info.setSource(1);
				alarmInfo.set(i, info);
			}
			if (alarmInfo!=null) {
				return alarmInfo;
			}else {
				return null;
			}
		}else {
			List<WorderCreateAlarmInfoPA> alarmInfo = alarmMapper.getDevRelationAlarm(pa.getQueryType(), null,
					null, null, library, pa.getSelectStartTime(), pa.getSelectEndTime(),
					pa.getSelectWord());
			for (int i = 0; i < alarmInfo.size(); i++) {
				WorderCreateAlarmInfoPA info = alarmInfo.get(i);
				info.setCommunityId(pa.getCommunityId());
				info.setSource(1);
				alarmInfo.set(i, info);
			}
			if (alarmInfo!=null) {
				return alarmInfo;
			}else {
				return null;
			}
		}
	}

	//获取设备信息***创建工单用
	@Override
	public List<WorderCreateDevInfoPA> getDevInfoForCreate(WorderCreatePA pa) {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		pa.setMap(library);
		List<WorderCreateDevInfoPA> devInfoForCreate = alarmMapper.getDevInfoForCreate(pa.getQueryType(),
				String.valueOf(pa.getDevId()), pa.getDeviceModule(), pa.getMac(), library);
		for (int i = 0; i < devInfoForCreate.size(); i++) {
			WorderCreateDevInfoPA info = devInfoForCreate.get(i);
			info.setCommunityId(pa.getCommunityId());
			info.setSource(1);
			devInfoForCreate.set(i, info);
		}
		return devInfoForCreate;
	}

	//前端获取处理时间列表
	@Override
	public List<Map<String, Object>> getWorderTime(WorderCreatePA pa) {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		pa.setMap(library);
		return alarmMapper.getWorderTime(library);
	}

	//新增报警***设备专用
	@Override
	public Integer insertAlarmToMainDevice(AlarmInsertPA alarmInsertPA) throws HKException {
		Gson gson=new Gson();
		Map<String, Object> lib = null;
		if (alarmInsertPA.getLib()!=null) 
			 lib = alarmInsertPA.getLib();
		else
			 lib = super.getTableParams(alarmInsertPA.getCommunityId());
		// 报警表数据id
		alarmInsertPA.setTest(lib);
		List<AlarmInsertPA> checkAlarm = alarmMapper.checkAlarmLog(lib, alarmInsertPA);
		if (checkAlarm!=null&&checkAlarm.size()!=0) {
			AlarmInsertPA paForLog = alarmMapper.getAlarmMainInfo(lib, checkAlarm.get(0).getAlarmID());
			alarmMapper.insertAlarmToVice(lib, paForLog);
			alarmMapper.updateAlarmMainTime(lib, checkAlarm.get(0).getAlarmID());
			
			Message strealtimeMessage = new Message();
			Map<String, Object> strealtimeMap=new HashMap<>();
			strealtimeMap.put("alarmMessage", alarmInsertPA.getAlarmMessage());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strealtimeMap.put("alarmTime",df.format(new Date()));
			strealtimeMap.put("address",alarmInsertPA.getAlarmAddress());
			strealtimeMap.put("alarmId",alarmInsertPA.getAlarmID());
			strealtimeMap.put("alarmImg",alarmInsertPA.getAlarmImg());
			strealtimeMap.put("alarmLevel",alarmInsertPA.getAlarmLevel());
			strealtimeMap.put("alarmSource",alarmInsertPA.getAlarmSource());
			strealtimeMap.put("alarmType",alarmInsertPA.getAlarmType());
			strealtimeMap.put("lat",alarmInsertPA.getLat());
			strealtimeMap.put("lng",alarmInsertPA.getLng());
			strealtimeMap.put("lableId",1);
			strealtimeMap.put("alarmFlag",alarmMapper.getAlarmMainWork(lib, alarmInsertPA.getAlarmID(),1));
			Map<String, Object> getInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(lib, alarmInsertPA.getAlarmID(), 1);
			strealtimeMap.put("alarmState", getInfoMap.get("报警状态"));
			strealtimeMap.put("alarmNo", getInfoMap.get("报警编号"));
			strealtimeMap.put("alarmHandler", getInfoMap.get("处理人"));
			if (alarmInsertPA.getAlarmType()==3) {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfVehicle());
			}else {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmSourceId());
			}
			strealtimeMessage.setCommunityId(alarmInsertPA.getCommunityId()+"");
			strealtimeMessage.setSource("0");
			strealtimeMessage.setCategory("C01");
			strealtimeMessage.setCode("strealtimeAlarmMsg");
			strealtimeMessage.setData(strealtimeMap);
			msgServiceImpl.sendMessage(strealtimeMessage);
			
			return paForLog.getAlarmID();
		} else {
			lib.put("alarmStateId", alarmInsertPA.getAlarmSourceType());
			lib.put("devid", alarmInsertPA.getAlarmSourceId());
			AlarmInsertPA paInfo=alarmMapper.getAlarmDevInfo(lib);
			alarmInsertPA.setAlarmAddress(paInfo.getAlarmAddress());
			alarmInsertPA.setAlarmMessage(paInfo.getAlarmMessage());
			alarmInsertPA.setLng(paInfo.getLng());
			alarmInsertPA.setLat(paInfo.getLat());
			alarmInsertPA.setAlarmSourceType(paInfo.getAlarmSourceType());
			alarmInsertPA.setAlarmLevel(paInfo.getAlarmLevel());
			if (paInfo.getHandelUser()!=null) {
			String userStr=paInfo.getHandelUser().replace("{\"userid\": [", "").replace("]}", "").replaceAll(" ", "");
			AlarmUserRootBean userBean=new AlarmUserRootBean();
			User userInfo = new User();
			List<User> userList=new ArrayList<>();
			String s[]=userStr.split(",");
			for (int i = 0; i < s.length; i++) {
				userInfo.setId(s[i]);
				userList.add(userInfo);
			}
			userBean.setUser(userList);
			alarmInsertPA.setHandelUser(gson.toJson(userBean));
			}
			alarmMapper.insertAlarmToMain(alarmInsertPA);
			
			Message strealtimeMessage = new Message();
			Map<String, Object> strealtimeMap=new HashMap<>();
			strealtimeMap.put("alarmMessage", alarmInsertPA.getAlarmMessage());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strealtimeMap.put("alarmTime",df.format(new Date()));
			strealtimeMap.put("address",alarmInsertPA.getAlarmAddress());
			strealtimeMap.put("alarmId",alarmInsertPA.getAlarmID());
			strealtimeMap.put("alarmImg",alarmInsertPA.getAlarmImg());
			strealtimeMap.put("alarmLevel",alarmInsertPA.getAlarmLevel());
			strealtimeMap.put("alarmSource",alarmInsertPA.getAlarmSource());
			strealtimeMap.put("alarmType",alarmInsertPA.getAlarmType());
			strealtimeMap.put("lat",alarmInsertPA.getLat());
			strealtimeMap.put("lng",alarmInsertPA.getLng());
			strealtimeMap.put("lableId",1);
			strealtimeMap.put("alarmFlag",alarmMapper.getAlarmMainWork(lib, alarmInsertPA.getAlarmID(),1));
			Map<String, Object> getInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(lib, alarmInsertPA.getAlarmID(), 1);
			strealtimeMap.put("alarmState", getInfoMap.get("报警状态"));
			strealtimeMap.put("alarmNo", getInfoMap.get("报警编号"));
			strealtimeMap.put("alarmHandler", getInfoMap.get("处理人"));
			if (alarmInsertPA.getAlarmType()==3) {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfVehicle());
			}else {
				strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmSourceId());
			}
			strealtimeMessage.setCommunityId(alarmInsertPA.getCommunityId()+"");
			strealtimeMessage.setSource("0");
			strealtimeMessage.setCategory("C01");
			strealtimeMessage.setCode("strealtimeAlarmMsg");
			strealtimeMessage.setData(strealtimeMap);
			msgServiceImpl.sendMessage(strealtimeMessage);
			
		}
		alarmMapper.updateDevState(alarmInsertPA.getTest(), alarmInsertPA.getAlarmSourceType(),alarmInsertPA.getAlarmSourceId());
		return alarmInsertPA.getAlarmID();
	}

	//工单详情
	@Override
	public Map<String, Object> worderDetail(WorderDetailPA pa) throws HKException {
		Gson gson=new Gson();
		Map<String, Object> detailInfo=new HashMap<>();
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		WorderDetailPA checkInfo=alarmMapper.getWorderStateType(library, pa.getWorderId());
		if (checkInfo!=null) {
			Map<String, Object> detailBaseInfo = alarmMapper.getWorderDetail(library,pa.getWorderId(), checkInfo.getState(),checkInfo.getType()	);
			String leaderTipJsonStr=detailBaseInfo.get("leaderTipJson").toString();
			leaderRootBean bean=gson.fromJson(leaderTipJsonStr, leaderRootBean.class);
			detailBaseInfo.remove("leaderTipJson");
			detailBaseInfo.put("leaderTips", bean);
			detailBaseInfo.put("orderCreateTime", detailBaseInfo.get("orderCreateTime").toString());
			if (checkInfo.getType()==1) {
			detailBaseInfo.put("alarmTime", detailBaseInfo.get("alarmTime").toString());
			}
			if (checkInfo.getType()==2) {
			detailBaseInfo.put("eventCreateTime", detailBaseInfo.get("eventCreateTime").toString());
			}
			if (checkInfo.getState()==3) {
				WorderHandleHangViewDM hangViewDM=gson.fromJson(detailBaseInfo.get("hangViewJson").toString(), WorderHandleHangViewDM.class);
				detailBaseInfo.put("hangViewJson", hangViewDM.getImage());
			}
			if (checkInfo.getState()==4) {
				WorderHandleFinishViewDM finishViewDM=gson.fromJson(detailBaseInfo.get("finishViewJson").toString(), WorderHandleFinishViewDM.class);
				if (finishViewDM!=null) {
					detailBaseInfo.put("finishViewJson", finishViewDM.getImage());
				}else {
					List<Object> list = new ArrayList<Object>();
					detailBaseInfo.put("finishViewJson", list);
				}
				detailBaseInfo.put("worderHandleEndTime", detailBaseInfo.get("worderHandleEndTime").toString());
			}
			detailInfo.putAll(detailBaseInfo);
			return detailInfo;
		}
		else {
			return null;
		}
	}

	//前端获取设备模块列表
	@Override
	public List<Map<String, Object>> getDevModule() {
		return alarmMapper.getDevModule();
	}

	//事件详情
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> eventDetail(EventDetailPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		EventDetailPA checkDetailPA = alarmMapper.getEventStateType(library, pa.getEventId());
		if (checkDetailPA!=null) {
			pa.setState(checkDetailPA.getState());
		Map<String, Object> detailBaseInfo = alarmMapper.getEventDetail(library, pa.getEventId(), pa.getState());
		Map<String, Object> eventInfo=new HashMap<>();
		Gson gson=new Gson();
		EventViewJsonRootBean eventViewJsonRootBean=gson.fromJson(detailBaseInfo.get("eventView").toString(), EventViewJsonRootBean.class);
		detailBaseInfo.remove("eventView");
		detailBaseInfo.put("eventReportVideo", eventViewJsonRootBean.getPendingView().getVideo());
		detailBaseInfo.put("eventReportImg", eventViewJsonRootBean.getPendingView().getImg());
		detailBaseInfo.put("eventReportMp3", eventViewJsonRootBean.getPendingView().getMp3());
		
		WorderHandleJsonRootBeanDM worderViewRootBean=gson.fromJson(detailBaseInfo.get("worderView").toString(), WorderHandleJsonRootBeanDM.class);
		detailBaseInfo.remove("worderView");
		String leaderTipJsonStr=detailBaseInfo.get("leaderTips").toString();
		leaderRootBean bean=gson.fromJson(leaderTipJsonStr, leaderRootBean.class);
		detailBaseInfo.put("leaderTips", bean);
		detailBaseInfo.put("reportTime", detailBaseInfo.get("reportTime").toString());
		detailBaseInfo.put("eventHtime", detailBaseInfo.get("eventHtime").toString());
		if (pa.getState()==0) {
			detailBaseInfo.remove("leaderTips");
		}
		if (pa.getState()==1) {
			detailBaseInfo.put("distributionTime", detailBaseInfo.get("distributionTime").toString());
		}
		if (pa.getState()==2) {
			detailBaseInfo.put("distributionTime", detailBaseInfo.get("distributionTime").toString());
			detailBaseInfo.put("worderHandleBeginTime", detailBaseInfo.get("worderHandleBeginTime").toString());
		}
		if (pa.getState()==3) {
			detailBaseInfo.put("distributionTime", detailBaseInfo.get("distributionTime").toString());
			detailBaseInfo.put("worderHandleEndTime", detailBaseInfo.get("worderHandleEndTime").toString());
			List<Object> list =new ArrayList<Object>();
			if (worderViewRootBean.getWorderHandleFinishView()!=null) {
				detailBaseInfo.put("worderFinishImg", worderViewRootBean.getWorderHandleFinishView().getImage());
			}else {
				detailBaseInfo.put("worderFinishImg", list);
			}
			detailBaseInfo.put("worderHandleBeginTime", detailBaseInfo.get("worderHandleBeginTime").toString());
		}
		if (pa.getState()==4) {
			detailBaseInfo.put("worderCloseImg",eventViewJsonRootBean.getClosedView().getImg());
		}
		if (checkDetailPA.getType()==1) {
			detailBaseInfo.put("devInfo", alarmMapper.getEventRelationDev(library, Integer.parseInt(detailBaseInfo.get("sourceId").toString())));
		}
		detailBaseInfo.remove("sourceId");
		eventInfo.putAll(detailBaseInfo);
			return eventInfo;
		}else {
			return null;
		}
	}
	
	//报警后台统计
	@Override
	public Map<String, Object> alarmBackCountInfo(CommunityParams pa) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmHTCountByType", alarmMapper.getAlarmHTCountBySource(lib));//(设备感知,人工上报,民意舆情)
		List<Map<String, Object>> info=alarmMapper.getAlarmHTCountByAllSource(lib);
		info.add(alarmMapper.getHTAlarmCarCount(lib));
		infoMap.put("alarmHTCountByAllType", info);//(按本周统计设备感知和人工上报和民意舆情的次数)
		infoMap.put("alarmHTCountByWeek", alarmMapper.getAlarmHTCountByWeek(lib));//(按本周统计事件发生次数)
		return infoMap;
	}

	//态势分析---今日报警总数
	@Override
	public Map<String, Object> alarmSTCountInfo(CommunityParams pa) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmSTAlarmCountToday", alarmMapper.getSTAlarmTodayCount(lib));//今日报警总数
		return infoMap;
	}

	//态势分析---报警趋势分析
	@Override
	public Map<String, Object> alarmSTTrendUMs(CommunityParams pa, Integer queryType) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		List<AlarmTrendUM> alarmTrendUM =  alarmMapper.getSTAlarmCountByTrend(lib, queryType);//获取到报警趋势
		infoMap.put("alarmSTAlarmCountByThrend", alarmTrendUM);//报警趋势分析
		return infoMap;
	}

	//态势分析---报警时段分析
	@Override
	public Map<String, Object> alarmSTAlarmCountByHour(CommunityParams pa, Integer queryType) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmSTAlarmCountByHour", alarmMapper.getSTAlarmCountByHour(lib,queryType));//报警时段分析
		return infoMap;
	}

	//态势分析---报警类型分布
	@Override
	public Map<String, Object> alarmSTAlarmCountBySourceType(CommunityParams pa, Integer queryType) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		List<Map<String, Object>> info = alarmMapper.getSTAlarmCountBySourceType(lib, queryType);
		info.add(alarmMapper.getSTAlarmCountBySourceTypeCar(lib, queryType));//把单独查出的报警车辆统计加进去
		infoMap.put("alarmSTAlarmCountBySourceType", info);//报警类型分布
		return infoMap;
	}

	//态势分析---未处理报警列表
	@Override
	public Map<String, Object> alarmSTAlarmCountNotDealCount(CommunityParams pa) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmSTAlarmCountNotDealCount", alarmMapper.getSTAlarmCountNotDealCount(lib));//未处理报警总数
		List<AlarmNotDealListUM> alarmNotDealListUM = alarmMapper.getSTAlarmCountNotDealCountList(lib);//获取未处理报警列表
		infoMap.put("alarmSTAlarmCountNotDealList", alarmNotDealListUM);//未处理报警列表
		return infoMap;
	}

	//态势分析---累计处理报警总数
	@Override
	public Map<String, Object> alarmSTAlarmCountProcessDealList(CommunityParams pa, Integer queryType) throws HKException {
		Map<String, Object> lib = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmSTAlarmCountProcessDealList", alarmMapper.getSTAlarmCountProcess(lib, queryType));//累计处理报警总数
		return infoMap;
	}
	
	//事件列表
	@Override
	public PageInfo<EventPageUM> eventPage(EventPagePA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
		if (pa.getEventStatus()!=null&&pa.getEventStatus().toString().length()>0) {
			if (pa.getEventStatus()==1) {
				pa.setEventStatus(0);
			}else {
			pa.setEventStatus(pa.getEventStatus()-1);
			}
		}
		Page<EventPageUM> page = alarmMapper.eventPage(library, pa);
		List<EventPageUM> list = page;
		return new PageInfo<>(page.getTotal(),list,pa.getPageSize(),pa.getPageIndex());
	}
	
	//关闭事件
	@Override
	public String closeEvent(EventClosePA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Integer eventId = pa.getEventId();
		Integer check = alarmMapper.checkEvent(library, eventId);
		if(check != 1){//判断该事件是否存在或者被关闭
			throw exceptionManager.create("EVE0001");
		} else {
			 String eventViewJsonStr = alarmMapper.getEventView(library, eventId);// 获取数据库存储的json
			 EventViewJsonRootBean eventRootBean = new EventViewJsonRootBean();
			 Gson gson = new Gson();//准备解析工具
			 eventRootBean = gson.fromJson(eventViewJsonStr, EventViewJsonRootBean.class);
			 List<String> paView = new ArrayList<>(); // 准备放param传过来资源的数组
			 if (pa.getEventView()!=null) {
				 String[] list = pa.getEventView().split(",");// 传过来的资源字符串分割把内容写进资源数组
				 for (int i = 0;i < list.length;i++){
					 paView.add(list[i]);
				 }
				 List<Img> clostImgList = new ArrayList<>();
				 for (int i = 0;i < paView.size();i++){ // 往关闭事件资源实体类中添加关闭事件资源数组的内容
					 Img closedImg = new Img();
					 closedImg.setId("" + i + "");
					 closedImg.setUrl(paView.get(i));
					 clostImgList.add(closedImg);
				 }
				 ClosedView closedView = eventRootBean.getClosedView();
				 closedView.setImg(clostImgList);
				 eventRootBean.setClosedView(closedView);
			}
			 pa.setEventView(JSON.toJSONString(eventRootBean));
			 alarmMapper.closeEvent(library, pa);
		}
		return "关闭成功";
	}

	//添加事件
	@Override
	public Integer EventNewPA(EventNewPA eventNewPA) throws HKException {
		Map<String, Object> library = super.getTableParams(eventNewPA.getCommunityId());
		eventNewPA.setMap(library);
		Gson gson=new Gson();
		EventViewJsonRootBean viewBean=gson.fromJson("{\"closedView\": {\"img\": [], \"mp3\": [], \"video\": []}, \"pendingView\": {\"img\": [], \"mp3\": [], \"video\": []}, \"processedView\": {\"img\": [], \"mp3\": [], \"video\": []}}", EventViewJsonRootBean.class);
		PendingView pendingView=viewBean.getPendingView();
		
		List<Img> imgInfo=new ArrayList<>();
		if (eventNewPA.getSourceImage()!=null&&eventNewPA.getSourceImage()!="") {
			String[] info=eventNewPA.getSourceImage().split(",");
			for (int i = 0; i < info.length; i++) {
				Img img=new Img();
				img.setId((i+1)+"");
				img.setUrl(info[i]);
				imgInfo.add(img);
			}
			pendingView.setImg(imgInfo);
		}
		viewBean.setPendingView(pendingView);
		eventNewPA.setEventView(gson.toJson(viewBean));
		
		Message msg = new Message();
		Map<String, Object> alarmMsg=new HashMap<>();
		alarmMsg.put("alarmMessage", eventNewPA.getEventTitle());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		alarmMsg.put("alarmTime",df.format(new Date()));
		alarmMsg.put("alarmAddress",eventNewPA.getAddressStr());
		alarmMsg.put("alarmHandleUser", alarmMapper.getAlarmPushHandleUserInfo(library,Integer.parseInt(eventNewPA.getHandleUserId())));
		msg.setCommunityId(eventNewPA.getCommunityId()+"");
		msg.setSource("0");
		msg.setCategory("C01");
		msg.setCode("alarmMsg");
		msg.setData(alarmMsg);
		msgServiceImpl.sendMessage(msg);
		
		String sourceId=eventNewPA.getSourceId();
		if (sourceId!=null) {
			String[] devInfoStrings=eventNewPA.getSourceId().split(",");
			for (int i = 0; i < devInfoStrings.length; i++) {
				eventNewPA.setSourceId(devInfoStrings[i]);
				alarmMapper.EventNewPA(eventNewPA);
			}
		}else {
			alarmMapper.EventNewPA(eventNewPA);
		}
		
		Message strealtimeMessage = new Message();
		Map<String, Object> strealtimeMap=new HashMap<>();
		strealtimeMap.put("alarmMessage", eventNewPA.getEventTitle());
		strealtimeMap.put("alarmTime",df.format(new Date()));
		strealtimeMap.put("address",eventNewPA.getAddressStr());
		strealtimeMap.put("alarmId",eventNewPA.getEventId());
		strealtimeMap.put("alarmImg",eventNewPA.getSourceImage());
//		strealtimeMap.put("alarmSource",alarmInsertPA.getAlarmSource());
//		strealtimeMap.put("alarmType",alarmInsertPA.getAlarmType());
		strealtimeMap.put("lat",eventNewPA.getAddressLat());
		strealtimeMap.put("lng",eventNewPA.getAddressLong());
		strealtimeMap.put("lableId",2);
		strealtimeMap.put("alarmFlag",7);
		Map<String, Object> getInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(library, eventNewPA.getEventId(), 2);
		strealtimeMap.put("alarmState", getInfoMap.get("报警状态"));
		strealtimeMap.put("alarmNo", getInfoMap.get("报警编号"));
		strealtimeMap.put("alarmHandler", getInfoMap.get("处理人"));
		strealtimeMap.put("alarmLevel",getInfoMap.get("报警等级"));
//		if (alarmInsertPA.getAlarmType()==3) {
//			strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmNatureOfVehicle());
//		}else {
//			strealtimeMap.put("alarmSourceId",alarmInsertPA.getAlarmSourceId());
//		}
		strealtimeMessage.setCommunityId(eventNewPA.getCommunityId()+"");
		strealtimeMessage.setSource("0");
		strealtimeMessage.setCategory("C01");
		strealtimeMessage.setCode("strealtimeAlarmMsg");
		strealtimeMessage.setData(strealtimeMap);
		msgServiceImpl.sendMessage(strealtimeMessage);
		
		alarmMapper.updateEventNo(library, eventNewPA.getEventId());
		return eventNewPA.getEventId();
	}
	
	//工单列表
	@Override
	public PageInfo<WorderListUM> worderPage(WorderListPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		PageHelper.startPage(pa.getPageIndex(), pa.getPageSize());
		Page<WorderListUM> page = alarmMapper.worderPage(library, pa);
		List<WorderListUM> list = page;
		return new PageInfo<>(page.getTotal(), list, pa.getPageSize(), pa.getPageIndex());
	}

	//后台首页,报警总数
	@Override
	public Integer alarmHTCount(CommunityParams pa) {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Integer alarmHTCount = alarmMapper.getAlarmHTCount(library);
		return alarmHTCount;
	}
	
	//工单总况
	@Override
	public Map<String, Object> worderGk(CommunityParams coummunityParams) throws HKException {
		Map<String, Object> library = super.getTableParams(coummunityParams.getCommunityId());

		List<WorderGkProRateUM> worderGkProRateUMList = alarmMapper.getWorderProTrend(library);//获取工单处理及时率趋势
		
		List<WorderGkOrderCountUM> worderGkOrderCountUM = alarmMapper.getWorderdata(library);//获取本月工单数量
 
		List<WorderGkStatusUM> worderGkStatusUM = alarmMapper.getWorderState(library);//获取本月工单处理状态
 
		List<WorderGkSourceUM> worderGkSourceUM = alarmMapper.getWorderSource(library);//获取本月工单来源分布
	 
		List<WorderGkUM> worderGkUM = alarmMapper.getWorderCount(library);//获取本月工单数统计
		 		
		Map<String, Object> allInfoMap=new HashMap<>();
		
		allInfoMap.put("promsg", worderGkProRateUMList);//及时处理率趋势
		allInfoMap.put("datamsg", worderGkOrderCountUM);//本月工单数量
		allInfoMap.put("statmsg", worderGkStatusUM);//本月工单处理状态
		allInfoMap.put("countmsg", worderGkUM);//本月工单数统计
		allInfoMap.put("sourcemsg", worderGkSourceUM);//本月工单来源分布
		return allInfoMap;
	}

	//后台首页
	@Override
	public PendingSituationUM backWorder(CommunityParams coummunityParams) throws HKException {
		Map<String, Object> library = super.getTableParams(coummunityParams.getCommunityId());
		PendingSituationUM worderHT = alarmMapper.getWorder(library);
	
		return worderHT;
	}

	//后台首页,工单报警总数
	@Override
	public Integer WorderHTCount(CommunityParams coummunityParams) throws HKException {
		Map<String, Object> library = super.getTableParams(coummunityParams.getCommunityId());
		Integer worderHTCount = alarmMapper.getHTWorderCount(library);
		return worderHTCount;
	}

	//后台首页,工单情况
	@Override
	public Map<String, Object> WorderHTStatus(CommunityParams coummunityParams) throws HKException {
		Map<String, Object> library = super.getTableParams(coummunityParams.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("worderBySource", alarmMapper.getHTWorderSourceCount(library));
		infoMap.put("worderByStatus", alarmMapper.getHTWorderBystatus(library));
		infoMap.put("worderByWeek", alarmMapper.getHTWorderByWeek(library));
		return infoMap;
	}

	//综合作业,工单列表,及时处理率
	@Override
	public Map<String, Object> WorderZYList(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		List<WorderZYOrderListUM> worderZYOrderListUMs = alarmMapper.getZYWorderList(library);
		infoMap.put("worderZYList", worderZYOrderListUMs);
		return infoMap;
	}

	//综合作业,设备感知,按时间段统计
	@Override
	public List<Integer []> AlarmZYList(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		List<List<AlarmZYGetTimeUM>> infoMap = new ArrayList();
		infoMap.add(alarmMapper.getZYAlarmCountByMonday(library));//周一,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountByTuesday(library));//周二,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountByWednesday(library));//周三,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountByThursday(library));//周四,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountByFriday(library));//周五,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountBySaturday(library));//周六,24小时时间段的报警统计
		infoMap.add(alarmMapper.getZYAlarmCountBySunday(library));//周日,24小时时间段的报警统计
		
		List<Integer []> test=new ArrayList<>();
		for (int i = 0; i < infoMap.size(); i++) {
			for (int j = 0; j < 24; j++) {
				Integer [] alarmMap = new Integer[3];
				alarmMap[0]=i;
				alarmMap[1]=Integer.parseInt(infoMap.get(i).get(j).getAlarmMonday().toString());
				alarmMap[2]=Integer.parseInt(infoMap.get(i).get(j).getMondayCount().toString());
				test.add(alarmMap);
			}
			
		}
		
		return test;
	}

	//综合作业,累计报警,今日报警,未处理报警,未处理工单,超时工单
	@Override
	public Map<String, Object> AlarmZYAllCount(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("alarmAllCount", alarmMapper.getZYAllCount(library));//获取到查询结果
		return infoMap;
	}
	
	@Override
	public Map<String, Object> AlarmWorkMainInfo(AlarmWorkPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		String queryType=pa.getQueryType();
		List<Map<String, Object>> getFireAlarmInfoForFont=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getElectricityAlarmInfoForFont=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getGasAlarmInfoForFont=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getCarePersonAlarmInfoForFontWork=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getBlackListPersonAlarmInfoForFontWork=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getCarAlarmInfoForFontWork=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getOtherAlarmInfoForFontWork=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> getHeatPointInfo=new ArrayList<Map<String,Object>>();
		Map<String, Object> returnInfo=new HashMap<String, Object>();
		if (queryType.contains("1")) {
			List<Map<String, Object>> info=alarmMapper.getDevAlarmInfoForFont(library, "1");
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				String fixMessage=alarmMapper.fixDevAlarmMessage(library, Integer.parseInt(map.get("id").toString()));
//				map.put("alarm_message", fixMessage);
				getFireAlarmInfoForFont.add(map);
			}
		}
		if (queryType.contains("2")) {
			List<Map<String, Object>> info=alarmMapper.getDevAlarmInfoForFont(library, "4");
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				getElectricityAlarmInfoForFont.add(map);
			}
		}
		if (queryType.contains("3")) {
			List<Map<String, Object>> info=alarmMapper.getDevAlarmInfoForFont(library, "2");
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				getGasAlarmInfoForFont.add(map);
			}
		}
		if (queryType.contains("4")) {
			List<Map<String, Object>> info=alarmMapper.getPersonAlarmInfoForFontWork(library, "F01");
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				getCarePersonAlarmInfoForFontWork.add(map);
			}
		}
		if (queryType.contains("5")) {
			List<Map<String, Object>> info=alarmMapper.getPersonAlarmInfoForFontWork(library, "F04");
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				getBlackListPersonAlarmInfoForFontWork.add(map);
			}
		}
		if (queryType.contains("6")) {
			List<Map<String, Object>> info=alarmMapper.getCarAlarmInfoForFontWork(library);
			for (Map<String, Object> map : info) {
				map.put("alarmTime", map.get("alarmTime").toString());
				getCarAlarmInfoForFontWork.add(map);
			}
		}
		if (queryType.contains("7")) {
			List<Map<String, Object>> info=alarmMapper.getOtherAlarmInfoForFontWork(library);
			for (Map<String, Object> map : info) {
				map.put("reportTime", map.get("reportTime").toString());
				getOtherAlarmInfoForFontWork.add(map);
			}
		}
		returnInfo.put("fireAlarmInfo", getFireAlarmInfoForFont);
		returnInfo.put("electricityAlarmInfo", getElectricityAlarmInfoForFont);
		returnInfo.put("gasAlarmInfo", getGasAlarmInfoForFont);
		returnInfo.put("carePersonAlarmInfo", getCarePersonAlarmInfoForFontWork);
		returnInfo.put("blackListPersonAlarmInfo", getBlackListPersonAlarmInfoForFontWork);
		returnInfo.put("carAlarmInfo", getCarAlarmInfoForFontWork);
		returnInfo.put("otherAlarmInfo", getOtherAlarmInfoForFontWork);
		returnInfo.put("heatPointInfo", getHeatPointInfo);
		return returnInfo;
	}
	
	@Override
	public List<AlarmUserLngLatUM> UserLngLat(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		List<AlarmUserLngLatUM> userLngLat = alarmMapper.UserLngLat(library);
		return userLngLat;
	}

	@Override
	public List<Map<String, Object>> workMainInfo(WorkPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		List<Map<String, Object>> getWorderInfoForFontWork=alarmMapper.getWorderInfoForFontWork(library,pa.getQueryType());
		for (int i = 0; i < getWorderInfoForFontWork.size(); i++) {
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap=getWorderInfoForFontWork.get(i);
			infoMap.put("createTime", infoMap.get("createTime").toString());
			infoMap.remove("alarmFlag");
			getWorderInfoForFontWork.set(i, infoMap);
		}
		return getWorderInfoForFontWork;
	}

	//态势分析,实时报警
	@Override
	public Map<String, Object> AlarmSTRealTimeAlerts(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infomap = new HashMap<>();
		infomap.put("alarmRealTimeAlerts", alarmMapper.getSTRealTimeAlerts(library));
		return infomap;
	}

	@Override
	public List<Map<String, Object>> alarmMainInfo(WorkPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		List<Map<String, Object>> getAlarmInfoForFontWork=alarmMapper.getAlarmWorkMainInfo(library,pa.getQueryType());
		for (int i = 0; i < getAlarmInfoForFontWork.size(); i++) {
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap=getAlarmInfoForFontWork.get(i);
			infoMap.put("alarmTime", infoMap.get("alarmTime").toString());
			String fixMessage=alarmMapper.fixDevAlarmMessage(library, Integer.parseInt(infoMap.get("id").toString()));
			infoMap.put("alarm_message", fixMessage);
			getAlarmInfoForFontWork.set(i, infoMap);
		}
		return getAlarmInfoForFontWork;
	}

	//态势分析,总报警,一级报警,处理及时率
	@Override
	public Map<String, Object> AlarmSTAlarmAllCount(CommunityParams pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Map<String, Object> infomap = new HashMap<>();
		infomap.put("alarmSTallcount", alarmMapper.getSTAlarmAllCount(library));
		return infomap;
	}

	@Override
	public Map<String, Object> alarmMainWorkDetail(AlarmDetailPA pa) throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Integer checkMainWorkType=alarmMapper.getAlarmMainWork(library, pa.getAlarmID(),pa.getLid());
		Map<String, Object> mapInfoMap=new HashMap<String, Object>();
		switch (checkMainWorkType) {
		case 1:
			mapInfoMap=alarmMapper.getMainWorkAlarmDevDetail(library, pa.getAlarmID());
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 2:
			mapInfoMap=alarmMapper.getMainWorkAlarmDevDetail(library, pa.getAlarmID());		
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 3:
			mapInfoMap=alarmMapper.getMainWorkAlarmDevDetail(library, pa.getAlarmID());
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 4:
			mapInfoMap=alarmMapper.getMainWorkAlarmPersonDetail(library, pa.getAlarmID());
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 5:
			mapInfoMap=alarmMapper.getMainWorkAlarmPersonDetail(library, pa.getAlarmID());
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 6:
			mapInfoMap=alarmMapper.getMainWorkAlarmCarDetail(library, pa.getAlarmID());
			mapInfoMap.remove("id");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		case 7:
			mapInfoMap=alarmMapper.getMainWorkAlarmOtherDetail(library, pa.getAlarmID(),pa.getLid());
			mapInfoMap.remove("lableType");
			mapInfoMap.remove("id");
			mapInfoMap.remove("height");
			mapInfoMap.remove("lng");
			mapInfoMap.remove("lat");
			break;
		}
		
		List<AlarmMainWorkListUM> backInfo=new ArrayList<AlarmMainWorkListUM>();
		AlarmMainWorkListCotentUM info=new AlarmMainWorkListCotentUM();
		for(String key : mapInfoMap.keySet()){
			switch (key) {
			case "报警时间":
				info.setAlarmTime(mapInfoMap.get(key).toString());
				break;
			case "报警状态":
				info.setState(mapInfoMap.get(key).toString());
				break;
			case "报警视频":
				info.setVideo(mapInfoMap.get(key).toString());
				break;
			case "报警图片":
				info.setImg(mapInfoMap.get(key).toString());
				break;
			case "报警编号":
				info.setAlarmNo(mapInfoMap.get(key).toString());
				break;
			default:
				AlarmMainWorkListUM backDemo=new AlarmMainWorkListUM();
				String name=key;
				String value=mapInfoMap.get(key).toString();
				backDemo.setName(name);
				backDemo.setValue(value);
				backInfo.add(backDemo);
				break;
			}
		}
		info.setContent(backInfo);
		
		Map<String, Object> map = new HashMap<String, Object>(0); 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(info); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    map.put(name, propertyUtilsBean.getNestedProperty(info, name)); 
                } 
            } 
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		List<String> listKey = new ArrayList<String>();
		while (it.hasNext()) {
		String str = it.next();
		if(map.get(str)==null || "".equals(map.get(str))){
		listKey.add(str) ;
		 }
		}
		for (String key : listKey) {
			map.remove(key);
		}
		return map;
	}

	@Override
	public Map<String, Object> workMainWorkDetail(AlarmDetailPA pa)
			throws HKException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		Map<String, Object> alarmWorkMainWorkDetail = alarmMapper.getAlarmWorkMainWorkDetail(library, pa.getWid());
		
		List<AlarmMainWorkListWorkUM> backInfo=new ArrayList<AlarmMainWorkListWorkUM>();
		AlarmMainWorkListCotentWorkUM info=new AlarmMainWorkListCotentWorkUM();
		for(String key : alarmWorkMainWorkDetail.keySet()){
			switch (key) {
			case "工单编号":
				info.setWorkNo(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "工单状态":
				info.setState(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "创建时间":
				info.setCreateTime(alarmWorkMainWorkDetail.get(key).toString());
				break;
//			case "工单标题":
//				info.setWorkTitle(alarmWorkMainWorkDetail.get(key).toString());
//				break;
			case "紧急程度":
				info.setOrderLevel(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "要求处理时间":
				info.setTimeConfine(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "处理人":
				info.setHandlerInfo(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "相关摄像头":
				info.setVideo(alarmWorkMainWorkDetail.get(key).toString());
				break;
			case "相关图片":
				info.setImg(alarmWorkMainWorkDetail.get(key).toString());
				break;
			default:
				AlarmMainWorkListWorkUM backDemo=new AlarmMainWorkListWorkUM();
				String name=key;
				String value=alarmWorkMainWorkDetail.get(key).toString();
				backDemo.setName(name);
				backDemo.setValue(value);
				backInfo.add(backDemo);
				break;
			}
		}
		info.setContent(backInfo);
		Map<String, Object> map = new HashMap<String, Object>(0); 
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(info); 
        for (int i = 0; i < descriptors.length; i++) { 
            String name = descriptors[i].getName(); 
            if (!"class".equals(name)) { 
                map.put(name, propertyUtilsBean.getNestedProperty(info, name)); 
            } 
        } 
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		List<String> listKey = new ArrayList<String>();
		while (it.hasNext()) {
		String str = it.next();
		if(map.get(str)==null || "".equals(map.get(str))){
		listKey.add(str) ;
		 }
		}
		for (String key : listKey) {
			map.remove(key);
		}
		return map;
	}

	@Override
	public List<Map<String, String>> getHeatPointInfo(WorkPA pa) throws HKException {
		Map<String, Object> library = super.getTableParams(pa.getCommunityId());
		List<Map<String, String>> heatPointInfo = alarmMapper.getHeatPointInfo(library);
		return heatPointInfo;
	}
	
}
