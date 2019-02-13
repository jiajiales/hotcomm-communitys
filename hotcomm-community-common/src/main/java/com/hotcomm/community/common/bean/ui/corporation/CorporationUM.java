package com.hotcomm.community.common.bean.ui.corporation;

import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CorporationUM implements Serializable {
	
	private static final long serialVersionUID = 1694965452811430006L;

	private Integer id;
	private String createTime;
	private String corName;
	private String address;
	private Integer labelId;
	private String labelName;
	private Integer devCount;
	private Integer corTypeId;
	private String corType;
	private String scope;
	private  double longitude;
	private  double latitude;
	private String busLicense;
	private Integer carCount;
	private Integer personCount;
	private Integer building;
	private Integer unit;
	private Integer layer;
	private Integer roomNum;
}
