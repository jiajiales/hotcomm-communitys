package com.hotcomm.community.common.bean.pa.process;

import java.io.Serializable;

import com.hotcomm.framework.comm.PageParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevAlarmLogPA extends PageParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private Integer devId;
	private Integer moduleId;
	
}
