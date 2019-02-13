package com.hotcomm.community.common.bean.pa.process.event;


import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EventClosePA extends CommunityParams implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9176456158397777561L;
	
	//关闭事件的id
	private Integer eventId;

	//关闭原因id
	private Integer closeResultId;
	
	//关闭备注
	private String closedRemark;
	
	//关闭资源
	private String eventView;
	
	private Integer userid;
}
