package com.hotcomm.community.common.bean.pa.device;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevicePagePA extends PageParams implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1854054332498116215L;
	private String devNum;
	private Integer ownId;
	private Integer state;
	private String startTime;
	private String endTime;
	private Integer moduleId;
	private Integer devType;
}
