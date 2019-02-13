package com.hotcomm.community.common.bean.pa.house;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UnitPA extends CommunityParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5370317599351341974L;
	
	private Integer id;                //
	private Integer buildingId;        //
	private String unitNo;            //单元号
	private String unitName;           //单元名称
	private String createUser;         //

}
