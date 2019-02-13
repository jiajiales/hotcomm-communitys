package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class HolePushDM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1191403882406407903L;

	/**
	 * 接收人id
	 */
	private Integer p_id;
	
	/**
	 * 发送消息
	 */
	private String message;
	
	/**
	 * 接受人名称
	 */
	private String p_name;
}
