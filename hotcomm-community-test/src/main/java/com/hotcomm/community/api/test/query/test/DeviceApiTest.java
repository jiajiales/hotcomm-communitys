package com.hotcomm.community.api.test.query.test;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceApiTest extends CommunityTest {

	public static final Logger ROOT = LoggerFactory.getLogger(DeviceApiTest.class);

	@Test
	public void test() {
		String url = host + "/device/door/pageData";
		ROOT.info("门禁设备分页列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 6));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void doorAttr() {
		String url = host + "device/door/queryAttr";
		ROOT.info("门禁设备属性");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, ""));// 待填写
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void doorDetails() {
		String url = host + "device/door/detailsData";
		ROOT.info("门禁设备详情");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, ""));// 待填写
		HttpClientUtils.post(requestMap, url);
	}

	/** 消防态势 **/
	@Test
	public void fireAlarmToYear() {
		String url = host + "device/fireposture/fireAlarmToYear";
		ROOT.info("消防年度报警趋势");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireAlarmToMonth() {
		String url = host + "device/fireposture/fireAlarmToMonth";
		ROOT.info("消防月度报警趋势");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireAlaemDisposeMsg() {
		String url = host + "device/fireposture/fireAlaemDisposeMsg";
		ROOT.info("本月消防报警处理情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireDeviceYearMsg() {
		String url = host + "device/fireposture/fireDeviceYearMsg";
		ROOT.info("较去年设备概况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireDeviceAlarmMsgToMonth() {
		String url = host + "device/fireposture/fireDeviceAlarmMsgToMonth";
		ROOT.info("本月各类设备消防报警情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireAlarmMsgToMonth() {
		String url = host + "device/fireposture/fireAlarmMsgToMonth";
		ROOT.info("本月各种消防警报情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireAlarmType() {
		String url = host + "device/fireposture/fireAlarmType";
		ROOT.info("本月设备所有报警类型");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	/** 消防感知 **/
	@Test
	public void fireDeviceMap() {
		String url = host + "device/firefell/fireDeviceMap";
		ROOT.info("消防设备感知地图设备展示");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireThisWeekAlarm() {
		String url = host + "device/firefell/fireThisWeekAlarm";
		ROOT.info("本周消防设备警报情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireDeviceAlarmMsg() {
		String url = host + "device/firefell/fireDeviceAlarmMsg";
		ROOT.info("消防各类设备报警感知情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireAlarmMsg() {
		String url = host + "device/firefell/fireAlarmMsg";
		ROOT.info("今日各种消防警报情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void fireMsgCount() {
		String url = host + "device/firefell/fireMsgCount";
		ROOT.info("消防设备基础信息统计");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	/** 设备感知 **/
	@Test
	public void deviceMap() {
		String url = host + "device/devicefell/deviceMap";
		ROOT.info("设备感知地图设备展示");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void deviceThisWeekAlarm() {
		String url = host + "device/devicefell/deviceThisWeekAlarm";
		ROOT.info("本周设备警报情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void deviceStateMsg() {
		String url = host + "device/devicefell/deviceStateMsg";
		ROOT.info("设备在线感知情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void deviceTodayDataMsg() {
		String url = host + "device/devicefell/deviceTodayDataMsg";
		ROOT.info("今日数据回传感知情况");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void deviceMsgCount() {
		String url = host + "device/devicefell/deviceMsgCount";
		ROOT.info("设备基础信息统计");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	/** 视频模块 **/
/*	@Test
	public void videodeviceList() {
		String url = host + "device/deviceList/videodeviceList";
		ROOT.info("设备列表不分页查询");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.post(requestMap, url);
	}*/

	@Test
	public void selectVideoDeviceOnMac() {
		String url = host + "device/deviceParticulars/selectVideoDeviceOnMac";
		ROOT.info("根据mac查询摄像头设备信息");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, "3M05329PAKED676"));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void videodevicePage() {
		String url = host + "device/deviceList/videodevicePage";
		ROOT.info("视频设备列表分页");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		HttpClientUtils.post(requestMap, url);
	}

	/** 设备模块 **/
	@Test
	public void getDevFixLog() {
		String url = host + "device/deviceList/getDevFixLog";
		ROOT.info("维修记录");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("devId", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void getDevAlarmLog() {
		String url = host + "device/deviceList/getDevAlarmLog";
		ROOT.info("报警记录");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("devId", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectVideoOnDevice() {
		String url = host + "device/deviceList/selectVideoOnDevice";
		ROOT.info("查询设备附近500米的摄像头");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("lat", EntityEnum.TEXT, 22.941106));
		requestMap.add(new HotHttpEntity("lon", EntityEnum.TEXT, 113.400945));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectDeviceState() {
		String url = host + "device/deviceGeneral/selectDeviceState";
		ROOT.info("设备数统计,状态，类型，报警分布");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectRulePage() {
		String url = host + "device/deviceRule/selectRulePage";
		ROOT.info("设备预警规则列表");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectRuleOnDev() {
		String url = host + "device/deviceParticulars/selectRuleOnDev";
		ROOT.info("查询单个设备或模块预警规则");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, "0000000022000005"));
		requestMap.add(new HotHttpEntity("useType", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectRuleOnDevSystem() {
		String url = host + "device/deviceParticulars/selectRuleOnDev";
		ROOT.info("查询单个设备或模块预警规则");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("useType", EntityEnum.TEXT, 0));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void selectDeviceOnMac() {
		String url = host + "device/deviceParticulars/selectDeviceOnMac";
		ROOT.info("根据mac查询设备信息");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, "0000000022000005"));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		HttpClientUtils.post(requestMap, url);
	}

	@Test
	public void devicePage() {
		String url = host + "device/deviceList/devicePage";
		ROOT.info("设备列表分页");
		List<HotHttpEntity> requestMap = new ArrayList<>();
		requestMap.add(new HotHttpEntity("communityId", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("moduleId", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 10));
		HttpClientUtils.post(requestMap, url);
	}
}
