package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonRoomPagePA extends PageParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3291206465507100283L;
	
	private Integer roomId;
	private Integer floorId;
	private Integer buildingId;
	private Integer pId;
	private Integer reason;
	private String content;

}
