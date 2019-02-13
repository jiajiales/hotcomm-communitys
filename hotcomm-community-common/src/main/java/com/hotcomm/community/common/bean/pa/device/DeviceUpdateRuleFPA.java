package com.hotcomm.community.common.bean.pa.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceUpdateRuleFPA implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7494431050703294905L;
	private Integer communityId;
	private List<DeviceUpdateRulePA> data;
	private Integer moduleId;// 模块id
	private String mac;// mac地址
	private String innerUserid;// 相关人员
	private String informUserid;// 通知人员
	private Integer alarmMiddle;// 间隔推送时间
	private Integer source;
	private String token;
}
