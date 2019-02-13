package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelTotalCountUM implements Serializable{

	private static final long serialVersionUID = 6730745937410004697L;
	
	private Integer regCarCount;//登记车辆
	private Integer comCarCount;//小区车辆
	private Integer corCarCount;//单位车辆
	private Integer otherRegCarCount;//其他登记车辆
	private Integer blacklistCarCount;//黑名单车辆
	private Integer strCarCount;//陌生车辆
	private Integer carRelDevCount;//车辆识别摄像机
}
