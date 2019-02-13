package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserCommunityPA implements Serializable {

	private static final long serialVersionUID = -7639391508079525965L;

	private Integer userId;

	private String cids;

	private Integer defaultCid;

}
