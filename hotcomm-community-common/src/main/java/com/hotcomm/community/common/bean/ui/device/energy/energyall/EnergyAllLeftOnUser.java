package com.hotcomm.community.common.bean.ui.device.energy.energyall;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EnergyAllLeftOnUser implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8277615228018747497L;
	private Integer business = 56;// 商业
	private Integer residence = 254;// 住宅
}
