package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FloorsListUM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5509527232693535752L;
	
	private Integer id;
	private String floorName;;

}
