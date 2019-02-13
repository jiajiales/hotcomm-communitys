package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserPwdPA implements Serializable {

	private static final long serialVersionUID = 555988081695291051L;

	private Integer userId;

	private String oldPassword;

	private String newPassword;

	private String newPassword2;

}
