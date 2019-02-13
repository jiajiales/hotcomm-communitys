package com.hotcomm.community.resful.comm;

import java.util.Arrays;
import java.util.List;

public interface RestfulUrlRecord {

	/** 前缀 */
	String PREFIX = "/wisdom";

	/** 登入 */
	String LOGIN = "/system/login";

	/** 登入 */
	String GET_VALIDATE_CODE = "/system/getValidateCode";

	/** 登出 */
	String LOGOUT = "/system/logout";

	/** 获取字典键值对 */
	String DICTIONARY_GET = "/dictionary/get";

	// ---区域相关---
	/** 获取区域列表 */
	String AREA_LIST = "/area/list";
	String AREA_LIST_FUN = "areaList";

	// ---首页相关---
	/** 获取关键性能指标KPI */
	String HOME_GET_KPI = "/home/getKPI";
	String HOME_GET_KPI_FUN = "homeGetKPI";

	/** 获取待处理情况 */
	String HOME_GET_PENDINGSITUATION = "/home/getPendingSituation";
	String HOME_GET_PENDINGSITUATION_FUN = "homeGetPendingSituation";

	/** 设备概况第三个图 */
	String HOME_GET_SELECTDEVICEALARMCOUNT = "/home/selectDeviceAlarmCount";
	String HOME_GET_SELECTDEVICEALARMCOUNT_FUN = "selectDeviceAlarmCount";

	// ---社区相关---
	/** 新增社区 */
	String COMMUNITY_ADD = "/community/add";
	String COMMUNITY_ADD_FUN = "communityAdd";

	/** 删除社区 */
	String COMMUNITY_DEL = "/community/del";
	String COMMUNITY_DEL_FUN = "communityDel";

	/** 更新社区 */
	String COMMUNITY_UPDATE = "/community/update";
	String COMMUNITY_UPDATE_FUN = "communityUpdate";

	/** 获取社区信息 */
	String COMMUNITY_GET = "/community/get";
	String COMMUNITY_GET_FUN = "communityGet";

	/** 获取社区列表 */
	String COMMUNITY_LIST = "/community/list";
	String COMMUNITY_LIST_FUN = "communityList";

	/** 获取所有的社区列表 */
	String COMMUNITY_LIST_ALL = "/community/listAll";
	String COMMUNITY_LIST_ALL_FUN = "communityListAll";

	/** 获取社区列表(详情) */
	String COMMUNITY_LIST_DETAIL = "/community/listDetail";
	String COMMUNITY_LIST_DETAIL_FUN = "communityListDetail";

	/** 分页查询社区 */
	String COMMUNITY_PAGE = "/community/page";
	String COMMUNITY_PAGE_FUN = "communityPage";

	/** 查询社区相关的角色用户列表 */
	String COMMUNITY_ROLE_USER_LIST = "/community/roleUserList";
	String COMMUNITY_ROLE_USER_LIST_FUN = "communityRoleUserList";

	/** 查询社区相关的用户列表 */
	String COMMUNITY_USER_LIST = "/community/userList";
	String COMMUNITY_USER_LIST_FUN = "communityUserList";

	/** 添加用户社区关联 */
	String COMMUNITY_ADD_USER_COMMUNITY = "/community/addUserCommunity";
	String COMMUNITY_ADD_USER_COMMUNITY_FUN = "communityAddUserCommunity";

	/** 获取用户社区关联 */
	String COMMUNITY_GET_USER_COMMUNITY = "/community/getUserCommunity";
	String COMMUNITY_GET_USER_COMMUNITY_FUN = "communityGetUserCommunity";

	// ---人口大数据展示---
	/** 人脸感知相关信息统计 */
	String PERSON_FACE_REACTION = "/person/face/reaction";
	String PERSON_FACE_REACTION_FUN = "getPersonFaceRec";

	/** 特殊人群人脸感知情况 */
	String PERSON_SPECIAL_REACTION = "/person/special/reaction";
	String PERSON_SPECIAL_REACTION_FUN = "getSpecialPersonRec";

	/** 今日人脸通行情况 */
	String PERSON_FACE_FEEL = "/person/face/feel";
	String PERSON_FACE_FEEL_FUN = "getFaceFellInfoOfDay";

	/** 本周人脸警报情况 */
	String PERSON_ALARM_LASTWEEK = "/person/face/alarm/lastWeek";
	String PERSON_ALARM_LASTWEEK_FUN = "getFaceAlarmLastWeek";

	/** 人脸实时推送 */
	String PERSON_FACE_MESSEND = "/person/face/mesSend";
	String PERSON_FACE_MESSEND_FUN = "faceMesSend";

	/** 最后50条人脸感知记录 */
	String PERSON_RECORD_LAST50 = "/person/face/record/last50";
	String PERSON_RECORD_LAST50_FUN = "getFaceRecordLast50";

	/** 通行基础数据统计 */
	String RECORD_FEEl_REATION = "/person/record/Reaction";
	String RECORD_FEEl_REATION_FUN = "RecordDataStatistics";

	/** 今日通行数据统计 */
	String RECORD_DAY_REATION = "/person/record/day";
	String RECORD_DAY_REATION_FUN = "RecordNumberByDay";

	/** 今日开门方式统计 */
	String RECORD_TYPE_REATION = "/person/record/type";
	String RECORD_TYPE_REATION_FUN = "RecordTotalByType";

	/** 本周人口警报情况 */
	String RECORD_ALARM_LASTWEEK = "/person/record/alarm/lastWeek";
	String RECORD_ALARM_LASTWEEK_FUN = "getRecordAlarmLastWeek";

	/** 最后50条通行记录 */
	String RECORD_FACE_LAST50 = "/person/record/face/last50";
	String RECORD_FACE_LAST50_FUN = "getRecordLast50";

	/** 通行感知推送 */
	String RECORD_MESSAGE_SEND = "/person/record/mesSend";
	String RECORD_MESSAGE_SEND_FUN = "RecordFeelMessageSend";

	// ---人口总况---
	/** 人口总况概况 */
	String POPULATION_SITUATION_DATA = "/population/situation/data";
	String POPULATION_SITUATION_DATA_FUN = "populationSituationData";

	/** 关注人群数 */
	String POPULATION_SITUATION_LABLE = "/population/situation/lable";
	String POPULATION_SITUATION_LABLE_FUN = "lablePopulationSituation";

	/** 人口结构 | 人口户籍情况 */
	String POPULATION_SITUATION_CLASSIFICATION = "/population/situation/Classification";
	String POPULATION_SITUATION_CLASSIFICATION_FUN = "PersonClassification";

	/** 警报情况 */
	String POPULATION_SITUATION_ALARMCONDITION = "/population/situation/AlarmCondition";
	String POPULATION_SITUATION_ALARMCONDITION_FUN = "personAlarmCondition";

	/** 今日通行情况 */
	String POPULATION_SITUATION_PASSINFOOFDAY = "/population/situation/PassInfoOfDay";
	String POPULATION_SITUATION_PASSINFOOFDAY_FUN = "PersonPassInfoOfDay";

	/** 首页人口情况 */
	String HOME_POPULATION_SITUATION = "/population/situation/home";
	String HOME_POPULATION_SITUATION_FUN = "getThePopulation";

	/** 本周通行情况 */
	String POPULATION_SITUATION_PASSINFOOFWEEk = "/population/situation/PassInfoOfWeek";
	String POPULATION_SITUATION_PASSINFOOFWEEk_FUN = "getPassInfoOfWeek";

	/** 本月通行情况 */
	String POPULATION_SITUATION_PASSINFOOFMON = "/population/situation/PassInfoOfMon";
	String POPULATION_SITUATION_PASSINFOOFMON_FUN = "PersonPassInfoOfMon";

	/** 本年通行情况 */
	String POPULATION_SITUATION_PASSINFOOFYEAR = "/population/situation/PassInfoOfYear";
	String POPULATION_SITUATION_PASSINFOOFYEAR_FUN = "getPassInfoOfYear";

	/** 人口趋势 */
	String POPULATION_SITUATION_TREND = "/population/situation/Trend";
	String POPULATION_SITUATION_TREND_FUN = "getPopulationTrend";

	/** 小区人口概况 */
	String POPULATION_SITUATION_RATIO = "/population/situation/Ratio";
	String POPULATION_SITUATION_RATIO_FUN = "getLalePopulationRatio";

	/** 常住人群概况 */
	String POPULATION_SITUATION_FLOORS = "/population/situation/Floors";
	String POPULATION_SITUATION_FLOORS_FUN = "getPersonNumByfloors";

	/** 实时通行情况 */
	String POPULATION_PASS_REALTIME = "/population/pass/realTime";
	String POPULATION_PASS_REALTIME_FUN = "getPassRealTime";

	/** 实时通行消息推送 */
	String POPULATION_PASS_MESSEND = "/population/pass/mesSend";
	String POPULATION_PASS_MESSEND_FUN = "PersonMessageSend";

	/** 本月报警类别情况 */
	String POPULATION_SITUATION_ALARMTYPE = "/population/situation/AlarmType";
	String POPULATION_SITUATION_ALARMTYPE_FUN = "AlarmOfTypeToMonth";

	// ---居民管理---
	/** 居民信息(分页) */
	String POPULATION_MESSAGE_PAGE = "/population/message/page";
	String POPULATION_MESSAGE_PAGE_FUN = "PersonPageList";

	/** 居民信息详情(分页) */
	String POPULATION_MESSAGE_INFO = "/population/message/Info";
	String POPULATION_MESSAGE_INFO_FUN = "PersonInfo";

	/** 删除居民信息 */
	String POPULATION_MESSAGE_DELETE = "/population/message/delete";
	String POPULATION_MESSAGE_DELETE_FUN = "deletePersonMessage";

	/** 添加居民信息 */
	String POPULATION_MESSAGE_ADD = "/population/message/add";
	String POPULATION_MESSAGE_ADD_FUN = "addPersonMessage";

	/** 修改居民信息 */
	String POPULATION_MESSAGE_UPDATE = "/population/message/update";
	String POPULATION_MESSAGE_UPDATE_FUN = "updatePersonMessage";

	// ---陌生人相关---
	/** 陌生人信息(分页) */
	String POPULATION_STRANGER_PAGE = "/population/stranger/page";
	String POPULATION_STRANGER_PAGE_FUN = "PersonStrangerPage";

	/** 陌生人信息 */
	String POPULATION_STRANGER_INFO = "/population/stranger/info";
	String POPULATION_STRANGER_INFO_FUN = "StrangerInfo";

	/** 删除陌生人信息 */
	String POPULATION_STRANGER_DEL = "/population/stranger/del";
	String POPULATION_STRANGER_DEL_FUN = "delPersonStranger";

	/** 陌生人转换 */
	String POPULATION_STRANGER_TRANSITION = "/population/stranger/transition";
	String POPULATION_STRANGER_TRANSITION_FUN = "SetStrangerToPerson";

	// ---关联房屋---
	/** 关联房屋信息 */
	String POPULATION_ROOM_RE = "/population/room/re";
	String POPULATION_ROOM_RE_FUN = "addPersonReRoom";

	/** 人房关联分页 */
	String POPULATION_ROOM_PAGE = "/population/room/page";
	String POPULATION_ROOM_PAGE_FUN = "PersonReRoomPage";

	// ---人口标签相关---
	/** 获取人口标签列表 */
	String POPULATION_LABLE_PAGE = "/population/lable/page";
	String POPULATION_LABLE_PAGE_FUN = "populationLablePage";

	/** 人口标签详情 */
	String POPULATION_LABLE_INFO = "/population/lable/Info";
	String POPULATION_LABLE_INFO_FUN = "populationLableInfo";

	/** 删除标签 */
	String POPULATION_LABLE_DELETE = "/population/lable/del";
	String POPULATION_LABLE_DELETE_FUN = "delPopulationLable";

	/** 修改标签 */
	String POPULATION_LABLE_UPDATA = "/population/lable/update";
	String POPULATION_LABLE_UPDATA_FUN = "updatePopulationLable";

	/** 添加标签 */
	String POPULATION_LABLE_INSERT = "/population/lable/insert";
	String POPULATION_LABLE_INSERT_FUN = "insertPopulationLable";

	/** 查询所有标签(下拉框) */
	String POPULATION_LABLE_SELECT = "/population/lable/select";
	String POPULATION_LABLE_SELECT_FUN = "selectPopulationLable";

	// ---人口警报相关--
	/** 人口警报列表分页 */
	String POPULATION_ALARM_PAGE = "/population/alarm/page";
	String POPULATION_ALARM_PAGE_FUN = "populationAlarmPage";

	/** 人口警报详情 */
	String POPULATION_ALARM_INFO = "/population/alarm/Info";
	String POPULATION_ALARM_INFO_FUN = "populationAlarmInfo";

	/** 删除人口警报记录 */
	String POPULATION_ALARM_DELETE = "/population/alarm/delete";
	String POPULATION_ALARM_DELETE_FUN = "deletePopulationAlarm";

	/** 添加人口警报记录 */
	String POPULATION_ALARM_ADD = "/population/alarm/add";
	String POPULATION_ALARM_ADD_FUN = "addPopulationAlarm";

	// ---人口通行相关--
	/** 人口通行列表分页 */
	String POPULATION_RECORD_PAGE = "/population/record/page";
	String POPULATION_RECORD_PAGE_FUN = "populationRecordPage";

	/** 人口通行详情 */
	String POPULATION_RECORD_INFO = "/population/record/Info";
	String POPULATION_RECORD_INFO_FUN = "populationRecordInfo";

	/** 删除人口通行记录 */
	String POPULATION_RECORD_DELETE = "/population/record/delete";
	String POPULATION_RECORD_DELETE_FUN = "deletePopulationRecord";

	/** 添加人口通行记录 */
	String POPULATION_RECORD_ADD = "/population/record/add";
	String POPULATION_RECORD_ADD_FUN = "addRecord";

	/** 根据pid/faceNo 获取最近1个月通行被记录的设备mac 与最新时间 */
	String POPULATION_RECORD_DEVICEMAC = "/population/record/getDeviceMac";
	String POPULATION_RECORD_DEVICEMAC_FUN = "getDeviceMacByRecord";

	// ---人口布控相关--
	/** 人口布控列表分页 */
	String POPULATION_HOLE_PAGE = "/population/hole/page";
	String POPULATION_HOLE_PAGE_FUN = "populationHolePage";

	/** 启动/关闭人口布控 */
	String POPULATION_HOLE_ONOFF = "/population/hole/OnOff";
	String POPULATION_HOLE_ONOFF_FUN = "populationHoleOnOff";

	/** 删除人口布控 */
	String POPULATION_HOLE_DELETE = "/population/hole/delete";
	String POPULATION_HOLE_DELETE_FUN = "deletePopulationHole";

	/** 人口布控详情 */
	String POPULATION_HOLE_INFO = "/population/hole/Info";
	String POPULATION_HOLE_INFO_FUN = "populationHoleInfo";

	/** 添加人口布控 */
	String POPULATION_HOLE_ADD = "/population/hole/add";
	String POPULATION_HOLE_ADD_FUN = "addPopulationHole";

	/** 修改人口布控 */
	String POPULATION_HOLE_UPDATE = "/population/hole/update";
	String POPULATION_HOLE_UPDATE_FUN = "updatePopulationHole";

	// ---角色管理相关---
	/** 新增角色 */
	String ROLE_ADD = "/role/add";
	String ROLE_ADD_FUN = "roleAdd";

	/** 删除角色 */
	String ROLE_DEL = "/role/del";
	String ROLE_DEL_FUN = "roleDel";

	/** 修改角色 */
	String ROLE_UPDATE = "/role/update";
	String ROLE_UPDATE_FUN = "roleUpdate";

	/** 查询角色 */
	String ROLE_GET = "/role/get";
	String ROLE_GET_FUN = "roleGet";

	/** 查询角色列表 */
	String ROLE_LIST = "/role/list";
	String ROLE_LIST_FUN = "roleList";

	/** 添加角色资源关联 */
	String ROLE_ADD_ROLERESOURCE = "/role/addRoleResources";
	String ROLE_ADD_ROLERESOURCE_FUN = "roleAddRoleResources";

	/** 查询角色资源关联 */
	String ROLE_GET_ROLERESOURCE = "/role/getRoleResources";
	String ROLE_GET_ROLERESOURCE_FUN = "roleGetRoleResources";

	// ---资源相关
	/** 新增资源 */
	String RESOURCE_ADD = "/resource/add";
	String RESOURCE_ADD_FUN = "resourceAdd";

	/** 删除资源 */
	String RESOURCE_DEL = "/resource/del";
	String RESOURCE_DEL_FUN = "resourceDel";

	/** 修改资源 */
	String RESOURCE_UPDATE = "/resource/update";
	String RESOURCE_UPDATE_FUN = "resourceUpdate";

	/** 查询资源 */
	String RESOURCE_GET = "/resource/get";
	String RESOURCE_GET_FUN = "resourceGet";

	/** 查询资源菜单列表 */
	String RESOURCE_LIST = "/resource/list";
	String RESOURCE_LIST_FUN = "resourceList";

	/** 查询资源菜单 */
	String RESOURCE_MENUS = "/resource/menus";
	String RESOURCE_MENUS_FUN = "resourceMenus";

	/** 查询分级资源菜单列表 */
	String RESOURCES_GRADING_GET = "/resource/gradingGet";
	String RESOURCES_GRADING_GET_FUN = "resourceGradingGet";

	// ---房屋模块---
	// ---楼栋管理---
	/** 楼栋列表 */
	String HOUSE_BUILDING_PAGE = "/house/building/pageData";
	String HOUSE_BUILDING_PAGE_FUN = "buildingPageData";

	/** 楼栋详情 */
	String HOUSE_BUILDING_DETAILSDATA = "/house/building/detailsData";
	String HOUSE_BUILDING_DETAILSDATA_FUN = "buildingDetailsData";

	/** 添加楼栋 */
	String HOUSE_BUILDING_ADDDATA = "/house/building/addData";
	String HOUSE_BUILDING_ADDDATA_FUN = "buildingAddData";

	/** 修改楼栋 */
	String HOUSE_BUILDING_UPDATEDATA = "/house/building/updateData";
	String HOUSE_BUILDING_UPDATEDATA_FUN = "buildingUpdateData";

	/** 删除楼栋 */
	String HOUSE_BUILDING_DELDATA = "/house/building/deleteData";
	String HOUSE_BUILDING_DELDATA_FUN = "buildingDeleteData";

	/** 楼栋列表 */
	String HOUSE_BUILDING_LIST = "/house/building/getBuildingList";
	String HOUSE_BUILDING_LIST_FUN = "buildingList";

	// ---单元管理---
	/** 单元分页列表 */
	String HOUSE_UNIT_PAGE = "/house/unit/pageData";
	String HOUSE_UNIT_PAGE_FUN = "unitPageData";

	/** 单元列表 */
	String HOUSE_UNIT_LIST = "/house/unit/getDataList";
	String HOUSE_UNIT_LIST_FUN = "unitGetDataList";

	/** 详情 */
	String HOUSE_UNIT_DETAILS = "/house/unit/detailsData";
	String HOUSE_UNIT_DETAILS_FUN = "unitDetailsData";

	/** 添加单元 */
	String HOUSE_UNIT_ADD = "/house/unit/addData";
	String HOUSE_UNIT_ADD_FUN = "unitAddData";

	/** 修改单元 */
	String HOUSE_UNIT_UPDATE = "/house/unit/updateData";
	String HOUSE_UNIT_UPDATE_FUN = "unitUpdateData";

	/** 删除单元 */
	String HOUSE_UNIT_DEL = "/house/unit/delData";
	String HOUSE_UNIT_DEL_FUN = "unitDelData";

	// ---楼层管理---
	/** 楼层分页列表 */
	String HOUSE_FLOOR_PAGE = "/house/floors/pageData";
	String HOUSE_FLOOR_PAGE_FUN = "floorsPageData";

	/** 楼层详情信息 */
	String HOUSE_FLOOR_DETAILS = "/house/floors/detailsData";
	String HOUSE_FLOOR_DETAILS_FUN = "floorsDetailsData";

	/** 添加楼层信息 */
	String HOUSE_FLOOR_ADD = "/house/floors/addData";
	String HOUSE_FLOOR_ADD_FUN = "floorsAddData";

	/** 修改楼层信息 */
	String HOUSE_FLOOR_UPDATE = "/house/floors/updateData";
	String HOUSE_FLOOR_UPDATE_FUN = "floorsUpdateData";

	/** 删除楼层信息 */
	String HOUSE_FLOOR_DEL = "/house/floors/delData";
	String HOUSE_FLOOR_DEL_FUN = "floorsDelData";

	/** 获取楼层列表 */
	String HOUSE_FLOOR_LIST = "/house/floors/getFloorList";
	String HOUSE_FLOOR_LIST_FUN = "floorsGetFloorList";

	// ---房间管理---
	/** 房间分页列表 */
	String HOUSE_ROOM_PAGE = "/house/room/pageData";
	String HOUSE_ROOM_PAGE_FUN = "roomPageData";

	/** 房间详情信息 */
	String HOUSE_ROOM_DETAILS = "/house/room/detailsData";
	String HOUSE_ROOM_DETAILS_FUN = "roomDetailsData";

	/** 新增房间信息 */
	String HOUSE_ROOM_ADD = "/house/room/addData";
	String HOUSE_ROOM_ADD_FUN = "rommAddData";

	/** 修改房间信息 */
	String HOUSE_ROOM_UPDATE = "/house/room/updateData";
	String HOUSE_ROOM_UPDATE_FUN = "roomUpdateData";

	/** 删除房间信息 */
	String HOUSE_ROOM_DEL = "/house/room/delData";
	String HOUSE_ROOM_DEL_FUN = "roomDelData";

	/** 查询房间列表 */
	String HOUSE_ROOM_LIST = "/house/room/getRoomList";
	String HOUSE_ROOM_LIST_FUN = "roomGetRoomList";

	/** 通过人口ID查询房间列表 */
	String HOUSE_ROOM_LIST_BY_PID = "/house/room/getRoomListByPid";
	String HOUSE_ROOM_LIST_BY_PID_FUN = "roomGetRoomListByPid";

	/** 查询所有房间列表 */
	String HOUSE_ROOM_ALL_LIST = "/house/room/getAllList";
	String HOUSE_ROOM_ALL_LIST_FUN = "roomGetAllList";

	// ---关注场所---
	/** 关注场所分页 */
	String HOUSE_WATCH_PLACE_PAGE = "/house/watchPlace/pageData";
	String HOUSE_WATCH_PLACE_PAGE_FUN = "watchPlacePageData";

	/** 关注场所查看详情 */
	String HOUSE_WATCH_PLACE_DETAILS = "/house/watchPlace/detailsData";
	String HOUSE_WATCH_PLACE_DETAILS_FUN = "watchPlaceDetailsData";

	/** 新增关注场所 */
	String HOUSE_WATCH_PLACE_ADD = "/house/watchPlace/addData";
	String HOUSE_WATCH_PLACE_ADD_FUN = "watchPlaceAddData";

	/** 修改场所 */
	String HOUSE_WATCH_PLACE_UPDATE = "/house/watchPlace/updateData";
	String HOUSE_WATCH_PLACE_UPDATE_FUN = "watchPlaceUpdateData";

	/** 删除场所 */
	String HOUSE_WATCH_PLACE_DEL = "/house/watchPlace/delData";
	String HOUSE_WATCH_PLACE_DEL_FUN = "watchPlaceDelData";

	// ---房屋总况---
	/** 房屋数据统计，楼栋数、总建筑面积、住宅型房间数、出租屋数、隐患场所、服务场所 */
	String HOUSE_STATISTICS = "/house/getHouseStatistics";
	String HOUSE_STATISTICS_FUN = "houseGetHouseStatistics";

	// ---综合态势---
	/** 楼栋信息<标点> */
	String HOUSE_BUILDINGS = "/house/getBuildings";
	String HOUSE_BUILDINGS_FUN = "houseGetBuildings";

	/** 房间统计 */
	String HOUSE_ROOM_STATISTICS = "/house/roomStatistics";
	String HOUSE_ROOM_STATISTICS_FUN = "houseRooomStatistics";

	/** 房屋楼栋数统计 */
	String HOUSE_BUILDING_STATISTICS = "/house/getBuildingStatistics";
	String HOUSE_BUILDING_STATISTICS_FUN = "houseGetBuildingStatistics";

	/** 隐患场所统计、服务场所统计 */
	String HOUSE_PLACE_STATISTICS = "/house/getPlaceStatistics";
	String HOUSE_PLACE_STATISTICS_FUN = "houseGetPlaceStatistics";

	/** 隐患、服务场所情况 */
	String HOUSE_PLACE_NUM = "/house/getPlaceNumData";
	String HOUSE_PLACE_NUM_FUN = "houseGetPlaceNumData";

	/** 出租屋空置率 */
	String HOUSE_ROOM_RENT = "/house/getData";
	String HOUSE_ROOM_RENT_FUN = "houseGetData";

	/** 住宅型房屋租赁和购买数情况 */
	String HOUSE_ROOM_RENT_SALE = "/house/getRentSaleRoom";
	String HOUSE_ROOM_RENT_SALE_FUN = "houseGetRentSaleRoom";

	// ---房屋态势---
	/** 获取房屋列表 */
	String HOUSE_POSTURE_PLACE_LIST = "/house/housePosture/getPlaceList";
	String HOUSE_POSTURE_PLACE_LIST_FUN = "housePostureGetPlaceList";

	// ---关联人口---
	/** 人口关联房屋分页列表 */
	String HOUSE_PERSON_ROOM_PAGE = "/house/personRoom/pageData";
	String HOUSE_PERSON_ROOM_PAGE_FUN = "personRoomPageData";

	/** 新增人房关联信息 */
	String HOUSE_PERSON_ROOM_ADD = "/house/personRoom/addData";
	String HOUSE_PERSON_ROOM_ADD_FUN = "personRoomAddData";

	/** 删除人房关联 */
	String HOUSE_PERSON_ROOM_DEL = "/house/personRoom/delData";
	String HOUSE_PERSON_ROOM_DEL_FUN = "personRoomDelData";

	/** 根据人名查询关联房间信息 */
	String HOUSE_PERSON_ROOM_RELATION = "/house/personRoom/getRelations";
	String HOUSE_PERSON_ROOM_RELATION_FUN = "personRoomGetRelations";

	// ---设备模块相关---
	/** 设备列表分页查询 */
	String DEVICE_DEVICELIST_DEVICEPAGE = "/device/deviceList/devicePage";
	String DEVICE_DEVICELIST_DEVICEPAGE_FUN = "devicePage";

	/** 设备安装 */
	String DEVICE_DEVICELIST_DEVICEINSTALL = "/device/deviceList/deviceInstall";
	String DEVICE_DEVICELIST_DEVICEINSTALL_FUN = "deviceInstall";

	/** 设备修改 */
	String DEVICE_DEVICELIST_UPDATEDEV = "/device/updateDevice/updateDev";
	String DEVICE_DEVICELIST_UPDATEDEV_FUN = "updateDev";

	/** 根据mac查询设备信息 */
	String DEVICE_DEVICEPARTICULARS_SELECTDEVICEONMAC = "/device/deviceParticulars/selectDeviceOnMac";
	String DEVICE_DEVICEPARTICULARS_SELECTDEVICEONMAC_FUN = "selectDeviceOnMac";

	/** 查询设备或系统预警规则 */
	String DEVICE_DEVICEPARTICULARS_SELECTRULEONDEV = "/device/deviceParticulars/selectRuleOnDev";
	String DEVICE_DEVICEPARTICULARS_SELECTRULEONDEV_FUN = "selectRuleOnDev";

	/** 安装验证mac */
	String DEVICE_DEVICELIST_DEVICEMAC = "/device/deviceList/deviceMac";
	String DEVICE_DEVICELIST_DEVICEMAC_FUN = "deviceMac";

	/** 预警方案列表 */
	String DEVICE_DEVICERULE_SELECTRULEPAGE = "/device/deviceRule/selectRulePage";
	String DEVICE_DEVICERULE_SELECTRULEPAGE_FUN = "selectRulePage";

	/** 设备数统计,状态，类型，报警分布 */
	String DEVICE_DEVICEGENERAL_SELECTDEVICESTATE = "/device/deviceGeneral/selectDeviceState";
	String DEVICE_DEVICEGENERAL_SELECTDEVICESTATE_FUN = "selectDeviceState";

	/** 修改预警规则 */
	String DEVICE_DEVICEPARTICULARS_UPDATESYSTEMRULE = "/device/deviceParticulars/updateSystemRule";
	String DEVICE_DEVICEPARTICULARS_UPDATESYSTEMRULE_FUN = "updateSystemRule";

	/** 修改预警规则 */
	String DEVICE_DEVICEPARTICULARS_UPDATERULE = "/device/deviceParticulars/updateRule";
	String DEVICE_DEVICEPARTICULARS_UPDATERULE_FUN = "updateRule";

	/** 设备基础信息统计 */
	String DEVICE_DEVICEFELL_DEVICEMSGCOUNT = "/device/devicefell/deviceMsgCount";
	String DEVICE_DEVICEFELL_DEVICEMSGCOUNT_FUN = "deviceMsgCount";

	/** 今日数据回传感知情况 */
	String DEVICE_DEVICEFELL_DEVICETODAYDATAMSG = "/device/devicefell/deviceTodayDataMsg";
	String DEVICE_DEVICEFELL_DEVICETODAYDATAMSG_FUN = "deviceTodayDataMsg";

	/** 设备在线感知情况 */
	String DEVICE_DEVICEFELL_DEVICESTATEMSG = "/device/devicefell/deviceStateMsg";
	String DEVICE_DEVICEFELL_DEVICESTATEMSG_FUN = "deviceStateMsg";

	/** 本周设备警报情况 */
	String DEVICE_DEVICEFELL_DEVICETHISWEEKALARM = "/device/devicefell/deviceThisWeekAlarm";
	String DEVICE_DEVICEFELL_DEVICETHISWEEKALARM_FUN = "deviceThisWeekAlarm";

	/** 设备感知地图设备展示 */
	String DEVICE_DEVICEFELL_DEVICEMAP = "/device/devicefell/deviceMap";
	String DEVICE_DEVICEFELL_DEVICEMAP_FUN = "deviceMap";

	/** 综合作业查询所有设备各个状态数量 */
	String DEVICE_DEVICEFELL_SELECTDEVICEALLSTATE = "/device/devicefell/selectDeviceAllState";
	String DEVICE_DEVICEFELL_SELECTDEVICEALLSTATE_FUN = "selectDeviceAllState";

	/** 消防设备基础信息统计 */
	String DEVICE_FIREFELL_FIREMSGCOUNT = "/device/firefell/fireMsgCount";
	String DEVICE_FIREFELL_FIREMSGCOUNT_FUN = "fireMsgCount";

	/** 今日各种消防警报情况 */
	String DEVICE_FIREFELL_FIREALARMMSG = "/device/firefell/fireAlarmMsg";
	String DEVICE_FIREFELL_FIREALARMMSG_FUN = "fireAlarmMsg";

	/** 消防各类设备报警感知情况 */
	String DEVICE_FIREFELL_FIREDEVICEALARMMSG = "/device/firefell/fireDeviceAlarmMsg";
	String DEVICE_FIREFELL_FIREDEVICEALARMMSG_FUN = "fireDeviceAlarmMsg";

	/** 本周消防设备警报情况 */
	String DEVICE_FIREFELL_FIRETHISWEEKALARM = "/device/firefell/fireThisWeekAlarm";
	String DEVICE_FIREFELL_FIRETHISWEEKALARM_FUN = "fireThisWeekAlarm";

	/** 设备感知地图设备展示 */
	String DEVICE_FIREFELL_FIREDEVICEMAP = "/device/firefell/fireDeviceMap";
	String DEVICE_FIREFELL_FIREDEVICEMAP_FUN = "fireDeviceMap";

	/** 本月设备所有报警类型 */
	String DEVICE_FIREPOSTURE_FIREALARMTYPE = "/device/fireposture/fireAlarmType";
	String DEVICE_FIREPOSTURE_FIREALARMTYPE_FUN = "fireAlarmType";

	/** 本月各种消防警报数量 */
	String DEVICE_FIREPOSTURE_FIREALARMMSGMONTH = "/device/fireposture/fireAlarmMsgToMonth";
	String DEVICE_FIREPOSTURE_FIREALARMMSGMONTH_FUN = "fireAlarmMsgToMonth";

	/** 本月各类设备消防报警情况 */
	String DEVICE_FIREFELL_FIREDEVICEALARMMSGMONTH = "/device/fireposture/fireDeviceAlarmMsgToMonth";
	String DEVICE_FIREFELL_FIREDEVICEALARMMSGMONTH_FUN = "fireDeviceAlarmMsgToMonth";

	/** 较去年设备概况 */
	String DEVICE_FIREFELL_FIREDEVICEYEARMSG = "/device/fireposture/fireDeviceYearMsg";
	String DEVICE_FIREFELL_FIREDEVICEYEARMSG_FUN = "fireDeviceYearMsg";

	/** 本月消防报警处理情况 */
	String DEVICE_FIREFELL_FIREALARMDISPOSEMSG = "/device/fireposture/fireAlaemDisposeMsg";
	String DEVICE_FIREFELL_FIREALARMDISPOSEMSG_FUN = "fireAlaemDisposeMsg";

	/** 消防月度报警趋势 */
	String DEVICE_FIREFELL_FIREALARMTOMONTH = "/device/fireposture/fireAlarmToMonth";
	String DEVICE_FIREFELL_FIREALARMTOMONTH_FUN = "fireAlarmToMonth";

	/** 消防年度报警趋势 */
	String DEVICE_FIREFELL_FIREALARMTOYEAR = "/device/fireposture/fireAlarmToYear";
	String DEVICE_FIREFELL_FIREALARMTOYEAR_FUN = "fireAlarmToYear";

	/** 视频设备安装 */
	String DEVICE_DEVICELIST_VIDEODEVICEINSTALL = "/device/deviceList/videodeviceInstall";
	String DEVICE_DEVICELIST_VIDEODEVICEINSTALL_FUN = "videodeviceInstall";

	/** 设备列表分页查询 */
	String DEVICE_DEVICELIST_VIDEODEVICEPAGE = "/device/deviceList/videodevicePage";
	String DEVICE_DEVICELIST_VIDEODEVICEPAGE_FUN = "videodevicePage";

	/** 设备列表不分页查询 */
	String DEVICE_DEVICELIST_VIDEODEVICELIST = "/device/deviceList/videodeviceList";
	String DEVICE_DEVICELIST_VIDEODEVICELIST_FUN = "videodeviceList";

	/** 根据mac查询摄像头设备信息 */
	String DEVICE_DEVICEPARTICULARS_SELECTVIDEODEVICEONMAC = "/device/deviceParticulars/selectVideoDeviceOnMac";
	String DEVICE_DEVICEPARTICULARS_SELECTVIDEODEVICEONMAC_FUN = "selectVideoDeviceOnMac";

	/** 摄像头设备修改 */
	String DEVICE_DEVICELIST_UPDATEVIDEODEV = "/device/updateDevice/updateVideoDev";
	String DEVICE_DEVICELIST_UPDATEVIDEODEV_FUN = "updateVideoDev";

	/** 查询设备附近500米的摄像头 */
	String DEVICE_DEVICELIST_SELECTVIDEOONDEVICE = "/device/deviceList/selectVideoOnDevice";
	String DEVICE_DEVICELIST_SELECTVIDEOONDEVICE_FUN = "selectVideoOnDevice";

	/** 请求视频播放地址 */
	String DEVICE_VIDEO_VIDEOPUSH = "/device/video/videoPush";
	String DEVICE_VIDEO_VIDEOPUSH_FUN = "videoPush";

	/** 关闭视频流 */
	String DEVICE_VIDEO_VIDEOCLOSE = "/device/video/videoClose";
	String DEVICE_VIDEO_VIDEOCLOSE_FUN = "videoClose";

	/** 请求回放播放流 */
	String DEVICE_VIDEO_VIDEOPUSHLOG = "/device/video/videoPushLog";
	String DEVICE_VIDEO_VIDEOPUSHLOG_FUN = "videoPushLog";

	/** 能源总页左边 */
	String DEVICE_ENERGY_ENERGYALLLEFT = "/device/energy/energyAllLeft";
	String DEVICE_ENERGY_ENERGYALLLEFT_FUN = "energyAllLeft";

	/** 能源总页右边 */
	String DEVICE_ENERGY_ENERGYALLRIGHT = "/device/energy/energyAllRight";
	String DEVICE_ENERGY_ENERGYALLRIGHT_FUN = "energyAllRight";

	/** 空气质量排名 **/
	String AIR_QUALITY_RANK = "/device/energy/airQualityRank";
	String AIR_QUALITY_RANK_FUN = "airQualityRank";

	/** 24小时pm2.5曲线 **/
	String PM_HOUR_CURVE = "/device/energy/curveOfPmByHour";
	String PM_HOUR_CURVE_FUN = "curveOfPmByHour";

	/** 24小时温度曲线 **/
	String TEMPERATURE_CURVE = "/device/energy/curveOfTemperatureByHour";
	String TEMPERATURE_CURVE_FUN = "curveOfTemperatureByHour";

	/** 24小时湿度曲线 **/
	String HUMIDITY_CURVE = "/device/energy/curveOfHumidityByHour";
	String HUMIDITY_CURVE_FUN = "curveOfHumidityByHour";

	/** 24小时噪音散点图 **/
	String NOISE_CURVE = "/device/energy/curveOfNoiseByHour";
	String NOISE_CURVE_FUN = "curveOfNoiseByHour";

	/** 本周小区平均 AQI趋势及较上周同比情况 **/
	String AQI_CURVE = "/device/energy/curveOfAqiByWeek";
	String AQI_CURVE_FUN = "curveOfAqiByWeek";

	/** 综合态势中间能源分析 **/
	String ENERGY_ENERGYMSGCOUNT = "/device/energy/energyAllMsgCount";
	String ENERGY_ENERGYMSGCOUNT_FUN = "curveOfAqiByWeek";

	/** 能源电表页左边 */
	String DEVICE_ENERGY_ENERGYELECTRICITYLEFT = "/device/energy/energyElectricityLeft";
	String DEVICE_ENERGY_ENERGYELECTRICITYLEFT_FUN = "energyElectricityLeft";

	/** 能源电表页右边 */
	String DEVICE_ENERGY_ENERGYELECTRICITYRIGHT = "/device/energy/energyElectricityRight";
	String DEVICE_ENERGY_ENERGYELECTRICITYRIGHT_FUN = "energyElectricityRight";

	/** 能源总页中间 */
	String DEVICE_ENERGY_ENERGYALLMIDDLE = "/device/energy/energyAllMiddle";
	String DEVICE_ENERGY_ENERGYALLMIDDLE_FUN = "energyAllMiddle";

	/** 环境左边天气数据 */
	String DEVICE_ENVIRONMENT_ENVIRONMENTLEFT = "/device/environment/environmentLeft";
	String DEVICE_ENVIRONMENT_ENVIRONMENTLEFT_FUN = "environmentLeft";

	/** 维修记录 */
	String DEVICE_DEVICELIST_GETDEVFIXLOG = "/device/deviceList/getDevFixLog";
	String DEVICE_DEVICELIST_GETDEVFIXLOG_FUN = "getDevFixLog";

	/** 报警记录 */
	String DEVICE_DEVICELIST_GETDEVALARMLOG = "/device/deviceList/getDevAlarmLog";
	String DEVICE_DEVICELIST_GETDEVALARMLOG_FUN = "getDevAlarmLog";

	/** 门禁设备分页列表 */
	String DEVICE_DOOR_PAGEDATA = "/device/door/pageData";
	String DEVICE_DOOR_PAGEDATA_FUN = "devDoorPageData";

	/** 门禁设备安装 */
	String DEVICE_DOOR_INSATALL = "/device/door/install";
	String DEVICE_DOOR_INSATALL_FUN = "devDoorInstall";

	/** 门禁设备修改 */
	String DEVICE_DOOR_UPDATE = "/device/door/updateData";
	String DEVICE_DOOR_UPDATE_FUN = "devDoorUpdateData";

	/** 查看门禁设备属性 */
	String DEVICE_DOOR_QUERY_ATTR = "/device/door/queryAttr";
	String DEVICE_DOOR_QUERY_ATTR_FUN = "devDoorQueryAttr";

	/** 查看门禁设备详情 */
	String DEVICE_DOOR_DETAILS = "/device/door/detailsData";
	String DEVICE_DOOR_DETAILS_FUN = "devDoorDetailsData";

	/** 地图中间设备总数，离线，报警数统计 */
	String DEVICE_POSTURESYNTHESIZE_SELECTDEVICENUM = "/device/posturesynthesize/selectDeviceNum";
	String DEVICE_POSTURESYNTHESIZE_SELECTDEVICENUM_FUN = "selectDeviceNum";

	/** 最近七天消防报警数量统计 */
	String DEVICE_POSTURESYNTHESIZE_SELECTRECENTLYSEVENDAYS = "/device/posturesynthesize/selectRecentlySevenDays";
	String DEVICE_POSTURESYNTHESIZE_SELECTRECENTLYSEVENDAYS_FUN = "selectRecentlySevenDays";

	/** 综合态势分析中间地图数据 */
	String DEVICE_POSTURESYNTHESIZE_FULLSTATISTICS = "/device/posturesynthesize/fullStatistics";
	String DEVICE_POSTURESYNTHESIZE_FULLSTATISTICS_FUN = "fullStatistics";

	// ---日志相关
	/** 操作日志分页查询 */
	String LOG_OPERATE_PAGE = "/log/operate/page";
	String LOG_OPERATE_PAGE_FUN = "logOperatePage";

	/** 性能日志分页查询 */
	String LOG_PERFORMANCE_PAGE = "/log/performance/page";
	String LOG_PERFORMANCE_PAGE_FUN = "logPerformancePage";

	// ---工作人员管理相关
	/** 新增工作人员 */
	String WORKER_ADD = "/worker/add";
	String WORKER_ADD_FUN = "workerAdd";

	/** 删除工作人员 */
	String WORKER_DEL = "/worker/del";
	String WORKER_DEL_FUN = "workerDel";

	/** 更新工作人员 */
	String WORKER_UPDATE = "/worker/update";
	String WORKER_UPDATE_FUN = "workerUpdate";

	/** 查看工作人员 */
	String WORKER_GET = "/worker/get";
	String WORKER_GET_FUN = "workerGet";

	/** 工作人员列表 */
	String WORKER_LIST = "/worker/list";
	String WORKER_LIST_FUN = "workerList";

	/** 分页查询工作人员 */
	String WORKER_PAGE = "/worker/page";
	String WORKER_PAGE_FUN = "workerPage";

	// ---报警模块相关---
	/** 综合作业主屏标点,报警内容 */
	String ALARM_WFONT = "/alarm/wfont";
	String ALARM_WFONT_FUN = "alarmWfont";

	/** 报警列表分页查询 */
	String ALARM_PAGE = "/alarm/page";
	String ALARM_PAGE_FUN = "alarmPage";

	/** 关闭报警 */
	String ALARM_CLOSE = "/alarm/close";
	String ALARM_CLOSE_FUN = "alarmClose";

	/** 报警详情 */
	String ALARM_DETAIL = "/alarm/detail";
	String ALARM_DETAIL_FUN = "alarmDetail";

	/** 报警新增 */
	String ALARM_INSERT = "/alarm/insert";
	String ALARM_INSERT_FUN = "alarmInsert";

	/** 报警日志 */
	String ALARM_LOG = "/alarm/log";
	String ALARM_LOG_FUN = "alarmLog";

	/** 报警获取设备类型 */
	String ALARM_DEV_MODULE = "/alarm/devModule";
	String ALARM_DEV_MODULE_FUN = "alarmDevModule";

	String WORDER_DETAIL = "/worder/detail";
	String WORDER_DETAIL_FUN = "worderDetail";

	String EVENT_DETAIL = "/event/detail";
	String EVENT_DETAIL_FUN = "eventDetail";

	/** 报警后台首页 */
	String ALARM_BACK = "/alarm/back";
	String ALARM_BACK_FUN = "alarmBack";

	/** 报警事态分析(今日报警总数) */
	String ALARM_ATTITUDE = "/alarm/attitude";
	String ALARM_ATTITUDE_FUN = "alarmAttitude";

	/** 报警事态分析(报警趋势分析) */
	String ALARM_TREND = "/alarm/trend";
	String ALARM_TREND_FUN = "alarmTrend";

	/** 报警事态分析(报警时段分析) */
	String ALARM_HOUR = "/alarm/hour";
	String ALARM_HOUR_FUN = "alarmHour";

	/** 报警事态分析(报警类型分布) */
	String ALARM_TYPE = "/alarm/type";
	String ALARM_TYPE_FUN = "alarmType";

	/** 报警事态分析(累计处理报警总数) */
	String ALARM_DEAL = "/alarm/deal";
	String ALARM_DEAL_FUN = "alarmDeal";

	/** 报警事态分析(未处理报警) */
	String ALARM_NOTDEAL = "/alarm/notdeal";
	String ALARM_NOTDEAL_FUN = "alarmNotdeal";

	String DEV_FIXLOG = "/dev/fixLog";
	String DEV_FIXLOG_FUN = "devFixLog";

	String DEV_ALMLOG = "/dev/almLog";
	String DEV_ALMLOG_FUN = "devAlmLog";

	/** 报警总况 */
	String ALARM_COUNTINFO = "/alarm/countInfo";
	String ALARM_COUNTINFO_FUN = "alarmCountInfo";

	String WOD_CREATE = "/worder/create";
	String WOD_CREATE_FUN = "worderCreate";

	String WOD_GETEVENT = "/worder/getEvent";
	String WOD_GETEVENT_FUN = "worderGetEvent";

	String WOD_GETALARM = "/worder/getAlarm";
	String WOD_GETALARM_FUN = "worderGetAlarm";

	String WOD_GETDEV = "/worder/getDev";
	String WOD_GETDEV_FUN = "worderGetDev";

	String WOD_GETTIME = "/worder/time";
	String WOD_GETTIME_FUN = "worderGetTime";

	/** 综合作业,设备感知,按时间段统计 */
	String ALARM_ZY = "/alarm/zy";
	String ALARM_ZY_FUN = "alarmzy";

	/** 综合作业,累计报警,今日报警,未处理报警,未处理工单,超时工单 */
	String ALARM_ZYCOUNT = "/alarm/zycount";
	String ALARM_ZYCOUNT_FUN = "alarmzycount";

	/** 态势分析,实时报警 */
	String ALARM_STREALTIME = "/alarm/strealtime";
	String ALARM_STREALTIME_FUN = "alarmstrealtime";

	/** 获取用户标点 */
	String ALARM_USERPOINT = "/alarm/userPoint";
	String ALARM_USERPOINT_FUN = "alarmUserPoint";

	/** 获取综合作业主屏幕工单信息 */
	String ALARM_WORK_MAIN = "/alarm/wordMain";
	String ALARM_WORK_MAIN_FUN = "alarmWorkMain";
	
	/**获取综合作业主屏幕报警详情*/
	String ALARM_WORK_MAIN_DETAIL = "/alarm/wordMainDetail";
	String ALARM_WORK_MAIN_DETAIL_FUN = "alarmWorkMainDetail";
	
	/**获取综合作业主屏幕工单详情*/
	String ALARM_WORK_MAIN_DETAIL_WORK = "/alarm/wordMainWorkDetail";
	String ALARM_WORK_MAIN_DETAIL_WORK_FUN = "alarmWorkMainWorkDetail";
	
	/**获取综合作业主屏幕热力图*/
	String ALARM_WORK_MAIN_POINT = "/alarm/wordMainPoint";
	String ALARM_WORK_MAIN_POINT_FUN = "alarmWorkMainPoint";
	

	/** 获取综合作业主屏幕报警信息 */
	String ALARM_ALARM_MAIN = "/alarm/alarmMain";
	String ALARM_ALARM_MAIN_FUN = "alarmAlarmMain";

	// ---用户管理相关
	/** 新增用户 */
	String USER_ADD = "/user/add";
	String USER_ADD_FUN = "userAdd";

	/** 删除用户 */
	String USER_DEL = "/user/del";
	String USER_DEL_FUN = "userDel";

	/** 更新用户 */
	String USER_UPDATE = "/user/update";
	String USER_UPDATE_FUN = "userUpdate";

	/** 查看用户 */
	String USER_GET = "/user/get";
	String USER_GET_FUN = "userGet";

	/** 用户列表 */
	String USER_LIST = "/user/list";
	String USER_LIST_FUN = "userList";

	/** 分页查询用户 */
	String USER_PAGE = "/user/page";
	String USER_PAGE_FUN = "userPage";

	/** 用户设置密码 */
	String USER_SET_PWD = "/user/setPwd";
	String USER_SET_PWD_FUN = "userSetPwd";

	// ---车辆模块相关---
	/** 登记车辆列表(分页) */
	String CAR_REG_PAGELIST = "/car/carReg/pageList";
	String CAR_REG_PAGELIST_FUN = "carRegPageList";

	/** 新增登记车辆 */
	String CAR_REG_INSERT = "/car/carReg/insert";
	String CAR_REG_INSERT_FUN = "carRegInsert";

	/** 删除登记车辆 */
	String CAR_REG_DELETE = "/car/carReg/delete";
	String CAR_REG_DELETE_FUN = "carRegDelete";

	/** 修改登记车辆 */
	String CAR_REG_UPDATE = "/car/carReg/update";
	String CAR_REG_UPDATE_FUN = "carRegUpdate";

	/** 查看登记车辆详情 */
	String CAR_REG_DETAIL = "/car/carReg/detail";
	String CAR_REG_DETAIL_FUN = "carRegDetail";

	/** 分页查询陌生车辆列表 */
	String CAR_STR_PAGELIST = "/car/carStr/pageList";
	String CAR_STR_PAGELIST_FUN = "carStrPageList";

	/** 新增陌生车辆 (内部调用，此处可作废) */
	String CAR_STR_INSERT = "/car/carStr/insert";
	String CAR_STR_INSERT_FUN = "carStrInsert";

	/** 查看陌生车辆详情 */
	String CAR_STR_DETAIL = "car/carStr/detail";
	String CAR_STR_DETAIL_FUN = "carStrDetail";

	/** 删除陌生车辆 (内部调用，此处可作废) */
	String CAR_STR_DELETE = "/car/carStr/delete";
	String CAR_STR_DELETE_FUN = "carStrDelete";

	/** 分页查询车辆标签列表 */
	String CAR_LABEL_PAGELIST = "/car/label/pageList";
	String CAR_LABEL_PAGELIST_FUN = "labelPageList";

	/** 查询车辆标签列表 */
	String CAR_LABEL_LIST = "/car/label/list";
	String CAR_LABEL_LIST_FUN = "labelList";

	/** 查询车辆标签、标签类型列表 */
	String CAR_LABEL_LAYERED_LIST = "/car/labelLayered/list";
	String CAR_LABEL_LAYERED_LIST_FUN = "labelLayeredList";

	/** 新增车辆标签 */
	String CAR_LABEL_INSERT = "/car/label/insert";
	String CAR_LABEL_INSERT_FUN = "labelInsert";

	/** 删除车辆标签 */
	String CAR_LABEL_DELETE = "/car/label/delete";
	String CAR_LABEL_DELETE_FUN = "labelDelete";

	/** 修改车辆标签 */
	String CAR_LABEL_UPDATE = "/car/label/update";
	String CAR_LABEL_UPDATE_FUN = "labelUpdate";

	/** 修改车辆标签关系 */
	String CAR_LABEL_RELATION_UPDATE = "/car/labelRelation/update";
	String CAR_LABEL_RELATION_UPDATE_FUN = "labelRelationUpdate";

	/** 查看车辆标签详情 */
	String CAR_LABEL_DETAIL = "/car/label/detail";
	String CAR_LABEL_DETAIL_FUN = "labelDetail";

	/** 分页查询车辆通行列表 */
	String CAR_PASS_PAGELIST = "/car/pass/pageList";
	String CAR_PASS_PAGELIST_FUN = "passPageList";

	/** 新增车辆通行记录 */
	String CAR_PASS_INSERT = "/car/pass/insert";
	String CAR_PASS_INSERT_FUN = "insert";

	/** 查询车辆通行记录详情 */
	String CAR_PASS_DETAIL = "car/pass/detail";
	String CAR_PASS_DETAIL_FUN = "passDetail";

	/** 分页查询车辆预警规则列表 */
	String CAR_RULE_PAGELIST = "/car/rule/pageList";
	String CAR_RULE_PAGELIST_FUN = "rulePageList";

	/** 新增车辆预警规则 (暂时无此功能) */
	String CAR_RULE_INSERT = "/car/rule/insert";
	String CAR_RULE_INSERT_FUN = "ruleInsert";

	/** 删除车辆预警规则 */
	String CAR_RULE_DELETE = "car/rule/delete";
	String CAR_RULE_DELETE_FUN = "ruleDelete";

	/** 修改车辆预警规则 */
	String CAR_RULE_UPDATE = "/car/rule/update";
	String CAR_RULE_UPDATE_FUN = "ruleUpdate";

	/** 查看车辆预警规则详情 */
	String CAR_RULE_DETAIL = "/car/rule/detail";
	String CAR_RULE_DETAIL_FUN = "ruleDetail";

	/** 新增车辆报警记录 */
	String CAR_ARARM_INSERT = "/car/ararm/insert";
	String CAR_ARARM_INSERT_FUN = "insert";

	/** 车辆报警记录详情 */
	String CAR_ARARM_DETAIL = "/car/ararm/detail";
	String CAR_ARARM_DETAIL_FUN = "detail";

	/** 查询车辆总况统计数 */
	String CAR_STATISTICAL_COUNT = "/car/statistical/count";
	String CAR_STATISTICAL_COUNT_FUN = "statisticalCount";

	/** 查询登记车辆结构总数 */
	String CAR_STATISTICAL_TYPE_COUNT = "/car/statistical/typeCount";
	String CAR_STATISTICAL_TYPE_COUNT_FUN = "statisticalTypeCount";

	/** 查询上个月份各时段进出车辆总数 */
	String CAR_STATISTICAL_MONTIME_COUNT = "/car/statistical/monTimeCount";
	String CAR_STATISTICAL_MONTIME_COUNT_FUN = "statisticalMonTimeCount";

	/** 查询本月停车情况 */
	String CAR_STATISTICAL_PARKING_COUNT = "/car/statistical/parkingCount";
	String CAR_STATISTICAL_PARKING_COUNT_FUN = "statisticalParkingCount";

	/** 本月关注车辆通行记录统计 */
	String CAR_STATISTICAL_ATTENTION_COUNT = "/car/statistical/attentionCount";
	String CAR_STATISTICAL_ATTENTION_COUNT_FUN = "statisticalAttentionCount";

	/** 本月关注车辆预警级别统计 */
	String CAR_STATISTICAL_ALARMLEVEL_COUNT = "/car/statistical/alarmLevelCount";
	String CAR_STATISTICAL_ALARMLEVEL_COUNT_FUN = "statisticalAlarmLevelCount";

	/** 月报警统计（报警趋势） */
	String CAR_STATISTICAL_ALARMDAY_COUNT = "/car/statistical/AlarmDayCount";
	String CAR_STATISTICAL_ALARMDAY_COUNT_FUN = "statisticalAlarmDayCount";

	/** 车辆神经感知基础信息统计 */
	String CAR_FEEL_INFO_COUNT = "/car/feel/infoCount";
	String CAR_FEEL_INFO_COUNT_FUN = "feelInfoCount";

	/** 按小时统计今日车辆通行次数、车辆数 */
	String CAR_FEEL_CAR_PASS_COUNT = "/car/feel/carPassCount";
	String CAR_FEEL_CAR_PASS_COUNT_FUN = "feelCarPassCount";

	/** 查询车辆当天不同报警类型数 */
	String CAR_FEEL_AlARM_TYPE_COUNT = "/car/feel/alarmTypeCount";
	String CAR_FEEL_AlARM_TYPE_COUNT_FUN = "feelAlarmTypeCount";

	/** 查询本周报警记录数 (作废) */
	String CAR_FEEL_AlARM_WEEK_COUNT = "/car/feel/alarmWeekCount";
	String CAR_FEEL_AlARM_WEEK_COUNT_FUN = "feelAlarmWeekCount";

	/** 查询当天通行车辆数、通行次数 */
	String CAR_FEEL_PASS_DAY_COUNT = "/car/feel/passDayCount";
	String CAR_FEEL_PASS_DAY_COUNT_FUN = "feelPassDayCount";

	/** 查询未处理的报警记录数 */
	String CAR_FEEL_ALARM_LIST = "/car/feel/alarmList";
	String CAR_FEEL_ALARM_LIST_FUN = "feelAlarmList";

	/** 查询当天车辆进、出次数 (态势分析) */
	String CAR_POS_TODAY_COUNT = "/car/pos/todayCount";
	String CAR_POS_TODAY_COUNT_FUN = "posTodayCount";

	/** 按小时查询月、年车辆进、出次数(态势分析) */
	String CAR_POS_ENTER_OUT_COUNT = "/car/pos/enterOutCount";
	String CAR_POS_ENTER_OUT_COUNT_FUN = "posEnterOutCount";

	/** 按天数查询最月、年停车车辆数 (态势分析) */
	String CAR_POS_PARKING_COUNT = "/car/pos/parkingCount";
	String CAR_POS_PARKING_COUNT_FUN = "posParkingCount";

	/** 统计关注车辆次数(态势分析) */
	String CAR_POS_ATTENTION_COUNT = "/car/pos/attentionCount";
	String CAR_POS_ATTENTION_COUNT_FUN = "posAttentionCount";

	/** 报警级别统计(态势分析) */
	String CAR_POS_ALARM_COUNT = "/car/pos/alarmCount";
	String CAR_POS_ALARM_COUNT_FUN = "posAlarmCount";

	/** 查询本周通行车辆数、通行次数(态势分析) */
	String CAR_POS_WEEK_COUNT = "/car/pos/weekCount";
	String CAR_POS_WEEK_COUNT_FUN = "weekCount";

	// ---定时任务模块相关---
	/** 分页查询任务列表 */
	String QT_PAGE = "/quartz/page";
	String QT_PAGE_FUN = "quartzPage";

	/** 新增或修改任务 */
	String QT_INSERT_UPDATE = "/quartz/insertOrUpd";
	String QT_INSERT_UPDATE_FUN = "insertOrUpd";

	/** 删除任务 */
	String QT_DELETE = "/quartz/delete";
	String QT_DELETE_FUN = "quatrzDelete";

	/** 暂停任务 */
	String QT_PAUSE = "/quartz/pause";
	String QT_PAUSE_FUN = "pause";

	/** 恢复任务 */
	String QT_RESUME = "/quartz/resume";
	String QT_RESUME_FUN = "resume";

	// ---单位模块相关---
	/** 分页查询单位列表 */
	String COR_PAGE = "/corporation/page";
	String COR_PAGE_FUN = "corPage";

	/** 查询单位列表 */
	String COR_LIST = "/corporation/list";
	String COR_LIST_FUN = "corList";

	/** 新增单位 */
	String COR_INSERT = "/corporation/insert";
	String COR_INSERT_FUN = "corInsert";

	/** 删除单位 */
	String COR_DELETE = "/corporation/delete";
	String COR_DELETE_FUN = "corDelete";

	/** 修改单位 */
	String COR_UPDATE = "/corporation/update";
	String COR_UPDATE_FUN = "corUpdate";

	/** 查看单位详情 */
	String COR_DETAIL = "/corporation/detail";
	String COR_DETAIL_FUN = "corDetai";

	/** 分页查询单位标签列表 */
	String COR_LABEL_PAGE = "/corporation/labelPage";
	String COR_LABEL_PAGE_FUN = "corLabelPage";

	/** 新增单位标签 */
	String COR_LABEL_INSERT = "/corporation/labelInsert";
	String COR_LABEL_INSERT_FUN = "corLabelInsert";

	/** 删除单位标签 */
	String COR_LABEL_DELETE = "/corporation/labelDelete";
	String COR_LABEL_DELETE_FUN = "corLabelDelete";

	/** 修改单位标签 */
	String COR_LABEL_UPDATE = "/corporation/labelUpdate";
	String COR_LABEL_UPDATE_FUN = "corLabelUpdate";

	/** 查看单位标签详情 */
	String COR_LABEL_DETAIL = "/corporation/labelDetail";
	String COR_LABEL_DETAIL_FUN = "corLabelDetai";

	/** 查询单位标签类型列表 */
	String COR_LABEL_TYPE_PAGE = "/corporation/labelTypeList";
	String COR_LABEL_TYPE_PAGE_FUN = "corLabelTypeList";

	/** 单位人口批量新增 */
	String COR_PER_BATCH_INSERT = "/corporation/corPerBatchInsert";
	String COR_PER_BATCH_INSERT_FUN = "corPerBatchInsert";

	/** 分页查询单位关联人口列表 */
	String COR_PERSON_PAGE = "/corporation/personPage";
	String COR_PERSON_FUN = "corPersonPage";

	/** 查询单位关联人口列表 */
	String COR_PERSON_LIST = "/corporation/personList";
	String COR_PERSON_LIST_FUN = "corPersonList";

	/** 单位总况统计 */
	String COR_TOTAL_CASE = "/corporation/totalCase";
	String COR_TOTAL_CASE_FUN = "corTotalCase";

	/** 单位类型统计 */
	String COR_TYPE_TOTAL = "/corporation/totalType";
	String COR_TYPE_TOTAL_FUN = "corTotalType";

	/** 关注单位统计 */
	String COR_ATTENTION_TOTAL = "/corporation/totalAttention";
	String COR_ATTENTIONL_FUN = "corTotalAttention";

	/** 每月登记单位数量 */
	String COR_MON_TOTAL = "/corporation/totalMon";
	String COR_MON_FUN = "corTotalMon";

	/** 删除单位人口关联关系 */
	String COR_PERSON_DELETE = "/corporation/personDelete";
	String COR_PERSON_DELETE_FUN = "corPersonDelete";

	// ---工单模块相关---
	/** 工单列表分页查询 */
	String WOD_PAGE = "/worder/page";
	String WOD_PAGE_FUN = "worderPage";

	/** 创建工单 */
	String WOD_ADD = "/worder/add";
	String WOD_ADD_FUN = "worderAdd";

	/** 工单详情 */
	String WOD_DET = "/worder/det";
	String WOD_DET_FUN = "worderdet";

	/** 工单概况 */
	String WOD_GK = "/worder/gk";
	String WOD_GK_FUN = "wordergk";

	/** 后台首页,工单情况 */
	String WOD_HT = "/worder/ht";
	String WOD_HT_FUN = "worderht";

	/** 综合作业,工单列表,及时处理率 */
	String WOD_ZY = "/worder/zy";
	String WOD_ZY_FUN = "worderzy";

	// ---事件模块---
	/** 事件列表分页查询 */
	String EVE_PAGE = "/event/page";
	String EVE_PAGE_FUN = "eventPage";

	/** 事件概况 */
	String EVE_GK = "/event/gk";
	String EVE_GK_FUN = "eventgk";

	/** 关闭事件 */
	String EVE_CLOSE = "/event/close";
	String EVE_CLOSE_FUN = "eventClose";

	/** 事件详情 */
	String EVE_DET = "/event/det";
	String EVE_DET_FUN = "eventdet";

	/** 添加事件 */
	String EVE_ADD = "/event/add";
	String EVE_ADD_FUN = "eventadd";

	// ---消息推送---
	/** 消息推送/数据解析 */
	String MSG_SEND = "/msg/sendMessage";

	/** 事件详情 */
	String MSG_SEND_TEST = "/msg/sendMessageTest";

	/** 新增消息通知 */
	String SYS_NOTICE_INSERT = "/notice/insert";
	String SYS_NOTICE_INSERT_FUN = "noticeInsert";

	/** 删除消息通知 */
	String SYS_NOTICE_DELETE = "/notice/delete";
	String SYS_NOTICE_DELETE_FUN = "noticeDelete";

	/** 修改消息通知 */
	String SYS_NOTICE_UPDATE = "/notice/update";
	String SYS_NOTICE_UPDATE_FUN = "noticeUpdate";

	/** 消息通知详情 */
	String SYS_NOTICE_DETAIL = "/notice/detail";
	String SYS_NOTICE_DETAIL_FUN = "noticeDetail";

	/** 分页查询消息通知 */
	String SYS_NOTICE_PAGELIST = "/notice/pageList";
	String SYS_NOTICE_PAGELIST_FUN = "noticePageList";

	List<String> SKIPUSRS = Arrays.asList(CAR_PASS_INSERT, GET_VALIDATE_CODE, AREA_LIST, AREA_LIST_FUN, DICTIONARY_GET,
			POPULATION_RECORD_ADD);

	static boolean skip(String url) {
		url = url.replace(PREFIX, "");
		return SKIPUSRS.contains(url);
	}

}
