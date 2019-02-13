package com.hotcomm.community.common.bean.pa.process.alarm;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorkPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private String queryType;
	
}
