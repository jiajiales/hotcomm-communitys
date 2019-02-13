package com.hotcomm.community.common.bean.db.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PushDM  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6901994476276656914L;

	private Integer p_id;
	
	private String p_name;
	
	private String message;

}
