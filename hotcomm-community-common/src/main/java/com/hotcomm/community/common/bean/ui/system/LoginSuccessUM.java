package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LoginSuccessUM implements Serializable {

	private static final long serialVersionUID = -6261291026555778575L;

	private String token;

	private Integer userId;

	private String realName;
	
	private String userName;

	private List<ResourceGradingUM> resourceGrading;

	private List<CommunityDetailListUM> communityDetailList;

	public LoginSuccessUM(String token, Integer userId, String realName, List<ResourceGradingUM> resourceGrading, List<CommunityDetailListUM> communityDetailList) {
		this.token = token;
		this.userId = userId;
		this.realName = realName;
		this.resourceGrading = resourceGrading;
		this.communityDetailList = communityDetailList;
	}

	public LoginSuccessUM(String token, Integer userId, String realName, String userName, List<ResourceGradingUM> resourceGrading,
			List<CommunityDetailListUM> communityDetailList) {
		super();
		this.token = token;
		this.userId = userId;
		this.realName = realName;
		this.userName = userName;
		this.resourceGrading = resourceGrading;
		this.communityDetailList = communityDetailList;
	}

	
}
