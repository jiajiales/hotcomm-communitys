package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.annotation.FieldValidate;
import com.hotcomm.framework.annotation.ParamType;
import com.hotcomm.framework.comm.ParamsValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ResourcePA implements ParamsValidate, Serializable {

	private static final long serialVersionUID = 1379629702688525758L;

	private Integer resId;

	private String icon;

	@FieldValidate(limit = "2,200", type = ParamType.CUSTOM, pattern = "^[A-Za-z0-9-/]+$")
	private String path;

	@FieldValidate(limit = "2,100", type = ParamType.CUSTOM, pattern = "^[A-Za-z0-9-]+$")
	private String key;

	@FieldValidate(limit = "2,50", type = ParamType.STRING)
	private String name;

	private Integer type;

	private Integer status;

	private Integer pid;

	@FieldValidate(limit = "0,10", type = ParamType.NUMBER)
	private Integer weight;

}
