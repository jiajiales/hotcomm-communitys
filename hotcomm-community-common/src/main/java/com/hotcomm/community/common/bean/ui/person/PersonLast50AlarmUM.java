package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonLast50AlarmUM implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6658116711885084585L;
	private Integer alarmId;
	private String Imgs;
	private Integer pId;
	private String pNo;
	private String Name;
	private String address;
	private Integer level;
	private String reason;
}
