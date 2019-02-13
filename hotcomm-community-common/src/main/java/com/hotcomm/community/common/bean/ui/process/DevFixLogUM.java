package com.hotcomm.community.common.bean.ui.process;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevFixLogUM implements Serializable {
	
	private static final long serialVersionUID = -4183203483316184170L;
	private String description;
	private String repairTime;
	private String repairMan;
	private String result;
}
