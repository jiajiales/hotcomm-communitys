package com.hotcomm.community.api.test.add;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hotcomm.community.common.bean.pa.device.DeviceInstallPA;
import com.hotcomm.community.common.bean.pa.process.alarm.AlarmInsertPA;
import com.hotcomm.community.common.bean.ui.device.SelectDeviceOnMacUM;
import com.hotcomm.community.common.bean.ui.house.BuildingListUM;
import com.hotcomm.community.common.bean.ui.house.FloorsListUM;
import com.hotcomm.community.common.bean.ui.house.UnitUM;
import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.house.BuildingService;
import com.hotcomm.community.common.service.house.FloorsService;
import com.hotcomm.community.common.service.house.UnitService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.resful.CommunityRunner;
import com.hotcomm.framework.web.exception.HKException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { CommunityRunner.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class DeviceApiTest extends CommunityTest {

	@Autowired
	DeviceService deviceService;

	@Autowired
	BuildingService buildingService;

	@Autowired
	FloorsService floorsService;

	@Autowired
	UnitService unitService;

	@Autowired
	ProscessService alarmService;

	// 新增设备
	@Test
	public void addDevice() throws HKException {
		Integer[] moduleids = { 1, 2, 3, 4 };
		String[] macs = { "00000000220002a6", "00000000220002a5", "00000000220002a4", "00000000220002a3",
				"0000000029000055", "0000000029000056", "0000000029000057", "0000000029000058", "000000004500000d",
				"000000004500000e", "000000004500000f", "000000004500000h", "0000000040000001", "0000000040000002",
				"0000000040000003", "0000000040000004" };
		String[][] yx = { { "113.749319", "22.9954" }, { "113.749942", "22.995237" }, { "113.749867", "22.995307" },
				{ "113.749657", "22.994872" }, { "113.751347", "22.994813" }, { "113.751605", "22.995188" },
				{ "113.749958", "22.995687" }, { "113.750961", "22.995534" }, { "113.748745", "22.994067" },
				{ "113.750843", "22.994289" }, { "113.750204", "22.994408" }, { "113.749293", "22.994437" },
				{ "113.749904", "22.993371" }, { "113.749942", "22.994047" }, { "113.748922", "22.993287" },
				{ "113.750317", "22.993795" } };
		// 楼栋列表<下拉菜单>
		List<BuildingListUM> buildingListUMs = buildingService.getBuildingList(communityId);
		Integer num = 0;
		for (int i = 0; i < moduleids.length; i++) {
			for (int j = 0; j < 4; j++) {
				DeviceInstallPA d = new DeviceInstallPA();
				d.setVideoList("3M05329PAKED676,4H05231PAJ554A3");
				d.setModuleId(moduleids[i]);
				String devType = "";
				switch (moduleids[i]) {
				case 1:
					devType = "智能烟感";
					break;
				case 2:
					devType = "智能可燃气";
					break;
				case 3:
					devType = "智能消防栓水压";
					break;
				case 4:
					devType = "智能剩余电流";
					break;
				default:
					break;
				}
				d.setDevType(devType);
				d.setDevTradeMark("康佳");
				d.setMac(macs[num]);
				Integer buildingRandom = new Random().nextInt(buildingListUMs.size()) % (buildingListUMs.size() - 0 + 1)
						+ 0;
				d.setBuildingNum(buildingListUMs.get(buildingRandom).getId());

				List<UnitUM> unitNum = unitService.getDataList(buildingListUMs.get(buildingRandom).getId(),
						communityId);
				Integer unitRandom = new Random().nextInt(unitNum.size()) % (unitNum.size() - 0 + 1) + 0;
				d.setUnitNum(unitNum.get(unitRandom).getId());

				List<FloorsListUM> floorNum = floorsService.getFloorsList(buildingListUMs.get(buildingRandom).getId(),
						communityId);
				Integer floorRandom = new Random().nextInt(floorNum.size()) % (floorNum.size() - 0 + 1) + 0;
				d.setFloorNum(floorNum.get(floorRandom).getId());
				d.setDevAddress("广东航康信息科技有限公司" + devType + "项目");
				d.setUseType("1");
				d.setLat(yx[num][1]);
				d.setLon(yx[num][0]);
				d.setInstallExplain("安装好了");
				d.setOwnId(1);
				d.setInstallUserid(1);
				d.setCommunityId(communityId);
				d.setSource(1);
				deviceService.deviceInstall(d);
				num++;
			}
		}
	}

	@Test
	public void addAlarm() {
		// 循环10次随机报警数据插入
		for (int k = 0; k < 10; k++) {
			String[] macs = { "00000000220002a6", "00000000220002a5", "00000000220002a4", "00000000220002a3",
					"0000000029000055", "0000000029000056", "0000000029000057", "0000000029000058", "000000004500000d",
					"000000004500000e", "000000004500000f", "000000004500000h", "0000000040000001", "0000000040000002",
					"0000000040000003", "0000000040000004" };
			Integer[][] alarmIdNum = { { 1, 3 }, { 2, 5 }, { 3, 5 }, { 4, 2 } };
			Integer alarmNum = 1;
			for (int i = 0; i < alarmIdNum.length; i++) {
				for (int j = 1; j <= alarmIdNum[i][1]; j++) {
					AlarmInsertPA alarmInsertPA = new AlarmInsertPA();
					alarmInsertPA.setCommunityId(communityId);
					alarmInsertPA.setSource(1);
					alarmInsertPA.setAlarmType(1);
					alarmInsertPA.setAlarmSourceType(alarmNum);
					Integer macId = new Random().nextInt((i + 1) * 4) % (((i + 1) * 4) - (i * 4)) + (i * 4);
					alarmInsertPA.setAlarmSource(macs[macId]);
					alarmInsertPA.setModuleId(alarmIdNum[i][0]);
					SelectDeviceOnMacUM s = deviceService.selectDeviceOnMac(macs[macId], communityId, alarmIdNum[i][0]);
					alarmInsertPA.setAlarmSourceId(s.getId());
					switch (alarmIdNum[i][0]) {
					case 3:
						Integer syValue = new Random().nextInt(10) % (10 - 0 + 1) + 0;
						alarmInsertPA.setAlarmValue(syValue + "");
						break;
					case 4:
						Integer sydlValue = new Random().nextInt(120) % (120 - 10 + 1) + 0;
						alarmInsertPA.setAlarmValue(sydlValue + "");
						break;
					default:
						break;
					}
					alarmNum++;
					alarmService.insertAlarmToMainDevice(alarmInsertPA);
				}
			}
		}
	}
}
