package com.hotcomm.community.common.bean.pa.process.event;

import java.io.Serializable;

import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EventDetailPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = -1854054332498116215L;
	private Integer eventId;
	private Integer state;
	private Integer type;
	
}
