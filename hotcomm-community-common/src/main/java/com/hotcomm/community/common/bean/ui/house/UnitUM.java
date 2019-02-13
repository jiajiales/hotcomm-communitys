package com.hotcomm.community.common.bean.ui.house;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UnitUM {
	
	private Integer id;
	private Integer buildingId;
	private String buildingName;
	private String unitNo;
	private String unitName;
	private String createTime;
	private String createUser;

}
