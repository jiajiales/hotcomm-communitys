package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserPA implements Serializable {

	private static final long serialVersionUID = -3196564545013846839L;

	private Integer userId;

	private String uuid;

	private String cids;

	private Integer roleId;

	private String userName;

	private String password;

	private Integer status;

	private String email;

	private String realName;

	private String telephone;

	private String remark;

	private String createUser;

	private String updateUser;

}
