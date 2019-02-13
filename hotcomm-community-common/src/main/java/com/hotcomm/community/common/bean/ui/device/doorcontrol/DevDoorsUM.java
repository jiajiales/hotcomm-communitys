package com.hotcomm.community.common.bean.ui.device.doorcontrol;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DevDoorsUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String devNum;                         //设备编号
	private String mac;
	private String localType;                      // 门禁位置类型：0:单元机 1:围墙机
	private String useType;                        // 应用场景 0通行 1消防 2监控 3其他
	private String devAddress;                     //设备地址
	private Integer ownId;                         //责任人ID
	private String owner;                          //责任人
	private String installState;                   //安装：1，未安装：0
	private String installTime;                    //安装时间
	private String state;                          //设备状态 0:正常 1:离线 2:故障 3:报警
	private Integer videoSize;                    //关联视频数
	private String lat;
	private String lon;
	private Double x;
	private Double y;
}
