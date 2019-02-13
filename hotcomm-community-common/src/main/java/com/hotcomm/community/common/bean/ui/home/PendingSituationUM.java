package com.hotcomm.community.common.bean.ui.home;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PendingSituationUM implements Serializable {

	private static final long serialVersionUID = -748662637193725646L;

	// 待处理工单(总数|百分比)
	private Integer orderNum;
	private Integer orderNumPercent;

	// 待处理上报警情(总数|百分比)
	private Integer reportAlertNum;
	private Integer reportAlertNumPercent;

	// 待处理人口报警(总数|百分比)
	private Integer populationAlertNum;
	private Integer populationAlertPercent;

	// 待处理设备报警(总数|百分比)
	private Integer deviceAlertNum;
	private Integer deviceAlertPercent;

	// 待处理车辆报警(总数|百分比)
	private Integer carAlertNum;
	private Integer carAlertPercent;

}
