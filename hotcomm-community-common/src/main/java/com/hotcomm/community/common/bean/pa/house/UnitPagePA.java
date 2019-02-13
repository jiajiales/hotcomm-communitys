package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UnitPagePA extends PageParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5427969836320593768L;
	
	private Integer buildingId;            //楼栋ID
	private String startTime;		       //创建时间
	private String endTime;                //创建时间
	private String content;                //关键字

}
