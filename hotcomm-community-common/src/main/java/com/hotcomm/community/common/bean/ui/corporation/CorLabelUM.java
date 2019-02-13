package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorLabelUM implements Serializable {
	
	private static final long serialVersionUID = 2155577138472401823L;
	
	private Integer id;
	private String  typeName;
	private Integer labelTypeId;
	private String labelName;
	private String describes;
	private String labelSource;
	private String  createTime;
	private String  createUser;
	private Integer state;
	private Boolean stateBoolean;
}
