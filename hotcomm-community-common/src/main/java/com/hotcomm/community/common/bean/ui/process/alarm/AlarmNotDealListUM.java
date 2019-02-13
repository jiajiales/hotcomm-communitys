package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmNotDealListUM implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4348837902883155395L;

	private String Address;
	private String handleUser;
	private String Title;
	private String reportTime;
}
