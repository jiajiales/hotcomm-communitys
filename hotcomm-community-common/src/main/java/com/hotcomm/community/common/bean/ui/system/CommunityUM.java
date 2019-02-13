package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityUM implements Serializable {

	private static final long serialVersionUID = 959436468108621286L;

	private Integer cid;

	private Integer doorduDepId; // 多度平台的小区ID

	private String code;

	private String provinceNo;

	private String proviceName;

	private String cityNo;

	private String cityName;

	private String regionNo;

	private String regionName;

	private String streetNo;

	private String streetName;

	private String villageNo;

	private String villageName;

	private String cname;

	private String caddress;

	private String lon;

	private String lat;

	private String linkUser;

	private String linkPhone;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private String databaseNo;

	private String pictureUrl;

	private String outlineCoords;

}
