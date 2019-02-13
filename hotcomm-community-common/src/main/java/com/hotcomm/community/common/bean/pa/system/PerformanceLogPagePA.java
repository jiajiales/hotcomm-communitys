package com.hotcomm.community.common.bean.pa.system;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PerformanceLogPagePA extends PageParams implements Serializable {

	private static final long serialVersionUID = -6219799962859254547L;

	private String startTime;

	private String endTime;

	private String requestUrl;

	private Integer responseStatus;

	private Integer startIndex;

	private Integer endIndex;

}
