package com.hotcomm.framework.comm;

import java.util.List;
import java.util.Map;

import com.hotcomm.framework.comm.Constant.DevModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LoginUser {
	
	private Integer userId;
	private String userName;
	private String realName;
	private String roleCode;
	private List<Map<String, Object>> resources;
	private List<Map<String, Object>> communityUsers;
	private Constant.DevModel devModel;// 1 非安全模式 2 安全模式

	public LoginUser(Integer userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public LoginUser(Integer userId, String userName, DevModel devModel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.devModel = devModel;
	}
	
	
}
