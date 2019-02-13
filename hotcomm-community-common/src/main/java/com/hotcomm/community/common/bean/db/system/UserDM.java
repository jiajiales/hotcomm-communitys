package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDM implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String password;

	private String userName;

	private Integer status;

	private String email;

	private String realName;

	private String telephone;

	private Date createTime;

	private Date updateTime;

	private String createUser;

	private Integer userType;

	private String remark;

	private Integer isDelete;

	public UserDM(String userName, String password, String telephone) {
		super();
		this.userName = userName;
		this.password = password;
		this.telephone = telephone;
	}

}
