package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PerformanceLogUM implements Serializable {

	private static final long serialVersionUID = -8801514371468823690L;

	private Integer id;

	private String requestIp;

	private String requestParams;

	private String requestUrl;

	private Long executeTimes;

	private String responseObj;

	private Integer responseStatus;

	private Date recordTime;

}
