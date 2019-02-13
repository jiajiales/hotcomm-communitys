package com.hotcomm.community.common.service.device;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.device.DeviceAlarmGzDM;
import com.hotcomm.community.common.bean.db.parse.DeviceSendMsg;
import com.hotcomm.community.common.bean.pa.device.DeviceInstallPA;
import com.hotcomm.community.common.bean.pa.device.DevicePagePA;
import com.hotcomm.community.common.bean.pa.device.VideoDeviceInstallPA;
import com.hotcomm.community.common.bean.ui.device.DeviceMacDataUM;
import com.hotcomm.community.common.bean.ui.device.DeviceMsgWorkUM;
import com.hotcomm.community.common.bean.ui.device.DevicePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceAlarmCount;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceStateUM;
import com.hotcomm.community.common.bean.ui.device.SelectRulePageUM;
import com.hotcomm.community.common.bean.ui.device.SelectVideoDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.device.VideoDeviceListUM;
import com.hotcomm.community.common.bean.ui.device.VideoDevicePageUM;
import com.hotcomm.community.common.bean.ui.device.video.PushMsg;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.web.exception.HKException;

public interface DeviceService {
	/**
	 * 设备列表分页查询
	 * 
	 * @param devicePagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<DevicePageUM> devicePage(DevicePagePA devicePagePA) throws HKException;

	/**
	 * 安装设备
	 * 
	 * @param deviceInstallPA
	 * @return
	 * @throws HKException
	 */
	public Integer deviceInstall(DeviceInstallPA deviceInstallPA) throws HKException;

	/**
	 * 修改设备
	 * 
	 * @param deviceInstallPA
	 * @return
	 * @throws HKException
	 */
	public Integer updateDev(DeviceInstallPA deviceInstallPA) throws HKException;

	/**
	 * 根据mac查询设备信息
	 * 
	 * @param mac
	 * @param communityId
	 * @param module_id
	 * @return
	 * @throws HKException
	 */
	public SelectDeviceOnMacUM selectDeviceOnMac(String mac, Integer communityId, Integer module_id) throws HKException;

	/**
	 * 根据mac查询设备预警规则
	 * 
	 * @param mac
	 * @param communityId
	 * @param use_type
	 * @param module_id
	 * @return
	 * @throws HKException
	 */
	public List<DeviceAlarmGzDM> selectRuleOnDev(String mac, Integer communityId, Integer use_type, Integer module_id)
			throws HKException;

	/**
	 * 安装验证mac
	 * 
	 * @param mac
	 * @param module_id
	 * @return
	 * @throws HKException
	 * @throws IOException
	 */
	public DeviceMacDataUM deviceMac(String mac, Integer module_id) throws HKException, IOException;

	/**
	 * 预警方案列表
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public List<SelectRulePageUM> selectRulePage(Integer communityId) throws HKException;

	/**
	 * 设备状态，类型，报警分布
	 * 
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public SelectDeviceStateUM selectDeviceState(Integer communityId) throws HKException;

	/**
	 * 修改系统预警规则
	 * 
	 * @param data
	 * @return
	 * @throws HKException
	 */
	public Integer updateSystemRule(String data) throws HKException;

	/**
	 * 修改设备预警规则
	 * 
	 * @param data
	 * @return
	 * @throws HKException
	 */
	public Integer updateRule(String data) throws HKException;

	/**
	 * 创建工单的设备列表选择
	 * 
	 * @param moduleid
	 * @param mac
	 * @param isAlarm
	 * @return
	 * @throws HKException
	 */
	public List<DeviceMsgWorkUM> selectDeviceMsgWork(Integer communityId, Integer moduleid, String mac, Integer isAlarm)
			throws HKException;

	/**
	 * 安装视频设备
	 * 
	 * @param deviceInstallPA
	 * @return
	 * @throws HKException
	 */
	public Integer videodeviceInstall(VideoDeviceInstallPA deviceInstallPA) throws HKException;

	/**
	 * 视频设备列表分页查询
	 * 
	 * @param devicePagePA
	 * @return
	 * @throws HKException
	 */
	public PageInfo<VideoDevicePageUM> videodevicePage(DevicePagePA devicePagePA) throws HKException;

	/**
	 * 视频设备列表不分页查询
	 * 
	 * @param devicePagePA
	 * @return
	 * @throws HKException
	 */
	public List<VideoDeviceListUM> videodeviceList(String mac) throws HKException;

	/**
	 * 根据mac查询摄像头信息
	 * 
	 * @param mac
	 * @param communityId
	 * @return
	 * @throws HKException
	 */
	public SelectVideoDeviceOnMacUM selectVideoDeviceOnMac(String mac, Integer communityId) throws HKException;

	/**
	 * 修改摄像头设备
	 * 
	 * @param deviceInstallPA
	 * @return
	 * @throws HKException
	 */
	public Integer updateVideoDev(VideoDeviceInstallPA deviceInstallPA) throws HKException;

	/**
	 * 查询设备附近500米的摄像头
	 * 
	 * @param communityId
	 * @return
	 */
	public List<VideoDeviceListUM> selectVideoOnDevice(Integer communityId, Double lat, Double lon) throws HKException;

	/**
	 * 分发推送给前端设备实时数据
	 * 
	 * @param deviceSendMsg
	 * @throws HKException
	 */
	public void DeviceSendMsg(DeviceSendMsg deviceSendMsg) throws HKException, IOException;

	/**
	 * 根据Mac地址查询社区编号
	 * 
	 * @param mac
	 * @return
	 * @throws HKException
	 * @throws IOException
	 */
	public DeviceMacDataUM deviceCommunityId(String mac) throws HKException;

	/**
	 * 请求播放视频
	 * 
	 * @param mac
	 * @return
	 * @throws HKException
	 */
	public PushMsg videoPush(Integer communityId, String mac) throws HKException;

	/**
	 * 关闭视频播放
	 * 
	 * @param videoIp
	 * @return
	 * @throws HKException
	 */
	public Map<String, String> videoClose(Integer communityId, String mac) throws HKException;

	/**
	 * 获取回放播放流
	 * 
	 * @param communityId
	 * @param mac
	 * @return
	 * @throws HKException
	 */
	public Object videoPushLog(Integer communityId, String mac, String startTime, String endTime) throws HKException;

	/**
	 * 后台首页设备情况第三个图
	 * 
	 * @param communityId
	 * @return
	 */
	public List<SelectDeviceAlarmCount> selectDeviceAlarmCount(Integer communityId) throws HKException;

	/**
	 * 设备离线报警
	 */
	public void deviceOffLineJob();
}