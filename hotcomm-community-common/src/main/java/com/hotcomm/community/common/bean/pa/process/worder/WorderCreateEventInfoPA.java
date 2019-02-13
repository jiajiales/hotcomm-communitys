package com.hotcomm.community.common.bean.pa.process.worder;

import java.io.Serializable;
import com.hotcomm.framework.comm.CommunityParams;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor

public class WorderCreateEventInfoPA extends CommunityParams implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer sourceId;
    private String sourceTitle;
    private String address;
    private String sourceCreateTime;
    private String eventCreateUser;
    private String sourceStateName;
    private Integer handleUserId;
    private String eventDesc;
    private String lng;
    private String lat;
}