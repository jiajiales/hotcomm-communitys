package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RecordNumDM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6493600118054327731L;

	private Integer pId;
	
	private String time;
	
	private Integer nums;
}
