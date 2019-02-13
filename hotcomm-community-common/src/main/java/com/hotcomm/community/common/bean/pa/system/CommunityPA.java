package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityPA implements Serializable {

	private static final long serialVersionUID = 8407059291005544792L;

	private Integer cid;

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

	private String updateUser;

	private String pictureUrl;

	private String outlineCoords;

}
