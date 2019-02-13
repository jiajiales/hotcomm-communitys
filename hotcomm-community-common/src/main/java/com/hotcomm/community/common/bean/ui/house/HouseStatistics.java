package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class HouseStatistics implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5696001002613239612L;

	private Integer buildingNum;
	private Double buildingRate;
	private Double totalConstructArea;
	private Double totalAreaRate;
	private Integer roomNum;
	private Double roomNumRate;
	private Integer rentRoomNum;
	private Double renRoomNumRate;
	private Integer dangerPlace;
	private Integer servicePlace;

}
