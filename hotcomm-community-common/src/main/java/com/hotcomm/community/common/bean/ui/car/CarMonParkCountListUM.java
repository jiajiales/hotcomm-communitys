package com.hotcomm.community.common.bean.ui.car;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CarMonParkCountListUM implements Serializable {
	
	private static final long serialVersionUID = -854738220185224957L;

	private List<CarMonParkCountUM> regCarCountList;
	private List<CarMonParkCountUM>  strCarCountList;
}
