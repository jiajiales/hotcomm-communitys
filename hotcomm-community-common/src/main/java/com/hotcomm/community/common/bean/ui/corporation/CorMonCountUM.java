package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorMonCountUM implements Serializable {
	
	private static final long serialVersionUID = -6608870311373662276L;
	
	private Integer  mon;
	private Integer count;
}
