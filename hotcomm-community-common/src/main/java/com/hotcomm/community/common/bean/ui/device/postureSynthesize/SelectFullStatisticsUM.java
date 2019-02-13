package com.hotcomm.community.common.bean.ui.device.postureSynthesize;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectFullStatisticsUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 904998166632969635L;
	private Integer num;
	private Integer offLine;
	private Integer alarm;
}
