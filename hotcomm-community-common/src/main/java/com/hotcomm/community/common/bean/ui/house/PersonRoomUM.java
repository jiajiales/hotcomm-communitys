package com.hotcomm.community.common.bean.ui.house;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PersonRoomUM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7746392841803301802L;

	private Integer id;
	private String headImg;
	private String pNo;
	private String name;
	private String reason;
	private Integer reasonId;
	private String remark;
	private String roomName;
	private String floorName;
	private String buildingName;
	private Integer pId;
	private Integer roomId;
	private Integer unitId;
	private String unitName;
}
