package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoleUserListUM implements Serializable {

	private static final long serialVersionUID = 6815450664023048995L;

	private String roleName;

	private List<UserListUM> userList;

}
