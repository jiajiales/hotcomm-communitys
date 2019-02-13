package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPostureTodayCountUM implements Serializable {
	
	private static final long serialVersionUID = -6228991743933840425L;
	
	public Integer enterCount;
	public Integer outCount;
	private Integer strCarTodayCount;
	private Integer regCarCount;
	private Integer balckCarTodayCount;
	
}
