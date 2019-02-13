package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityListUM implements Serializable {

	private static final long serialVersionUID = 69485397426041785L;

	private Integer cid;

	private String cname;

	private Integer isDefault;

}
