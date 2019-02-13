package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityDetailListUM implements Serializable {

	private static final long serialVersionUID = -7124368512314697110L;

	private Integer cid;

	private String cname;

	private Integer isDefault;

	private String proviceName;

	private String cityName;

	private String regionName;

	private String streetName;

	private String villageName;

	private String caddress;

	private String lon;

	private String lat;

	private String pictureUrl;

	private String outlineCoords;

}
