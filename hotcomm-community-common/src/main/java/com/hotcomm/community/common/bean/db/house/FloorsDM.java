package com.hotcomm.community.common.bean.db.house;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FloorsDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6194676638315182751L;
	
	private Integer id;
	private Integer buildingId;
	private String floorName;
	private Integer floorNum;
	private Double constructArea;
	private Double useArea;
	private Integer attribute;
	private String layoutPath;
	private String createUser;
	private Date createTime;
	private Date updateTime;
	private String remark;
}
