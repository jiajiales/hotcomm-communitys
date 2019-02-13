package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;
import java.util.List;

import com.hotcomm.community.common.bean.db.person.PersonDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDetailDM;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonHoleInfoToOneUM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319029024639561441L;
	
	private PersonDM person;
	
	private String holeTitle;
	
	private Integer alarmLv;
	
	private List<PersonHoleDetailDM> detail;
	
	private String push;
	
}
