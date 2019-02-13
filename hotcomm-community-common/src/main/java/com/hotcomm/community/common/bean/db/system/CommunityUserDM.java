package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityUserDM implements Serializable {

	private static final long serialVersionUID = -5812235504509849225L;

	private Integer communityId;

	private Integer userId;

	private Integer communityDbCode;

}
