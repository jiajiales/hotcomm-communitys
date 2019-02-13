package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonLast50RecordUM  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4251426964971457711L;
	private Integer recordId;
	private String Imgs;
	private Integer pId;
	private String  pNo;
	private String pName;
	private String 	address;
	private Integer recordType;
	private String TypeName;
	private String recordTime;
	private Integer lableId;
	private String lableType;
	private String lableName;
}
