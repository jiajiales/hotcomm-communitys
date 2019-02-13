package com.hotcomm.community.common.bean.ui.device.posture;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FireAlaemDisposeMsgUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8547695677648650061L;
	private Integer untreated;// 未处理
	private Integer processedIng;// 处理中
	private Integer processed;// 已处理
	private double untreatedPercent = 0;// 未处理百分比
	private double processedIngPercent = 0;// 处理中百分比
	private double processedPercent = 0;// 已处理百分比
}
