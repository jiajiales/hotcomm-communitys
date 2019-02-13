package com.hotcomm.community.common.bean.ui.home;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class KPIUM implements Serializable {

	private static final long serialVersionUID = 7381587377361989494L;

	private Integer totalPopulation; // 实有人口数

	private Integer buildingNum; // 房屋楼栋数

	private Integer regCarCount; // 实有车辆数

	private Integer totalDevNum; // 设备总数

	private Integer corporationCount; // 单位总数

	private Integer alarmNum; // 报警数

	private Integer orderNum; // 工单总数

}
