package com.hotcomm.community.api.test.query;

import com.hotcomm.community.api.test.query.test.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	CarApiTest.class, 
	DeviceApiTest.class, 
	HouseApiTest.class, 
	PersonApiTest.class, 
	ProcessApiTest.class, 
	SystemApiTest.class, 
	HomeApiTest.class })
public class TestRunning {

}
