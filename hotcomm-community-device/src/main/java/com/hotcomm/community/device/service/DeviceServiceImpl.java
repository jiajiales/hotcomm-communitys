package com.hotcomm.community.device.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.hotcomm.community.common.bean.db.device.DeviceAlarmGzDM;
import com.hotcomm.community.common.bean.db.device.SelectDeviceStateDM;
import com.hotcomm.community.common.bean.db.parse.DeviceAlarmEN.DeviceAlarmState;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.pa.device.DeviceInstallPA;
import com.hotcomm.community.common.bean.pa.device.DevicePagePA;
import com.hotcomm.community.common.bean.pa.device.DeviceUpdateRuleFPA;
import com.hotcomm.community.common.bean.pa.device.DeviceUpdateRulePA;
import com.hotcomm.community.common.bean.pa.device.VideoDeviceInstallPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.device.DeviceMacCodeUM;
import com.hotcomm.community.common.bean.ui.device.DeviceMacDataUM;
import com.hotcomm.community.common.bean.ui.device.DeviceMsgWorkUM;
import com.hotcomm.community.common.bean.ui.device.DevicePageUM;
import com.hotcomm.community.common.bean.ui.device.Device_GzUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceAlarmCount;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateAlarmUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateCountUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateDevUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStatePercentUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateTypeUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateUM;
import com.hotcomm.community.common.bean.ui.device.SelectRulePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.VideoDeviceListUM;
import com.hotcomm.community.common.bean.ui.device.VideoDevicePageUM;
import com.hotcomm.community.common.bean.ui.device.VideoUM;
import com.hotcomm.community.common.bean.ui.device.video.PushMsg;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.device.mapper.DeviceFellMapper;
import com.hotcomm.community.device.mapper.DeviceMapper;
import com.hotcomm.community.message.bean.ui.Message;
import com.hotcomm.community.message.service.MsgServiceImpl;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.UUIDUtils;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class DeviceServiceImpl extends BaseService implements DeviceService {

	@Autowired
	DeviceMapper deviceMapper;

	@Autowired
	CommunityService communityService;

	@Autowired
	MsgServiceImpl msgServiceImpl;

	@Autowired
	ProscessService alarmService;

	@Autowired
	private DeviceFellMapper devicefellmapper;

	@Value("${spring.data.url}")
	private String dataUrl;

	@Value("${spring.communityurl.url}")
	private String communityUrl;

	@Value("${spring.video.url}")
	private String videoUrl;

	/**
	 * 设备列表分页查询
	 */
	@Override
	public PageInfo<DevicePageUM> devicePage(DevicePagePA devicePagePA) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(devicePagePA.getCommunityId());
		PageHelper.startPage(devicePagePA.getPageIndex(), devicePagePA.getPageSize());
		Page<DevicePageUM> page = deviceMapper.devicePage(tableParams, devicePagePA);
		List<DevicePageUM> devicePageList = page;
		return new PageInfo<>(page.getTotal(), devicePageList, devicePagePA.getPageSize(), devicePagePA.getPageIndex());
	}

	/**
	 * 安装设备
	 */
	@Transactional
	@Override
	public Integer deviceInstall(DeviceInstallPA deviceInstallPA) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(deviceInstallPA.getCommunityId());
			deviceInstallPA.setDynamic_dbname((String) tableParams.get("dynamic_dbname"));
			// 判断是否存在图片
			if (deviceInstallPA.getPictureList() != null && !deviceInstallPA.getPictureList().isEmpty()) {
				String[] picture = deviceInstallPA.getPictureList().split(",");
				String picture_list = "{\"picture\":[";
				for (int i = 0; i < picture.length; i++) {
					picture_list += "\"" + picture[i];
					if (i != (picture.length - 1))
						picture_list += "\",";
					else
						picture_list += "\"]}";
				}
				deviceInstallPA.setPictureList(picture_list);
			}
			// 判断视频id是否为空
			if (deviceInstallPA.getVideoList() != null && !deviceInstallPA.getVideoList().isEmpty()) {
				String[] video = deviceInstallPA.getVideoList().split(",");
				String video_list = "{\"video\":[";
				for (int i = 0; i < video.length; i++) {
					video_list += "\"" + video[i];
					if (i != (video.length - 1))
						video_list += "\",";
					else
						video_list += "\"]}";
				}
				deviceInstallPA.setVideoList(video_list);
			}
			// 插入设备表数据
			deviceMapper.deviceInstall(deviceInstallPA);
			String dev_num = "";
			switch (deviceInstallPA.getModuleId()) {
			case 1:
				dev_num = "HKYG_00";
				break;
			case 2:
				dev_num = "HKKRQ_00";
				break;
			case 3:
				dev_num = "HKSY_00";
				break;
			case 4:
				dev_num = "HKSXDL_00";
				break;
			}
			// 修改新增设备的设备编号
			deviceMapper.deviceInstallUpdateNum(tableParams, deviceInstallPA.getMac(),
					dev_num + deviceInstallPA.getDevid());
			// 查询出该设备类型下的所有规则,第三位参数传0代表查询系统规则
			List<DeviceAlarmGzDM> deviceAlarmGzDM = deviceMapper.deviceAlarmGz(tableParams,
					deviceInstallPA.getModuleId(), 0, null);
			System.out.println(deviceAlarmGzDM);
			// 给新设备关联规则
			deviceMapper.deviceAlarmGzInsert(tableParams, deviceAlarmGzDM, deviceInstallPA.getMac());
			// 新增基础库总表数据
			deviceMapper.deviceInstallCommunity(tableParams, deviceInstallPA.getMac(),
					deviceInstallPA.getCommunityId());
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0001", e);
		}
		return 1;
	}

	/**
	 * 修改设备
	 */
	@Transactional
	@Override
	public Integer updateDev(DeviceInstallPA deviceInstallPA) throws HKException {
		Map<String, Object> tableParams = null;
		try {
			tableParams = super.getTableParams(deviceInstallPA.getCommunityId());
			// 判断图片是否为空
			if (!deviceInstallPA.getPictureList().isEmpty()) {
				String[] picture = deviceInstallPA.getPictureList().split(",");
				String picture_list = "{\"picture\":[";
				for (int i = 0; i < picture.length; i++) {
					picture_list += "\"" + picture[i];
					if (i != (picture.length - 1))
						picture_list += "\",";
					else
						picture_list += "\"]}";
				}
				deviceInstallPA.setPictureList(picture_list);
			} else
				deviceInstallPA.setPictureList(null);
			// 判断视频id是否为空
			if (!deviceInstallPA.getVideoList().isEmpty()) {
				String[] video = deviceInstallPA.getVideoList().split(",");
				String video_list = "{\"video\":[";
				for (int i = 0; i < video.length; i++) {
					video_list += "\"" + video[i];
					if (i != (video.length - 1))
						video_list += "\",";
					else
						video_list += "\"]}";
				}
				deviceInstallPA.setVideoList(video_list);
			} else
				deviceInstallPA.setVideoList(null);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0002", e);
		}
		return deviceMapper.updateDev(tableParams, deviceInstallPA);
	}

	/**
	 * 根据mac查询设备信息
	 */
	@Override
	public SelectDeviceOnMacUM selectDeviceOnMac(String mac, Integer communityId, Integer module_id)
			throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		SelectDeviceOnMacUM selectDeviceOnMacUM = deviceMapper.selectDeviceOnMac(tableParams, mac, module_id);
		if (selectDeviceOnMacUM != null && selectDeviceOnMacUM.getVideoList() != null
				&& selectDeviceOnMacUM.getVideoList() != "") {
			VideoUM videMacList = new Gson().fromJson(selectDeviceOnMacUM.getVideoList(), VideoUM.class);
			List<SelectVideoDeviceOnMacUM> videoArrayList = new ArrayList<SelectVideoDeviceOnMacUM>();
			for (int i = 0; i < videMacList.getVideo().size(); i++) {
				videoArrayList.add(selectVideoDeviceOnMac(videMacList.getVideo().get(i), communityId));
			}
			selectDeviceOnMacUM.setVideoArrayList(videoArrayList);
		}
		return selectDeviceOnMacUM;
	}

	/**
	 * 根据mac查询设备预警规则
	 */
	@Override
	public List<DeviceAlarmGzDM> selectRuleOnDev(String mac, Integer communityId, Integer use_type, Integer module_id)
			throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		// 第三位参数传1代表查询设备自定义规则
		return deviceMapper.deviceAlarmGz(tableParams, module_id, use_type, mac);
	}

	/**
	 * 安装验证mac
	 * 
	 * @throws IOException
	 */
	@Override
	public DeviceMacDataUM deviceMac(String mac, Integer module_id) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		map.put("deviceCode", mac);
		Map<String, Object> connection = HttpPost(map, dataUrl);
		if (!connection.containsKey("state") || !connection.containsKey("json"))
			throw exceptionManager.create("DEVE0008");

		int state = (int) connection.get("state");
		if (state == 200) {
			// 发送成功
			Gson gson = new Gson(); // 获取到数据中心返回数据
			DeviceMacCodeUM deviceMacCodePA = gson.fromJson((String) connection.get("json"), DeviceMacCodeUM.class);

			// 如果找不到设备信息直接返回空
			if (deviceMacCodePA.getData() == null) // 返回该设备不存在
				throw exceptionManager.create("DEVE0003");

			// 二次校验，前端页面当前添加设备类型是否与输入的mac设备类型一致
			switch (deviceMacCodePA.getData().getDeviceTypeCode()) {
			case "yg":
				if (module_id != 1)
					throw exceptionManager.create("DEVE0005");
				break;
			case "krq":
				if (module_id != 2)
					throw exceptionManager.create("DEVE0005");
				break;
			case "xfsy":
			case "sy":
				if (module_id != 3)
					throw exceptionManager.create("DEVE0005");
				break;
			case "dl":
				if (module_id != 4)
					throw exceptionManager.create("DEVE0005");
				break;
			default:
				break;
			}

			// 三次校验，该设备是否已经安装过
			DeviceMacDataUM basedata = deviceMapper.deviceMac(mac);
			if (basedata != null && basedata.getDeviceCode() != null) // 返回该设备已安装
				throw exceptionManager.create("DEVE0004");

			return deviceMacCodePA.getData();
		}
		throw exceptionManager.create("DEVE0008");
	}

	/**
	 * 预警方案列表
	 * 
	 */
	@Override
	public List<SelectRulePageUM> selectRulePage(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);

		// 查询出规则列表数据
		List<SelectRulePageUM> selectRulePageDMs = deviceMapper.selectRulePage(tableParams);
		// 把相关人员id转为list
		Gson gson = new Gson();
		for (int i = 0; i < selectRulePageDMs.size(); i++) {
			SelectRulePageUM selectRulePageUM = selectRulePageDMs.get(i);
			// 判断相关人员字段是否为空
			if (selectRulePageDMs.get(i).getInnerUserid() != null
					&& selectRulePageDMs.get(i).getInnerUserid().length() > 0) {
				// 把相关人员id转为list
				Device_GzUM Inner_userid = gson.fromJson(selectRulePageDMs.get(i).getInnerUserid(), Device_GzUM.class);
				// 根据用户id查询出用户名称，电话
				List<String> inner_userid = deviceMapper.selectRulePageUser(tableParams, Inner_userid.getUserid());
				// 把相关人员信息写进返回类
				selectRulePageUM.setInnerUseridMap(inner_userid);
			}
			// 判断通知人员字段是否为空
			if (selectRulePageDMs.get(i).getInformUserid() != null
					&& selectRulePageDMs.get(i).getInformUserid().length() > 0) {
				// 把通知人员id转list
				Device_GzUM Inform_userid = gson.fromJson(selectRulePageDMs.get(i).getInformUserid(),
						Device_GzUM.class);
				// 根据用户id查询出用户名称，电话
				List<String> inform_userid = deviceMapper.selectRulePageUser(tableParams, Inform_userid.getUserid());
				// 把通知人员信息写进返回类
				selectRulePageUM.setInformUseridMap(inform_userid);
			}

			// 把相关人员信息放进返回实体类
			selectRulePageDMs.set(i, selectRulePageUM);
		}
		return selectRulePageDMs;
	}

	/**
	 * 设备状态，类型，报警分布
	 * 
	 */
	@Override
	public SelectDeviceStateUM selectDeviceState(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");

		// 返回的实体类
		SelectDeviceStateUM selectDeviceStateUMs = new SelectDeviceStateUM();

		// 设备总数
		Integer devcount = 0;
		// 正常总数
		Integer normalcount = 0;
		// 离线总数
		Integer offlinecount = 0;
		// 故障总数
		Integer faultcount = 0;
		// 报警总数
		Integer alarmcount = 0;

		// 查询出设备模块，每个模块设备总数，正常数，离线数，故障数
		List<SelectDeviceStateDM> selectDeviceStateDMs = deviceMapper.selectDeviceState(tableParams);
		SelectDeviceStateDM videos = new SelectDeviceStateDM();
		videos.setModuleName("摄像头");
		videos.setDevNum(devicefellmapper.DeviceMsgCountOnVideo(tableParams));
		videos.setNormal(videos.getDevNum());
		selectDeviceStateDMs.add(videos);

		// 计算所有的设备总数/在线总数/离线总数/故障总数
		for (SelectDeviceStateDM s : selectDeviceStateDMs) {
			devcount += s.getDevNum();
			normalcount += s.getNormal();
			offlinecount += s.getOffLine();
			faultcount += s.getFault();
		}

		// 设备状态分布统计
		SelectDeviceStatePercentUM percent = new SelectDeviceStatePercentUM();
		percent.setNormal(normalcount);
		percent.setOffLine(offlinecount);
		percent.setFault(faultcount);
		if (devcount != 0) {
			percent.setNormalPercent(Double.parseDouble(df.format(((double) normalcount / devcount) * 100)));
			percent.setOfflinePercent(Double.parseDouble(df.format(((double) offlinecount / devcount) * 100)));
			percent.setFaultPercent(Double.parseDouble(df.format(((double) faultcount / devcount) * 100)));
		}
		selectDeviceStateUMs.setPercent(percent);

		// 各类设备状态分布
		List<SelectDeviceStateDevUM> dev = new ArrayList<SelectDeviceStateDevUM>();
		for (int i = 0; i < selectDeviceStateDMs.size(); i++) {
			SelectDeviceStateDevUM devson = new SelectDeviceStateDevUM();
			devson.setModuleName(selectDeviceStateDMs.get(i).getModuleName());
			devson.setNormal(selectDeviceStateDMs.get(i).getNormal());
			devson.setOffLine(selectDeviceStateDMs.get(i).getOffLine());
			devson.setFault(selectDeviceStateDMs.get(i).getFault());
			dev.add(devson);
		}
		selectDeviceStateUMs.setDev(dev);

		// 设备类型分布
		List<SelectDeviceStateTypeUM> type = new ArrayList<SelectDeviceStateTypeUM>();

		for (int i = 0; i < selectDeviceStateDMs.size(); i++) {
			SelectDeviceStateTypeUM typeson = new SelectDeviceStateTypeUM();
			typeson.setModuleName(selectDeviceStateDMs.get(i).getModuleName());
			typeson.setDevNum(selectDeviceStateDMs.get(i).getDevNum());
			type.add(typeson);
		}
		selectDeviceStateUMs.setType(type);

		// 查询本月报警
		List<SelectDeviceStateDM> stateDMs = deviceMapper.selectDeviceAlarm(tableParams);
		// 本月报警设备分布
		List<SelectDeviceStateAlarmUM> alarm = new ArrayList<SelectDeviceStateAlarmUM>();
		for (SelectDeviceStateDM s : stateDMs)
			alarmcount += s.getAlarm();
		for (int i = 0; i < stateDMs.size(); i++) {
			SelectDeviceStateAlarmUM alarmson = new SelectDeviceStateAlarmUM();
			alarmson.setModuleName(stateDMs.get(i).getModuleName());
			alarmson.setAlarm(stateDMs.get(i).getAlarm());

			if (alarmcount != 0)
				alarmson.setAlarmPercent(
						Double.parseDouble(df.format(((double) stateDMs.get(i).getAlarm() / alarmcount) * 100)));

			alarm.add(alarmson);
		}
		selectDeviceStateUMs.setAlarm(alarm);

		// 设备总数,本月设备报警数,当前离线设备数,本月故障设备数统计
		SelectDeviceStateCountUM count = new SelectDeviceStateCountUM();
		count.setDevCount(devcount);
		count.setAlarmCount(alarmcount);
		count.setOffLineCount(selectDeviceStateUMs.getPercent().getOffLine());
		count.setFaultCount(selectDeviceStateUMs.getPercent().getFault());
		selectDeviceStateUMs.setCount(count);

		return selectDeviceStateUMs;
	}

	/**
	 * 修改系统预警规则
	 * 
	 */
	@Transactional
	@Override
	public Integer updateSystemRule(String data) throws HKException {
		try {
			Gson gson = new Gson();
			// 将json数据源转化为对象
			DeviceUpdateRuleFPA deviceUpdateRuleFPAs = gson.fromJson(data, DeviceUpdateRuleFPA.class);
			// 查出社区数据库
			Map<String, Object> tableParams = super.getTableParams(deviceUpdateRuleFPAs.getCommunityId());

			// 修改规则私有属性
			for (DeviceUpdateRulePA s : deviceUpdateRuleFPAs.getData()) {
				deviceMapper.updateSystemRule(tableParams, s);
			}
			// 修改统一属性，相关人员，通知人员，间隔推送时间
			deviceMapper.updateSystemRuleUserid(tableParams, deviceUpdateRuleFPAs);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0006", e);
		}
		return 1;
	}

	/**
	 * 修改设备预警规则
	 * 
	 */
	@Transactional
	@Override
	public Integer updateRule(String data) throws HKException {
		try {
			Gson gson = new Gson();
			// 将json数据源转化为对象
			DeviceUpdateRuleFPA deviceUpdateRuleFPAs = gson.fromJson(data, DeviceUpdateRuleFPA.class);
			// 查出社区数据库
			Map<String, Object> tableParams = super.getTableParams(deviceUpdateRuleFPAs.getCommunityId());

			// 修改规则私有属性
			for (DeviceUpdateRulePA s : deviceUpdateRuleFPAs.getData()) {
				deviceMapper.updateSystemRule(tableParams, s);
			}
			// 修改统一属性，相关人员，通知人员，间隔推送时间
			deviceMapper.updateRuleUserid(tableParams, deviceUpdateRuleFPAs);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0006", e);
		}
		return 1;
	}

	/**
	 * 创建工单的设备列表选择
	 * 
	 */
	@Override
	public List<DeviceMsgWorkUM> selectDeviceMsgWork(Integer communityId, Integer moduleid, String mac, Integer isAlarm)
			throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		return deviceMapper.selectDeviceMsgWork(tableParams, moduleid, mac, isAlarm);
	}

	/**
	 * 安装视频设备
	 */
	@Transactional
	@Override
	public Integer videodeviceInstall(VideoDeviceInstallPA deviceInstallPA) throws HKException {
		try {
			Map<String, Object> tableParams = super.getTableParams(deviceInstallPA.getCommunityId());
			deviceInstallPA.setDynamic_dbname((String) tableParams.get("dynamic_dbname"));
			// 判断是否存在图片
			if (!deviceInstallPA.getPictureList().isEmpty()) {
				String[] picture = deviceInstallPA.getPictureList().split(",");
				String picture_list = "{\"picture\":[";
				for (int i = 0; i < picture.length; i++) {
					picture_list += "\"" + picture[i];
					if (i != (picture.length - 1))
						picture_list += "\",";
					else
						picture_list += "\"]}";
				}
				deviceInstallPA.setPictureList(picture_list);
			}
			// 判断视频id是否为空
			if (!deviceInstallPA.getVideoList().isEmpty()) {
				String[] video = deviceInstallPA.getVideoList().split(",");
				String video_list = "{\"video\":[";
				for (int i = 0; i < video.length; i++) {
					video_list += "\"" + video[i];
					if (i != (video.length - 1))
						video_list += "\",";
					else
						video_list += "\"]}";
				}
				deviceInstallPA.setVideoList(video_list);
			}
			deviceMapper.videodeviceInstall(deviceInstallPA);
			// 修改新增设备的设备编号
			deviceMapper.videodeviceInstallUpdateNum(tableParams, deviceInstallPA.getMac(),
					"HKVIDEO_00" + deviceInstallPA.getDevid());
			// 新增基础库总表数据
			deviceMapper.deviceInstallCommunity(tableParams, deviceInstallPA.getMac(),
					deviceInstallPA.getCommunityId());
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0001", e);
		}
		return null;
	}

	/**
	 * 视频设备列表分页查询
	 * 
	 */
	@Override
	public PageInfo<VideoDevicePageUM> videodevicePage(DevicePagePA devicePagePA) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(devicePagePA.getCommunityId());
		PageHelper.startPage(devicePagePA.getPageIndex(), devicePagePA.getPageSize());
		Page<VideoDevicePageUM> page = deviceMapper.videodevicePage(tableParams, devicePagePA);
		List<VideoDevicePageUM> devicePageList = page;
		return new PageInfo<>(page.getTotal(), devicePageList, devicePagePA.getPageSize(), devicePagePA.getPageIndex());
	}

	/**
	 * 视频设备列表不分页查询
	 * 
	 */
	@Override
	public List<VideoDeviceListUM> videodeviceList(String mac) throws HKException {
		// 查询所有社区
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		// 需要返回的实体类
		List<VideoDeviceListUM> videoDeviceListUMs = new ArrayList<VideoDeviceListUM>();
		// 遍历查询每个社区的摄像头信息
		for (CommunityListAllUM s : communityList) {
			Map<String, Object> tableParams = super.getTableParams(s.getCid());
			videoDeviceListUMs.addAll(deviceMapper.videodeviceList(tableParams, mac));
		}
		return videoDeviceListUMs;
	}

	/**
	 * 根据mac查询摄像头信息
	 * 
	 */
	@Override
	public SelectVideoDeviceOnMacUM selectVideoDeviceOnMac(String mac, Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		SelectVideoDeviceOnMacUM s = deviceMapper.selectVideoDeviceOnMac(tableParams, mac);
		if (s != null && s.getVideoList() != null && s.getVideoList() != "") {
			VideoUM videMacList = new Gson().fromJson(s.getVideoList(), VideoUM.class);
			List<SelectVideoDeviceOnMacUM> videoArrayList = new ArrayList<SelectVideoDeviceOnMacUM>();
			for (int i = 0; i < videMacList.getVideo().size(); i++) {
				videoArrayList.add(selectVideoDeviceOnMac(videMacList.getVideo().get(i), communityId));
			}
			s.setVideoArrayList(videoArrayList);
		}
		return s;
	}

	/**
	 * 修改摄像头设备信息
	 * 
	 */
	@Override
	public Integer updateVideoDev(VideoDeviceInstallPA deviceInstallPA) throws HKException {
		Map<String, Object> tableParams = null;
		try {
			tableParams = super.getTableParams(deviceInstallPA.getCommunityId());
			// 判断图片是否为空
			if (!deviceInstallPA.getPictureList().isEmpty()) {
				String[] picture = deviceInstallPA.getPictureList().split(",");
				String picture_list = "{\"picture\":[";
				for (int i = 0; i < picture.length; i++) {
					picture_list += "\"" + picture[i];
					if (i != (picture.length - 1))
						picture_list += "\",";
					else
						picture_list += "\"]}";
				}
				deviceInstallPA.setPictureList(picture_list);
			} else
				deviceInstallPA.setPictureList(null);
			// 判断视频id是否为空
			if (!deviceInstallPA.getVideoList().isEmpty()) {
				String[] video = deviceInstallPA.getVideoList().split(",");
				String video_list = "{\"video\":[";
				for (int i = 0; i < video.length; i++) {
					video_list += "\"" + video[i];
					if (i != (video.length - 1))
						video_list += "\",";
					else
						video_list += "\"]}";
				}
				deviceInstallPA.setVideoList(video_list);
			} else
				deviceInstallPA.setVideoList(null);
		} catch (Exception e) {
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0007", e);
		}
		return deviceMapper.updateVideoDev(tableParams, deviceInstallPA);
	}

	@Override
	public List<VideoDeviceListUM> selectVideoOnDevice(Integer communityId, Double lat, Double lon) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		List<VideoDeviceListUM> list = deviceMapper.videodeviceList(tableParams, null);
		for (int i = 0; i < list.size(); i++) {
			Double m = getDistance(lat, lon, Double.parseDouble(list.get(i).getLat()),
					Double.parseDouble(list.get(i).getLon()));
			if (m > 500) {
				// 如果直径距离大于500米则删除该摄像头数据
				list.remove(i);
				i--;
			} else {
				VideoDeviceListUM son = list.get(i);
				son.setM(m);
				list.set(i, son);
			}
		}
		return list;
	}

	/**
	 * 通过经纬度获取距离(单位：米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 距离
	 */
	public static double getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 分发推送给前端设备实时数据
	 * 
	 */
	@Override
	public void DeviceSendMsg(DeviceSendMsg deviceSendMsg) throws HKException, IOException {
		Gson gson = new Gson();
		Map<String, Object> connection;

		// 社区id等于空则不作处理
		if (deviceSendMsg.getCommunityId() == null)
			return;
		// 查社区数据库
		Map<String, Object> tableParams = deviceSendMsg.getTableParams();
		// 查出设备信息
		SelectDeviceOnMacUM deviceMsg = deviceMapper.selectDeviceOnMac(tableParams, deviceSendMsg.getMac(),
				deviceSendMsg.getModuleId());

		// 推送给前端的对象
		deviceSendMsg.setModule(deviceMsg.getModule());
		deviceSendMsg.setDevNum(deviceMsg.getDevNum());
		deviceSendMsg.setCode(deviceMsg.getCode());
		deviceSendMsg.setLat(deviceMsg.getLat());
		deviceSendMsg.setLon(deviceMsg.getLon());
		deviceSendMsg.setLevel(deviceMapper.selectAlarmLevel(deviceSendMsg.getAlarmid()));
		deviceSendMsg.setDevType(deviceMsg.getDevType());
		deviceSendMsg.setInstallTime(deviceMsg.getInstallTime());
		deviceSendMsg.setUUID(UUIDUtils.get32BitUUID());

		// 推送数据格式
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("communityId", deviceSendMsg.getCommunityId().toString());
		// {态势：C01，感知：C02}
		msg.put("category", "C02");
		msg.put("source", "0");

		if (deviceSendMsg.getIs_alarm() == 0) {// 心跳数据
			deviceSendMsg.setState("正常");
			msg.put("data", gson.toJson(deviceSendMsg));

			// 推送给消防感知实时数据
			msg.put("code", "fireFellMsg");
			connection = HttpPost(msg, communityUrl);
		} else if (deviceSendMsg.getIs_alarm() == 1) {// 报警数据
			deviceSendMsg.setState("异常");
			msg.put("data", gson.toJson(deviceSendMsg));

			// 判断是否消防报警
			Integer is_fireFellAlarm = deviceMapper.selectFireFellAlarm(tableParams, deviceSendMsg.getAlarmid());
			if (is_fireFellAlarm != 0) // 如果是消防报警就发送
			{
				// 推送给消防感知实时数据
				msg.put("code", "fireFellMsg");
				connection = HttpPost(msg, communityUrl);
			}

			// 报警数据推送给后台
			msg.put("source", "1");
			msg.put("code", "deviceFellMsg");
			connection = HttpPost(msg, communityUrl);

		}

		// 推送给设备感知实时数据
		msg.put("source", "0");
		msg.put("code", "deviceFellMsg");
		connection = HttpPost(msg, communityUrl);

		System.out.println(connection);
	}

	public Map<String, Object> HttpPost(Map<String, String> param, String urlString) {
		System.out.println(new Gson().toJson(param));
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		int state = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(urlString);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Consts.UTF_8);
				entity.setContentType("application/x-www-form-urlencoded");
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000)
						.build();// 设置请求和传输超时时间
				httpPost.setConfig(requestConfig);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			state = response.getStatusLine().getStatusCode();
			map.put("state", state);
			map.put("json", resultString);
		} catch (Exception e) {
			e.printStackTrace();
			throw this.exceptionManager.configLog(error).errorRecord("DEVE0009", e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw this.exceptionManager.configLog(error).errorRecord("DEVE0009", e);
			}
		}
		return map;
	}

	@Override
	public DeviceMacDataUM deviceCommunityId(String mac) throws HKException {
		return deviceMapper.deviceMac(mac);
	}

	@Override
	public PushMsg videoPush(Integer communityId, String mac) throws HKException {
		SelectVideoDeviceOnMacUM v = selectVideoDeviceOnMac(mac, communityId);
		Map<String, String> map = new HashMap<String, String>();
		if (v != null) {
			map.put("nvrUserName", v.getNvrUserName());
			map.put("nvrPassWord", v.getNvrPassWord());
			map.put("videoIp", v.getVideoIp());
			map.put("nvrIp", v.getNvrIp());
			map.put("port", v.getPort());
			map.put("channel", v.getChannel());
		}

		return new Gson().fromJson(HttpPost(map, videoUrl + "videoPush").get("json").toString(), PushMsg.class);
	}

	@Override
	public Map<String, String> videoClose(Integer communityId, String mac) throws HKException {
		SelectVideoDeviceOnMacUM v = selectVideoDeviceOnMac(mac, communityId);
		if (v == null)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("videoIp", v.getVideoIp());
		String json = HttpPost(map, videoUrl + "videoClose").get("json").toString();
		map.clear();
		map.put("code", json);
		return map;
	}

	@Override
	public PushMsg videoPushLog(Integer communityId, String mac, String startTime, String endTime) throws HKException {
		SelectVideoDeviceOnMacUM v = selectVideoDeviceOnMac(mac, communityId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("nvrUserName", v.getNvrUserName());
		map.put("nvrPassWord", v.getNvrPassWord());
		map.put("videoIp", v.getVideoIp());
		map.put("nvrIp", v.getNvrIp());
		map.put("port", v.getPort());
		map.put("channel", v.getChannel());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return new Gson().fromJson(HttpPost(map, videoUrl + "videoPushLog").get("json").toString(), PushMsg.class);
	}

	/**
	 * 后台首页设备情况第三个图
	 */
	@Override
	public List<SelectDeviceAlarmCount> selectDeviceAlarmCount(Integer communityId) throws HKException {
		Map<String, Object> tableParams = super.getTableParams(communityId);
		DecimalFormat df = new DecimalFormat("#0.00");
		List<SelectDeviceAlarmCount> selectDeviceAlarmCounts = deviceMapper.selectDeviceAlarmCount(tableParams);
		// 计算报警总数
		Integer count = 0;
		for (SelectDeviceAlarmCount s : selectDeviceAlarmCounts)
			count += s.getAlarmCount();
		// 如果报警总数等于0就不用计算百分比
		if (count == 0)
			return selectDeviceAlarmCounts;

		for (int i = 0; i < selectDeviceAlarmCounts.size(); i++) {
			SelectDeviceAlarmCount son = selectDeviceAlarmCounts.get(i);
			son.setPercent(Double.parseDouble(df.format(((double) son.getAlarmCount() / count) * 100)));
		}
		return selectDeviceAlarmCounts;
	}

	/**
	 * 设备离线报警
	 */
	@Transactional
	@Override
	public void deviceOffLineJob() {
		// 查询所有社区
		List<CommunityListAllUM> communityList = communityService.queryListCommunityAll();
		for (int i = 0; i < communityList.size(); i++) {
			// 查询社区数据库
			Map<String, Object> tableParams = super.getTableParams(communityList.get(i).getCid());
			// 查出超过规则设定时间的离线设备列表
			List<SelectDeviceOnMacUM> OffLineList = deviceMapper.selectOffLineDevice(tableParams);
			// 把全部的离线设备状态改为离线
			deviceMapper.updateOffLineDevice(tableParams);
			// 把离线的设备，报警信息逐个推送出去
			for (SelectDeviceOnMacUM s : OffLineList) {
				DeviceSendMsg deviceSendMsg = new DeviceSendMsg();
				deviceSendMsg.setCommunityId(communityList.get(i).getCid());
				deviceSendMsg.setMac(s.getMac());
				deviceSendMsg.setModuleId(s.getModuleId());
				deviceSendMsg.setModule(s.getModule());
				deviceSendMsg.setDevType(s.getDevType());
				deviceSendMsg.setDevNum(s.getDevNum());
				deviceSendMsg.setCode(s.getCode());
				deviceSendMsg.setLat(s.getLat());
				deviceSendMsg.setLon(s.getLon());
				deviceSendMsg.setInstallTime(s.getInstallTime());
				deviceSendMsg.setIs_alarm(1);
				deviceSendMsg.setAlarmName("离线报警");
				deviceSendMsg.setLevel(3);
				deviceSendMsg.setUUID(UUIDUtils.get32BitUUID());

				// 写入报警类数据
				AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
				alarmInsertPA.setCommunityId(communityList.get(i).getCid());
				alarmInsertPA.setSource(1);
				alarmInsertPA.setAlarmType(1);
				alarmInsertPA
						.setAlarmSourceType(DeviceAlarmState.getByName(deviceSendMsg.getModuleId() + "离线报警").getId());
				alarmInsertPA.setAlarmSource(deviceSendMsg.getMac());
				alarmInsertPA.setLib(tableParams);
				alarmInsertPA.setModuleId(deviceSendMsg.getModuleId());
				DeviceSendMsg d = deviceMapper.selectDeviceIdOnMac(tableParams, deviceSendMsg.getMac());
				alarmInsertPA.setAlarmSourceId(d.getId());
				Integer alarmid = alarmService.insertAlarmToMainDevice(alarmInsertPA);
				deviceSendMsg.setId(alarmid);

				deviceSendMsg.setOwnName(d.getOwnName());
				deviceSendMsg.setOwnPhone(d.getOwnPhone());

				Message msg = new Message();
				msg.setCommunityId(communityList.get(i).getCid() + "");
				msg.setSource("0");
				msg.setCategory("C02");
				msg.setCode("deviceFellMsg");
				msg.setData(deviceSendMsg);
				// 传给前台设备感知页面
				msgServiceImpl.sendMessage(msg);
				// 传给后台报警
				msg.setSource("1");
				msgServiceImpl.sendMessage(msg);
			}
		}
	}
}
