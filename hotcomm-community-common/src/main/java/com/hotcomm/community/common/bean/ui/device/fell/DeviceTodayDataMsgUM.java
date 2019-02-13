package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceTodayDataMsgUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6712129108868794280L;
	private String moduleName;
	private Integer offLine = 0;
	private Integer normal = 0;
}
