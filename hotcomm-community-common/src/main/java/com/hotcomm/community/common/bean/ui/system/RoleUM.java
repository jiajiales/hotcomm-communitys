package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoleUM implements Serializable {

	private static final long serialVersionUID = -7977169428504970897L;

	private Integer roleId;

	private String roleCode;

	private String description;

	private String roleName;

	private Integer status;

	private Integer level;

}
