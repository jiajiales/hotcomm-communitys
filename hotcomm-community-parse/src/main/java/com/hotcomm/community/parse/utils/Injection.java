package com.hotcomm.community.parse.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotcomm.community.common.service.device.DeviceService;
import com.hotcomm.community.common.service.process.ProscessService;
import com.hotcomm.community.parse.service.ParseService;

//初始化mapper
@Component
public class Injection {

	@Autowired
	public ParseService parseService;

	@Autowired
	public ProscessService alarmService;

	@Autowired
	public DeviceService deviceService;

	public static Injection injection;

	@PostConstruct
	public void init() {
		injection = this;
	}

}
