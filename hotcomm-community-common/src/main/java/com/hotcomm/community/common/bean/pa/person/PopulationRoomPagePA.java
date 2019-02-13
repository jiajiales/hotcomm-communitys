package com.hotcomm.community.common.bean.pa.person;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PopulationRoomPagePA extends PageParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188967033147484946L;

	/**
	 * 居民id
	 */
	private Integer pid;
	
	/**
	 * 楼栋id
	 */
	private Integer building;
	
	/**
	 * 楼层id
	 */
	private Integer floor;
	
	/**
	 * 关系
	 */
	private Integer reason;
	
	/**
	 * 模糊搜索内容
	 */
	private String content;
}
