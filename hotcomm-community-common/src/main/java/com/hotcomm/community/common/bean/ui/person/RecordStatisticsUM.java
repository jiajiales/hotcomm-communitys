package com.hotcomm.community.common.bean.ui.person;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class RecordStatisticsUM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2823566521235951800L;

	/**
	 * 人数
	 */
	private Integer num;
	
	/**
	 * 次数
	 */
	private Integer time;
}
