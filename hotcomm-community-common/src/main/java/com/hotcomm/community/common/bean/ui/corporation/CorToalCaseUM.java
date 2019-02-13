package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorToalCaseUM implements Serializable{
	
	private static final long serialVersionUID = 7458554851832756779L;
	
	private Integer corporationCount;
	private Integer attenCorCount;
	private Integer corPerCount;
	private Integer corCarCount;

}
