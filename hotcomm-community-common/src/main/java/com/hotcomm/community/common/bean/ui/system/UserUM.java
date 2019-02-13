package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserUM implements Serializable {

	private static final long serialVersionUID = -7136979822277014918L;

	private Integer userId;

	private Integer roleId;

	private String cids;

	private String userName;

	private String roleName;

	private Integer status;

	private String email;

	private String realName;

	private String telephone;

	private Date createTime;

	private Date updateTime;

	private String createUser;

	private String remark;

}
