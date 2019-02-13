package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarFeelPassWeekCountUM implements Serializable {
	
	private static final long serialVersionUID = 4122533715883055875L;
	
	private Integer carTotalCount;//车辆数
	private Integer  carTotalPassCount;//车辆通行次数
	private List<CarPassRecordsUM>  carPassRecords;

}
