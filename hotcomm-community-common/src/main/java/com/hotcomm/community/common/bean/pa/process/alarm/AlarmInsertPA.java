package com.hotcomm.community.common.bean.pa.process.alarm;

import java.io.Serializable;
import java.util.Map;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmInsertPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private Map<String, Object> lib;                //
	private Integer alarmType;                      //报警类型 1:设备 2:车辆 3:人员 4：其他
	private Integer alarmSourceType;                //报警源 类别ID 例如 人口标签编号,车辆标签编号,报警类型编号
	private Integer alarmSourceId;                  //设备编号|车辆编号|人员编号
	private String alarmSource;                     //报警源 人口名称|车牌号|设备mac
	private String alarmMessage;                    //报警内容 需要组装 根据来源类型
	private String alarmAddress;                    //报警位置
	private Integer alarmLevel;                     //报警等级
	private String handelUser;                      //
	private String timeNum;                         //第三方报警时间
	
	private String alarmValue;
	private String alarmNatureOfVehicle;
	private String alarmNatureOfPerson;
	private Integer moduleId;

	private String alarmImg;
	private String alarmMp3;
	private String alarmVideo;

	private Integer alarmID;

	private Map<String, Object> test;

	private String insertViewJson;

	private String createTime;
	private Integer createUser;
	private Integer updateUser;
	private Integer updateTime;
	private Integer userid;

	private Integer checkAlarmCount;
	private String lng;
	private String lat;
	private Integer lableId;

}
