package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserListUM implements Serializable {

	private static final long serialVersionUID = -1857106578764470973L;

	private Integer userId;

	private String username; // 此处为用户真实姓名

}
