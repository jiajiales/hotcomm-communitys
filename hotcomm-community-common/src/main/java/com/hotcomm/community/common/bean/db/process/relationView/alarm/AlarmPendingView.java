/**
  * Copyright 2018 bejson.com 
  */
package com.hotcomm.community.common.bean.db.process.relationView.alarm;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AlarmPendingView {

	private List<AlarmImg> img;
	private List<AlarmVideo> video;
	private List<AlarmMp3> mp3;

}