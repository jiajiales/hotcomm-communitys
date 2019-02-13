package com.hotcomm.community.common.bean.ui.process.alarm;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmUserLngLatUM implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3513259367093333529L;

	private String roleName;
	private String userName;
	private String telephone;
	private String lng;
	private String lat;
	
}
