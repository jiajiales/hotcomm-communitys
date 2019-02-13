package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LogDM implements Serializable {

	private static final long serialVersionUID = 7961970345741489773L;

	private Integer id;

	private String recordUser;

	private String recordEvent;

	private String recordCode;

	private Date recordTime;

	private String recordIp;

	private String recordParams;

	private String responseParams;

	private String recordAction;

	private Integer recordStatus;

	private String recordErrorMessage;

	public LogDM(String recordUser, String recordEvent, String recordCode, Date recordTime, String recordIp, String recordParams, String responseParams) {
		super();
		this.recordUser = recordUser;
		this.recordEvent = recordEvent;
		this.recordCode = recordCode;
		this.recordTime = recordTime;
		this.recordIp = recordIp;
		this.recordParams = recordParams;
		this.responseParams = responseParams;
	}

}
