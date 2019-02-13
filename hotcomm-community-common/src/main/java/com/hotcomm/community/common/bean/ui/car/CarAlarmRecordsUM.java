package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmRecordsUM implements Serializable {

	private static final long serialVersionUID = -2445809482997627956L;

	private Integer id;
	private String alarmContent;//报警内容：报警规则名称+车牌号
	private Integer alarmLeve;
	private String createTime;
	private String address;
	
	private String num;
	private String mac;
	private String photo;
	private Integer type;
	private String alarmName;
	private String color;
	private String brand;
	private String modelType;
	private String model;	
	private Integer alarmCount;
	private String firstImgPath;
}
