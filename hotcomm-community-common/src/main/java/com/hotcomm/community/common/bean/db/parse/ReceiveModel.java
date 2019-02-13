package com.hotcomm.community.common.bean.db.parse;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ReceiveModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7294150865990095535L;
	public String id;
	public String macAddr;
	public String data;
	public String recv;
	public extra extra;
	public String pub;
	public Map<String, Object> analyMsg;// battery电量，temp温度，alarmid报警类型id:0是心跳数据,-1是需要和预设值判断是否报警
}
