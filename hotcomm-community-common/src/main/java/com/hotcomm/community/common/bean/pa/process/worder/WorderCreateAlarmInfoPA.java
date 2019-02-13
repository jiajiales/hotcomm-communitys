package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class WorderCreateAlarmInfoPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private String sourceTitle;
	private String sourceId;
	private String sourceInfo;
	private String sourceCreateTime;
	private String sourceStateName;
	
	private Integer devId;
	private Integer moduleId;
	
	private String lat;
	private String lng;
	
}
