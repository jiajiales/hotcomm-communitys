package com.hotcomm.community.common.bean.db.house;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UnitDM {
	
	 private Integer id;
	 private Integer doorduUnitId;
	 private Integer buildingId;
	 private String unitName;
	 private Date createTime;
     private String createUser;
     private Boolean isdelete;
}
