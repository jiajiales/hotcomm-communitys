package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoleDM implements Serializable {

	private static final long serialVersionUID = 5490499216865407235L;

	private Integer id;

	private String desc;

	private String roleName;

	private Integer status;

}
