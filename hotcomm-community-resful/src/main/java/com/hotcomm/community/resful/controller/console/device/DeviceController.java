package com.hotcomm.community.resful.controller.console.device;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotcomm.community.common.bean.pa.device.DeviceInstallPA;
import com.hotcomm.community.common.bean.pa.device.DevicePagePA;
import com.hotcomm.community.common.bean.pa.device.VideoDeviceInstallPA;
import com.hotcomm.community.common.bean.pa.process.DevAlarmLogPA;
import com.hotcomm.community.common.bean.pa.process.DevFixLogPA;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.Function;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@Autowired
	ProscessService alarmService;

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEPAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEPAGE_FUN)
	@LogEvent(code = "DEV00101")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "moduleId", code = "DEVP0001") })
	public ApiResult page(DevicePagePA devicePagePA) {
		return ApiResult.success(deviceService.devicePage(devicePagePA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEINSTALL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEINSTALL_FUN)
	@LogEvent(code = "DEV00102")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "moduleId", code = "DEVP0001"), @Param(key = "devType", code = "DEVP0002"),
			@Param(key = "devTradeMark", code = "DEVP0003"), @Param(key = "mac", code = "DEVP0004"),
			@Param(key = "buildingNum", code = "DEVP0005"), @Param(key = "devAddress", code = "DEVP0006"),
			@Param(key = "useType", code = "DEVP0007"), @Param(key = "lat", code = "DEVP0008"),
			@Param(key = "lon", code = "DEVP0009"), @Param(key = "installExplain", code = "DEVP00010"),
			@Param(key = "ownId", code = "DEVP00011"), @Param(key = "installUserid", code = "DEVP00012") })
	public ApiResult deviceInstall(DeviceInstallPA deviceInstallPA) {
		return ApiResult.success(deviceService.deviceInstall(deviceInstallPA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_UPDATEDEV }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_UPDATEDEV_FUN)
	@LogEvent(code = "DEV00103")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP00014") })
	public ApiResult updateDev(DeviceInstallPA deviceInstallPA) {
		return ApiResult.success(deviceService.updateDev(deviceInstallPA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTDEVICEONMAC }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTDEVICEONMAC_FUN)
	@LogEvent(code = "DEV00104")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP00014"), @Param(key = "moduleId", code = "DEVP0001") })
	public ApiResult selectDeviceOnMac(String mac, Integer communityId, Integer moduleId) {
		return ApiResult.success(deviceService.selectDeviceOnMac(mac, communityId, moduleId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTRULEONDEV }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTRULEONDEV_FUN)
	@LogEvent(code = "DEV00105")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "useType", code = "DEVP00015") })
	public ApiResult selectRuleOnDev(String mac, Integer communityId, Integer useType, Integer moduleId) {
		return ApiResult.success(deviceService.selectRuleOnDev(mac, communityId, useType, moduleId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEMAC }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_DEVICEMAC_FUN)
	@LogEvent(code = "DEV00106")
	@ParamsValidate(validateParams = { @Param(key = "moduleId", code = "DEVP0001"),
			@Param(key = "mac", code = "DEVP00014") })
	public ApiResult deviceMac(String mac, Integer moduleId) throws IOException {
		return ApiResult.success(deviceService.deviceMac(mac, moduleId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICERULE_SELECTRULEPAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICERULE_SELECTRULEPAGE_FUN)
	@LogEvent(code = "DEV00107")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectRulePage(Integer communityId) throws IOException {
		return ApiResult.success(deviceService.selectRulePage(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEGENERAL_SELECTDEVICESTATE }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEGENERAL_SELECTDEVICESTATE_FUN)
	@LogEvent(code = "DEV00108")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult selectDeviceState(Integer communityId) throws IOException {
		return ApiResult.success(deviceService.selectDeviceState(communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_UPDATESYSTEMRULE }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_UPDATESYSTEMRULE_FUN)
	@LogEvent(code = "DEV00109")
	public ApiResult updateSystemRule(@RequestBody String data) throws IOException {
		return ApiResult.success(deviceService.updateSystemRule(data));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_UPDATERULE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_UPDATERULE_FUN)
	@LogEvent(code = "DEV00110")
	public ApiResult updateRule(@RequestBody String data) throws IOException {
		return ApiResult.success(deviceService.updateRule(data));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICEINSTALL }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICEINSTALL_FUN)
	@LogEvent(code = "DEV00111")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "devType", code = "DEVP0002"), @Param(key = "devTradeMark", code = "DEVP0003"),
			@Param(key = "mac", code = "DEVP0004"), @Param(key = "buildingNum", code = "DEVP0005"),
			@Param(key = "devAddress", code = "DEVP0006"), @Param(key = "useType", code = "DEVP0007"),
			@Param(key = "lat", code = "DEVP0008"), @Param(key = "lon", code = "DEVP0009"),
			@Param(key = "installExplain", code = "DEVP00010"), @Param(key = "ownId", code = "DEVP00011"),
			@Param(key = "installUserid", code = "DEVP00012"), @Param(key = "devLocation", code = "DEVP00013"),
			@Param(key = "userName", code = "DEVP00016"), @Param(key = "passWord", code = "DEVP00017"),
			@Param(key = "videoIp", code = "DEVP00018"), @Param(key = "port", code = "DEVP00019"),
			@Param(key = "channel", code = "DEVP00020") })
	public ApiResult videodeviceInstall(VideoDeviceInstallPA deviceInstallPA) {
		return ApiResult.success(deviceService.videodeviceInstall(deviceInstallPA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICEPAGE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICEPAGE_FUN)
	@LogEvent(code = "DEV00112")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01") })
	public ApiResult videodevicePage(DevicePagePA devicePagePA) {
		return ApiResult.success(deviceService.videodevicePage(devicePagePA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_VIDEODEVICELIST }, method = { RequestMethod.POST })
	public ApiResult videodeviceList(String mac) {
		return ApiResult.success(deviceService.videodeviceList(mac));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTVIDEODEVICEONMAC }, method = {
			RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICEPARTICULARS_SELECTVIDEODEVICEONMAC_FUN)
	@LogEvent(code = "DEV00113")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP00014") })
	public ApiResult selectVideoDeviceOnMac(String mac, Integer communityId) {
		return ApiResult.success(deviceService.selectVideoDeviceOnMac(mac, communityId));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_UPDATEVIDEODEV }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_UPDATEVIDEODEV_FUN)
	@LogEvent(code = "DEV00114")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP00014") })
	public ApiResult updateVideoDev(VideoDeviceInstallPA deviceInstallPA) {
		return ApiResult.success(deviceService.updateVideoDev(deviceInstallPA));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_SELECTVIDEOONDEVICE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_SELECTVIDEOONDEVICE_FUN)
	@LogEvent(code = "DEV00116")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "lat", code = "DEVP00021"), @Param(key = "lon", code = "DEVP00022") })
	public ApiResult selectVideoOnDevice(Integer communityId, Double lat, Double lon) {
		return ApiResult.success(deviceService.selectVideoOnDevice(communityId, lat, lon));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_VIDEO_VIDEOPUSH }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_VIDEO_VIDEOPUSH_FUN)
	@LogEvent(code = "DEV00117")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult videoPush(Integer communityId, String mac) {
		return ApiResult.success(deviceService.videoPush(communityId, mac));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_VIDEO_VIDEOCLOSE }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_VIDEO_VIDEOCLOSE_FUN)
	@LogEvent(code = "DEV00118")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004") })
	public ApiResult videoClose(Integer communityId, String mac) {
		return ApiResult.success(deviceService.videoClose(communityId, mac));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_VIDEO_VIDEOPUSHLOG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_VIDEO_VIDEOPUSHLOG_FUN)
	@LogEvent(code = "DEV00119")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "mac", code = "DEVP0004"), @Param(key = "startTime", code = "DEVP00024"),
			@Param(key = "endTime", code = "DEVP00025") })
	public ApiResult videoPushLog(Integer communityId, String mac, String startTime, String endTime) {
		return ApiResult.success(deviceService.videoPushLog(communityId, mac, startTime, endTime));
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_GETDEVFIXLOG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_GETDEVFIXLOG_FUN)
	@LogEvent(code = "DEV00120")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "devId", code = "DEVP00026"), @Param(key = "moduleId", code = "DEVP0001") })
	public ApiResult getDevFixLog(DevFixLogPA pa) {
		return ApiResult.success(null);
	}

	@RequestMapping(value = { RestfulUrlRecord.DEVICE_DEVICELIST_GETDEVALARMLOG }, method = { RequestMethod.POST })
	@Function(key = RestfulUrlRecord.DEVICE_DEVICELIST_GETDEVALARMLOG_FUN)
	@LogEvent(code = "DEV00121")
	@ParamsValidate(validateParams = { @Param(key = "communityId", code = "CY_F01"),
			@Param(key = "devId", code = "DEVP00026"), @Param(key = "moduleId", code = "DEVP0001") })
	public ApiResult getDevAlarmLog(DevAlarmLogPA pa) {
		return ApiResult.success(alarmService.getDevAlarmLog(pa));
	}
}
