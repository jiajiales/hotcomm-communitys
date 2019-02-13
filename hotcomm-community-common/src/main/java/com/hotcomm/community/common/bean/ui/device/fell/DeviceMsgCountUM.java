package com.hotcomm.community.common.bean.ui.device.fell;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMsgCountUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8772831464052502310L;
	private Integer moduleId;
	private String moduleName;
	private Integer devNum;
}
