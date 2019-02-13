package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserLoginPA implements Serializable {

	private static final long serialVersionUID = -4645275313739200998L;

	private String userName;

	private String password;

	//private Integer logintype; // 登陆类型（1:PC端登陆; 2:APP登陆）

	private String code; // 验证码

	private String uuid; // uuid（用于获取缓存中验证码的key）

}
