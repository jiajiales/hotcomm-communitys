package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class WatchPlacePagePA extends PageParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4884393574112699904L;
	
	private Integer ways;
	private Integer placeType;
	private Integer dangerLevel;
	private String content;
}
