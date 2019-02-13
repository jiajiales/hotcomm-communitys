package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class LableTagDM implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271962450852542396L;

	private Integer lableId;
	
	private String typeCode;
	
	private String typeName;
	
	private String lableName;
}
