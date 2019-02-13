package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmTrendUM implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3513259367093333529L;

	private Integer devCount;
	private Integer perCount;
	private Integer opiCount;
	private String dateInfo;
	
}
