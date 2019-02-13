package com.hotcomm.community.common.bean.db.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class OperateLogDM implements Serializable {

	private static final long serialVersionUID = 8982506277583387502L;

	private Integer id;

	private String recordUser;

	private String recordEvent;

	private String recordCode;

	private Date recordTime;

	private String recordIp;

	private Integer communityId;

	public OperateLogDM(String recordUser, String recordEvent, String recordCode, Date recordTime, String recordIp, Integer communityId) {
		super();
		this.recordUser = recordUser;
		this.recordEvent = recordEvent;
		this.recordCode = recordCode;
		this.recordTime = recordTime;
		this.recordIp = recordIp;
		this.communityId = communityId;
	}

}
