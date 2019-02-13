package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarLabelRelationUM implements Serializable {
	
	private static final long serialVersionUID = 6678437914733822365L;

	private Integer id;
	private String carNum;
	private Integer labelId;
}
