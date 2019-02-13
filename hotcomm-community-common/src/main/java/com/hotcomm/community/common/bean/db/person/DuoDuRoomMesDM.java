package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DuoDuRoomMesDM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6844975842108508561L;

	/**
	 * 多度房间id
	 */
	private Integer duRoomId;
	
	/**
	 * 多度单元id
	 */
	private Integer duUnitId;
}
