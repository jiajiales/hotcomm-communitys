package com.hotcomm.community.data.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPA;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPagePA;
import com.hotcomm.community.common.bean.pa.device.DevicePagePA;
import com.hotcomm.community.common.bean.pa.person.PersonPagePA;
import com.hotcomm.community.common.bean.pa.process.event.EventNewPA;
import com.hotcomm.community.common.bean.ui.car.CarPassRecordsUM;
import com.hotcomm.community.common.bean.ui.device.DevicePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.house.FloorsUM;
import com.hotcomm.community.common.bean.ui.house.RoomUM;
import com.hotcomm.community.common.bean.ui.system.UserListUM;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.common.service.house.FloorsService;
import com.hotcomm.community.common.service.house.RoomService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.data.basTask.basTask;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

@Component
public class ProcessServiceTest extends basTask {

//	@Autowired
//	private ProcessServiceImpl processServiceImpl;

	@Autowired
	PersonService personService;

	@Autowired
	CarPassRecordsService carService;

	@Autowired
	RoomService roomService;

	@Autowired
	BuildingService buildingService;

	@Autowired
	DeviceService deviceService;

	@Autowired
	CommunityService communityService;

	@Autowired
	FloorsService floorService;

	String[] subjectForPerson = { "荀浩二", "童孜娴", "仇瑶恒", "单淼", "聂浩然", "弘婕", "汤浩丹", "彭翻嘉", "潘灵欣", "苗飞飞", "唐玉", "耿通",
			"湛森" };
	String[] timeConfine = { "60", "480", "1440", "4320", "7200" };
	String[] predicateForPerson = { "违规出入", "未归",
			"设备未连接网络",
			"车辆违停", "欠缴物业费", "发生火灾"
			,"设备主板故障","设备电量空关机"
			, "门未关", "漏电", "漏水" };

	String[] subjectForSubstance = { "7301", "7309", "1021", "9055", "6015", "8016", "4018" };
	String[] car = { "赣C73091", "粤B12541", "黑A98451", "粤B02576", "粤B61430", "粤B15823", "粤B02576" };

	String[] module = { "烟感", "可燃气", "消防栓水压", "剩余电流", "摄像头", "门禁", "电表", "水表", "可燃气表" };
	String[] address = { "楼梯口", "电梯口", "走廊旁", "消防门" };
	String[] carAddress = { "地下停车场", "篮球场", "羽毛球场", "游泳池入口", "绿道", "单元口" };

	@Scheduled(cron = processCron)
	public void addRegCar() throws HKException {
		
		CarPassRecordsPagePA carPa=new CarPassRecordsPagePA();
		carPa.setPageSize(9999999);
		carPa.setPageIndex(1);
		carPa.setCommunityId(1);
		PageInfo<CarPassRecordsUM> carListInfo = carService.page(carPa);
		
		PersonPagePA personListPa=new PersonPagePA();
		personListPa.setPageSize(9999999);
		personListPa.setPageIndex(1);
		personListPa.setCommunityId(1);
		PageInfo<PersonDM> personList = personService.getPersonPageList(personListPa);
		
		List<UserListUM> eventReporter = communityService.queryUserListByCid(1, "6");
		
		List<UserListUM> worderUser = communityService.queryUserListByCid(1, "7");
		
		List<Map<String, Object>> allList = roomService.getAllList(1);
		
		for (int i = 0; i < 1; i++) {
			
			EventNewPA eventNewPA = new EventNewPA();
			PersonPagePA personPagePA=new PersonPagePA();
			personPagePA.setCommunityId(1);
			personPagePA.setPageIndex(1);
			personPagePA.setPageSize(999999);
			
			String predicate = predicateForPerson[(int)(Math.random()*predicateForPerson.length)];
			String subject = "";
			String accusative = "";
			switch (predicate) {
				case "违规出入": 
					String carnum = carListInfo.getData().get((int)(Math.random()*carListInfo.getData().size())-1).getNum();
					subject ="车辆"+carnum; 
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
//					eventNewPA.setSourceId(carnum);
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "未归":
					Integer weiguiP = personList.getData().get((int)(Math.random()*personList.getData().size())-1).getPId();
					String weiguipname = personService.getPersonInfo(1, weiguiP).getName();
					Integer roomIdweigui=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM detailsDataRoomweigui = roomService.detailsData(roomIdweigui, 1);
					subject=detailsDataRoomweigui.getBuildingName()+detailsDataRoomweigui.getUnitName()+detailsDataRoomweigui.getFloorName()+detailsDataRoomweigui.getRoomName()+"室"+weiguipname;
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
					eventNewPA.setSourceId(weiguiP.toString());
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "设备未连接网络":
						String[] moduleids = {"1","2","3","4"};
						String moduleid=moduleids[(int)(Math.random()*moduleids.length)];
						DevicePagePA devicePa=new DevicePagePA();
						devicePa.setCommunityId(1);
						devicePa.setPageSize(9999999);
						devicePa.setPageIndex(1);
						devicePa.setModuleId(Integer.parseInt(moduleid));
						PageInfo<DevicePageUM> devicePage = deviceService.devicePage(devicePa);
							DevicePageUM devicePageUM = devicePage.getData().get((int)(Math.random()*devicePage.getData().size()));
							SelectDeviceOnMacUM selectDeviceOnMac = deviceService.selectDeviceOnMac(devicePageUM.getMac(), 1, Integer.parseInt(moduleid));
							FloorsUM floorInfo = floorService.detailsData(selectDeviceOnMac.getFloorNum(), 1);
							subject =floorInfo.getBuildingName()+floorInfo.getUnitName()+floorInfo.getFloorName()+selectDeviceOnMac.getMac();
							eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
							eventNewPA.setSourceId(devicePageUM.getId().toString());
							eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
							eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "设备主板故障": 
					String[] moduleids1 = {"1","2","3","4"};
					String moduleid1=moduleids1[(int)(Math.random()*moduleids1.length)];
					DevicePagePA devicePa1=new DevicePagePA();
					devicePa1.setCommunityId(1);
					devicePa1.setPageSize(9999999);
					devicePa1.setPageIndex(1);
					devicePa1.setModuleId(Integer.parseInt(moduleid1));
					PageInfo<DevicePageUM> devicePage1 = deviceService.devicePage(devicePa1);
						DevicePageUM devicePageUM1 = devicePage1.getData().get((int)(Math.random()*devicePage1.getData().size()));
						SelectDeviceOnMacUM selectDeviceOnMac1 = deviceService.selectDeviceOnMac(devicePageUM1.getMac(), 1, Integer.parseInt(moduleid1));
						FloorsUM floorInfo1 = floorService.detailsData(selectDeviceOnMac1.getFloorNum(), 1);
						subject =floorInfo1.getBuildingName()+floorInfo1.getUnitName()+floorInfo1.getFloorName()+selectDeviceOnMac1.getMac();
						eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
						eventNewPA.setSourceId(devicePageUM1.getId().toString());
						eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
						eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "设备电量空关机":
					String[] moduleids11 = {"1","2","3","4"};
					String moduleid11=moduleids11[(int)(Math.random()*moduleids11.length)];
					DevicePagePA devicePa11=new DevicePagePA();
					devicePa11.setCommunityId(1);
					devicePa11.setPageSize(9999999);
					devicePa11.setPageIndex(1);
					devicePa11.setModuleId(Integer.parseInt(moduleid11));
					PageInfo<DevicePageUM> devicePage11 = deviceService.devicePage(devicePa11);
						DevicePageUM devicePageUM11 = devicePage11.getData().get((int)(Math.random()*devicePage11.getData().size()));
						SelectDeviceOnMacUM selectDeviceOnMac11 = deviceService.selectDeviceOnMac(devicePageUM11.getMac(), 1, Integer.parseInt(moduleid11));
						FloorsUM floorInfo11 = floorService.detailsData(selectDeviceOnMac11.getFloorNum(), 1);
						subject =floorInfo11.getBuildingName()+floorInfo11.getUnitName()+floorInfo11.getFloorName()+selectDeviceOnMac11.getMac();
						eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
						eventNewPA.setSourceId(devicePageUM11.getId().toString());
						eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
						eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "门未关": 
					Integer mengweiguanp = personList.getData().get((int)(Math.random()*personList.getData().size())-1).getPId();
					Integer mengweiguanroomid=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM mengweiguanroom = roomService.detailsData(mengweiguanroomid, 1);
					subject=mengweiguanroom.getBuildingName()+mengweiguanroom.getUnitName()+mengweiguanroom.getFloorName()+mengweiguanroom.getRoomName()+"室";
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
					eventNewPA.setSourceId(mengweiguanp.toString());
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "漏电":
					Integer mengweiguanroomid1=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM mengweiguanroom1 = roomService.detailsData(mengweiguanroomid1, 1);
					subject=mengweiguanroom1.getBuildingName()+mengweiguanroom1.getUnitName()+mengweiguanroom1.getFloorName()+address[(int)(Math.random()*address.length)]+"旁"+"配电箱"; 
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "漏水": 
					Integer mengweiguanroomid11=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM mengweiguanroom11 = roomService.detailsData(mengweiguanroomid11, 1);
					subject=mengweiguanroom11.getBuildingName()+mengweiguanroom11.getUnitName()+mengweiguanroom11.getFloorName()+address[(int)(Math.random()*address.length)]+"旁"+"消防栓";
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "车辆违停":
					String carnum1 = carListInfo.getData().get((int)(Math.random()*carListInfo.getData().size())-1).getNum();
					subject =carnum1; 
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
//					eventNewPA.setSourceId(carnum1);
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "欠缴物业费":
					Integer pId221 = personList.getData().get((int)(Math.random()*personList.getData().size())-1).getPId();
					String pName112111 = personService.getPersonInfo(1, pId221).getName();
					Integer roomId211=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM detailsDataRoom112111 = roomService.detailsData(roomId211, 1);
					subject=detailsDataRoom112111.getBuildingName()+detailsDataRoom112111.getUnitName()+detailsDataRoom112111.getFloorName()+detailsDataRoom112111.getRoomName()+"室"+pName112111;
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
					eventNewPA.setSourceId(pId221.toString());
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
				case "发生火灾": 
					Integer mengweiguanp111 = personList.getData().get((int)(Math.random()*personList.getData().size())-1).getPId();
					Integer mengweiguanroomid111=Integer.parseInt(allList.get((int)(Math.random()*allList.size())).get("roomId").toString());
					RoomUM mengweiguanroom111 = roomService.detailsData(mengweiguanroomid111, 1);
					subject=mengweiguanroom111.getBuildingName()+mengweiguanroom111.getUnitName()+mengweiguanroom111.getFloorName()+mengweiguanroom111.getRoomName()+"室";
					eventNewPA.setTimeConfine(timeConfine[(int)(Math.random()*timeConfine.length)]);
					eventNewPA.setSourceId(mengweiguanp111.toString());
					eventNewPA.setUserid(eventReporter.get((int)(Math.random()*eventReporter.size())).getUserId());
					eventNewPA.setHandleUserId(worderUser.get((int)(Math.random()*worderUser.size())).getUserId().toString());
					break;
			}
			eventNewPA.setSource(1);
			eventNewPA.setCommunityId(1);
			eventNewPA.setEventTitle(subject+predicate+accusative);
			eventNewPA.setEventDesc(subject+predicate+accusative);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			eventNewPA.setEventHTime(df.format(new Date()));
			eventNewPA.setAddressLat("22.940563");
			eventNewPA.setAddressLong("113.391744");
			eventNewPA.setAddressStr("新中银 金色华庭");
			eventNewPA.setSourceVideo("3M05329PAKED676,4H05231PAJ554A3");
			eventNewPA.setSourceImage("http://39.108.54.252:8185/hotcomm-community/common/20190107560200_937dc8e7a4534d2486124502f4eea5ca.jpg,http://39.108.54.252:8185/hotcomm-community/common/20190107570200_9d731cdc87fe41529d50328fd823a53a.jpg");
			Integer type = null;
			switch (predicate) {
			case "违规出入": type=6; break;
			case "未归": type=2;break;
			case "设备未连接网络": type=1; break;
			case "车辆违停": type=6;break;
			case "欠缴物业费": type=2;break;
			case "发生火灾":type=3; break;
			case "设备主板故障": type=1; break;
			case "设备电量空关机": type= 1; break;
			case "门未关": type=1; break;
			case "漏电": type=1; break;
			case "漏水": type=1; break;
			}
			eventNewPA.setSourceType(type.toString());
//			processServiceImpl.EventNewPA(eventNewPA);
		}
	}

	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

}
