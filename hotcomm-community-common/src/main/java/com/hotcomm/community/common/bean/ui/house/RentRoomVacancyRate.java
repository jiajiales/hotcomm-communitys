package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RentRoomVacancyRate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6907672565396037670L;
	
	private String rentRoom;
	private int roomNum;
	private Double ratio;              //占比比率
	
	public RentRoomVacancyRate(String rentRoom, int roomNum, Double ratio) {
		super();
		this.rentRoom = rentRoom;
		this.roomNum = roomNum;
		this.ratio = ratio;
	}
}
