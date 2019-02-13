package com.hotcomm.community.common.bean.db.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireDeviceYearMsgDM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1945639396351918433L;
	private String moduleName;
	private Integer lastYearNum;
	private Integer nowYearNum;
	private Integer moduleId;
}
