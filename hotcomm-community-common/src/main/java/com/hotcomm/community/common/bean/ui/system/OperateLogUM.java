package com.hotcomm.community.common.bean.ui.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class OperateLogUM implements Serializable {

	private static final long serialVersionUID = -6808361959406689740L;

	private Integer id;

	private String recordUser;

	private String recordEvent;

	private String recordCode;

	private Date recordTime;

	private String recordIp;

	private String communityName;

}
