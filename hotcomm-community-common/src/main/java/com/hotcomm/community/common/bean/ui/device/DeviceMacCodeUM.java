package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMacCodeUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7677458561037429393L;

	private String code;
	private DeviceMacDataUM data;
}
