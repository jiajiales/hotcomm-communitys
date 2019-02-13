package com.hotcomm.community.common.bean.pa.device.doorcontrol;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DoorcontrolDevicedata {
	
	
	
	public DoorcontrolDevicedata() {}
	
	public DoorcontrolDevicedata(Integer id, String mac, String devNum, String devType, String devTrademark,
			Integer state, Integer moduleId, String devAddress, Integer useType, Integer doorcontrolAttrId) {
		super();
		this.id = id;
		this.mac = mac;
		this.devNum = devNum;
		this.devType = devType;
		this.devTrademark = devTrademark;
		this.state = state;
		this.moduleId = moduleId;
		this.devAddress = devAddress;
		this.useType = useType;
		this.doorcontrolAttrId = doorcontrolAttrId;
	}
												//
	private Integer id;                     	//
	private String mac;                  		//mac--->guid
	private String devNum;						//设备编号
	private String devType="智能门禁";         	//
	private String devTrademark;				//品牌
	private Integer state;						//设备状态 0:正常 1:离线 2:故障 3:报警
	private Integer moduleId;					//模块ID
	private String devAddress;					//设备地址
	private Integer useType;					//设备应用场景 0通行 1消防 2监控 3其他
	private Integer doorcontrolAttrId;			//关联属性ID
}                                              
                                              