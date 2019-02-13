package com.hotcomm.community.api.test.add;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hotcomm.community.accesscontrol.mapper.DoorcontrolAttributeMapper;
import com.hotcomm.community.accesscontrol.mapper.DoorcontrolMapper;
import com.hotcomm.community.common.bean.db.device.doorcontrol.DoorcontrolAttributeDM;
import com.hotcomm.community.common.bean.pa.device.doorcontrol.DoorcontrolDevicedata;
import com.hotcomm.community.common.bean.ui.system.CommunityUM;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.resful.CommunityRunner;
import com.hotcomm.framework.comm.ApplicationEnvironment;
import com.hotcomm.framework.utils.SpringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CommunityRunner.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class DoorDevServiceTest extends CommunityTest{
	@Autowired
	DoorcontrolAttributeMapper attributeMapper;
	@Autowired
	DoorcontrolMapper doorcontrolMapper;
	
	@Test
	public void addDoorDev(){
		//添加门禁属性
		Map<String, Object> tableParams=new HashMap<String, Object>();
		CommunityService communityService = SpringUtil.getBean(CommunityService.class);
		CommunityUM communityUM = communityService.getCommunity(communityId);
		tableParams.put("base_dbname", SpringUtil.getBean(ApplicationEnvironment.class).baseDbName);
		tableParams.put("dynamic_dbname", communityUM.getDatabaseNo());
		List<DoorcontrolAttributeDM> params=new ArrayList<DoorcontrolAttributeDM>();
		params.add(new DoorcontrolAttributeDM(null,1,true,true,true,1,null,"DMJ6001812-01740",0));
		params.add(new DoorcontrolAttributeDM(null,1,true,true,true,1,null,"DMJ6001812-01750",0));
		attributeMapper.insertBatch(tableParams, params);
		//新增门禁设备
		List<DoorcontrolDevicedata> param=new ArrayList<>();
		param.add(new DoorcontrolDevicedata(null,"DMJ6001812-01740","MJ-DMJ6001812-01740","智能门禁","康佳",0,6,null,0,params.get(0).getId()));
		param.add(new DoorcontrolDevicedata(null,"DMJ6001812-01750","MJ-DMJ6001812-01750","智能门禁","康佳",0,6,null,0,params.get(1).getId()));
		doorcontrolMapper.insertBatch(tableParams, param);
	}
}
