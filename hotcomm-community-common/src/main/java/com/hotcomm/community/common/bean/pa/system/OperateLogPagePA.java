package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class OperateLogPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -3572420295095052978L;

	private String startTime;

	private String endTime;

	private String recordUser;

	private Integer cid;

	private Integer startIndex;

	private Integer endIndex;

}
