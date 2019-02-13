package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorkerPA implements Serializable {

	private static final long serialVersionUID = 2321807354981212029L;

	private Integer workerId;

	private Integer cid;

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
