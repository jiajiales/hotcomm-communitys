package com.hotcomm.framework.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityParams extends BaseParams {
	
	private Integer communityId;
}
