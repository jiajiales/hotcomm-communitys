package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderCreateDevInfoPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private String devType;
	private Integer devId;
	private String devNum;
	private Integer moduleId;
	private String address;
	private String alarmState;
	private String lng;
	private String lat;
	
}
