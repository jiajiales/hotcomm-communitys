package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceThisWeekAlarmUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 950128888058968516L;
	private Integer id;
	private Integer moduleId;
	private String moduleName;
	private String devAddress;
	private String level;
	private String stateName;
}
