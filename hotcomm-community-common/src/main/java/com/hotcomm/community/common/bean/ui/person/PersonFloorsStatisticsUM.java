package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PersonFloorsStatisticsUM  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917011811952927903L;

	private Integer buildingId;
	
	private String buildingName;
	
	private Integer num;
}
