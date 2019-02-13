package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorTypeCountUM implements Serializable {
	
	private static final long serialVersionUID = 7810058173757740423L;
	
	private  String corType;
	private Integer corTypeCount;
}
