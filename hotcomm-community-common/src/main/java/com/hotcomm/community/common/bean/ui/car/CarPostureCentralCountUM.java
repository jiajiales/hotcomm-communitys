package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarPostureCentralCountUM implements Serializable {

	private static final long serialVersionUID = -522378727209580623L;
	
	private Integer strCarTodayCount;
	private Integer regCarCount;
	private Integer balckCarTodayCount;
}
