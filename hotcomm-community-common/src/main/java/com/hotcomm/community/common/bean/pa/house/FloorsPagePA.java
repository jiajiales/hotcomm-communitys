package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FloorsPagePA extends PageParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8246297091663855466L;
	
	private Integer buildingId;            //楼栋ID
	private Integer unitId;                //单元ID
	private Integer attribute;             //楼层属性
	private String content;                //关键字

}
