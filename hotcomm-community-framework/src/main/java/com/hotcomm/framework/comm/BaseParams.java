package com.hotcomm.framework.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BaseParams {
	
	private Integer source; //0:大数据，1：后台
	
}
