package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommunityPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = 1188125953066489050L;

	private String cname;

	private Integer startIndex;

	private Integer endIndex;

}
