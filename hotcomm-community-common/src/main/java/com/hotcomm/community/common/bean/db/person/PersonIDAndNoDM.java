package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonIDAndNoDM implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3680527914647573296L;

	private Integer pId;
	
	private String pNo;
	
	private Integer lableId;
	
	private String lableName;
}
