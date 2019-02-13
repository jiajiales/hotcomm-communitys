package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DeviceMacDataUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8232884918081164453L;
	private Integer communityId;//社区标号ID
	private String deviceCode;// mac地址
	private String deviceType;// 设备类型
	private String funType;// 设备功能类型
	private String deviceBrand;// 设备品牌
	private String deviceTypeCode;// 设备code；设备类型唯一标识
}
