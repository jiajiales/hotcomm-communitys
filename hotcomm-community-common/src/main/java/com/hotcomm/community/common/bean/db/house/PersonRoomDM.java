package com.hotcomm.community.common.bean.db.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonRoomDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6342840105524109067L;

	private Integer pId;
	private Integer roomId;
	private Integer reason;
	private String remark;

}
