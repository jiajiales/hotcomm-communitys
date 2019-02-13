package com.hotcomm.community.common.bean.ui.process.worder;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderGkSourceUM implements Serializable {

	//本月工单来源分布
	/**	
	 * 
	 */
	private static final long serialVersionUID = -4393903310439699066L;

	//工单来源 1:报警 2:事件 3:设备 4:其他
	private String sourceType;
	
	//工单来源所占百分比
	private String percent;
	
	//根据来源类型统计
	private Integer countByType;
}
