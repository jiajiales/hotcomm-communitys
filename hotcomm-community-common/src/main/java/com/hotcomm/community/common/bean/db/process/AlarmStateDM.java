package com.hotcomm.community.common.bean.db.process;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmStateDM implements Serializable{

	private static final long serialVersionUID = -2053231418925581224L;

	private Integer id;
	private String stateName;
	private Integer moduleId;
	private Integer isDelete;
	private String addTime;
	private Integer type;
	private Integer level;
	private String stateType;
	private Integer matchValue;
	
}
