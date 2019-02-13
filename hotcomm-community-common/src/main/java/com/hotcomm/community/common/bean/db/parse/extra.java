package com.hotcomm.community.common.bean.db.parse;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class extra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783079974700081378L;

	public String commsysType;

	public double rssi;

	public double snr;

	public double frameCnt;

	public String gwid;

	public String gwip;

	public String channel;

	public Integer sf;
}
