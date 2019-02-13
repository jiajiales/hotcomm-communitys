package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmMainWorkListUM implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4348837902883155395L;

	private String name;
	private String value;

}
