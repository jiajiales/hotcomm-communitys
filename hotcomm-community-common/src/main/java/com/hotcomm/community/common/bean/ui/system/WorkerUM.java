package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorkerUM implements Serializable {

	private static final long serialVersionUID = 2478940915510137417L;

	private Integer workerId;

	private Integer roleId;

	private Integer cid;

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
