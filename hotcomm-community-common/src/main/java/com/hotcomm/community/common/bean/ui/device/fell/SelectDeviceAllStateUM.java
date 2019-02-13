package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectDeviceAllStateUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6585864392493763571L;
	private String module_name;
	private Integer all;
	private Integer normal;
	private Integer offLine;
	private Integer breakdown;
	private Integer alarm;
}
