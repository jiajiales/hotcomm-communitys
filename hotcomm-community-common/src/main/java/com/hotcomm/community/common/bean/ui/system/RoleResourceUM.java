package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoleResourceUM implements Serializable {

	private static final long serialVersionUID = -4642133351963630607L;

	private Integer roleId;

	private String resourceIds;

}
