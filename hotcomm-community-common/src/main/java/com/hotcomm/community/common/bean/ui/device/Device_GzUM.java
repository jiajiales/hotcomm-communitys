package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Device_GzUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3555071247734102583L;

	private Integer offline;
	private Integer battery;
	private Integer symax;
	private Integer symin;
	private Integer dlmax;
	private Integer dlmin;
	private List<Integer> userid;
	private List<String> picture;
	private List<String> video;
	private Integer time;
}
