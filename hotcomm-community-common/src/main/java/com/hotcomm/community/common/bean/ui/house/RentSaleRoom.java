package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RentSaleRoom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4037443929278391817L;

	private String rentOrSale;
	private Integer roomNum;
	private Double ratio;
}
