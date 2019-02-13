package com.hotcomm.community.common.bean.pa.device.doorcontrol;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DoorRecordPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//通行记录ID
	private Integer pId;
	private String name;
	private String recordTime;
	private String recordAddress;
	private Integer lableId;
	private String imgs;
	private String video;
	private Integer recordType;
	private String openStyleName;//开门方式
	private String deviceCode;
	private Integer doorduLogId;
	
	private Integer sex;//性别 1男2女
	private Integer age;//年龄
	private String headimg;//用户头像
	private String lon;//经度
	private String lat;//维度
}
