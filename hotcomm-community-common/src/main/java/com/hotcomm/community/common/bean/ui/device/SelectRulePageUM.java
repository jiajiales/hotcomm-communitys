package com.hotcomm.community.common.bean.ui.device;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SelectRulePageUM implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 203777099667621621L;
	private Integer moduleId;
	private String moduleName;
	private Integer gzNum;
	private String innerUserid;
	private String informUserid;
	private List<String> innerUseridMap;
	private List<String> informUseridMap;
}
