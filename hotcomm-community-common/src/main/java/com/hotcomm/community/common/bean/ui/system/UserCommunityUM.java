package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserCommunityUM implements Serializable {

	private static final long serialVersionUID = 2290805227770051772L;

	private Integer userId;

	private String communityIds;

}
