package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityListAllUM implements Serializable {

	private static final long serialVersionUID = -4654104454456597073L;

	private Integer cid;

	private String cname;
	
	private Integer doorduDepId;

}
