package com.hotcomm.community.common.bean.db.parse;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceSendMsg implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2104507775741893168L;
	private Integer id;// 报警数据id
	private Integer communityId;// 社区id
	private String mac;// mac
	private Integer moduleId;// 模块id
	private String module;// 设备名称
	private String devType;// 设备型号/类型
	private String devNum;// 设备编号
	private String code;// 设备地址
	private String lat;// 纬度
	private String lon;// 经度
	private String installTime;// 安装时间
	private Integer is_alarm;// 0心跳1报警
	private Integer alarmid;// 报警id
	private String alarmName;// 报警名称
	private Integer level;// 报警级别
	private Map<String, Object> tableParams;// 数据库
	private String state;// 数据状态
	private String UUID;
	private String nowTime;// 当前时间
	private String ownName;// 责任人名字
	private String ownPhone; // 责任人电话

	public void setMac(String mac) {
		this.mac = mac;
		Date date = new Date(); // 原时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // 实例化SimpleDateFormat
		setNowTime(sdf.format(date));
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

}
