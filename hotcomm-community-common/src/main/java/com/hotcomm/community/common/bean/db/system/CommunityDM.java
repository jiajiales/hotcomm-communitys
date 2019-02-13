package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityDM implements Serializable {

	private static final long serialVersionUID = 3430624587784387165L;

	private Integer cid;

	private Integer doorduDepId;

	private String code;

	private String provinceNo;

	private String cityNo;

	private String regionNo;

	private String streetNo;

	private String villageNo;

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

	private Integer isDelete;

}
