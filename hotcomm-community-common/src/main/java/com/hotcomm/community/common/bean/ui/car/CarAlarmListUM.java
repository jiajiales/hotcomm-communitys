package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarAlarmListUM implements Serializable {
	
	private static final long serialVersionUID = 2584114184268520682L;
	private Integer id;
	private String photo;
	private String num;
	private String address;
	private Integer alaemLeve;
	private String alarmName;
}
