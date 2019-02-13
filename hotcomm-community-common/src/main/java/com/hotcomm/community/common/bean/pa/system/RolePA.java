package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

//import com.hotcomm.framework.annotation.FieldValidate;
//import com.hotcomm.framework.annotation.ParamType;
import com.hotcomm.framework.comm.ParamsValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RolePA implements ParamsValidate, Serializable {

	private static final long serialVersionUID = 5322218927470947622L;

	private Integer roleId;

	private String roleCode;

	//@FieldValidate(limit = "1,30", type = ParamType.STRING)
	private String desc;

	//@FieldValidate(limit = "1,30", type = ParamType.STRING)
	private String roleName;

	private Integer status;

	private Integer level;

	private String resourceIds;

}
