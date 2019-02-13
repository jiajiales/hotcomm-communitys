package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NoticeDM implements Serializable {

	private static final long serialVersionUID = 8589424371124273536L;

	/**
	 * id
	 */
	private int id;

	/**
	 * 社区id
	 */
	private int communityid;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * create_user
	 */
	private String createUser;

	/**
	 * create_time
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 更改人
	 */
	private String updateUser;

}
