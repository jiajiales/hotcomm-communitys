package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PerformanceDM implements Serializable {

	private static final long serialVersionUID = 1320847980241808509L;

	private String requestIp;

	private String requestParams;

	private String requestUrl;

	private Long executeTimes;

	private String responseObj;

	private Integer responseStatus;

	private Date recordTime;

	public PerformanceDM(String requestIp, String requestParams, String requestUrl, Long executeTimes) {
		this.requestIp = requestIp;
		this.requestParams = requestParams;
		this.requestUrl = requestUrl;
		this.executeTimes = executeTimes;
		this.recordTime = new Date();
	}

	public PerformanceDM(String requestIp, String requestParams, String requestUrl, Long executeTimes, String responseObj, Integer responseStatus, Date recordTime) {
		super();
		this.requestIp = requestIp;
		this.requestParams = requestParams;
		this.requestUrl = requestUrl;
		this.executeTimes = executeTimes;
		this.responseObj = responseObj;
		this.responseStatus = responseStatus;
		this.recordTime = recordTime;
	}

}
