package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = 384235128596084750L;

	private Integer cid;

	private String userName;

	private Integer status;

	private Integer startIndex;

	private Integer endIndex;

}
