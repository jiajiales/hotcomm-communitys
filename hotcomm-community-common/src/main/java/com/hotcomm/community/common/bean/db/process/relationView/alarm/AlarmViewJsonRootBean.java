/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationView.alarm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmViewJsonRootBean {

	private AlarmPendingView pendingView;
	private AlarmProcessedView processedView;
	private AlarmClosedView closedView;
	private AlarmWorderFinishView finishView;

}