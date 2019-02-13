package com.hotcomm.community.common.bean.pa.device.doorcontrol;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DevDoorPagePA extends PageParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String devNum;           //设备编号
	private Integer ownId;           //责任人
	private Integer state;           //设备状态 0:正常 1:离线 2:故障 3:报警
	private String startTime;        //安装时间
	private String endTime;          //安装时间
	private Integer moduleId;
}
